package edu.project4;

import edu.project4.Etinties.Function;
import edu.project4.Etinties.Point;
import edu.project4.Etinties.Variant;
import edu.project4.FactorialAssets.Factorial;
import edu.project4.ReadyFunctions.Functions;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Flame {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static double XMIN = -1.777;
    private final static double XMAX = 1.777;
    private final static double YMIN = -1;
    private final static double YMAX = 1;
    private final static int FULL_HD_RES_X = 1920;
    private final static int FULL_HD_RES_Y = 1080;
    private final static int CHANNELS = 4;
    private final static int CHANNEL_RED = 0;
    private final static int CHANNEL_GREEN = 1;
    private final static int CHANNEL_BLUE = 2;
    private final static int CHANNEL_HITCOUNTER = 3;
    private final static int MINIMAL_ITERATION = 20;
    private final ExecutorService executor;
    private final int iterations;
    private final int samples;
    private final int threads;
    private final double xmax;
    private final double xmin;
    private final double ymax;
    private final double ymin;
    private final Functions functions;
    private final int resolutionX;
    private final int resolutionY;
    private final short[][][] matrixDisplay;
    private final ReentrantLock lock;
    private final int superPxSize;
    private final double gammaCorrection;
    private final boolean mirroring;
    private int maxFrequency;

    public Flame(int threads, int samples, int iterations, double gamma, boolean mirroring, int superPxSize) {
        this.threads = threads;
        this.executor = Executors.newFixedThreadPool(threads);
        this.samples = samples;
        this.iterations = iterations;
        this.functions = new Functions();
        this.gammaCorrection = gamma;
        this.mirroring = mirroring;
        this.superPxSize = superPxSize;

        this.xmin = XMIN;
        this.xmax = XMAX;
        this.ymin = YMIN;
        this.ymax = YMAX;

        this.resolutionX = FULL_HD_RES_X;
        this.resolutionY = FULL_HD_RES_Y;

        this.matrixDisplay =
            new short[this.resolutionY * this.superPxSize][this.resolutionX * this.superPxSize][CHANNELS];

        this.lock = new ReentrantLock();
//      todo  this.randomSeed = randomSeed;
    }

    public void render() {
        var tasks = Stream.generate(() -> CompletableFuture.runAsync(
                    this::renderThread,
                    executor
                )
            )
            .limit(this.threads)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
    }

    private void renderThread() {
        LOGGER.info("Connect to task");
        // TODO set seed into random ?
        int samplesPerThread = samples / threads;
        for (int i = 0; i < samplesPerThread; i++) {
            var currentX = getRandomDoubleFromDiapason(this.xmin, this.xmax);
            var currentY = getRandomDoubleFromDiapason(this.ymin, this.ymax);

            for (int j = 0; j < iterations; j++) {
                Function function = this.functions.getRandomFunction();

                try {
                    Point point = iterate(currentX, currentY, function, j);

                    if (j <= MINIMAL_ITERATION) {
                        continue;
                    }

                    if (!containsInRange(point)) {
                        continue;
                    }

                    var newPoint = getCorrectedPoint(point);
                    if (!containsInCanvas(newPoint)) {
                        continue;
                    }

                    this.lock.lock();
                    try {
                        double[] colors = function.color().getColorValues();

                        this.matrixDisplay[(int) newPoint.y()][(int) newPoint.x()][CHANNEL_RED] =
                            (short) ((colors[CHANNEL_RED]
                                + this.matrixDisplay[(int) newPoint.y()][(int) newPoint.x()][CHANNEL_RED])
                                / 2.0);

                        this.matrixDisplay[(int) newPoint.y()][(int) newPoint.x()][CHANNEL_GREEN] =
                            (short) ((colors[CHANNEL_GREEN]
                                + this.matrixDisplay[(int) newPoint.y()][(int) newPoint.x()][CHANNEL_GREEN])
                                / 2.0);

                        this.matrixDisplay[(int) newPoint.y()][(int) newPoint.x()][CHANNEL_BLUE] =
                            (short) ((colors[CHANNEL_BLUE]
                                + this.matrixDisplay[(int) newPoint.y()][(int) newPoint.x()][CHANNEL_BLUE])
                                / 2.0);

                        this.matrixDisplay[(int) newPoint.y()][(int) newPoint.x()][CHANNEL_HITCOUNTER] +=
                            1; // Hitcounter

                    } finally {
                        this.lock.unlock();
                    }

                } catch (Exception e) {
                    LOGGER.warn("Stopped sample "
                        + i + " in step "
                        + iterations + " because - "
                        + e.getMessage());
                    break;
                }
            }

            LOGGER.info(i + "/" + samplesPerThread);
        }
    }

    public short[][][] postRendering() {
        this.maxFrequency = 1;
        short[][][] pixels = new short[this.resolutionY][this.resolutionX][CHANNELS];
        short[][][] image = new short[this.resolutionY][this.resolutionX][CHANNELS - 1];

        var tasks = IntStream.range(0, this.threads)
            .mapToObj(numberOfThread -> CompletableFuture.runAsync(() -> {
                List<Integer> localData;

                localData = pixelCompression(pixels, numberOfThread);

                this.lock.lock();
                this.maxFrequency = Math.max(localData.getFirst(), this.maxFrequency);
                this.lock.unlock();

                gammaCorrection(pixels, numberOfThread, image);

            }, executor))
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();

        return image;
    }

    private List<Integer> pixelCompression(short[][][] pixels, int start) {
        var localMax = 0;
        var localAvg = 0;

        for (int x = start; x < this.resolutionX; x += this.threads) {
            LOGGER.info("Get row for compression - " + x);
            for (int y = 0; y < this.resolutionY; y++) {
                short[] col = new short[] {0, 0, 0, 0};

                superPixelResize(col, y, x);
                pixels[y][x] = divisionByElements(col);
                localMax = Math.max(localMax, col[CHANNEL_HITCOUNTER]);
                localAvg += col[CHANNEL_HITCOUNTER];
            }
        }

        return List.of(
            localMax,
            localAvg / this.resolutionY
        );
    }

    private void superPixelResize(short[] col, int y, int x) {
        for (int i = 0; i < this.superPxSize; i++) {
            for (int j = 0; j < this.superPxSize; j++) {
                col[CHANNEL_RED] +=
                    this.matrixDisplay[y * this.superPxSize + i][x * this.superPxSize + j][CHANNEL_RED];
                col[CHANNEL_GREEN] +=
                    this.matrixDisplay[y * this.superPxSize + i][x * this.superPxSize + j][CHANNEL_GREEN];
                col[CHANNEL_BLUE] +=
                    this.matrixDisplay[y * this.superPxSize + i][x * this.superPxSize + j][CHANNEL_BLUE];
                col[CHANNEL_HITCOUNTER] +=
                    this.matrixDisplay[y * this.superPxSize + i][x * this.superPxSize + j][CHANNEL_HITCOUNTER];
            }
        }
    }

    private void gammaCorrection(short[][][] pixels, int start, short[][][] image) {
        for (int x = start; x < this.resolutionX; x += this.threads) {
            LOGGER.info("Get row for gamma correction - " + x);
            for (int y = 0; y < this.resolutionY; y++) {
                if (pixels[y][x][CHANNEL_HITCOUNTER] > 0) {
                    image[y][x] = multiplicationByElements(pixels[y][x]);
                }
            }
        }
    }

    private short[] divisionByElements(short[] col) {
        col[CHANNEL_RED] = (short) (col[CHANNEL_RED] / (this.superPxSize * this.superPxSize));
        col[CHANNEL_GREEN] = (short) (col[CHANNEL_GREEN] / (this.superPxSize * this.superPxSize));
        col[CHANNEL_BLUE] = (short) (col[CHANNEL_BLUE] / (this.superPxSize * this.superPxSize));
        col[CHANNEL_HITCOUNTER] = (short) (col[CHANNEL_HITCOUNTER] / (this.superPxSize * this.superPxSize));

        return col;
    }

    private short[] multiplicationByElements(short[] col) {
        @SuppressWarnings("MagicNumber")
        short[] x = Arrays.copyOf(col, 3);

        if (col[CHANNEL_HITCOUNTER] > 0) {
            double y =
                Math.pow((Math.log(col[CHANNEL_HITCOUNTER]) / Math.log(this.maxFrequency)), 1.0 / this.gammaCorrection);

            x[0] = (short) (x[0] * y);
            x[1] = (short) (x[1] * y);
            x[2] = (short) (x[2] * y);
        }

        return x;
    }

    public short[][][] getMatrixDisplay() {
        short[][][] original = this.matrixDisplay;
        short[][][] copy = new short[original.length][][];

        for (int i = 0; i < original.length; i++) {
            copy[i] = new short[original[i].length][];
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = original[i][j].clone();
            }
        }

        return copy;
    }

    private boolean containsInCanvas(Point point) {
        return 0 < point.x() && point.x() < this.resolutionX * this.superPxSize
            && 0 < point.y() && point.y() < this.resolutionY * this.superPxSize;
    }

    private Point getCorrectedPoint(Point point) {
        int x = (int) ((point.x() - this.xmin) / (this.xmax - this.xmin) * this.resolutionX * this.superPxSize);
        int y = (int) ((point.y() - this.ymin) / (this.ymax - this.ymin) * this.resolutionY * this.superPxSize);

        return new Point(
            x, y
        );
    }

    private boolean containsInRange(Point point) {
        return (this.xmin < point.x() && point.x() < this.xmax)
            && (this.ymin < point.y() && point.y() < this.ymax);
    }

    @SuppressWarnings("MagicNumber")
    private Point iterate(double dataX, double dataY, Function function, int iterCount) {
        var newX = dataX;
        var newY = dataY;

        List<Variant> variants = function.variants();
        for (Variant currentVar : variants) {
            Factorial type = currentVar.type();
            var weight = currentVar.weight();
            var coefficients = function.coefficients();
            var parameters = currentVar.parameters();

            var x = coefficients.a() * newX + coefficients.b() * newY + coefficients.c();
            var y = coefficients.d() * newX + coefficients.e() * newY + coefficients.f();

            Point point = type.apply(
                new Point(x, y),
                coefficients,
                parameters
            );

            newX = weight * point.x();
            newY = weight * point.y();
        }

        // PT
        var postTransformation = function.postTransformation();
        newX = postTransformation.a() * newX + postTransformation.b() * newY + postTransformation.c();
        newY = postTransformation.d() * newX + postTransformation.e() * newY + postTransformation.f();

        // Mirroring
        if (this.mirroring) {
            if (iterCount % 4 == 0) {
                newX *= -1;
            } else if (iterCount % 3 == 0) {
                newX *= -1;
                newY *= -1;
            } else if (iterCount % 2 == 0) {
                newY *= -1;
            }
        }

        return new Point(newX, newY);
    }

    private double getRandomDoubleFromDiapason(double min, double max) {
        return min + ((max - min) * ThreadLocalRandom.current().nextDouble());
    }
}

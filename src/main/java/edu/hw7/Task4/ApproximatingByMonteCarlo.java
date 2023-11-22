package edu.hw7.Task4;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ApproximatingByMonteCarlo {

    private final Random random = new SecureRandom();
    private final static double SIZE_SQUARE = 2.0;
    private final static double RADIUS_SQUARE = SIZE_SQUARE / 2.0;
    private final int countPoints;
    private int circleCount;

    public ApproximatingByMonteCarlo(int numberOfSamplePoints) {
        this.countPoints = numberOfSamplePoints;
        this.circleCount = 0;
    }

    public void iterate() {
        for (int i = 0; i < this.countPoints; i++) {
            if (isPointInCircle(generatePoint())) {
                this.circleCount += 1;
            }
        }
    }

    public void multithreadingIterate(int threads) {
        int iteratePerThread = this.countPoints / threads;
        this.circleCount = IntStream.range(0, threads).parallel().reduce(0, (accumulator, element) ->
            accumulator + multiThreadIterate(iteratePerThread)
        );
    }

    public Point generatePoint() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(-1.0, 1.0),
            ThreadLocalRandom.current().nextDouble(-1.0, 1.0)
        );
    }

    @SuppressWarnings("MagicNumber")
    public double getNearestPi() {
        return 4 * ((double) this.circleCount / this.countPoints);
    }

    private int multiThreadIterate(int iteratePerThread) {
        int curCount = 0;
        for (int i = 0; i < iteratePerThread; i++) {
            if (isPointInCircle(generatePoint())) {
                curCount += 1;
            }
        }
        return curCount;
    }

    private boolean isPointInCircle(Point point) {
        return (point.x() * point.x() + point.y() * point.y()) < (RADIUS_SQUARE * RADIUS_SQUARE);
    }
}

package edu.project4.FactorialAssets;

import edu.project4.Etinties.Coefficients;
import edu.project4.Etinties.Parameters;
import edu.project4.Etinties.Point;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.math3.util.FastMath;

@SuppressWarnings("MagicNumber")
public class FactorialFactory {

    private FactorialFactory() {
    }

    public final static Factorial LINEAR =
        (Point point, Coefficients coefficients, Parameters parameters) -> new Point(point.x(), point.y());
    public final static Factorial SINUSOIDAL =
        (Point point, Coefficients coefficients, Parameters parameters) -> new Point(
            FastMath.sin(point.x()),
            FastMath.sin(point.y())
        );
    public final static Factorial SPHERICAL = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var r = FastMath.sqrt(FastMath.pow(point.x(), 2) + FastMath.pow(point.y(), 2));
        return new Point(
            (point.x() / FastMath.pow(r, 2)),
            (point.y() / FastMath.pow(r, 2))
        );
    };
    public final static Factorial HEART = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var r = FastMath.sqrt(FastMath.pow(point.x(), 2) + FastMath.pow(point.y(), 2));
        var th = FastMath.atan2(point.y(), point.x());
        return new Point(
            r * FastMath.sin(th * r),
            r * -FastMath.cos(th * r)
        );
    };
    public final static Factorial DIAMOND = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var r = FastMath.sqrt(FastMath.pow(point.x(), 2) + FastMath.pow(point.y(), 2));
        var th = FastMath.atan2(point.y(), point.x());
        return new Point(
            FastMath.sin(th) * FastMath.cos(r),
            FastMath.cos(th) * FastMath.sin(r)
        );
    };
    public final static Factorial FISHEYE = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var re = 2 / (FastMath.sqrt(FastMath.pow(point.x(), 2) + FastMath.pow(point.y(), 2)) + 1);
        return new Point(
            re * point.y(),
            re * point.x()
        );
    };
    public final static Factorial EX = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var r = FastMath.hypot(point.x(), point.y());
        var theta = FastMath.atan2(point.y(), point.x());
        var i = FastMath.pow(FastMath.sin(theta + r), 3);
        var j = FastMath.pow(FastMath.cos(theta - r), 3);

        return new Point(
            r * (i + j),
            r * (i - j)
        );
    };
    public final static Factorial POPCORN = (Point point, Coefficients coefficients, Parameters parameters) -> {
        return new Point(
            point.x() + coefficients.c() * FastMath.sin(FastMath.tan(3.0 * point.y())),
            point.y() + coefficients.f() * FastMath.sin(FastMath.tan(3.0 * point.x()))
        );
    };
    public final static Factorial HANDKERCHIEF = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var r = FastMath.hypot(point.x(), point.y());
        var theta = FastMath.atan2(point.y(), point.x());

        return new Point(
            r * FastMath.sin(theta + r),
            r * FastMath.cos(theta - r)
        );
    };
    public final static Factorial BUBBLE = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var r = 4 + point.x() * point.x() + point.y() * point.y();

        return new Point(
            (4.0 * point.x()) / r,
            (4.0 * point.y()) / r
        );
    };
    public final static Factorial GAUSSIAN = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var t = ThreadLocalRandom.current().nextGaussian(parameters.param1(), parameters.param2());
        var r = 2 * FastMath.PI * ThreadLocalRandom.current().nextDouble();
        return new Point(
            t * FastMath.cos(r),
            t * FastMath.sin(r)
        );
    };
    public final static Factorial SWIRL = (Point point, Coefficients coefficients, Parameters parameters) -> {
        var r = (point.x() * point.x()) + (point.y() * point.y());
        return new Point(
            point.x() * FastMath.sin(r) - point.y() * FastMath.cos(r),
            point.x() * FastMath.cos(r) + point.y() * FastMath.sin(r)
        );
    };
}

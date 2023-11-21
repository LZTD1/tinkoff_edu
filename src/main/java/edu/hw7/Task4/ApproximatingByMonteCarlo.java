package edu.hw7.Task4;

import java.security.SecureRandom;
import java.util.Random;

public class ApproximatingByMonteCarlo {

    private final Random random = new SecureRandom();
    private final int SIZE_SQUARE = 2;
    private final int RADIUS_SQUARE = SIZE_SQUARE / 2;
    private final int countPoints;
    private final int totalCount;
    private final int circleCount;

    public ApproximatingByMonteCarlo(int numberOfSamplePoints) {
        this.countPoints = numberOfSamplePoints;
        this.totalCount = 0;
        this.circleCount = 0;
    }

    public Point generatePoint() {
        return new Point(
            random.nextDouble(0, SIZE_SQUARE),
            random.nextDouble(0, SIZE_SQUARE)
        );
    }
    public double getNearestPi(){
        return 4 * ((double) circleCount / totalCount);
    }

    private boolean isPointInCircle(Point point) {
        return point.x() * point.x() + point.y() * point.y() < RADIUS_SQUARE;
    }

}

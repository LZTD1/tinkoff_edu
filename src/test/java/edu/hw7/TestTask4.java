package edu.hw7;

import edu.hw7.Task4.ApproximatingByMonteCarlo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask4 {

    private final ApproximatingByMonteCarlo monteCarlo = new ApproximatingByMonteCarlo(1_000);

    @Test
    void testGeneratePoint() {
        var point = monteCarlo.generatePoint();
        assertTrue(point.x() <= 1 && point.x() >= -1);
        assertTrue(point.y() <= 1 && point.y() >= -1);
    }

    @Test
    void iterateBySingleThread() {
        monteCarlo.iterate();
    }

    @Test
    void iterateByMultiThread() {
        monteCarlo.multithreadingIterate(4);
    }
}

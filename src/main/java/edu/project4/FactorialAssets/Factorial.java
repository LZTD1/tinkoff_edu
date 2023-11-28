package edu.project4.FactorialAssets;

import edu.project4.Etinties.Coefficients;
import edu.project4.Etinties.Parameters;
import edu.project4.Etinties.Point;

@FunctionalInterface
public interface Factorial {
    Point apply(Point point, Coefficients coefficients, Parameters parameters);
}

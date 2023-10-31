package edu.project2.Exceptions;

public class RouteCalculationError extends RuntimeException {
    public RouteCalculationError(String s) {
        super(s);
    }
}

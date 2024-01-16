package edu.hw10.Task1.Exceptions;

public class NoFoundValueError extends RuntimeException {
    public NoFoundValueError(String m) {
        super("Unknown value: " + m);
    }
}

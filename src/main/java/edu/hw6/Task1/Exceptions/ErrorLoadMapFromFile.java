package edu.hw6.Task1.Exceptions;

public class ErrorLoadMapFromFile extends RuntimeException {
    public ErrorLoadMapFromFile(String s) {
        super(s);
    }
}

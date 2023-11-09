package edu.hw6.Task1.Exceptions;

public class ErrorCreatingFile extends RuntimeException {
    public ErrorCreatingFile(String s) {
        super(s);
    }
}

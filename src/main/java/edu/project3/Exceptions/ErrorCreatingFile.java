package edu.project3.Exceptions;

public class ErrorCreatingFile extends RuntimeException {
    public ErrorCreatingFile(String s) {
        super(s);
    }
}

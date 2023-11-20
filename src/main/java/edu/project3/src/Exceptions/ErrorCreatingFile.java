package edu.project3.src.Exceptions;

public class ErrorCreatingFile extends RuntimeException {
    public ErrorCreatingFile(String s) {
        super(s);
    }
}

package edu.project3.Exceptions;

public class BadLogLineException extends RuntimeException {
    public BadLogLineException(String s) {
        super(s);
    }
}

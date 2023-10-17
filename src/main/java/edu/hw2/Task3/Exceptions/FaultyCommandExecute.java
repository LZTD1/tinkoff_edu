package edu.hw2.Task3.Exceptions;

public class FaultyCommandExecute extends RuntimeException {
    public FaultyCommandExecute(String message) {
        super(message);
    }
}

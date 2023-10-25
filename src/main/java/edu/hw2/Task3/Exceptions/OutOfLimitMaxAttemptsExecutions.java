package edu.hw2.Task3.Exceptions;

public class OutOfLimitMaxAttemptsExecutions extends RuntimeException {
    public OutOfLimitMaxAttemptsExecutions(String message, Throwable cause) {
        super(message, cause);
    }
}

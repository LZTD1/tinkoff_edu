package edu.hw2.Task3.Exceptions;

public class ConnectionExistsErrorException extends RuntimeException {
    public ConnectionExistsErrorException(String message) {
        super(message);
    }
}

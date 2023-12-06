package edu.hw10.Task1.Exceptions;

public class MethodIsNotFindedError extends RuntimeException {
    public MethodIsNotFindedError() {
        super("This method is not finded!");
    }
}

package edu.hw10.Task1.Exceptions;

public class MethodIsNofAFabricError extends RuntimeException {
    public MethodIsNofAFabricError() {
        super("Current method is not a fabric!");
    }
}

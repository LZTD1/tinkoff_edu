package edu.hw4.Validations;

public record WeightError(String errorMessage, int errorCode) implements ValidationError{
}

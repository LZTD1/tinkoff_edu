package edu.hw4.Validations;

public record AgeError(String errorMessage, int errorCode) implements ValidationError{
}

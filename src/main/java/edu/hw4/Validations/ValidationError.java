package edu.hw4.Validations;

sealed public interface ValidationError permits AgeError, WeightError {

    String errorMessage();

    int errorCode();
}

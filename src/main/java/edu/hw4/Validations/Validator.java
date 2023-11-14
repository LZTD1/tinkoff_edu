package edu.hw4.Validations;

import edu.hw4.Animal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    private final static int MAX_WEIGHT = 1000;
    private final static int MAX_AGE = 50;

    private final static int ERROR_CODE_MAX_HEIGHT = 100;
    private final static int ERROR_CODE_MAX_AGE = 101;

    private final static String ERROR_MESSAGE_MAX_WEIGHT = "Too much weight!";
    private final static String ERROR_MESSAGE_MAX_AGE = "Too much age!";

    private Validator() {
    }

    public static Set<ValidationError> getValidate(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.weight() > MAX_WEIGHT) {
            errors.add(new WeightError(ERROR_MESSAGE_MAX_WEIGHT, ERROR_CODE_MAX_HEIGHT));
        }
        if (animal.age() > MAX_AGE) {
            errors.add(new AgeError(ERROR_MESSAGE_MAX_AGE, ERROR_CODE_MAX_AGE));
        }

        return errors;
    }

    public static String getValidateReadable(Animal animal) {
        List<String> errors = new ArrayList<>();
        if (animal.weight() > MAX_WEIGHT) {
            errors.add(new WeightError(ERROR_MESSAGE_MAX_WEIGHT, ERROR_CODE_MAX_HEIGHT).errorMessage());
        }
        if (animal.age() > MAX_AGE) {
            errors.add(new AgeError(ERROR_MESSAGE_MAX_AGE, ERROR_CODE_MAX_AGE).errorMessage());
        }

        return String.join(" ", errors);
    }
}

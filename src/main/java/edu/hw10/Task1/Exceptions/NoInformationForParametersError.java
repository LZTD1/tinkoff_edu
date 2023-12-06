package edu.hw10.Task1.Exceptions;

public class NoInformationForParametersError extends RuntimeException {
    public NoInformationForParametersError(String m) {
        super("Can`t generate param for " + m);
    }
}

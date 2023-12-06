package edu.hw10.Task1.Classes;

public class SimpleClassWithParams {

    private final int number;
    private final String simpleField;

    public SimpleClassWithParams(int number) {
        this.simpleField = "created!";
        this.number = number;
    }

    @Override public String toString() {
        return "SimpleClassWithParams{" +
            "number=" + number +
            ", simpleField='" + simpleField + '\'' +
            '}';
    }
}

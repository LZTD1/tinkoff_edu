package edu.hw10.Task1.Classes;

public class SimpleClass {

    private final String simpleField;

    public SimpleClass() {
        this.simpleField = "created!";
    }

    @Override public String toString() {
        return "SimpleClass{" +
            "simpleField='" + simpleField + '\'' +
            '}';
    }
}

package edu.hw10.Task1.Classes;

public class SimpleClassWithFabricAndParams {

    private final int digit;

    private SimpleClassWithFabricAndParams(int digit) {
        this.digit = digit;
    }

    public static SimpleClassWithFabricAndParams fabricMethod(int digit) {
        return new SimpleClassWithFabricAndParams(digit);
    }

}

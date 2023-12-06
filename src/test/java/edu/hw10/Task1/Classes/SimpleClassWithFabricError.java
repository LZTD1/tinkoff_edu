package edu.hw10.Task1.Classes;

import java.util.List;

public class SimpleClassWithFabricError {

    private SimpleClassWithFabricError() {
    }

    public static SimpleClassWithFabricError fabricMethod(List<Integer> list) {
        return new SimpleClassWithFabricError();
    }

    @Override public String toString() {
        return "SimpleClassWithFabricError{}";
    }
}

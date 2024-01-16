package edu.hw10.Task1.Classes;

import java.util.concurrent.ThreadLocalRandom;

public class SimpleClassWithFabric {

    private SimpleClassWithFabric() {
    }

    public static SimpleClassWithFabric fabricMethod() {
        return new SimpleClassWithFabric();
    }

    public static int getRandValues() {
        return ThreadLocalRandom.current().nextInt();
    }
}

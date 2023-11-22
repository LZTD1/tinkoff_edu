package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private final AtomicInteger counter;

    public AtomicCounter(int initialValue) {
        this.counter = new AtomicInteger(initialValue);
    }

    public int getValue() {
        return this.counter.get();
    }

    public int increment() {
        return this.counter.incrementAndGet();
    }
}

package edu.hw7.Task1;

public class NotAtomicCounter {

    private int counter;

    public NotAtomicCounter(int initialValue) {
        this.counter = initialValue;
    }
    public int getValue() {
        return this.counter;
    }
    public int increment() {
        this.counter += 1;
        return this.counter;
    }
}

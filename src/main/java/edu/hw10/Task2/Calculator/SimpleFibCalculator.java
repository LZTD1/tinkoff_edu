package edu.hw10.Task2.Calculator;

public class SimpleFibCalculator implements FibCalculator {

    public SimpleFibCalculator() {
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public long fib(int number) {
        if (number < 3) {
            return 1;
        } else {
            return fib(number - 2) + fib(number - 1);
        }
    }
}

package edu.hw7.Task2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FactorialCalculate {

    private final int factorialOf;

    public FactorialCalculate(int factorialOf) {
        this.factorialOf = factorialOf;
    }

    public int calculate() {
        int[] array = IntStream.range(1, factorialOf + 1).toArray();

        int result = Arrays.stream(array)
            .parallel()
            .reduce(1, (accumulator, element) -> accumulator * element);

        return result;
    }
}

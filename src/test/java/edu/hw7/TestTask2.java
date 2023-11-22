package edu.hw7;

import edu.hw7.Task2.FactorialCalculate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {

    public static Stream<Arguments> factorialData() {
        return Stream.of(
            Arguments.of(5, 120),
            Arguments.of(6, 720),
            Arguments.of(7, 5040),
            Arguments.of(8, 40320),
            Arguments.of(9, 362880)
        );
    }

    @ParameterizedTest
    @MethodSource("factorialData")
    void getFactorial(int factorial, int expected) {
        var fact = new FactorialCalculate(factorial);
        var result = fact.calculate();

        assertEquals(expected, result);
    }
}

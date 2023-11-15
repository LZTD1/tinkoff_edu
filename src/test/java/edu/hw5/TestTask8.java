package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw5.Task8.task8Zadanie1;
import static edu.hw5.Task8.task8Zadanie2;
import static edu.hw5.Task8.task8Zadanie3;
import static edu.hw5.Task8.task8Zadanie4;
import static edu.hw5.Task8.task8Zadanie5;
import static edu.hw5.Task8.task8Zadanie6;
import static edu.hw5.Task8.task8Zadanie7;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask8 {
    @ParameterizedTest
    @ValueSource(strings = {"1010", "101011", "601"})
    void testTask1False(String binary) {
        assertFalse(task8Zadanie1(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "0"})
    void testTask1True(String binary) {
        assertTrue(task8Zadanie1(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"01010", "0", "1010"})
    void testTask2True(String binary) {
        assertTrue(task8Zadanie2(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "0101", "10101", "101017"})
    void testTask2False(String binary) {
        assertFalse(task8Zadanie2(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"000", "000000", "101010"})
    void testTask3True(String binary) {
        assertTrue(task8Zadanie3(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "0000", "1010101010", "00000044"})
    void testTask3False(String binary) {
        assertFalse(task8Zadanie3(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"110", "1110", "1111"})
    void testTask4True(String binary) {
        assertTrue(task8Zadanie4(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111", "55"})
    void testTask4False(String binary) {
        assertFalse(task8Zadanie4(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1010101", "111111"})
    void testTask5True(String binary) {
        assertTrue(task8Zadanie5(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"01010101", "000000", "161616"})
    void testTask5False(String binary) {
        assertFalse(task8Zadanie5(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"010000", "00000001", "100000000"})
    void testTask6True(String binary) {
        assertTrue(task8Zadanie6(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101", "00000001000001", "01", "10", "1000055"})
    void testTask6False(String binary) {
        assertFalse(task8Zadanie6(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0101010101", "1010101010", "00000100001"})
    void testTask7True(String binary) {
        assertTrue(task8Zadanie7(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"110011000", "01010101100", "1100100101010", "011111", "01661"})
    void testTask7False(String binary) {
        assertFalse(task8Zadanie7(binary));
    }

}

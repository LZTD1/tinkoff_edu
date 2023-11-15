package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw5.Task7.contains3charactersAndThirdSymbolIsZero;
import static edu.hw5.Task7.sameSymbolsInStartAndEnd;
import static edu.hw5.Task7.sizeBetween1and3;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask7 {
    @ParameterizedTest
    @ValueSource(strings = {"110", "100", "1000000000", "000", "010", "0000"})
    void testContains3charactersAndThirdSymbolIsZeroTrue(String binary) {
        assertTrue(contains3charactersAndThirdSymbolIsZero(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "001", "00", "011", "10", "990"})
    void testContains3charactersAndThirdSymbolIsZeroFalse(String binary) {
        assertFalse(contains3charactersAndThirdSymbolIsZero(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1001", "0000", "101"})
    void testSameSymbolsInStartAndEndTrue(String binary) {
        assertTrue(sameSymbolsInStartAndEnd(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "0001", "1000", "1010", "01", "687656"})
    void testSameSymbolsInStartAndEndFalse(String binary) {
        assertFalse(sameSymbolsInStartAndEnd(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "01", "001", "0", "10", "11"})
    void testSizeBetween1and3True(String binary) {
        assertTrue(sizeBetween1and3(binary));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0000", "1010", "", "1111", "75"})
    void testSizeBetween1and3False(String binary) {
        assertFalse(sizeBetween1and3(binary));
    }
}

package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task7.validationOne;
import static edu.hw5.Task7.validationThree;
import static edu.hw5.Task7.validationTwo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask7 {
    @Test
    void testValidationOne() {
        assertTrue(validationOne("100"));
        assertTrue(validationOne("110"));
        assertFalse(validationOne("10"));
        assertTrue(validationOne("1000000000"));
        assertFalse(validationOne("101"));
        assertTrue(validationOne("000"));
        assertFalse(validationOne("001"));
        assertFalse(validationOne("00"));
        assertTrue(validationOne("010"));
        assertFalse(validationOne("011"));
        assertTrue(validationOne("0000"));
    }

    @Test
    void testValidationTwo() {
        assertTrue(validationTwo("1001"));
        assertFalse(validationTwo("1000"));
        assertFalse(validationTwo("10"));
        assertTrue(validationTwo("0000"));
        assertFalse(validationTwo("0001"));
        assertTrue(validationTwo("101"));
        assertFalse(validationTwo("1010"));
        assertFalse(validationTwo("01"));
    }

    @Test
    void testValidationThree() {
        assertTrue(validationThree("1"));
        assertTrue(validationThree("01"));
        assertTrue(validationThree("001"));
        assertFalse(validationThree(""));
        assertFalse(validationThree("0000"));
        assertTrue(validationThree("0"));
        assertTrue(validationThree("10"));
        assertFalse(validationThree("1010"));
        assertTrue(validationThree("11"));
        assertFalse(validationThree("1111"));
    }
}

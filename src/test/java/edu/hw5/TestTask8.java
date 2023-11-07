package edu.hw5;

import org.junit.jupiter.api.Test;
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
    @Test
    void testTask1() {
        assertTrue(task8Zadanie1("101"));
        assertFalse(task8Zadanie1("1010"));
        assertFalse(task8Zadanie1("101011"));
        assertTrue(task8Zadanie1("0"));
    }

    @Test
    void testTask2() {
        assertTrue(task8Zadanie2("01010"));
        assertTrue(task8Zadanie2("0"));
        assertFalse(task8Zadanie2("0101"));

        assertFalse(task8Zadanie2("1"));
        assertTrue(task8Zadanie2("1010"));
        assertFalse(task8Zadanie2("10101"));
    }
    @Test
    void testTask3() {
        assertTrue(task8Zadanie3("000"));
        assertTrue(task8Zadanie3("000000"));
        assertTrue(task8Zadanie3("101010"));

        assertFalse(task8Zadanie3("00"));
        assertFalse(task8Zadanie3("0000"));
        assertFalse(task8Zadanie3("1010101010"));
    }
    @Test
    void testTask4() {
        assertTrue(task8Zadanie4("110"));
        assertTrue(task8Zadanie4("1110"));
        assertTrue(task8Zadanie4("1111"));

        assertFalse(task8Zadanie4("11"));
        assertFalse(task8Zadanie4("111"));
    }
    @Test
    void testTask5() {
        assertTrue(task8Zadanie5("1010101"));
        assertTrue(task8Zadanie5("111111"));

        assertFalse(task8Zadanie5("01010101"));
        assertFalse(task8Zadanie5("000000"));
    }
    @Test
    void testTask6() {
        assertTrue(task8Zadanie6("010000"));
        assertTrue(task8Zadanie6("00000001"));
        assertTrue(task8Zadanie6("100000000"));

        assertFalse(task8Zadanie6("10101"));
        assertFalse(task8Zadanie6("00000001000001"));
        assertFalse(task8Zadanie6("01"));
        assertFalse(task8Zadanie6("10"));
    }
    @Test
    void testTask7() {
        assertTrue(task8Zadanie7("0101010101"));
        assertTrue(task8Zadanie7("1010101010"));
        assertTrue(task8Zadanie7("00000100001"));

        assertFalse(task8Zadanie7("110011000"));
        assertFalse(task8Zadanie7("01010101100"));
        assertFalse(task8Zadanie7("1100100101010"));
        assertFalse(task8Zadanie7("011111"));
    }

}

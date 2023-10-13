package edu.hw1;

public class Task2 {
    private Task2() {
    }
    private static final int DELIMETER = 10;
    public static int countDigits(int decimal) {
        int digitsCount = 0;
        int newDecimal = decimal;

        while (newDecimal > 0) {
            newDecimal = newDecimal / DELIMETER;
            digitsCount++;
        }
        return digitsCount;
    }

}

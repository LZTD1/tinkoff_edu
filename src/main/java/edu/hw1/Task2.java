package edu.hw1;

public class Task2 {
    private static final int DELIMETER = 10;
    private Task2() {
    }

    public static int countDigits(int decimal) {
        int digitsCount = 0;

        while (decimal > 0) {
            decimal = decimal / DELIMETER;
            digitsCount++;
        }
        return digitsCount;
    }

}

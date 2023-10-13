package edu.hw1;

public class Task2 {
    public static int countDigits(int decimal) {
        int digitsCount = 0;

        while (decimal > 0) {
            decimal = decimal / 10;
            digitsCount++;
        }
        return digitsCount;
    }

    public static void main(String[] args) {

    }
}

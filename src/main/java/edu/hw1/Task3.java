package edu.hw1;

public class Task3 {
    private Task3() {
    }

    public static int findMin(int[] a) {
        int min = a[0];
        for (int decimal : a) {
            if (decimal < min) {
                min = decimal;
            }
        }
        return min;
    }

    public static int findMax(int[] a) {
        int max = a[0];
        for (int decimal : a) {
            if (decimal > max) {
                max = decimal;
            }
        }
        return max;
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        return findMin(a1) > findMin(a2) || findMax(a1) < findMax(a2);
    }
}



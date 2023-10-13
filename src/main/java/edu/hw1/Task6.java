package edu.hw1;

import edu.hw1.Exceptions.InvalidDecimalInFunction;
import java.util.Arrays;

public class Task6 {
    private Task6() {
    }

    private static final int LOWERDECIMAL = 1000;
    private static final int UPPERDECIMAL = 9999;
    private static final int KDECIMAL = 6174;

    public static int sortingCharArray(char[] arr) {
        Arrays.sort(arr);

        return Integer.parseInt(new String(arr));
    }

    public static int sortingReverseCharArray(char[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return Integer.parseInt(new String(arr));
    }

    public static int countK(int decimal) {
        return countK(decimal, 1);
    }

    public static int countK(int decimal, int callsCount) {

        if (decimal <= LOWERDECIMAL || decimal > UPPERDECIMAL) {
            throw new InvalidDecimalInFunction("Введите число в рамках (1000;9999] !");
        }
        char[] numberStr = Integer.toString(decimal).toCharArray();

        int sortedInt = sortingCharArray(numberStr);
        int reverseInt = sortingReverseCharArray(numberStr);
        int total;
        if (sortedInt > reverseInt) {
            total = sortedInt - reverseInt;
        } else {
            total = reverseInt - sortedInt;
        }
        if (total == KDECIMAL) {
            return callsCount;
        } else {
            callsCount++;
        }
        return countK(total, callsCount);
    }
}

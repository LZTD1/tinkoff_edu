package edu.hw1;

import edu.hw1.Exceptions.InvalidDecimalInFunction;
import java.util.Arrays;

public class task_6 {
    public static int sortingCharArray(char[] arr){
        char[] object = arr;
        Arrays.sort(object);

        return Integer.parseInt(new String(object));
    }
    public static int sortingReverseCharArray(char[] arr){
        char[] object = arr;
        Arrays.sort(object);
        for (int i = 0; i < object.length / 2; i++) {
            char temp = object[i];
            object[i] = object[object.length - 1 - i];
            object[object.length - 1 - i] = temp;
        }
        return Integer.parseInt(new String(object));
    }
    public static int countK(int decimal){
        return countK(decimal, 1);
    }
    public static int countK(int decimal, int counter){
        if(decimal <= 1000 || decimal > 9999){
            throw new InvalidDecimalInFunction("Введите число в рамках (1000;9999] !");
        }
        char[] numberStr = Integer.toString(decimal).toCharArray();

        int sortedInt = sortingCharArray(numberStr);
        int reverseInt = sortingReverseCharArray(numberStr);
        int total = 0;
        if(sortedInt > reverseInt){
            total = sortedInt-reverseInt;
        }else{
            total = reverseInt-sortedInt;
        }
        if(total == 6174){
            return counter;
        }else{
            counter++;
        }
        return countK(total, counter);
    }

    public static void main(String[] args) {
        System.out.println(countK(1234));
    }
}

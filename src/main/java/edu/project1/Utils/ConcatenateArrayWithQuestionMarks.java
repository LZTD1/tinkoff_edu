package edu.project1.Utils;

public class ConcatenateArrayWithQuestionMarks {
    public static String concatenateArrayWithQuestionMarks(char[] array, String delimiter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(delimiter);
            }
        }
        return result.toString();
    }

    public static String concatenateArrayWithQuestionMarks(char[] array) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
            }
        }
        return result.toString();
    }
}

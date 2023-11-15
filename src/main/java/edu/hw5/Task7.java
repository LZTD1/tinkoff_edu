package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {

    private final static String PATTERN_FOR_TASK1 = "^[01]{2}0[01]*$";
    private final static String PATTERN_FOR_TASK2 = "^([01])[01]*\\1$";
    private final static String PATTERN_FOR_TASK3 = "^[01]{1,3}$";

    private Task7() {
    }

    public static boolean contains3charactersAndThirdSymbolIsZero(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK1);
        return alphabetPattern.matcher(s).find();
    }

    public static boolean sameSymbolsInStartAndEnd(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK2);
        return alphabetPattern.matcher(s).find();
    }

    public static boolean sizeBetween1and3(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK3);
        return alphabetPattern.matcher(s).find();
    }
}

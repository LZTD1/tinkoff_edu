package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {

    private static final String PATTERN_FOR_TASK1 = "^[01]([01][01])*$";
    private static final String PATTERN_FOR_TASK2 = "^(0([01][01])*|(?=1)([01]{2})*)$";
    private static final String PATTERN_FOR_TASK3 = "^(1*01*01*01*)*$";
    private static final String PATTERN_FOR_TASK4 = "^(?!111?$)[01]*$";
    private static final String PATTERN_FOR_TASK5 = "^(1(01)*0?)*$";
    private static final String PATTERN_FOR_TASK6 = "^(0{2,}1|10{2,}|0+10+)$";
    private static final String PATTERN_FOR_TASK7 = "^((0*10*)(?!1))*$";

    private Task8() {
    }

    public static boolean task8Zadanie1(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK1);
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie2(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK2);
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie3(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK3);
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie4(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK4);
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie5(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK5);
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie6(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK6);
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie7(String s) {
        Pattern alphabetPattern = Pattern.compile(PATTERN_FOR_TASK7);
        return alphabetPattern.matcher(s).find();
    }
}

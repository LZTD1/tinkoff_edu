package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {

    private Task8() {
    }

    public static boolean task8Zadanie1(String s) {
        Pattern alphabetPattern = Pattern.compile("^[01]([01][01])*$");
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie2(String s) {
        Pattern alphabetPattern = Pattern.compile("^(0([01][01])*|(?=1)([01]{2})*)$");
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie3(String s) {
        Pattern alphabetPattern = Pattern.compile("^(1*01*01*01*)*$");
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie4(String s) {
        Pattern alphabetPattern = Pattern.compile("^(?!111?$)[01]*$");
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie5(String s) {
        Pattern alphabetPattern = Pattern.compile("^(1(01)*0?)*$");
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie6(String s) {
        Pattern alphabetPattern = Pattern.compile("^(0{2,}1|10{2,}|0+10+)$");
        return alphabetPattern.matcher(s).find();
    }

    public static boolean task8Zadanie7(String s) {
        Pattern alphabetPattern = Pattern.compile("^((0*10*)(?!1))*$");
        return alphabetPattern.matcher(s).find();
    }
}

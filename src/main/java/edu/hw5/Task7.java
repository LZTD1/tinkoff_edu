package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {

    private Task7() {
    }

    public static boolean validationOne(String s) { //содержит не менее 3 символов, причем третий символ равен 0
        Pattern alphabetPattern = Pattern.compile("^[01]{2}0[01]*$");
        return alphabetPattern.matcher(s).find();
    }

    public static boolean validationTwo(String s) { //начинается и заканчивается одним и тем же символом
        Pattern alphabetPattern = Pattern.compile("^([01])[01]*\\1$");
        return alphabetPattern.matcher(s).find();
    }

    public static boolean validationThree(String s) { //длина не менее 1 и не более 3
        Pattern alphabetPattern = Pattern.compile("^[01]{1,3}$");
        return alphabetPattern.matcher(s).find();
    }
}

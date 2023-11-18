package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {

    private Task6() {
    }

    public static boolean isSubsequence(String s, String t) {
        String myRegexString = String.join(".*", s.split(""));
        Pattern myRegex = Pattern.compile(myRegexString);
        return myRegex.matcher(t).find();
    }
}

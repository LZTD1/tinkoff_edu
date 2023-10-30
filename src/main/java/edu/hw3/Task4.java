package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task4 {

    private final static int FIVE = 5;
    private final static int THREE = 3;

    private Task4() {
    }

    public static String convertToRoman(int digits) {
        String[] rom = new String[] {
            "I", // 1
            "V", // 5
            "X", // 10
            "L", // 50
            "C", // 100
            "D", // 500
            "M", // 1000
        };
        List<List<String>> numbers = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add("0");
                add("1");
                add("2");
                add("3");
            }});
            add(new ArrayList<>() {{
                add("4");
            }});
            add(new ArrayList<>() {{
                add("5");
                add("6");
                add("7");
                add("8");
            }});
            add(new ArrayList<>() {{
                add("9");
            }});
        }};
        String answer = "";
        char[] reversedString = new StringBuilder(Integer.toString(digits))
            .reverse()
            .toString()
            .toCharArray();
        int romCounter = 0;

        for (int i = 0; i < reversedString.length; i++) {
            String currentCharAsString = String.valueOf(reversedString[i]);

            if (numbers.get(0).contains(currentCharAsString)) { // 0, 1, 2, 3
                answer = rom[romCounter].repeat(Integer.parseInt(String.valueOf(reversedString[i])))
                    + answer;
            } else if (numbers.get(1).contains(currentCharAsString)) { // 4
                answer = rom[romCounter] + rom[romCounter + 1] + answer;
            } else if (numbers.get(2).contains(currentCharAsString)) { // 5, 6, 7, 8
                answer = rom[romCounter + 1]
                    + rom[romCounter].repeat(Integer.parseInt(String.valueOf(reversedString[i])) - FIVE) + answer;
            } else if (numbers.get(THREE).contains(currentCharAsString)) { // 9
                answer = rom[romCounter] + rom[romCounter + 2] + answer;
            }

            romCounter += 2;
        }

        return answer;
    }
}

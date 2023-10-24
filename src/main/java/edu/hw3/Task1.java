package edu.hw3;

public class Task1 {

    private static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private Task1() {}

    public static String atbash(String input) {
        StringBuilder newStr = new StringBuilder();
        char[] symbols = input.toCharArray();
        for (char symbol : symbols) {
            int indexThisSymbol = ENGLISH_ALPHABET.indexOf(Character.toLowerCase(symbol));
            if (indexThisSymbol != -1) {
                char[] alphabetChar = ENGLISH_ALPHABET.toCharArray();
                char transitionCharacter = alphabetChar[ENGLISH_ALPHABET.length() - 1 - indexThisSymbol];
                if (Character.isUpperCase(symbol)) {
                    newStr.append(Character.toUpperCase(transitionCharacter));
                } else {
                    newStr.append(transitionCharacter);
                }
            } else {
                newStr.append(symbol);
            }
        }
        return newStr.toString();
    }
}

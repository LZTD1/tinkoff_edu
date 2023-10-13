package edu.hw1;

public class Task5 {
    private Task5() {
    }

    public static int reduceArray(int numbersDecimal) {
        char[] numbers = Integer.toString(numbersDecimal).toCharArray();

        StringBuilder result = new StringBuilder(numbers.length / 2);
        for (int i = 0; i < numbers.length; i += 2) {
            int sum = Character.getNumericValue(numbers[i]) + Character.getNumericValue(numbers[i + 1]);
            result.append(sum);
        }
        return Integer.parseInt(result.toString());
    }

    public static boolean isPalindromeDescendant(int decimal) {

        boolean isPalindrome = false;
        char[] numberStr = Integer.toString(decimal).toCharArray();

        if (numberStr.length % 2 != 0) {
            return false;
        }
        for (int i = 0; i != numberStr.length / 2; i++) {
            if (numberStr[i] == numberStr[numberStr.length - i - 1]) {
                isPalindrome = true;
            } else {
                isPalindrome = false;
                break;
            }
        }
        if (!isPalindrome && numberStr.length > 1) {
            int toSendIntoReduce = Integer.parseInt(new String(numberStr));

            return isPalindromeDescendant(reduceArray(toSendIntoReduce));
        }
        return isPalindrome;
    }
}

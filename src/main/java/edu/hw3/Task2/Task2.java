package edu.hw3.Task2;

import edu.hw3.Task2.Exceptions.InvalidClusterize;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Task2 {

    private static final Map<Character, Character> CLUSTERS = new HashMap<>() {{
        put('(', ')');
    }};

    private Task2() {

    }

    public static List<String> clusterize(String string) {
        if (isValidString(string)) {
            StringBuilder sb = new StringBuilder();
            List<String> clusterized = new ArrayList<>();
            Stack<Character> stack = new Stack<>();

            for (char character : string.toCharArray()) {
                sb.append(character);
                if (CLUSTERS.containsKey(character)) {
                    stack.push(character);
                } else {
                    if (CLUSTERS.get(stack.pop()).equals(character)) {
                        if (stack.isEmpty()) {
                            clusterized.add(sb.toString());
                            sb.setLength(0);
                        }
                    }
                }
            }
            return clusterized;
        } else {
            throw new InvalidClusterize("Invalid clusterize!");
        }
    }

    public static boolean isValidString(String string) {
        Stack<Character> stack = new Stack<>();
        for (char character : string.toCharArray()) {
            if (CLUSTERS.containsKey(character)) {
                stack.push(character);
            } else {
                if (stack.isEmpty() || !CLUSTERS.get(stack.pop()).equals(character)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

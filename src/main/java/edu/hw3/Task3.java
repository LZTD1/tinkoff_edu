package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    private Task3() {

    }

    public static <T> Map<Object, Integer> freqDict(List<T> objects) {
        Map<Object, Integer> counter = new HashMap<>();

        for (T object : objects) {
            counter.put(object, counter.getOrDefault(object, 0) + 1);
        }
        return counter;
    }
}

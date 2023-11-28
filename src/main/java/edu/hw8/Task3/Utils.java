package edu.hw8.Task3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {
    }

    public static Map<String, String> convertListToMap(List<String> lines) {
        return lines.stream()
            .map(line -> line.split(" "))
            .collect(Collectors.toMap(parts -> parts[1], parts -> parts[0]));
    }
}

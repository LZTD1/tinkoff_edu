package edu.hw5;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    private final static int SECONDS_IN_DAY = 86400;
    private final static int SECONDS_IN_MINUTE = 60;
    private final static int SECONDS_IN_HOURS = 3600;

    private Task1() {
    }

    public static List<String> getSplitedDiapazon(String diapazon) {
        Pattern pattern = Pattern.compile("^(\\d*-\\d*-\\d*,\\s?\\d*:\\d*)\\s-\\s(\\d*-\\d*-\\d*,\\s?\\d*:\\d*)$");
        Matcher matcher = pattern.matcher(diapazon);

        if (matcher.find() && matcher.groupCount() == 2) {
            String firstPart = matcher.group(1);
            String secondPart = matcher.group(2);

            return List.of(
                firstPart,
                secondPart
            );
        } else {
            return List.of();
        }
    }

    public static Long getSessionDuration(String diapazon) {
        List<String> diapazons = getSplitedDiapazon(diapazon);
        if (diapazons.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        LocalDateTime startDateTime = LocalDateTime.parse(diapazons.get(0), formatter);
        Instant start = startDateTime.toInstant(ZoneOffset.UTC);

        LocalDateTime endDateTime = LocalDateTime.parse(diapazons.get(1), formatter);
        Instant end = endDateTime.toInstant(ZoneOffset.UTC);

        return Duration.between(start, end).getSeconds();
    }

    public static String getAverageDurationSession(String diapazon) {
        String[] splitedByString = diapazon.split("\n");
        if (splitedByString.length > 0) {
            var averageDuration = (long) Arrays.stream(splitedByString)
                .mapToLong(Task1::getSessionDuration)
                .average()
                .orElse(0);

            return getStringDateFromSeconds(averageDuration);
        } else {
            return null;
        }
    }

    public static String getStringDateFromSeconds(long sec) {
        long absSeconds = Math.abs(sec);

        int days = (int) (absSeconds / SECONDS_IN_DAY);
        int hours = (int) ((absSeconds - days * SECONDS_IN_DAY) / SECONDS_IN_HOURS);
        int minutes = (int) ((absSeconds - days * SECONDS_IN_DAY - hours * SECONDS_IN_HOURS) / SECONDS_IN_MINUTE);
        StringBuilder myString = new StringBuilder();

        if (days > 0) {
            myString.append(String.format("%dд ", days));
        }
        if (hours > 0) {
            myString.append(String.format("%dч ", hours));
        }
        if (minutes > 0) {
            myString.append(String.format("%dм ", minutes));
        }

        return myString.toString().trim();
    }
}

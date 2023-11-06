package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private final static int SEARCHED_DAY = 13;

    private Task2() {
    }

    public static List<String> getAllScaryFridayOnTheYear(int year) {
        List<String> scaryFridays = new ArrayList<>();

        for (LocalDate date = LocalDate.of(year, 1, SEARCHED_DAY);
             date.isBefore(LocalDate.of(year + 1, 1, SEARCHED_DAY));
             date = date.plusMonths(1)) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                scaryFridays.add(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        }
        return scaryFridays;
    }
}

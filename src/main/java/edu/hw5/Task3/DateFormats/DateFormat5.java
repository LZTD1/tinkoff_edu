package edu.hw5.Task3.DateFormats;

import edu.hw5.Task3.FormatDate;
import java.time.LocalDate;
import java.util.Optional;

public class DateFormat5 extends FormatDate {

    @Override
    public Optional<LocalDate> getDateFromString(String stringDate) {
        if ("today".equalsIgnoreCase(stringDate)) {
            LocalDate today = LocalDate.now();
            return Optional.of(today);
        } else if ("tomorrow".equalsIgnoreCase(stringDate)) {
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            return Optional.of(tomorrow);
        } else if ("yesterday".equalsIgnoreCase(stringDate)) {
            LocalDate yesterday = LocalDate.now().minusDays(1);
            return Optional.of(yesterday);
        } else {
            return checkNext(stringDate);
        }

    }
}

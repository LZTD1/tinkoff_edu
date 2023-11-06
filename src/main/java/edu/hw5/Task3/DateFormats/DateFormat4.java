package edu.hw5.Task3.DateFormats;

import edu.hw5.Task3.FormatDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateFormat4 extends FormatDate {

    @Override
    public Optional<LocalDate> getDateFromString(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
        try {
            return Optional.of(LocalDate.parse(stringDate, formatter));
        } catch (DateTimeParseException e) {
            return checkNext(stringDate);
        }
    }
}

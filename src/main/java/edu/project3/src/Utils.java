package edu.project3.src;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {

    private Utils() {
    }

    public static OffsetDateTime getDateFromString(String dateString) {
        String pattern = "dd/MMM/yyyy:HH:mm:ss Z";

        return OffsetDateTime.parse(
            dateString,
            DateTimeFormatter.ofPattern(pattern, new Locale("en"))
        );
    }

    public static OffsetDateTime convertStringToDate(String dateString) {
        String pattern = "yyyy-MM-dd";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneOffset.UTC);

        LocalDate date = LocalDate.parse(dateString, formatter);

        return date.atStartOfDay().atOffset(ZoneOffset.UTC);
    }
}

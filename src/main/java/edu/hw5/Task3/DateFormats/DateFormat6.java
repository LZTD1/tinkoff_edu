package edu.hw5.Task3.DateFormats;

import edu.hw5.Task3.FormatDate;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormat6 extends FormatDate {
    @Override
    public Optional<LocalDate> getDateFromString(String stringDate) {
        Pattern pattern = Pattern.compile("^(\\d*)\\s*days?\\s*(ago|before)");
        Matcher matcher = pattern.matcher(stringDate);

        if (matcher.find()) {
            LocalDate today = LocalDate.now();

            String days = matcher.group(1);
            if (matcher.group(2).equals("before")) {
                return Optional.of(today.plusDays(Integer.parseInt(days)));
            } else {
                return Optional.of(today.minusDays(Integer.parseInt(days)));
            }
        } else {
            return checkNext(stringDate);
        }
    }
}

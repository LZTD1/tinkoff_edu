package edu.project3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private Utils() {
    }

    public static Date getDateFromString(String dateString) {
        String pattern = "dd/MMM/yyyy:HH:mm:ss Z";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }
}

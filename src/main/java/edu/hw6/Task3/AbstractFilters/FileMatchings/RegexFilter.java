package edu.hw6.Task3.AbstractFilters.FileMatchings;

import edu.hw6.Task3.AbstractFilter;
import java.util.regex.Pattern;

public interface RegexFilter extends AbstractFilter {
    static AbstractFilter regexContains(String regex) {
        Pattern matcher = Pattern.compile(regex);

        return entry -> matcher.matcher(entry.getFileName().toString()).find();
    }
}

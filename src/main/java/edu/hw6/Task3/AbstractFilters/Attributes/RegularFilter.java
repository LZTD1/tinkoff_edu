package edu.hw6.Task3.AbstractFilters.Attributes;

import edu.hw6.Task3.AbstractFilter;
import java.nio.file.Files;

public interface RegularFilter extends AbstractFilter {
    static AbstractFilter regular() {
        return Files::isRegularFile;
    }
}

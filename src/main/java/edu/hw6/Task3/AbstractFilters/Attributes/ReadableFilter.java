package edu.hw6.Task3.AbstractFilters.Attributes;

import edu.hw6.Task3.AbstractFilter;
import java.nio.file.Files;

public interface ReadableFilter extends AbstractFilter {
    static AbstractFilter readable() {
        return Files::isReadable;
    }
}

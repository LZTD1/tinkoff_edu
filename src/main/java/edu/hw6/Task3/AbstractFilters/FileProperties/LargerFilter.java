package edu.hw6.Task3.AbstractFilters.FileProperties;

import edu.hw6.Task3.AbstractFilter;
import java.nio.file.Files;

public interface LargerFilter extends AbstractFilter {
    static AbstractFilter largerThan(int bytes) {
        return entry -> Files.size(entry) > bytes;
    }

}

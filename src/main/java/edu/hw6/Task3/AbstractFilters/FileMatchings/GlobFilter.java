package edu.hw6.Task3.AbstractFilters.FileMatchings;

import edu.hw6.Task3.AbstractFilter;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public interface GlobFilter extends AbstractFilter {
    static AbstractFilter globMatches(String glob) {
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:" + glob);

        return entry -> matcher.matches(entry.getFileName());
    }
}

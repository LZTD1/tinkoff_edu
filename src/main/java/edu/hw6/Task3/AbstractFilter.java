package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }

    static AbstractFilter largerThan(int bytes) {
        return entry -> Files.size(entry) > bytes;
    }

    static AbstractFilter globMatches(String glob) {
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:" + glob);

        return entry -> matcher.matches(entry.getFileName());
    }
}

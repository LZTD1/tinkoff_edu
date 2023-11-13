package edu.hw6.Task3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    static AbstractFilter largerThan(int bytes) {
        return entry -> Files.size(entry) > bytes;
    }

    static AbstractFilter globMatches(String glob) {
        PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:" + glob);

        return entry -> matcher.matches(entry.getFileName());
    }

    static AbstractFilter regexContains(String regex) {
        Pattern matcher = Pattern.compile(regex);

        return entry -> matcher.matcher(entry.getFileName().toString()).find();
    }

    static AbstractFilter magicNumber(Object... numbers) {
        return entry -> {
            try (FileInputStream ins = new FileInputStream(entry.toFile())) {
                for (Object currentObj : numbers) {
                    int a = ins.read();
                    if ((currentObj instanceof Integer)) {
                        if ((int) currentObj != a) {
                            return false;
                        }
                    } else if ((currentObj instanceof Character)) {
                        int charValue = ((Character) currentObj).charValue();
                        if (charValue != a) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    default AbstractFilter and(AbstractFilter other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }
}

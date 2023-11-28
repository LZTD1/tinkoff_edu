package edu.hw6.Task3;

import java.nio.file.Files;

public interface FileProperties extends AbstractFilter {
    static AbstractFilter largerThan(int bytes) {
        return entry -> Files.size(entry) > bytes;
    }
}

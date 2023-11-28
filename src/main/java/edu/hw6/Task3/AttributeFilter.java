package edu.hw6.Task3;

import java.nio.file.Files;

public interface AttributeFilter extends AbstractFilter {
    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter regular() {
        return Files::isRegularFile;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

}

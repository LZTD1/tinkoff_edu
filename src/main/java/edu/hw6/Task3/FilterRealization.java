package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.Path;

public class FilterRealization implements AbstractFilter {
    @Override
    public boolean accept(Path entry) throws IOException {
        return false;
    }

}

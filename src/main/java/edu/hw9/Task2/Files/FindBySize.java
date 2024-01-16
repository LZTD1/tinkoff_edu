package edu.hw9.Task2.Files;

import edu.hw9.Task2.ForkFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindBySize extends ForkFilter {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static long BYTES_IN_KILOBYTES = 1024L;
    private final Path path;
    private final List<Path> paths;
    private final long size;

    private FindBySize(Path startPath, long size, List<Path> paths) {
        this.path = startPath;
        this.size = size;
        this.paths = paths;
    }

    public FindBySize(Path startPath, long sizeKilobytes) {
        this.path = startPath;
        this.size = sizeKilobytes;
        this.paths = new ArrayList<Path>();
    }

    @Override
    protected List<Path> compute() {
        for (File currentFile : Objects.requireNonNull(path.toFile().listFiles())) {
            if (currentFile.isFile()) {
                try {

                    if (
                        size * BYTES_IN_KILOBYTES <= Files.size(currentFile.toPath())
                            && Files.size(currentFile.toPath()) < size * BYTES_IN_KILOBYTES + BYTES_IN_KILOBYTES

                    ) {
                        LOGGER.info("[FOUND!] {}", currentFile.toString());
                        paths.add(currentFile.toPath());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (currentFile.isDirectory()) {
                var newTask = new FindBySize(currentFile.toPath(), size, paths);
                newTask.fork();
            }
        }
        return paths;
    }
}

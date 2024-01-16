package edu.hw9.Task2.Files;

import edu.hw9.Task2.ForkFilter;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindByExtension extends ForkFilter {

    private final static Logger LOGGER = LogManager.getLogger();
    private final Path path;
    private final List<Path> paths;
    private final String extension;
    private final PathMatcher matcher;

    private FindByExtension(Path startPath, String extension, List<Path> paths, PathMatcher matcher) {
        this.path = startPath;
        this.extension = extension;
        this.paths = paths;
        this.matcher = matcher;
    }

    public FindByExtension(Path startPath, String extension) {
        this.path = startPath;
        this.extension = extension;
        this.matcher = FileSystems.getDefault().getPathMatcher("glob:**/*." + extension);
        this.paths = new ArrayList<Path>();
    }

    @Override
    protected List<Path> compute() {
        for (File currentFile : Objects.requireNonNull(path.toFile().listFiles())) {
            if (currentFile.isFile()) {
                if (this.matcher.matches(currentFile.toPath())) {
                    LOGGER.info("[FOUND!] {}", currentFile.toString());
                    paths.add(currentFile.toPath());
                }
            }
            if (currentFile.isDirectory()) {
                var newTask = new FindByExtension(currentFile.toPath(), extension, paths, matcher);
                newTask.fork();
            }
        }
        return paths;
    }
}

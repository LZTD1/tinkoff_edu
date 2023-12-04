package edu.hw9.Task2.Files;

import edu.hw9.Task2.ForkFilter;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectoriesWithMoreFiles extends ForkFilter {

    private final Path path;
    private final int moreThan;
    private final List<Path> paths;
    private final static Logger LOGGER = LogManager.getLogger();

    public DirectoriesWithMoreFiles(Path startPath, int moreThan, List<Path> paths) {
        this.path = startPath;
        this.moreThan = moreThan;
        this.paths = paths;
    }

    public DirectoriesWithMoreFiles(Path startPath, int moreThan) {
        this.path = startPath;
        this.moreThan = moreThan;
        this.paths = new ArrayList<Path>();
    }

    @Override
    protected List<Path> compute() {
        for (File currentFile : Objects.requireNonNull(path.toFile().listFiles())) {
            if (currentFile.isDirectory()) {
                if (Objects.requireNonNull(currentFile.listFiles()).length >= this.moreThan) {
                    LOGGER.info("[FOUND!] {}", currentFile.toString());
                    paths.add(currentFile.toPath());
                }
                var newTask = new DirectoriesWithMoreFiles(currentFile.toPath(), moreThan, paths);
                newTask.fork();
            }
        }
        return paths;
    }
}

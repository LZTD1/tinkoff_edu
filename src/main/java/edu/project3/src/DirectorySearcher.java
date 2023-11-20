package edu.project3.src;

import edu.project3.src.Exceptions.FileNotExists;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DirectorySearcher {

    private final String pathString;

    public DirectorySearcher(String pathString) {
        this.pathString = pathString;
    }

    public List<Path> getFilesFromPath() {
        if (this.pathString.contains("*")) {
            return findAllPaths();
        } else {
            return getSinglePath();
        }
    }

    private List<Path> getSinglePath() {
        Path path = Paths.get(this.pathString);
        if (path.toFile().exists()) {
            return List.of(path);
        } else {
            throw new FileNotExists("File not exists!");
        }
    }

    private List<Path> findAllPaths() {
        List<Path> matchingPaths = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + this.pathString);

        String startDir = getStartDir(this.pathString);

        try {
            Files.walkFileTree(Path.of(startDir), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(filePath)) {
                        matchingPaths.add(filePath);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return matchingPaths;
    }

    private String getStartDir(String path) {
        int firstAsteriskIndex = path.indexOf("*");
        int lastSlashIndex = path.lastIndexOf("/", firstAsteriskIndex);
        return path.substring(0, lastSlashIndex);
    }
}

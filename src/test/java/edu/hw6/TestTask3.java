package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.AbstractFilter.moreThan;

public class TestTask3 {

    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;

    @Test
    void test() {
        AbstractFilter filter = regularFile
            .and(moreThan(4));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/main/java/edu/hw6/Task3"),
            filter
        )) {
            entries.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

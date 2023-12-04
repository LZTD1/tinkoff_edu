package edu.hw6;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task2.cloneFile;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask2 {
    @AfterAll static void cleanUp() {
        Paths.get("src/test/resources/hw6/fileForCopy - копия (1).txt").toFile().delete();
        Paths.get("src/test/resources/hw6/fileForCopy - копия (2).txt").toFile().delete();
        Paths.get("src/test/resources/hw6/fileForCopy - копия (3).txt").toFile().delete();
    }

    @Test
    void cloneFileTest() {
        Path testPath = Paths.get("src/test/resources/hw6/fileForCopy.txt");
        Path res = cloneFile(testPath);
        assertThat(res.getFileName().toString()).isEqualTo("fileForCopy - копия (1).txt");

        testPath = Paths.get("src/test/resources/hw6/fileForCopy.txt");
        res = cloneFile(testPath);
        assertThat(res.getFileName().toString()).isEqualTo("fileForCopy - копия (2).txt");

        testPath = Paths.get("src/test/resources/hw6/fileForCopy.txt");
        res = cloneFile(testPath);
        assertThat(res.getFileName().toString()).isEqualTo("fileForCopy - копия (3).txt");

    }
}

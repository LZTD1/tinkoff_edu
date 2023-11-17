package edu.project3;

import edu.project3.src.Exceptions.BadInputArgument;
import edu.project3.src.Manager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static edu.project3.TestUtils.getFileExtension;
import static edu.project3.TestUtils.readFileContent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {
    @Test
    @DisplayName("Тестирование с не парными аргументами")
    void badArgumentsTest(){
        String[] args = {
            "--path",
            "someUrl",
            "--format"
        };

        assertThrows(BadInputArgument.class, ()->new Manager(args).getStats());
    }
    @Test
    @DisplayName("Тестирование без аргумента пути")
    void badArgumentsTest2(){
        String[] args = {
            "--format",
            "markdown"
        };
        assertThrows(BadInputArgument.class, ()->new Manager(args).getStats());
    }
    @Test
    @DisplayName("Проверка на создание файла формата markdown")
    void testExtensionMarkdown(){
        String[] args = {
            "--format",
            "markdown",
            "--path",
            "src/test/resources/project3Test/testLog.log"
        };

        File stats = new Manager(args).getStats();

        assertTrue(stats.exists());
        assertEquals("md", getFileExtension(stats));

        assertThat(
            readFileContent(stats.toPath())
        ).isEqualTo(
            readFileContent(Paths.get("src/test/resources/project3Test/ManagerTest/mockTestLog.md"))
        );

        stats.delete();
    }
    @Test
    @DisplayName("Проверка на создание файла формата adoc")
    void testExtensionAdoc(){
        String[] args = {
            "--format",
            "adoc",
            "--path",
            "src/test/resources/project3Test/testLog.log"
        };

        File stats = new Manager(args).getStats();

        assertTrue(stats.exists());
        assertEquals("adoc", getFileExtension(stats));

        assertThat(
            readFileContent(stats.toPath())
        ).isEqualTo(
            readFileContent(Paths.get("src/test/resources/project3Test/ManagerTest/mockTestLog.adoc"))
        );

        stats.delete();
    }

    @Test
    @DisplayName("Проверка на создание файла из ссылки")
    void testUrlCreatorReport(){
        String[] args = {
            "--format",
            "markdown",
            "--path",
            "https://raw.githubusercontent.com/LZTD1/tinkoff_edu/project3/src/test/resources/project3Test/testLog.log"
        };

        File stats = new Manager(args).getStats();

        assertTrue(stats.exists());
        assertEquals("md", getFileExtension(stats));

        assertThat(
            readFileContent(stats.toPath())
        ).isEqualTo(
            readFileContent(Paths.get("src/test/resources/project3Test/ManagerTest/mockTestLogFromURL.md"))
        );

        stats.delete();
    }
}

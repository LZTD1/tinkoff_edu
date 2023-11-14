package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.magicNumber;
import static edu.hw6.Task3.AbstractFilter.regexContains;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask3 {

    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;

    @Test
    void testFindMagicNumbers() {
        AbstractFilter filter = regularFile
            .and(magicNumber(0x89, 'P', 'N', 'G'));
        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(normalizePaths(entry.toString())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            normalizePaths("src\\test\\resources\\hw6\\filesToTask3\\screen.png")
        ));
    }

    @Test
    void testFindByRegex() {
        AbstractFilter filter = regularFile
            .and(regexContains("^some.+java$"));
        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(normalizePaths(entry.toString())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            normalizePaths("src\\test\\resources\\hw6\\filesToTask3\\somejava.java")
        ));
    }

    @Test
    void testFindByGlob() {
        AbstractFilter filter = regularFile
            .and(globMatches("*.txt"));
        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(normalizePaths(entry.toString())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            normalizePaths("src\\test\\resources\\hw6\\filesToTask3\\sometext.txt")
        ));
    }

    @Test
    void testFindByLarger() {
        AbstractFilter filter = regularFile
            .and(largerThan(2_000));

        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(normalizePaths(entry.toString())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            normalizePaths("src\\test\\resources\\hw6\\filesToTask3\\screen2.jpg"),
            normalizePaths("src\\test\\resources\\hw6\\filesToTask3\\screen.png")
        ));
    }

    @Test
    void testFindByLargeAndMagicNumbers() {
        AbstractFilter filter = regularFile
            .and(largerThan(2_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'));

        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(normalizePaths(entry.toString())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            normalizePaths("src\\test\\resources\\hw6\\filesToTask3\\screen.png")
        ));
    }

    private String normalizePaths(String path) { // в разных осях, разные слеши, прямые или обратные, поэтому для теста нормализую
        return Paths.get(path).toUri().toString();
    }
}

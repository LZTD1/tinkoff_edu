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
import static edu.hw6.Task3.AbstractFilters.Attributes.RegularFilter.regular;
import static edu.hw6.Task3.AbstractFilters.FileMatchings.GlobFilter.globMatches;
import static edu.hw6.Task3.AbstractFilters.FileMatchings.MagicFilter.magicNumber;

import static edu.hw6.Task3.AbstractFilters.FileMatchings.RegexFilter.regexContains;
import static edu.hw6.Task3.AbstractFilters.FileProperties.LargerFilter.largerThan;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask3 {

    @Test
    void testFindMagicNumbers() {
        AbstractFilter filter = regular()
            .and(magicNumber(0x89, 'P', 'N', 'G'));
        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(entry.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            Paths.get("src", "test", "resources", "hw6", "filesToTask3", "screen.png").toString()
        ));
    }

    @Test
    void testFindByRegex() {
        AbstractFilter filter = regular()
            .and(regexContains("^some.+java$"));
        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(entry.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            Paths.get("src", "test", "resources", "hw6", "filesToTask3", "somejava.java").toString()
        ));
    }

    @Test
    void testFindByGlob() {
        AbstractFilter filter = regular()
            .and(globMatches("*.txt"));
        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(entry.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            Paths.get("src", "test", "resources", "hw6", "filesToTask3", "sometext.txt").toString()
        ));
    }

    @Test
    void testFindByLarger() {
        AbstractFilter filter = regular()
            .and(largerThan(2_000));

        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(entry.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            Paths.get("src", "test", "resources", "hw6", "filesToTask3", "screen2.jpg").toString(),
            Paths.get("src", "test", "resources", "hw6", "filesToTask3", "screen.png").toString()
        ));
    }

    @Test
    void testFindByLargeAndMagicNumbers() {
        AbstractFilter filter = regular()
            .and(largerThan(2_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'));

        ArrayList<String> toExcept = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Paths.get("src/test/resources/hw6/filesToTask3"),
            filter
        )) {
            entries.forEach(entry -> toExcept.add(entry.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(toExcept).containsExactlyInAnyOrderElementsOf(List.of(
            Paths.get("src", "test", "resources", "hw6", "filesToTask3", "screen.png").toString()
        ));
    }
}

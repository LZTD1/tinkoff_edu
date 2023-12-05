package edu.hw9;

import edu.hw9.Task2.Directories.DirectoriesWithMoreFiles;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import edu.hw9.Task2.Files.FindByExtension;
import edu.hw9.Task2.Files.FindBySize;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask2 {
    @Test
    void testMoreFiles() {
        try(ForkJoinPool forkJoinPool = new ForkJoinPool()){
            List<Path> result = forkJoinPool.invoke(new DirectoriesWithMoreFiles(
                Path.of("src/test/resources/hw9/Task2"),
                2
            ));

            forkJoinPool.awaitQuiescence(10, TimeUnit.SECONDS);

            assertThat(result.size()).isEqualTo(2);
            assertThat(result).containsExactlyInAnyOrder(
                Path.of("src","test","resources","hw9","Task2","dir1"),
                Path.of("src","test","resources","hw9","Task2","dir3")
            );
        }
    }
    @Test
    void testFindByExtension() {
        try(ForkJoinPool forkJoinPool = new ForkJoinPool()){
            List<Path> result = forkJoinPool.invoke(new FindByExtension(
                Path.of("src/test/resources/hw9/Task2"),
                "txt"
            ));

            forkJoinPool.awaitQuiescence(10, TimeUnit.SECONDS);

            assertThat(result.size()).isEqualTo(2);
            assertThat(result).containsExactlyInAnyOrder(
                Path.of("src","test","resources","hw9","Task2","dir2", "file1.txt"),
                Path.of("src","test","resources","hw9","Task2","dir1", "file1.txt")
            );
        }
    }
    @Test
    void testFindFilesBySize() {
        try(ForkJoinPool forkJoinPool = new ForkJoinPool()){
            List<Path> result = forkJoinPool.invoke(new FindBySize(
                Path.of("src/test/resources/hw9/Task2"),
                226
            ));

            forkJoinPool.awaitQuiescence(10, TimeUnit.SECONDS);

            assertThat(result.size()).isEqualTo(1);
            assertThat(result).containsExactlyInAnyOrder(
                Path.of("src","test","resources","hw9","Task2","dir1", "amogus.png")
            );
        }
    }
    @Test
    void testFindFilesBySizeNoOne() {
        try(ForkJoinPool forkJoinPool = new ForkJoinPool()){
            List<Path> result = forkJoinPool.invoke(new FindBySize(
                Path.of("src/test/resources/hw9/Task2"),
                1024
            ));

            forkJoinPool.awaitQuiescence(10, TimeUnit.SECONDS);

            assertThat(result).isEmpty();
        }
    }
}

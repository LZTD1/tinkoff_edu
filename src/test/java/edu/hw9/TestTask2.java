package edu.hw9;

import edu.hw9.Task2.Directories.DirectoriesWithMoreFiles;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
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
}

package edu.hw8;

import edu.hw8.Task3.Bruteforce;
import edu.hw8.Task3.HashGenerator;
import edu.hw8.Task3.Manager;
import edu.hw8.Task3.NoSuchFile;
import edu.hw8.Task3.ReadFromFile;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static edu.hw8.Task3.Utils.convertListToMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTask3 {
    @Nested
    class testFileReader {
        @Test
        void testNoSuchFileException() {
            assertThrows(NoSuchFile.class,
                () -> new ReadFromFile(Path.of("src/main/resources/hw8/Task3/noSuch.txt")));
        }
        @Test
        void testReadFileContent() {
            var fileReader = new ReadFromFile(Path.of("src/main/resources/hw8/Task3/database.txt"));

            var result = fileReader.getFileContent();

            assertThat(result.size()).isEqualTo(4);
            assertThat(result).isEqualTo(List.of(
                "a.v.petrov 81dc9bdb52d04dc20036dbd8313ed055",
                "v.v.belov 0800fc577294c34e0b28ad2839435945",
                "a.s.ivanov 54c84b40e9ff5a31472904a0cd2f0a17",
                "k.p.maslov 4579b42387e89efb5c1ae5e7fbe04427"
            ));
        }
    }

    @Nested
    class testBruteforce{
        @Test
        void testGenerate(){
            var forcer = new Bruteforce("abc", 2, new ArrayBlockingQueue<>(20), 5);

            forcer.bruteForce();

            assertThat(forcer.getQueue().toArray()).isEqualTo(
              new String[]{
                  "aa",
                  "ab",
                  "ac",
                  "ba",
                  "bb",
                  "bc",
                  "ca",
                  "cb",
                  "cc"
              }
            );
        }
    }
    @Nested
    class testHashGenerator{
        @Test
        void testHashing() throws InterruptedException {
            BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
            queue.put("test");

            var hasher = new HashGenerator(queue);

            assertThat(hasher.generateHash().hash()).isEqualTo("098f6bcd4621d373cade4e832627b4f6");
        }
    }

    @Nested
    class testManager{

        @Test
        void testManager() throws InterruptedException {
            var manager = new Manager(
                Path.of("src/test/resources/hw8/Task3/database.txt"),
                4
            );
            manager.setMaxLength(2);
            var findedPasswords = manager.startBruteforce();

            System.out.println(findedPasswords);

            Thread.sleep(2000);
        }

    }

    @Nested
    class testUtils{
        @Test
        void testConvertListToMap(){
            List<String> myList = List.of(
              "hello world",
              "array list",
              "hash map"
            );

            Map<String, String> myMap = convertListToMap(myList);

            assertThat(myMap).hasSize(3);
            assertThat(myMap).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                    "world", "hello",
                    "list", "array",
                    "map", "hash"
                )
            );
        }
    }
}

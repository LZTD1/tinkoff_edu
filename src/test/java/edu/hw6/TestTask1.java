package edu.hw6;

import edu.hw6.Task1.DiskMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask1 {

    @Test
    void testCreatingFile() {
        var diskMap = new DiskMap();
        File file = new File(diskMap.getPathFile());
        assertTrue(file.exists());
        diskMap.destructor();
    }

    @Test
    void testLoadFromFile() {
        Path myPath = Paths.get("src/test/resources/hw6/testMap.txt");
        DiskMap diskMap = new DiskMap(myPath);

        assertThat(diskMap.getHashMap()).isEqualTo(Map.of(
            "key", "value",
            "key2", "value"
        ));

        diskMap.put("key3", "value");
        assertThat(diskMap.getHashMap()).isEqualTo(Map.of(
            "key", "value",
            "key2", "value",
            "key3", "value"
        ));

        diskMap.remove("key3", "value");
        assertThat(diskMap.getHashMap()).isEqualTo(Map.of(
            "key", "value",
            "key2", "value"
        ));
    }

    @Test
    void testWriting() {
        var diskMap = new DiskMap();

        diskMap.put("key", "value");
        diskMap.put("key2", "value2");

        try (BufferedReader bufferreader = new BufferedReader(new FileReader(diskMap.getPathFile()))) {
            int mySize = 0;
            String line;
            HashMap<String, String> hm = new HashMap<>();
            while ((line = bufferreader.readLine()) != null) {
                mySize += 1;
                String[] data = line.split(":", 2);
                hm.put(data[0], data[1]);
            }

            assertEquals(mySize, diskMap.size());
            assertEquals(hm, diskMap.getHashMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        diskMap.destructor();
    }
}

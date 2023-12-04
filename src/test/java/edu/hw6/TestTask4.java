package edu.hw6;

import edu.hw6.Task4.SimpleFileWriter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask4 {

    private static final String PATH = "src/test/resources/hw6/FilesToTask4/myText.txt";

    @Test
    void writeTest() {
        String toWritten =
            String.valueOf(java.util.UUID.randomUUID()); // Генерю каждый раз новый UUID просто для того что бы текст менялся

        try (SimpleFileWriter fileWriter = new SimpleFileWriter(PATH)) {
            fileWriter.printInFile(toWritten);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String myWritten = getWritten();
        assertThat(toWritten).isEqualTo(myWritten);
    }

    @Test
    void changeCheckSumTest() {
        String toWritten = String.valueOf(java.util.UUID.randomUUID());
        long before;
        long after;

        try (SimpleFileWriter fileWriter = new SimpleFileWriter(PATH)) {
            before = fileWriter.getCheckSum();
            fileWriter.printInFile(toWritten);
            after = fileWriter.getCheckSum();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String myWritten = getWritten();
        assertThat(toWritten).isEqualTo(myWritten);
        assertThat(before).isNotEqualTo(after);
    }

    private String getWritten() {
        var myFile = Paths.get(PATH).toFile();
        StringBuilder sb = new StringBuilder();

        try (var inputStream = new FileInputStream(myFile)) {
            try (BufferedInputStream bis = new BufferedInputStream(inputStream)) {
                int i;
                while ((i = bis.read()) != -1) {
                    sb.append((char) i);
                }
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

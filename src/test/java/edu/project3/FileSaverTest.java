package edu.project3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static edu.project3.LogAnalyzeTest.MOCK_LOGS;
import static org.assertj.core.api.Assertions.assertThat;

public class FileSaverTest {
    @Test
    void markdownSaveToFileTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS);
        LogStatsSaver logSaver = new LogStatsSaver(
            logAnalyze.getAllStatistics(),
            LogStatsSaver.ExtensionFile.markdown
        );

        File file = logSaver.saveIntoFile();

        assertThat(file).exists();
        assertThat(file).isFile();
        assertThat(file).isReadable();
        assertThat(file).isNotEmpty();
        assertThat(getFileExtension(file)).isEqualTo("md");

        assertThat(
            readFileContent(file.toPath())
        ).isEqualTo(
            readFileContent(Paths.get("src/test/resources/project3Test/mockReport.md"))
        );

        file.delete();
    }

    @Test
    void adocSaveToFileTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS);
        LogStatsSaver logSaver = new LogStatsSaver(
            logAnalyze.getAllStatistics(),
            LogStatsSaver.ExtensionFile.adoc
        );

        File file = logSaver.saveIntoFile();

        assertThat(file).exists();
        assertThat(file).isFile();
        assertThat(file).isReadable();
        assertThat(file).isNotEmpty();
        assertThat(getFileExtension(file)).isEqualTo("adoc");

        assertThat(
            readFileContent(file.toPath())
        ).isEqualTo(
            readFileContent(Paths.get("src/test/resources/project3Test/mockReport.adoc"))
        );

        file.delete();
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        } else {
            return "";
        }
    }

    private static String readFileContent(Path filePath) {
        try {
            byte[] fileBytes = Files.readAllBytes(filePath);
            return new String(fileBytes);
        } catch (IOException e) {
            return null;
        }
    }
}

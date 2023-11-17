package edu.project3;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import edu.project3.src.LogAnalyze;
import edu.project3.src.LogStatsSaver;
import org.junit.jupiter.api.Test;
import static edu.project3.TestUtils.MOCK_LOGS;
import static edu.project3.TestUtils.getFileExtension;
import static edu.project3.TestUtils.readFileContent;
import static org.assertj.core.api.Assertions.assertThat;

public class FileSaverTest {
    @Test
    void markdownSaveToFileTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "", "");
        LogStatsSaver logSaver = new LogStatsSaver(
            logAnalyze.getAllStatistics(List.of("directory/mockLogs")),
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
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "", "");
        LogStatsSaver logSaver = new LogStatsSaver(
            logAnalyze.getAllStatistics(List.of("directory/mockLogs")),
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
}

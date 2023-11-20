package edu.project3.src.Parsers;

import edu.project3.src.Exceptions.FileNotExists;
import edu.project3.src.Model.LogReport;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileParser extends Parser {

    private final Path filePath;
    private final File file;
    private List<LogReport> processedLogs;

    public FileParser(Path filePath) {
        if (filePath.toFile().exists()) {
            this.filePath = filePath;
            this.file = filePath.toFile();
        } else {
            throw new FileNotExists("The transferred file does not exist!" + filePath);
        }
    }

    @Override
    public List<LogReport> parseLogs() {
        this.processedLogs = new ArrayList<>();
        try (LineIterator it = FileUtils.lineIterator(this.file, "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                LogReport log = parseLine(line);
                this.processedLogs.add(log);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.processedLogs;
    }

}

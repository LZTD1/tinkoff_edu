package edu.project3.ParsersTest;

import edu.project3.Exceptions.BadLogLineException;
import edu.project3.Exceptions.FileNotExists;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import edu.project3.Model.LogReport;
import edu.project3.Parsers.FileParser;
import edu.project3.Parsers.Parser;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileParserTest {

    @Test
    void testSuccessfulRead() {
        Parser fileParser = new FileParser(Paths.get("src/test/resources/project3Test/testLog.log"));
        List<LogReport> logs = fileParser.parseLogs();

        assertThat(logs.size()).isEqualTo(2);
        assertThat(logs).containsExactlyInAnyOrderElementsOf(MOCKED_LOGS_REPORT_OBJECT);
    }

    @Test
    void testFailedReadLineInFile() {
        Parser fileParser = new FileParser(Paths.get("src/test/resources/project3Test/badLog.log"));

        assertThrows(BadLogLineException.class, fileParser::parseLogs);
    }

    @Test
    void testFailedReadFile() {
        assertThrows(FileNotExists.class, () ->
            new FileParser(Paths.get("src/test/resources/project3Test/aboba.log")));
    }

    protected static Date getDateFromString(String group) {
        String pattern = "dd/MMM/yyyy:HH:mm:ss Z";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(group);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    @Test
    void testLoadLargeLog(){
        Parser fileParser = new FileParser(Paths.get("src/test/resources/project3Test/largeLog.log"));
        List<LogReport> logs = fileParser.parseLogs();

        assertThat(logs.size()).isEqualTo(51462);
    }

    protected static List<LogReport> MOCKED_LOGS_REPORT_OBJECT = List.of(
        new LogReport(
            "5.9.121.211",
            getDateFromString("25/May/2015:21:05:27 +0000"),
            new LogReport.RequestModel(
                LogReport.RequestModel.HttpMethod.GET,
                "/downloads/product_2",
                "HTTP/1.1"
            ),
            404,
            340,
            "Debian APT-HTTP/1.3 (0.8.10.3)"
        ),
        new LogReport(
            "10.0.2.236",
            getDateFromString("25/May/2015:21:05:49 +0000"),
            new LogReport.RequestModel(
                LogReport.RequestModel.HttpMethod.GET,
                "/downloads/product_2",
                "HTTP/1.1"
            ),
            304,
            0,
            "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
        )
    );
}

package edu.project3.ParsersTest;

import edu.project3.Exceptions.BadLogLineException;
import edu.project3.Model.LogReport;
import edu.project3.Parsers.Parser;
import edu.project3.Parsers.WebsiteParser;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.project3.ParsersTest.FileParserTest.MOCKED_LOGS_REPORT_OBJECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WebsiteParserTest {

    private final String MOCK_URL =
        "https://raw.githubusercontent.com/LZTD1/tinkoff_edu/project3/src/test/resources/project3Test/testLog.log";
    private final String BAD_MOCK_URL = "https://raw.githubusercontent.com/LZTD1/tinkoff_edu/master/README.md";
    private final int COUNT_LOGS_MOCK = 2;

    @Test
    void testSuccessfulRead() {
        Parser fileParser = new WebsiteParser(MOCK_URL);
        List<LogReport> logs = fileParser.parseLogs();

        assertThat(logs.size()).isEqualTo(COUNT_LOGS_MOCK);
    }

    @Test
    void testObjectValidGeneration() {
        Parser fileParser = new WebsiteParser(MOCK_URL);
        List<LogReport> logs = fileParser.parseLogs();

        assertThat(logs).containsExactlyInAnyOrderElementsOf(MOCKED_LOGS_REPORT_OBJECT);
    }

    @Test
    void testBadRead() {
        Parser fileParser = new WebsiteParser(BAD_MOCK_URL);

        assertThrows(BadLogLineException.class, fileParser::parseLogs);
    }

}

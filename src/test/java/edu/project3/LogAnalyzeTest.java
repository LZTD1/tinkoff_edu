package edu.project3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import edu.project3.src.LogAnalyze;
import edu.project3.src.Model.LogReport;
import org.junit.jupiter.api.Test;
import static edu.project3.TestUtils.MOCK_LOGS;
import static org.assertj.core.api.Assertions.assertThat;

public class LogAnalyzeTest {
    @Test
    void getGeneralStatisticTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "", "");
        HashMap<String, String> mainLogs = logAnalyze.getGeneralStatistic();

        assertThat(mainLogs).isEqualTo(
            Map.of(
                "dateStart", "25.05.2015",
                "endDate", "28.05.2015",
                "countRequests", "11",
                "averageResponseSize", "4111b"
            )
        );
    }

    @Test
    void getGeneralStatisticWithDateToTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "", "2015-05-26");
        HashMap<String, String> mainLogs = logAnalyze.getGeneralStatistic();

        assertThat(mainLogs).isEqualTo(
            Map.of(
                "dateStart", "25.05.2015",
                "endDate", "25.05.2015",
                "countRequests", "4",
                "averageResponseSize", "5500b"
            )
        );
    }
    @Test
    void getGeneralStatisticWithDateFromTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "2015-05-26", "");
        HashMap<String, String> mainLogs = logAnalyze.getGeneralStatistic();

        assertThat(mainLogs).isEqualTo(
            Map.of(
                "dateStart", "26.05.2015",
                "endDate", "28.05.2015",
                "countRequests", "7",
                "averageResponseSize", "3318b"
            )
        );
    }

    @Test
    void getRequestedResourcesTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "", "");
        Map<String, Long> resources = logAnalyze.getRequestedResources();

        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("/downloads/product_5", 4L);
        expected.put("/downloads/product_4", 3L);
        expected.put("/downloads/product_3", 2L);
        expected.put("/downloads/product_2", 1L);
        expected.put("/downloads/product_1", 1L);

        assertThat(resources).isEqualTo(
            expected
        );
    }

    @Test
    void getRequestedCodeTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "", "");
        Map<String, Long> resources = logAnalyze.getRequestedCode();

        Map<String, Long> expected = new LinkedHashMap<>();
        expected.put("404", 5L);
        expected.put("304", 3L);
        expected.put("505", 2L);
        expected.put("200", 1L);

        assertThat(resources).isEqualTo(
            expected
        );
    }
    @Test
    void getMostFiveHugeRequestsTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "", "");
        List<LogReport> result = logAnalyze.getMostFiveHugeRequests();

        assertThat(result).isEqualTo(
            List.of(
                MOCK_LOGS.get(7),
                MOCK_LOGS.get(0),
                MOCK_LOGS.get(8),
                MOCK_LOGS.get(1),
                MOCK_LOGS.get(2)
            )
        );
    }
    @Test
    void getMostRequestedIpsTest() {
        LogAnalyze logAnalyze = new LogAnalyze(MOCK_LOGS, "", "");
        Map<String, Long> result = logAnalyze.getMostRequestedIps();

        assertThat(result).isEqualTo(
            Map.of(
                "5.9.121.211", 6L,
                "10.0.2.236", 3L,
                "10.0.2.231", 2L
            )
        );
    }
}

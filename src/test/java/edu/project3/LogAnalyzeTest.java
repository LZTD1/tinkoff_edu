package edu.project3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import edu.project3.src.LogAnalyze;
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
                "averageResponseSize", "216b"
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
                "averageResponseSize", "340b"
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
                "averageResponseSize", "145b"
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
}

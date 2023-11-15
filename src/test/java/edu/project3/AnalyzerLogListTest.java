package edu.project3;

import edu.project3.Model.LogReport;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static edu.project3.FileParserTest.getDateFromString;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalyzerLogListTest {
    @Test
    void getGeneralStatisticTest() {
        AnalyzerLogList logAnalyze = new AnalyzerLogList(MOCK_LOGS);
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
    void getRequestedResourcesTest() {
        AnalyzerLogList logAnalyze = new AnalyzerLogList(MOCK_LOGS);
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
        AnalyzerLogList logAnalyze = new AnalyzerLogList(MOCK_LOGS);
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

    private final List<LogReport> MOCK_LOGS =
        List.of(
            new LogReport(
                "5.9.121.211",
                getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_1",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                getDateFromString("25/May/2015:11:05:27 +0000"),
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
                "5.9.121.211",
                getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_3",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_3",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_4",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_4",
                    "HTTP/1.1"
                ),
                505,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_4",
                    "HTTP/1.1"
                ),
                505,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "10.0.2.236",
                getDateFromString("28/May/2015:10:05:49 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_5",
                    "HTTP/1.1"
                ),
                304,
                0,
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            ),
            new LogReport(
                "10.0.2.236",
                getDateFromString("28/May/2015:10:05:49 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_5",
                    "HTTP/1.1"
                ),
                304,
                0,
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            ),
            new LogReport(
                "10.0.2.236",
                getDateFromString("28/May/2015:10:05:49 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_5",
                    "HTTP/1.1"
                ),
                200,
                0,
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            ),
            new LogReport(
                "10.0.2.236",
                getDateFromString("28/May/2015:10:05:49 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_5",
                    "HTTP/1.1"
                ),
                304,
                0,
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            )
        );
}

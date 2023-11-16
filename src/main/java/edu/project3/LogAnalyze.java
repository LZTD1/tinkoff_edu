package edu.project3;

import edu.project3.Model.AnalyticsModel;
import edu.project3.Model.LogReport;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyze {

    private final List<LogReport> logs;

    public LogAnalyze(List<LogReport> logs) {
        this.logs = logs;
    }

    public AnalyticsModel getAllStatistics() {
        return new AnalyticsModel(
            getGeneralStatistic(),
            getRequestedResources(),
            getRequestedCode()
        );
    }

    public HashMap<String, String> getGeneralStatistic() {
        HashMap<String, String> result = new HashMap<>();

        result.put("dateStart", dateToString(this.logs.get(0).timeLog()));
        result.put("endDate", dateToString(this.logs.get(this.logs.size() - 1).timeLog()));
        result.put("countRequests", String.valueOf(this.logs.size()));
        result.put("averageResponseSize", getAverageSizeResponse() + "b");

        return result;
    }

    public LinkedHashMap<String, Long> getRequestedResources() {
        Map<String, Long> unsorted = this.logs.stream()
            .collect(
                Collectors.groupingBy(
                    entry -> entry.request().resource(),
                    Collectors.counting()
                )
            );

        return sortMapByValues(unsorted);
    }

    public LinkedHashMap<String, Long> getRequestedCode() {
        Map<String, Long> unsorted = this.logs.stream()
            .collect(
                Collectors.groupingBy(
                    logReport -> String.valueOf(logReport.statusCode()),
                    Collectors.counting()
                )
            );

        return sortMapByValues(unsorted);
    }

    private String getAverageSizeResponse() {
        return String.valueOf(
            (int) this.logs.stream().mapToInt(LogReport::bytesSend)
                .summaryStatistics()
                .getAverage()
        );
    }

    private String dateToString(Date date) {
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    private LinkedHashMap<String, Long> sortMapByValues(Map<String, Long> unsorted) {
        LinkedHashMap<String, Long> sortedMap = new LinkedHashMap<>();

        unsorted.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

        return sortedMap;
    }
}

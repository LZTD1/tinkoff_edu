package edu.project3.src;

import edu.project3.src.Model.AnalyticsModel;
import edu.project3.src.Model.LogReport;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static edu.project3.src.Utils.convertStringToDate;

public class LogAnalyze {

    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private List<LogReport> logs;

    public LogAnalyze(List<LogReport> logs, String from, String to) {
        this.logs = logs;
        this.from = !from.isEmpty() ? convertStringToDate(from) : null;
        this.to = !to.isEmpty() ? convertStringToDate(to) : null;

        getLogsReportByDates();
    }

    public AnalyticsModel getAllStatistics(List<String> fileNames) {
        return new AnalyticsModel(
            fileNames,
            getGeneralStatistic(),
            getRequestedResources(),
            getRequestedCode()
        );
    }

    public HashMap<String, String> getGeneralStatistic() {
        HashMap<String, String> result = new HashMap<>();

        result.put("dateStart", getStartDate());
        result.put("endDate", getEndDate());

        result.put("countRequests", String.valueOf(this.logs.size()));
        result.put("averageResponseSize", getAverageSizeResponse() + "b");

        return result;
    }

    private void getLogsReportByDates() {
        this.logs = this.logs.stream()
            .filter(entry -> entry.timeLog() != null
                    && (
                    this.from == null
                        || entry.timeLog().isEqual(this.from)
                        || entry.timeLog().isAfter(this.from)
                )
                    && (
                    this.to == null
                        || entry.timeLog().isEqual(this.to)
                        || entry.timeLog().isBefore(this.to)
                )
            )
            .toList();
    }

    private String getStartDate() {
        Optional<LogReport> minObject = this.logs.stream()
            .min(Comparator.comparing(LogReport::timeLog));

        return minObject.map(logReport -> dateToString(logReport.timeLog()))
            .orElse("-");
    }

    private String getEndDate() {
        Optional<LogReport> maxObject = this.logs.stream()
            .max(Comparator.comparing(LogReport::timeLog));

        return maxObject.map(logReport -> dateToString(logReport.timeLog()))
            .orElse("-");
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

    private String dateToString(OffsetDateTime date) {
        String pattern = "dd.MM.yyyy";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return date.format(formatter);
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

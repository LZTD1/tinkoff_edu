package edu.project3.src.Model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record AnalyticsModel(
    List<String> fileNames,
    HashMap<String, String> generalStatistic,
    LinkedHashMap<String, Long> requestedResources,
    LinkedHashMap<String, Long> requestedCode,
    List<LogReport> mostFiveHugeRequest,
    Map<String, Long> mostRequestedIps
) {
}

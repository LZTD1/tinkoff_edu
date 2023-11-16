package edu.project3.Model;

import java.util.HashMap;
import java.util.LinkedHashMap;

public record AnalyticsModel(
    HashMap<String, String> generalStatistic,
    LinkedHashMap<String, Long> requestedResources,
    LinkedHashMap<String, Long> requestedCode
) {
}

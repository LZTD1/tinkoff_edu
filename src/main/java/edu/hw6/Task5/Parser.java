package edu.hw6.Task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private Parser() {
    }

    public static List<Long> parseAllNews(String news) {
        Pattern newsPattern = Pattern.compile("(\\d+)");
        Matcher matcher = newsPattern.matcher(news);
        List<Long> allNews = new ArrayList<>();

        while (matcher.find()) {
            allNews.add(Long.parseLong(matcher.group()));
        }

        return allNews;
    }

    public static Optional<String> getNewsTitle(String news) {
        Pattern newsPattern = Pattern.compile("(?>\\\"title\\\":\\\")(.+)(?=\\\",\\\"type\\\")");
        var matcher = newsPattern.matcher(news);

        if (matcher.find()) {
            return Optional.of(matcher.group(1));
        } else {
            return Optional.empty();
        }
    }
}

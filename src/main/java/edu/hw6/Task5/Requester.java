package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import static edu.hw6.Task5.Parser.getNewsTitle;
import static edu.hw6.Task5.Parser.parseAllNews;
import static java.net.http.HttpClient.newHttpClient;

public class Requester {

    private static final int REQUEST_TIMEOUT = 10;

    public Requester() {
    }

    private HttpRequest requestBuild(String url) throws URISyntaxException {
        return HttpRequest.newBuilder()
            .uri(new URI(url))
            .GET()
            .header("AcceptEncoding", "gzip")
            .timeout(Duration.of(REQUEST_TIMEOUT, ChronoUnit.SECONDS))
            .build();
    }

    private String requestSend(HttpRequest request) throws IOException, InterruptedException {
        return newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private String combineRequest(String url) throws URISyntaxException, IOException, InterruptedException {
        var request = requestBuild(url);
        return requestSend(request);
    }

    public List<Long> hackerNewsTopStories() {
        try {
            String newsString = combineRequest("https://hacker-news.firebaseio.com/v0/topstories.json");
            return parseAllNews(newsString);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return List.of();
        }

    }

    public Optional<String> news(String id) {
        try {
            String newsString = combineRequest("https://hacker-news.firebaseio.com/v0/item/" + id + ".json");
            return getNewsTitle(newsString);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return Optional.empty();
        }
    }
}

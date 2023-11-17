package edu.project3.src.Parsers;

import edu.project3.src.Model.LogReport;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class WebsiteParser extends Parser {

    private static final long REQUEST_TIMEOUT = 10;
    private final Pattern patternNewLine = Pattern.compile("(.+)\\n?");
    private final String siteUrl;
    private ArrayList<LogReport> processedLogs;

    public WebsiteParser(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @Override
    public List<LogReport> parseLogs() {
        this.processedLogs = new ArrayList<>();
        try {
            String rawLogs = combineRequest(this.siteUrl);

            Matcher matcher = patternNewLine.matcher(rawLogs);
            while (matcher.find()) {
                LogReport log = parseLine(matcher.group(1));
                this.processedLogs.add(log);
            }

            return this.processedLogs;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return List.of();
        }
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
        return newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString())
            .body();
    }

    private String combineRequest(String url) throws URISyntaxException, IOException, InterruptedException {
        var request = requestBuild(url);
        return requestSend(request);
    }
}

package edu.project3;

import edu.project3.Exceptions.BadLogLineException;
import edu.project3.Exceptions.FileNotExists;
import edu.project3.Model.LogReport;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileParser {

    @SuppressWarnings("LineLength")
    private final static String PATTERN_FOR_LOG =
        "^((\\d+\\.?)+).+(?<=\\[)(.+?)(?=\\]).+\\\"(GET|POST|HEAD)\\s(.+?)\\s(.+?)\\\"\\s(\\d+)\\s(\\d+).+\\\"(.+)(?=\\\")";
    // https://regex101.com/r/nxlDtM/1
    private final static int VALID_GROUP_COUNT = 9;
    private final static int GROUP_IP = 1;
    private final static int GROUP_TIME = 3;
    private final static int GROUP_REQUEST_METHOD = 4;
    private final static int GROUP_REQUEST_RESOURCE = 5;
    private final static int GROUP_REQUEST_PROTOCOL = 6;
    private final static int GROUP_STATUS = 7;
    private final static int GROUP_COUNT_BYTES = 8;
    private final static int GROUP_USER_AGENT = 9;
    private final Path filePath;
    private final File file;
    private List<LogReport> processedLogs;

    public FileParser(Path filePath) {
        if (filePath.toFile().exists()) {
            this.filePath = filePath;
            this.file = filePath.toFile();
        } else {
            throw new FileNotExists("The transferred file does not exist!");
        }
    }

    private static LogReport parseLine(String line) {
        Pattern patternLog = Pattern.compile(PATTERN_FOR_LOG);
        Matcher matcher = patternLog.matcher(line);

        if (matcher.find() && matcher.groupCount() == VALID_GROUP_COUNT) {
            return new LogReport(
                matcher.group(GROUP_IP),
                getDateFromString(matcher.group(GROUP_TIME)),
                new LogReport.RequestModel(
                    matcher.group(GROUP_REQUEST_METHOD).equals("GET")
                        ? LogReport.RequestModel.HttpMethod.GET : LogReport.RequestModel.HttpMethod.POST,
                    matcher.group(GROUP_REQUEST_RESOURCE),
                    matcher.group(GROUP_REQUEST_PROTOCOL)
                ),
                Integer.parseInt(matcher.group(GROUP_STATUS)),
                Integer.parseInt(matcher.group(GROUP_COUNT_BYTES)),
                matcher.group(GROUP_USER_AGENT)
            );
        } else {
            throw new BadLogLineException(line + " cant parse this line");
        }
    }

    private static Date getDateFromString(String group) {
        String pattern = "dd/MMM/yyyy:HH:mm:ss Z";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date;
        try {
            date = dateFormat.parse(group);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    public List<LogReport> parseLogs() {
        this.processedLogs = new ArrayList<>();
        try (LineIterator it = FileUtils.lineIterator(this.file, "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                LogReport log = parseLine(line);
                this.processedLogs.add(log);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.processedLogs;
    }
}

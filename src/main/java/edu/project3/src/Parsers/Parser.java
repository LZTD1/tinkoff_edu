package edu.project3.src.Parsers;

import edu.project3.src.Exceptions.BadLogLineException;
import edu.project3.src.Model.LogReport;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.src.Utils.getDateFromString;

public abstract class Parser {

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

    public abstract List<LogReport> parseLogs();

    protected LogReport parseLine(String line) {
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
}

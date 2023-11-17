package edu.project3.src;

import edu.project3.src.Exceptions.BadInputArgument;
import edu.project3.src.Model.AnalyticsModel;
import edu.project3.src.Model.LogReport;
import edu.project3.src.Parsers.FileParser;
import edu.project3.src.Parsers.Parser;
import edu.project3.src.Parsers.WebsiteParser;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Manager {

    private final String[] args;
    private final static String PATH_KEY = "--path";

    public Manager(String[] args) {
        this.args = args;
    }

    private static LogStatsSaver.ExtensionFile getExtensionByString(String ext) {
        return ext.equals("markdown")
            ? LogStatsSaver.ExtensionFile.markdown : LogStatsSaver.ExtensionFile.adoc;
    }

    private static List<List<?>> directorySearcher(String path) {
        DirectorySearcher dirSearcher = new DirectorySearcher(path);
        List<Path> pathToFiles = dirSearcher.getFilesFromPath();

        List<String> fileNames = new ArrayList<>();
        List<LogReport> allReports = new ArrayList<>();
        for (Path currentPath : pathToFiles) {
            fileNames.add(currentPath.getFileName().toString());
            Parser fileParser = new FileParser(currentPath);
            List<LogReport> logs = fileParser.parseLogs();
            allReports.addAll(logs);
        }

        return List.of(
            fileNames,
            allReports
        );
    }

    private static void putInMap(String[] args, int argsCount, HashMap<String, String> mapArgs) {
        try {
            for (int i = 0; i < argsCount; i += 2) {
                putLine(i, mapArgs, args);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BadInputArgument("One of the entered parameters does not value!");
        }

    }

    private static void putLine(int i, HashMap<String, String> mapArgs, String[] tempArgs) {
        mapArgs.put(tempArgs[i], tempArgs[i + 1]);
    }

    public File getStats() {
        int argsCount = (int) Arrays.stream(args).count();
        HashMap<String, String> mapArgs = new HashMap<>();
        putInMap(args, argsCount, mapArgs);

        if (!mapArgs.containsKey(PATH_KEY)) {
            throw new BadInputArgument("Argument --path is required!");
        }

        List<LogReport> allReports;
        List<String> fileNames;

        if (!mapArgs.get(PATH_KEY).contains("http")) {
            var result = directorySearcher(mapArgs.get(PATH_KEY));

            fileNames = (List<String>) result.get(0);
            allReports = (List<LogReport>) result.get(1);
        } else {
            var parser = new WebsiteParser(mapArgs.get(PATH_KEY));

            fileNames = List.of(mapArgs.get(PATH_KEY));
            allReports = parser.parseLogs();
        }

        LogAnalyze logAnalyze = new LogAnalyze(
            allReports,
            mapArgs.getOrDefault("--from", ""),
            mapArgs.getOrDefault("--to", "")
        );

        AnalyticsModel statistics = logAnalyze.getAllStatistics(fileNames);
        LogStatsSaver logsSaver =
            new LogStatsSaver(statistics, getExtensionByString(mapArgs.getOrDefault("--format", "")));
        File ouputedFile = logsSaver.saveIntoFile();

        return ouputedFile;
    }
}

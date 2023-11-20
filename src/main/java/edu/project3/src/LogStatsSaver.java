package edu.project3.src;

import edu.project3.src.Exceptions.ErrorCreatingFile;
import edu.project3.src.Model.AnalyticsModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LogStatsSaver {

    private final static int LENGTH_FILENAME = 10;
    private final static String DELIMITER = "|";
    private final Path filePath = null;
    private final AnalyticsModel model;
    private final ExtensionFile fileExtension;

    public LogStatsSaver(AnalyticsModel model, ExtensionFile ext) {
        this.model = model;
        this.fileExtension = ext;
    }

    public File saveIntoFile() {
        File myFile = constructFile();
        List<String> dataToSaveInFile = getLinesToSaveInFile();
        writeInFile(dataToSaveInFile, myFile);

        return myFile;
    }

    private void writeInFile(List<String> dataToSaveInFile, File myFile) {
        try (FileWriter writer = new FileWriter(myFile)) {

            StringBuilder sb = new StringBuilder();
            for (var entry : dataToSaveInFile) {
                sb.append(entry).append(System.lineSeparator());
            }
            String toInserted = sb.toString();
            writer.write(toInserted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getLinesToSaveInFile() {
        List<String> dataToSaveInFile;
        if (this.fileExtension.equals(ExtensionFile.markdown)) {
            dataToSaveInFile = writeLogsInFileMarkdown();
        } else {
            dataToSaveInFile = writeLogsInFileAdoc();
        }
        return dataToSaveInFile;
    }

    @SuppressWarnings("MultipleStringLiterals")
    private List<String> writeLogsInFileAdoc() {
        List<String> inFile = new ArrayList<>();

        inFile.add("Общая информация");
        inFile.add("[options=\"header\"]");
        inFile.add("|=======================");
        inFile.add("|Метрика|Значение");
        inFile.add("|Файл(-ы)|" + getListNamesFromModel());
        inFile.add("|Начальная дата|" + this.model.generalStatistic().get("dateStart"));
        inFile.add("|Конечная дата|" + this.model.generalStatistic().get("endDate"));
        inFile.add("|Количество запросов|" + this.model.generalStatistic().get("countRequests"));
        inFile.add("|Средний размер ответа|" + this.model.generalStatistic().get("averageResponseSize"));
        inFile.add("|=======================");

        inFile.add("Запрашиваемые ресурсы");
        inFile.add("[options=\"header\"]");
        inFile.add("|=======================");
        inFile.add("|Ресурс|Количество");
        for (var entry : this.model.requestedResources().entrySet()) {
            inFile.add(DELIMITER + entry.getKey()
                + DELIMITER + entry.getValue());
        }
        inFile.add("|=======================");

        inFile.add("Коды ответа");
        inFile.add("[options=\"header\"]");
        inFile.add("|=======================");
        inFile.add("|Код|Имя|Количество");
        for (var entry : this.model.requestedCode().entrySet()) {
            inFile.add(DELIMITER + entry.getKey()
                + DELIMITER + getNameByCode(entry.getKey())
                + DELIMITER + entry.getValue());
        }
        inFile.add("|=======================");

        inFile.add("Топ 5 больших запросов");
        inFile.add("[options=\"header\"]");
        inFile.add("|=======================");
        inFile.add("|Реквест|Размер");
        for (var entry : this.model.mostFiveHugeRequest()) {
            inFile.add(DELIMITER + entry.toString()
                + DELIMITER + entry.bytesSend());
        }
        inFile.add("|=======================");

        inFile.add("Запрашиваемые адреса");
        inFile.add("[options=\"header\"]");
        inFile.add("|=======================");
        inFile.add("|Адрес|Количество");
        for (var entry : this.model.mostRequestedIps().entrySet()) {
            inFile.add(DELIMITER + entry.getKey()
                + DELIMITER + entry.getValue());
        }
        inFile.add("|=======================");

        return inFile;
    }

    @SuppressWarnings("MultipleStringLiterals")
    private List<String> writeLogsInFileMarkdown() {
        List<String> inFile = new ArrayList<>();

        inFile.add("#### Общая информация");
        inFile.add("|Метрика|Значение|");
        inFile.add("| ------------ | ------------ |");
        inFile.add("|Файл(-ы)|" + getListNamesFromModel() + "|");
        inFile.add("|Начальная дата|" + this.model.generalStatistic().get("dateStart") + DELIMITER);
        inFile.add("|Конечная дата|" + this.model.generalStatistic().get("endDate") + DELIMITER);
        inFile.add("|Количество запросов|" + this.model.generalStatistic().get("countRequests") + DELIMITER);
        inFile.add("|Средний размер ответа|" + this.model.generalStatistic().get("averageResponseSize") + DELIMITER);

        inFile.add("#### Запрашиваемые ресурсы");
        inFile.add("|Ресурс|Количество|");
        inFile.add("| ------------ | ------------ |");
        for (var entry : this.model.requestedResources().entrySet()) {
            inFile.add(DELIMITER + entry.getKey()
                + DELIMITER + entry.getValue()
                + DELIMITER);
        }

        inFile.add("#### Коды ответа");
        inFile.add("|Код|Имя|Количество|");
        inFile.add("| ------------ | ------------ | ------------ |");
        for (var entry : this.model.requestedCode().entrySet()) {
            inFile.add(DELIMITER + entry.getKey()
                + DELIMITER + getNameByCode(entry.getKey())
                + DELIMITER + entry.getValue()
                + DELIMITER);
        }

        inFile.add("#### Топ 5 больших запросов");
        inFile.add("|Реквест|Размер|");
        inFile.add("| ------------ | ------------ |");
        for (var entry : this.model.mostFiveHugeRequest()) {
            inFile.add(DELIMITER + entry.toString()
                + DELIMITER + entry.bytesSend() + DELIMITER);
        }

        inFile.add("#### Запрашиваемые адреса");
        inFile.add("|Адрес|Количество|");
        inFile.add("| ------------ | ------------ |");
        for (var entry : this.model.mostRequestedIps().entrySet()) {
            inFile.add(DELIMITER + entry.getKey()
                + DELIMITER + entry.getValue() + DELIMITER);
        }

        return inFile;
    }

    private String getListNamesFromModel() {
        return String.join(",", this.model.fileNames());
    }

    @SuppressWarnings("MagicNumber")
    private String getNameByCode(String code) {
        return switch (Integer.parseInt(code)) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 304 -> "Not Modified";
            case 206 -> "Partial Content";
            case 403 -> "Forbidden";
            case 416 -> "Requested Range Not Satisfiable";
            case 505 -> "HTTP Version Not Supported";
            default -> "Unknown";
        };
    }

    private File constructFile() {
        String ext = this.fileExtension.equals(ExtensionFile.markdown) ? "md" : "adoc";
        String newFilePath = getRandomNameFile() + "." + ext;

        File file = new File(newFilePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new ErrorCreatingFile("Ошибка при создании файла: " + e.getMessage());
        }
        return file;
    }

    @SuppressWarnings("MagicNumber")
    private String getRandomNameFile() {

        return new Random().ints(LENGTH_FILENAME, 48, 123)
            .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
            .mapToObj(c -> String.valueOf((char) c))
            .collect(Collectors.joining());
    }

    public enum ExtensionFile {
        markdown,
        adoc
    }
}

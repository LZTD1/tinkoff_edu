package edu.project3;

import edu.project3.src.Model.LogReport;
import edu.project3.src.Utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TestUtils {

    private TestUtils(){
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        } else {
            return "";
        }
    }

    public static String readFileContent(Path filePath) {
        try {
            byte[] fileBytes = Files.readAllBytes(filePath);
            return new String(fileBytes);
        } catch (IOException e) {
            return null;
        }
    }

    public static final List<LogReport> MOCK_LOGS =
        List.of(
            new LogReport(
                "5.9.121.211",
                Utils.getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_1",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                Utils.getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_2",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                Utils.getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_3",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                Utils.getDateFromString("25/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_3",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                Utils.getDateFromString("26/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_4",
                    "HTTP/1.1"
                ),
                404,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                Utils.getDateFromString("26/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_4",
                    "HTTP/1.1"
                ),
                505,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "5.9.121.211",
                Utils.getDateFromString("27/May/2015:11:05:27 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_4",
                    "HTTP/1.1"
                ),
                505,
                340,
                "Debian APT-HTTP/1.3 (0.8.10.3)"
            ),
            new LogReport(
                "10.0.2.236",
                Utils.getDateFromString("27/May/2015:10:05:49 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_5",
                    "HTTP/1.1"
                ),
                304,
                0,
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            ),
            new LogReport(
                "10.0.2.236",
                Utils.getDateFromString("27/May/2015:10:05:49 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_5",
                    "HTTP/1.1"
                ),
                304,
                0,
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            ),
            new LogReport(
                "10.0.2.236",
                Utils.getDateFromString("28/May/2015:10:05:49 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_5",
                    "HTTP/1.1"
                ),
                200,
                0,
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            ),
            new LogReport(
                "10.0.2.236",
                Utils.getDateFromString("28/May/2015:10:05:49 +0000"),
                new LogReport.RequestModel(
                    LogReport.RequestModel.HttpMethod.GET,
                    "/downloads/product_5",
                    "HTTP/1.1"
                ),
                304,
                0,
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            )
        );
}

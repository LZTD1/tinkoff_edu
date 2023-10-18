package edu.project1.Utils;

import edu.project1.Exceptions.ReadFileWrongException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetTextFromFile {
    private GetTextFromFile() {
    }

    public static String getTextFromFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder myString = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                myString.append(line);
                line = reader.readLine();
            }
            reader.close();

            return myString.toString();
        } catch (IOException e) {
            throw new ReadFileWrongException(e.getMessage());
        }
    }
}

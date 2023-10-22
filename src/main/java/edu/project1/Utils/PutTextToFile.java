package edu.project1.Utils;

import edu.project1.Exceptions.PutFileWrongException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PutTextToFile {
    private PutTextToFile() {
    }

    public static void putTextToFile(String json, String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                throw new PutFileWrongException("Wrong path address! " + path);
            }

            FileOutputStream fileOut = new FileOutputStream(path);
            fileOut.write(json.getBytes());
            fileOut.close();
        } catch (IOException e) {
            throw new PutFileWrongException(e.getMessage());
        }
    }
}

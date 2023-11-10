package edu.hw6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class Task2 {

    private Task2() {
    }

    public static Path cloneFile(Path path) {
        String fileFullName = path.getFileName().toString();
        int dotIndex = fileFullName.indexOf(".");
        String extension = fileFullName.substring(dotIndex);
        String fileName = fileFullName.substring(0, dotIndex);

        int count = 0;
        String prefiks;
        File newFileName;

        do  {
            count += 1;
            prefiks = " - копия (" + count + ")";
            newFileName = new File(path.getParent().toString(), fileName + prefiks + extension);
        }while(newFileName.exists());

        try {
            copyFileUsingNIO(path.toFile(), newFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newFileName.toPath();
    }

    private static void copyFileUsingNIO(File sourceFile, File destinationFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(sourceFile);
        FileOutputStream outputStream = new FileOutputStream(destinationFile);
        try (
            inputStream;
            outputStream;
            FileChannel inChannel = inputStream.getChannel();
            FileChannel outChannel = outputStream.getChannel()
        ) {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        }
    }
}

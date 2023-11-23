package edu.hw8.Task3;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

    private final Path filePath;

    public ReadFromFile(Path filePath){
        if(filePath.toFile().exists()){
            this.filePath = filePath;
        }else{
            throw new NoSuchFile("No such file by path!");
        }
    }
    public List<String> getFileContent(){
        String line;
        List<String> lines = new ArrayList<>();
        try(BufferedReader buffer = new BufferedReader(new FileReader(this.filePath.toFile()))){
            while((line = buffer.readLine()) != null){
                lines.add(line.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}

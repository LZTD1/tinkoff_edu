package edu.hw6.Task1;

import edu.hw6.Task1.Exceptions.ErrorCreatingFile;
import edu.hw6.Task1.Exceptions.ErrorLoadMapFromFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final static int LENGTH_FILENAME = 10;
    private HashMap<String, String> hashMap = new HashMap<>();
    private String pathFile;

    public DiskMap() {
        try {
            constructFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DiskMap(Path path) {
        diskMapFromPath(path);
    }

    private void diskMapFromPath(Path path) {
        var myMap = path.toFile();
        if (myMap.exists()) {
            this.pathFile = path.toString();
            parseMapFromFile();
        } else {
            throw new ErrorLoadMapFromFile("File is not exist!");
        }
    }

    private void parseMapFromFile() {
        String line;
        try (BufferedReader bufferreader = new BufferedReader(new FileReader(this.pathFile))) {
            while ((line = bufferreader.readLine()) != null) {
                String[] data = line.split(":", 2);
                this.hashMap.put(data[0], data[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("MagicNumber")
    private String getRandomNameFile() {

        return new Random().ints(LENGTH_FILENAME, 48, 123)
            .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
            .mapToObj(c -> String.valueOf((char) c))
            .collect(Collectors.joining());
    }

    private void constructFile() {
        this.pathFile = getRandomNameFile() + ".txt";

        File file = new File(pathFile);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new ErrorCreatingFile("Ошибка при создании файла: " + e.getMessage());
        }
    }

    private void updateMap() {
        try (FileWriter writer = new FileWriter(this.pathFile)) {

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : this.hashMap.entrySet()) {
                sb.append(entry.getKey()).append(":").append(entry.getValue()).append(System.lineSeparator());
            }
            String toInserted = sb.toString();
            writer.write(toInserted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return this.hashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.hashMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.hashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.hashMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return this.hashMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        var result = this.hashMap.put(key, value);
        updateMap();
        return result;
    }

    @Override
    public String remove(Object key) {
        var result = this.hashMap.remove(key);
        updateMap();
        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        this.hashMap.putAll(m);
        updateMap();
    }

    @Override
    public void clear() {
        this.hashMap.clear();
        updateMap();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return this.hashMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return this.hashMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return this.hashMap.entrySet();
    }

    public String getPathFile() {
        return pathFile;
    }

    public void destructor() {
        this.hashMap = null;
        File myObj = new File(this.pathFile);
        if (!myObj.delete()) {
            throw new RuntimeException("Cant delete file!");
        }
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }
}

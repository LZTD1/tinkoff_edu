package edu.hw8.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import static edu.hw8.Task3.Utils.convertListToMap;

public class Manager {

    private final int threadsCount;
    private final Path path;
    private final static Logger LOGGER = LogManager.getLogger();
    private final ExecutorService executor;
    private final ArrayBlockingQueue<String> queue;
    private final Map<String, String> findedPasswords;
    private String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    private int maxLen = 5;

    public Manager(Path databasePath, int threadCount) {
        this.queue = new ArrayBlockingQueue<>(threadCount);
        this.threadsCount = threadCount;
        this.path = databasePath;
        this.findedPasswords = new HashMap<>();
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public void setAlphabetic(String alphabet) {
        this.alphabet = alphabet;
    }

    public void setMaxLength(int maxLen) {
        this.maxLen = maxLen;
    }

    public Map<String, String> startBruteforce() {
        var content = fileInteraction();
        var dataMap = convertListToMap(content);
        AtomicBoolean flag = new AtomicBoolean(true);

        var bruteForcer = new Bruteforce(alphabet, maxLen, queue, 5);
        var hashGenerator = new HashGenerator(queue);

        CompletableFuture.runAsync(bruteForcer::bruteForce);

        while (flag.get()){
            CompletableFuture.supplyAsync(hashGenerator::generateHash, executor)
                .thenAccept(result -> {
                    if(dataMap.containsKey(result.hash())) {
                        this.findedPasswords.put(result.password(), dataMap.get(result.hash()));
                        LOGGER.info("FIND | " + result);
                    }
                })
                .thenAccept(result -> {
                    if(this.findedPasswords.size() == dataMap.size()){
                        flag.set(false);
                    }
                });
        }

        return findedPasswords;
    }

    private List<String> fileInteraction() {
        ReadFromFile reader = new ReadFromFile(path);
        return reader.getFileContent();
    }
}

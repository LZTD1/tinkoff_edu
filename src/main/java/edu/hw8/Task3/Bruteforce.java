package edu.hw8.Task3;

import java.util.concurrent.BlockingQueue;

public class Bruteforce {

    private final int maxLength;
    private final BlockingQueue<String> queue;
    private final char[] alphabet;
    private final int threadsCount;

    public Bruteforce(String alphabet, int maxLength, BlockingQueue<String> queue, int threads) {
        this.alphabet = alphabet.toCharArray();
        this.threadsCount = threads;
        this.queue = queue;
        this.maxLength = maxLength;
    }

    public void bruteForce() {
        for (int i = 2; i <= maxLength; i++) {
            bruteForceByLength("", i);
        }
    }

    private void bruteForceByLength(String current, int len) {
        try {
            if (len == current.length()) {
                this.queue.put(current);
            } else {
                for (char c : alphabet) {
                    bruteForceByLength(current + c, len);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public BlockingQueue<String> getQueue() {
        return this.queue;
    }
}

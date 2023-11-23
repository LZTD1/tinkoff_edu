package edu.hw8.Task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

public class MyNewFixedThreadPool implements ThreadPool {

    private final BlockingQueue<Runnable> blockingQueue;
    private final Stream<Thread> threads;

    public MyNewFixedThreadPool(int countThreads) {
        this.blockingQueue = new ArrayBlockingQueue<>(countThreads);

        this.threads = Stream.generate(() -> new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = blockingQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        })).limit(countThreads);

    }

    @Override
    public void start() {
        this.threads.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            this.blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        this.threads.forEach(Thread::interrupt);
    }
}

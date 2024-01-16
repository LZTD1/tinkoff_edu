package edu.hw9.Task1;

import edu.hw9.Task1.Entities.MetricData;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Producers {

    public final static int MAX_LENGTH_DATA = 20;
    public final static int MIN_LENGTH_DATA = 5;
    public final static double MAX_DATA_VALUE = 200.;
    private final BlockingQueue<MetricData> queqe;

    public Producers(BlockingQueue<MetricData> queue) {
        this.queqe = queue;
    }

    public MetricData generateAndPushInQueue() {
        var data = generateData();

        try {
            this.queqe.put(data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public List<MetricData> generateAndPushInQueue(int count) {
        List<MetricData> data = generateData(count);

        data.forEach((item) -> {
            try {
                this.queqe.put(item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return data;
    }

    public MetricData generateData() {
        return new MetricData(
            getRandomDataName(),
            getRandomDataValues()
        );
    }

    public List<MetricData> generateData(int count) {
        return Stream.generate(() -> new MetricData(
                getRandomDataName(),
                getRandomDataValues()
            ))
            .limit(count)
            .toList();
    }

    private String getRandomDataName() {
        return UUID.randomUUID().toString();
    }

    private double[] getRandomDataValues() {
        return DoubleStream.generate(
                () -> ThreadLocalRandom.current().nextDouble(MAX_DATA_VALUE)
            )
            .limit(ThreadLocalRandom.current().nextInt(MIN_LENGTH_DATA, MAX_LENGTH_DATA))
            .toArray();
    }
}

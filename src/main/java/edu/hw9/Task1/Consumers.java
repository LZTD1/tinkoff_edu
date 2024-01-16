package edu.hw9.Task1;

import edu.hw9.Task1.Entities.AnalyzeData;
import edu.hw9.Task1.Entities.MetricData;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class Consumers {

    private final BlockingQueue<MetricData> queqe;

    public Consumers(BlockingQueue<MetricData> queue) {
        this.queqe = queue;
    }

    public AnalyzeData analyzeData() {
        MetricData item;
        try {
            item = this.queqe.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new AnalyzeData(
            item.name(),
            getMaxValue(item.data()),
            getMinValue(item.data()),
            getAverageValue(item.data()),
            getSumValue(item.data())
        );
    }

    private double getMaxValue(double[] data) {
        return Arrays.stream(data).summaryStatistics().getMax();
    }

    private double getAverageValue(double[] data) {
        return Arrays.stream(data).summaryStatistics().getAverage();
    }

    private double getMinValue(double[] data) {
        return Arrays.stream(data).summaryStatistics().getMin();
    }

    private double getSumValue(double[] data) {
        return Arrays.stream(data).summaryStatistics().getSum();
    }
}

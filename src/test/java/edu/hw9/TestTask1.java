package edu.hw9;

import edu.hw9.Task1.Consumers;
import edu.hw9.Task1.Entities.AnalyzeData;
import edu.hw9.Task1.Entities.MetricData;
import edu.hw9.Task1.Producers;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static edu.hw9.Task1.Producers.MAX_DATA_VALUE;
import static edu.hw9.Task1.Producers.MAX_LENGTH_DATA;
import static edu.hw9.Task1.Producers.MIN_LENGTH_DATA;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {
    @Nested
    class TestProducers {
        @Test
        void testGenerateSingleData() {
            var queue = new ArrayBlockingQueue<MetricData>(1);
            var prod = new Producers(queue);

            MetricData result = prod.generateData();

            assertThat(result).isNotNull();

            assertThat(result.name()).isNotNull();
            assertThat(result.name()).isNotEmpty();

            assertThat(result.data()).isNotNull();
            assertThat(result.data()).isNotEmpty();
            assertThat(result.data().length).isBetween(MIN_LENGTH_DATA, MAX_LENGTH_DATA);
        }

        @Test
        void testGenerateManyData() {
            var queue = new ArrayBlockingQueue<MetricData>(1);
            var prod = new Producers(queue);

            List<MetricData> result = prod.generateData(5);

            assertThat(result.size()).isEqualTo(5);
        }

        @Test
        void testWorkWithQueue() throws InterruptedException {
            var queue = new ArrayBlockingQueue<MetricData>(1);
            var prod = new Producers(queue);

            var result = prod.generateAndPushInQueue();

            assertThat(queue.size()).isEqualTo(1);
            assertThat(result).isEqualTo(queue.take());
        }

        @Test
        void testWorkWithQueueManyGenerations() throws InterruptedException {
            var queue = new ArrayBlockingQueue<MetricData>(5);
            var prod = new Producers(queue);

            List<MetricData> result = prod.generateAndPushInQueue(5);

            assertThat(queue.size()).isEqualTo(5);
            assertThat(result.get(0)).isEqualTo(queue.take());
            assertThat(result.get(1)).isEqualTo(queue.take());
        }
    }

    @Nested
    class TestConsumers {
        @Test
        void testConsumer() throws InterruptedException {
            AnalyzeData mock = new AnalyzeData(
                "mockData",
                2.0,
                0.0,
                1.0,
                3.0
            );

            var queue = new ArrayBlockingQueue<MetricData>(1);
            queue.put(new MetricData(
                "mockData", new double[] {0.0, 1.0, 2.0}
            ));

            var consumer = new Consumers(queue);

            AnalyzeData result = consumer.analyzeData();
            assertThat(result).isNotNull();
            assertThat(result).isEqualTo(mock);
        }
    }

    @Nested
    class TestAll {

        private final static Logger LOGGER = LogManager.getLogger();

        @Test
        void testAll() throws ExecutionException, InterruptedException {
            var queue = new ArrayBlockingQueue<MetricData>(10);

            var consumer = new Consumers(queue);
            var producers = new Producers(queue);

            var tasksProducers = Stream.generate(
                    () -> CompletableFuture.runAsync(
                        producers::generateAndPushInQueue,
                        Executors.newFixedThreadPool(10) // Для каждого свой пул
                        // иначе только producers забивают пул и consumers некуда встать
                    )
                )
                .limit(100)
                .toArray(CompletableFuture[]::new);

            var tasksConsumers = Stream.generate(
                    () -> CompletableFuture.supplyAsync(
                        consumer::analyzeData,
                        Executors.newFixedThreadPool(10)
                    )
                )
                .limit(100)
                .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(tasksProducers).join();
            CompletableFuture.allOf(tasksConsumers).join();

            AnalyzeData data = (AnalyzeData) tasksConsumers[0].get();

            LOGGER.info(
                "\n[name] {}\n[avgValue] {}\n[maxValue] {}\n[minValue] {}\n[sumValues] {}",
                data.name(),
                data.averageValue(),
                data.maxValue(),
                data.minValue(),
                data.sumValue()
            );

            assertThat(data.name()).isNotNull();
            assertThat(data.name()).isNotEmpty();
            assertThat(data.minValue()).isBetween(0.0, MAX_DATA_VALUE);
            assertThat(data.maxValue()).isBetween(0.0, MAX_DATA_VALUE);
            assertThat(data.averageValue()).isBetween(0.0, MAX_DATA_VALUE);
        }
    }
}

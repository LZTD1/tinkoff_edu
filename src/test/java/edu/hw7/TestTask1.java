package edu.hw7;

import edu.hw7.Task1.AtomicCounter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {

    @Test
    void test(){
        var myCounter = new AtomicCounter(0);

        Thread firstIncrementer = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                myCounter.increment();
            }
        });
        Thread secondIncrementer = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                myCounter.increment();
            }
        });
        Thread thirdIncrementer = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                myCounter.increment();
            }
        });

        firstIncrementer.start();
        secondIncrementer.start();
        thirdIncrementer.start();

        try {
            firstIncrementer.join();
            secondIncrementer.join();
            thirdIncrementer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertThat(myCounter.getValue()).isEqualTo(300_000);
    }
}

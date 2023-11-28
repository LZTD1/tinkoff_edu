package edu.hw8;

import edu.hw8.Task2.MyNewFixedThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class TestTask2 {

    protected final static Logger LOGGER = LogManager.getLogger();

    class Fibonacci implements Runnable {
        private final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        private int getFib(int curN) {
            if (curN <= 2) {
                return 1;
            } else {
                return getFib(curN - 1) + getFib(curN - 2);
            }
        }

        @Override
        public void run() {
            LOGGER.info("[" + Thread.currentThread().getName() + "] get to job - " + this.n);
            int result = 0;
            result = getFib(this.n);
            LOGGER.info("[" + Thread.currentThread().getName() + "] result is - " + result);
        }
    }

    @Test
    void testThrPool() throws Exception {
        var pool = new MyNewFixedThreadPool(2);
        pool.start();

        pool.execute(new Fibonacci(40));
        pool.execute(new Fibonacci(41));
        pool.execute(new Fibonacci(42));
        pool.execute(new Fibonacci(43));
        pool.execute(new Fibonacci(44));

        Thread.sleep(5_000);
        pool.close();
    }
}

package edu.hw7;

import edu.hw7.Task1.AtomicCounter;
import edu.hw7.Task1.NotAtomicCounter;
import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestTask1 {
    @Nested class testAtomic extends MultithreadedTestCase {
        private AtomicCounter myCounter;

        @Override
        public void initialize() {
            myCounter = new AtomicCounter(0);
        }

        public void thread1() throws InterruptedException {
            myCounter.increment();
        }

        public void thread2() throws InterruptedException {
            myCounter.increment();
        }

        @Override
        public void finish() {
            assertEquals(2, myCounter.getValue());
        }

        @Test
        public void testCounter() throws Throwable {
            TestFramework.runManyTimes(new testAtomic(), 300);
        }
    }

    @Nested class testDefault extends MultithreadedTestCase {
        private NotAtomicCounter myCounter;

        @Override
        public void initialize() {
            myCounter = new NotAtomicCounter(0);
        }

        public void thread1() throws InterruptedException {
            myCounter.increment();
        }

        public void thread2() throws InterruptedException {
            myCounter.increment();
        }

        @Override
        public void finish() {
            assertTrue(0 <= myCounter.getValue() && myCounter.getValue() <= 2);
        }

        @Test
        public void testCounter() throws Throwable {
            TestFramework.runManyTimes(new testAtomic(), 300);
        }
    }

}

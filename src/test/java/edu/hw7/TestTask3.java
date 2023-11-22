package edu.hw7;

import edu.hw7.Task3.NotSynchronizedDatabase;
import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.SynchronizedDatabase;
import edu.hw7.Task3.Task35.ReadWriteLockDatabase;
import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;
import java.util.Objects;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestTask3 {
    private final Person MOCK_PERSON = new Person(
        1,
        "Georgy",
        "Moscow",
        "89998887766"
    );
    private final int COUNT_REPEAT = 300;

    @Nested
    class TestNotSynchronized extends MultithreadedTestCase {
        private PersonDatabase myDatabase;
        private Person foundByName;
        private Person foundByPhone;
        private Person foundByAddress;

        @Override
        public void initialize() {
            myDatabase = new NotSynchronizedDatabase();
        }

        public void thread1() throws InterruptedException {
            myDatabase.add(MOCK_PERSON);
        }

        public void thread2() throws InterruptedException {
            foundByAddress = myDatabase.findByAddress(MOCK_PERSON.address());
            foundByName = myDatabase.findByName(MOCK_PERSON.name());
            foundByPhone = myDatabase.findByPhone(MOCK_PERSON.phoneNumber());
        }

        @Override
        public void finish() {
            try {
                boolean result = (foundByAddress == null && foundByName == null && foundByPhone == null)
                    || (foundByAddress.address() == MOCK_PERSON.address()
                    && foundByName.name() == MOCK_PERSON.name()
                    && foundByPhone.phoneNumber() == MOCK_PERSON.phoneNumber());

                assertTrue(result);
            } catch (Exception e) {
                assertEquals(
                    e.getClass(),
                    NullPointerException.class
                ); // Проверяем что действительно класс ведет себя не валидно, и берет когда все поля еще не доступны
            }
        }

        @Test
        public void testGetter() throws Throwable {
            TestFramework.runManyTimes(new TestTask3.TestNotSynchronized(), COUNT_REPEAT);
        }
    }

    @Nested
    class TestSynchronized extends MultithreadedTestCase {
        private PersonDatabase myDatabase;
        private Person foundByName;
        private Person foundByPhone;
        private Person foundByAddress;

        @Override
        public void initialize() {
            myDatabase = new SynchronizedDatabase();
        }

        public void thread1() throws InterruptedException {
            myDatabase.add(MOCK_PERSON);
        }

        public void thread2() throws InterruptedException {
            foundByAddress = myDatabase.findByAddress(MOCK_PERSON.address());
            foundByName = myDatabase.findByName(MOCK_PERSON.name());
            foundByPhone = myDatabase.findByPhone(MOCK_PERSON.phoneNumber());
        }

        @Override
        public void finish() {
            boolean result = false;

            if(foundByAddress != null){ // Если первый НЕ null
                if(foundByName != null && foundByPhone != null){ // то и другие должны быть не null
                    result = true;
                }
            }else{ // Если первый null - то не важно какие другие
                result = true;
            }

            assertTrue(result);
        }

        @Test
        public void testGetterSync() throws Throwable {
            TestFramework.runManyTimes(new TestTask3.TestSynchronized(), COUNT_REPEAT);
        }
    }

    @Nested
    class TestWithReadWriteLock extends MultithreadedTestCase {
        private PersonDatabase myDatabase;
        private Person foundByName;
        private Person foundByPhone;
        private Person foundByAddress;

        @Override
        public void initialize() {
            myDatabase = new ReadWriteLockDatabase();
        }

        public void thread1() throws InterruptedException {
            myDatabase.add(MOCK_PERSON);
        }

        public void thread2() throws InterruptedException {
            foundByAddress = myDatabase.findByAddress(MOCK_PERSON.address());
            foundByName = myDatabase.findByName(MOCK_PERSON.name());
            foundByPhone = myDatabase.findByPhone(MOCK_PERSON.phoneNumber());
        }

        @Override
        public void finish() {
            boolean result = false;

            if(foundByAddress != null){ // Если первый НЕ null
                if(foundByName != null && foundByPhone != null){ // то и другие должны быть не null
                    result = true;
                }
            }else{ // Если первый null - то не важно какие другие
                result = true;
            }

            assertTrue(result);
        }

        @Test
        public void testGetterSync() throws Throwable {
            TestFramework.runManyTimes(new TestTask3.TestWithReadWriteLock(), COUNT_REPEAT);
        }
    }

}


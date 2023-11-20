package edu.hw7;

import edu.hw7.Task3.NotSynchronizedDatabase;
import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.SynchronizedDatabase;
import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestTask3 {
    private final Person MOCK_PERSON = new Person(
        1,
        "Georgy",
        "Moscow",
        "89998887766"
    );

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
            TestFramework.runManyTimes(new TestTask3.TestNotSynchronized(), 300);
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
            System.out.println("+==================+");
            System.out.println(foundByAddress);
            System.out.println(foundByName);
            System.out.println(foundByPhone);
            System.out.println("+==================+");
            boolean result;
            if (foundByAddress == null && foundByName == null && foundByPhone == null){
                 result = true;
            } else if (Objects.equals(foundByAddress.address(), MOCK_PERSON.address())
                && Objects.equals(foundByName.name(), MOCK_PERSON.name())
                && Objects.equals(foundByPhone.phoneNumber(), MOCK_PERSON.phoneNumber())) {
                 result = true;

            }else{
                 result = false;
            }

            assertTrue(result);
        }

        @Test
        public void testGetterSync() throws Throwable {
            TestFramework.runManyTimes(new TestTask3.TestSynchronized(), 300);
        }
    }
}

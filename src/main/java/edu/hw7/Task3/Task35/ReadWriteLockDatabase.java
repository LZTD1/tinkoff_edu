package edu.hw7.Task3.Task35;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class ReadWriteLockDatabase implements PersonDatabase {

    private final HashMap<String, Person> phoneBook;
    private final HashMap<String, Person> addressBook;
    private final HashMap<String, Person> nameBook;
    private final HashMap<Integer, Person> idBook; // for quick remove person from other dict
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public ReadWriteLockDatabase() {
        this.nameBook = new HashMap<>();
        this.phoneBook = new HashMap<>();
        this.addressBook = new HashMap<>();
        this.idBook = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            nameBook.put(person.name(), person);
            phoneBook.put(person.phoneNumber(), person);
            addressBook.put(person.address(), person);
            idBook.put(person.id(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idBook.get(id);
            if (person != null) {
                nameBook.remove(person.name());
                phoneBook.remove(person.phoneNumber());
                addressBook.remove(person.address());
                idBook.remove(id);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByName(String name) {
        lock.readLock().lock();
        Person person = this.nameBook.get(name);
        lock.readLock().unlock();
        return person;
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        lock.readLock().lock();
        Person person = this.addressBook.get(address);
        lock.readLock().unlock();
        return person;
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        lock.readLock().lock();
        Person person = this.phoneBook.get(phone);
        lock.readLock().unlock();
        return person;
    }
}

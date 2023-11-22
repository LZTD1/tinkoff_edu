package edu.hw7.Task3;

import java.util.HashMap;
import org.jetbrains.annotations.Nullable;

public class SynchronizedDatabase implements PersonDatabase {
    private final HashMap<String, Person> nameBook = new HashMap<>();
    private final HashMap<String, Person> phoneBook = new HashMap<>();
    private final HashMap<String, Person> addressBook = new HashMap<>();
    private final HashMap<Integer, Person> idBook = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        nameBook.put(person.name(), person);
        phoneBook.put(person.phoneNumber(), person);
        addressBook.put(person.address(), person);
        idBook.put(person.id(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idBook.get(id);
        if (person != null) {
            nameBook.remove(person.name());
            phoneBook.remove(person.phoneNumber());
            addressBook.remove(person.address());
            idBook.remove(id);
        }
    }

    @Override
    public synchronized @Nullable Person findByName(String name) {
        return nameBook.get(name);
    }

    @Override
    public synchronized @Nullable Person findByAddress(String address) {
        return addressBook.get(address);
    }

    @Override
    public synchronized @Nullable Person findByPhone(String phone) {
        return phoneBook.get(phone);
    }
}

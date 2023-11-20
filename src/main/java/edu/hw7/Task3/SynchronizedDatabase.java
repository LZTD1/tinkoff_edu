package edu.hw7.Task3;

import java.util.HashMap;
import org.jetbrains.annotations.Nullable;

public class SynchronizedDatabase implements PersonDatabase {

    private final HashMap<String, Person> phoneBook;
    private final HashMap<String, Person> addressBook;
    private final HashMap<String, Person> nameBook;
    private final HashMap<Integer, Person> idBook; // for quick remove person from other dict

    public SynchronizedDatabase() {
        this.nameBook = new HashMap<>();
        this.phoneBook = new HashMap<>();
        this.addressBook = new HashMap<>();
        this.idBook = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        updateBooks(person);
    }

    @Override
    public synchronized void delete(int id) {
        deletePersonById(id);
    }

    @Override
    public synchronized @Nullable Person findByName(String name) {
        return this.nameBook.get(name);
    }

    @Override
    public synchronized @Nullable Person findByAddress(String address) {
        return this.addressBook.get(address);
    }

    @Override
    public synchronized @Nullable Person findByPhone(String phone) {
        return this.phoneBook.get(phone);
    }

    private void deletePersonById(int id) {
        Person person = this.idBook.get(id);
        if (person != null) {
            this.nameBook.remove(person.name());
            this.phoneBook.remove(person.phoneNumber());
            this.addressBook.remove(person.address());
            this.idBook.remove(id);
        }
    }

    private void updateBooks(Person person) {
        updateNameBook(person);
        updatePhoneBook(person);
        updateAddressBook(person);
        updateIdBook(person);
    }

    private void updateIdBook(Person person) {
        this.idBook.put(person.id(), person);
    }

    private void updateNameBook(Person person) {
        this.nameBook.put(person.name(), person);
    }

    private void updatePhoneBook(Person person) {
        this.phoneBook.put(person.phoneNumber(), person);
    }

    private void updateAddressBook(Person person) {
        this.addressBook.put(person.address(), person);
    }
}

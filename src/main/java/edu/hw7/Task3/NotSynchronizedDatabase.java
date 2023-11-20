package edu.hw7.Task3;

import java.util.HashMap;
import org.jetbrains.annotations.Nullable;

public class NotSynchronizedDatabase implements PersonDatabase {

    private final HashMap<String, Person> phoneBook;
    private final HashMap<String, Person> addressBook;
    private final HashMap<String, Person> nameBook;
    private final HashMap<Integer, Person> idBook; // for quick remove person from other dict

    public NotSynchronizedDatabase() {
        this.nameBook = new HashMap<>();
        this.phoneBook = new HashMap<>();
        this.addressBook = new HashMap<>();
        this.idBook = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        updateBooks(person);
    }

    @Override
    public void delete(int id) {
        deletePersonById(id);
    }

    @Override
    public @Nullable Person findByName(String name) {
        return this.nameBook.get(name);
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        return this.addressBook.get(address);
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        return this.phoneBook.get(phone);
    }

    private void deletePersonById(int id) {
        synchronized (this.idBook.get(id)) {
            this.nameBook.remove(this.idBook.get(id).name());
            this.phoneBook.remove(this.idBook.get(id).phoneNumber());
            this.addressBook.remove(this.idBook.get(id).address());
            this.idBook.remove(id);
        }
    }

    private void updateBooks(Person person) { // Интересно, можно ли как нибудь улучшить
        updateNameBook(person); // что бы я не писал метод для каждого поля
        updatePhoneBook(person);
        updateAddressBook(person);
        updateIdBook(person);
    }

    private void updateIdBook(Person person) {
        this.idBook.put(
            person.id(), person
        );
    }

    private void updateNameBook(Person person) {
        this.nameBook.put(
            person.name(), person
        );
    }

    private void updatePhoneBook(Person person) {
        this.phoneBook.put(
            person.phoneNumber(), person
        );
    }

    private void updateAddressBook(Person person) {
        this.addressBook.put(
            person.address(), person
        );
    }
}

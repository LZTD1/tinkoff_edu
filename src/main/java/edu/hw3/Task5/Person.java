package edu.hw3.Task5;

import edu.hw3.Task5.Exceptions.UnexpectedString;
import java.util.ArrayList;

public class Person {

    public static ArrayList<Person> persons = new ArrayList<>();
    private String name = null;
    private String surname = null;

    public Person(String fio) {
        personFactory(fio);
    }

    public static ArrayList<Person> getAllPersons() {
        return persons;
    }

    public static void removeAllPersonsFromList() {
        persons = new ArrayList<>();
    }

    private void personFactory(String fio) {
        String[] splittedFio = fio.split(" ");
        if (splittedFio.length == 2) {
            this.name = splittedFio[0];
            this.surname = splittedFio[1];
        } else if (splittedFio.length == 1) {
            this.name = splittedFio[0];
        } else {
            throw new UnexpectedString("Format string \"Name Surname\" !");
        }
        persons.add(this);
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }
}

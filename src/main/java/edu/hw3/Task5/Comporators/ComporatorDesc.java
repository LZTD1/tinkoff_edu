package edu.hw3.Task5.Comporators;

import edu.hw3.Task5.Person;
import java.util.Comparator;

public class ComporatorDesc implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        String compareO1 = o1.getSurname() != null ? o1.getSurname() : o1.getName();
        String compareO2 = o2.getSurname() != null ? o2.getSurname() : o2.getName();

        return compareO2.compareTo(compareO1);
    }
}

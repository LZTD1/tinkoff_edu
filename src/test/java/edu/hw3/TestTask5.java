package edu.hw3;

import edu.hw3.Task5.Exceptions.UnexpectedSortingType;
import edu.hw3.Task5.Exceptions.UnexpectedString;
import edu.hw3.Task5.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static edu.hw3.Task5.ParseContacts.parseContacts;
import static edu.hw3.Task5.Person.getAllPersons;
import static edu.hw3.Task5.Person.removeAllPersonsFromList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTask5 {

    @BeforeEach
    void cleanUp(){
        removeAllPersonsFromList();
    }

    @Test
    void testSortingDesc(){
        Person p1 = new Person("Paul Erdos"); //
        Person p2 = new Person("Leonhard Euler"); //
        Person p3 = new Person("Carl Gauss"); //
        ArrayList<Person> allP = getAllPersons();

        List<Person> result = parseContacts(allP, "DESC");

        assertThat(result).isEqualTo(new ArrayList<Person>(){{
            add(p3);
            add(p2);
            add(p1);
        }});
    }
    @Test
    void testSortingAsc(){
        Person p1 = new Person("John Locke"); //
        Person p2 = new Person("Thomas Aquinas"); //
        Person p3 = new Person("David Hume"); //
        Person p4 = new Person("Rene Descartes"); //
        ArrayList<Person> allP = getAllPersons();

        List<Person> result = parseContacts(allP, "ASC");

        assertThat(result).isEqualTo(new ArrayList<Person>(){{
            add(p2);
            add(p4);
            add(p3);
            add(p1);
        }});
    }
    @Test
    void testSortingFailed(){
        Person p1 = new Person("John Locke"); //
        ArrayList<Person> allP = getAllPersons();

        assertThrows(UnexpectedSortingType.class, () -> parseContacts(allP, "D"));
    }
    @Test
    void testPersonFailed(){
        assertThrows(UnexpectedString.class, () -> new Person("John Locke Bocke"));
    }
}

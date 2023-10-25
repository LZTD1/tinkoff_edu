package edu.hw3.Task5;

import edu.hw3.Task5.Comporators.ComporatorAsc;
import edu.hw3.Task5.Comporators.ComporatorDesc;
import edu.hw3.Task5.Exceptions.UnexpectedSortingType;
import java.util.List;

public class ParseContacts {

    private ParseContacts() {
    }

    public static List<Person> parseContacts(List<Person> persons, String type) {

        if (type.equals("DESC")) {
            persons.sort(new ComporatorDesc());
        } else if (type.equals("ASC")) {
            persons.sort(new ComporatorAsc());
        } else {
            throw new UnexpectedSortingType("Unexpected sorting type, aviable \"ASC\" and \"DESC\"!");
        }

        return persons;
    }
}

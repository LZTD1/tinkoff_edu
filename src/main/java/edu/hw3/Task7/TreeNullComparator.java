package edu.hw3.Task7;

import java.util.Comparator;

public class TreeNullComparator<T> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        if (o1 == null) {
            if (o2 == null) {
                return 0;
            } else {
                return 1;
            }
        } else if (o2 == null) {
            return -1;
        } else {
            return o1.toString().compareTo(o2.toString());
        }
    }
}

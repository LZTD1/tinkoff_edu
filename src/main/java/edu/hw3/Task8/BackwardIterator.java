package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {

    private int cursor;
    private int size;
    private List<T> collection;

    public BackwardIterator(List<T> collection) {
        this.cursor = collection.size();
        this.size = collection.size();
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        return cursor > size;
    }

    @Override
    public T next() {
        cursor -= 1;
        return collection.get(cursor);
    }
}

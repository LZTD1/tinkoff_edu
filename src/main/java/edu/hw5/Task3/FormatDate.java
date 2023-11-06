package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class FormatDate {

    private FormatDate next;

    public static FormatDate link(FormatDate first, FormatDate... chain) {
        FormatDate head = first;
        for (FormatDate nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    protected Optional<LocalDate> checkNext(String stringDate) {
        if (next == null) {
            return Optional.empty();
        }
        return next.getDateFromString(stringDate);
    }

    abstract public Optional<LocalDate> getDateFromString(String stringDate);
}

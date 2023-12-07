package edu.hw10.Task1.Classes;

import edu.hw10.Task1.Anntotaions.Max;
import edu.hw10.Task1.Anntotaions.Min;
import edu.hw10.Task1.Anntotaions.NotNull;

public class SimpleClassWithAnnotation {

    private final String string;
    private final int value;
    private final int value2;

    public final int MAX_VALUE = 1000;
    public final int MIN_VALUE = 500;

    public SimpleClassWithAnnotation(
        @NotNull String string,
        @Max(MAX_VALUE) int value,
        @Min(MIN_VALUE) int value2
    ) {
        this.string = string;
        this.value = value;
        this.value2 = value2;
    }

    public String getString() {
        return string;
    }

    public int getValue() {
        return value;
    }

    public int getValue2() {
        return value2;
    }
}

package edu.hw3;

import edu.hw3.Task7.TreeNullComparator;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask7 {
    @Test
    void comporatorNullTest(){
        TreeMap<String, String> tree = new TreeMap<>(new TreeNullComparator<>());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}

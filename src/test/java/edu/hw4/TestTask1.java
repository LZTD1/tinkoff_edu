package edu.hw4;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {

    @Test
    void sortingByHeight() {
        List<Animal> myZoo = List.of(
            new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 44, 1, false),
            new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 31, 1, false),
            new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 42, 1, false)
        );

        List<Animal> result = myZoo.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();

        assertThat(result).isEqualTo(
            List.of(
                new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 31, 1, false),
                new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 42, 1, false),
                new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 44, 1, false)
            )
        );
    }
}

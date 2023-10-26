package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask2 {

    @Test
    void sortingByWeightAndLimit() {
        Animal gosha = new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 44, 3, false);
        Animal petya = new Animal("Petya", Animal.Type.BIRD, Animal.Sex.F, 3, 31, 1, false);
        Animal jack = new Animal("Jack", Animal.Type.BIRD, Animal.Sex.F, 3, 42, 2, false);

        List<Animal> myZoo = List.of(
            gosha,
            petya,
            jack
        );

        List<Animal> result = myZoo.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(2)
            .toList();

        assertThat(result).isEqualTo(
            List.of(
                gosha,
                jack
            )
        );
    }
}

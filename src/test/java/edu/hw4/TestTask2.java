package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
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

        List<Animal> result = sortAnimalsByWeightWithLimit(myZoo, 2);

        assertThat(result).isEqualTo(
            List.of(
                gosha,
                jack
            )
        );
    }

    @Test
    void sortingByWeightAndLimitWithSmallList() {
        Animal smallBird = new Animal("Small", Animal.Type.BIRD, Animal.Sex.M, 1, 20, 1, true);

        List<Animal> smallZoo = List.of(smallBird);

        List<Animal> result = sortAnimalsByWeightWithLimit(smallZoo, 2);

        assertThat(result).containsExactly(smallBird);
    }

    private List<Animal> sortAnimalsByWeightWithLimit(List<Animal> animals, int limit) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(limit)
            .collect(Collectors.toList());
    }
}

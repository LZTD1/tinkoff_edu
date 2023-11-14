package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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

        List<Animal> result = sortAnimalsByHeight(myZoo);

        assertThat(result).isEqualTo(
            List.of(
                new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 31, 1, false),
                new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 42, 1, false),
                new Animal("Gosha", Animal.Type.BIRD, Animal.Sex.F, 3, 44, 1, false)
            )
        );
    }

    @Test
    void sortingByHeightWithEmptyList() {
        List<Animal> emptyZoo = List.of();

        List<Animal> result = sortAnimalsByHeight(emptyZoo);

        assertThat(result).isEmpty();
    }

    @Test
    void sortingByHeightWithOneAnimal() {
        List<Animal> singleAnimalZoo = List.of(
            new Animal("Leo", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 4, true)
        );

        List<Animal> result = sortAnimalsByHeight(singleAnimalZoo);

        assertThat(result).containsExactly(
            new Animal("Leo", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 4, true)
        );
    }

    private List<Animal> sortAnimalsByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .collect(Collectors.toList());
    }
}

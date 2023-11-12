package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask3 {

    @Test
    void MappingAnimals() {
        Animal gosha = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false);
        Animal petya = new Animal("Petya", Animal.Type.BIRD, Animal.Sex.F, 3, 31, 1, false);
        Animal jack = new Animal("Jack", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);
        Animal brotherJack = new Animal("Brother`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);

        List<Animal> myZoo = List.of(
            gosha,
            petya,
            jack,
            brotherJack
        );

        Map<Animal.Type, Long> result = mapAnimalTypesCount(myZoo);

        assertThat(result).isEqualTo(
            Map.of(
                Animal.Type.FISH, 1L,
                Animal.Type.BIRD, 1L,
                Animal.Type.SPIDER, 2L
            )
        );
    }

    private Map<Animal.Type, Long> mapAnimalTypesCount(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }
}

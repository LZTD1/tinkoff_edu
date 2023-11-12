package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask6 {
    @Test
    void heavyAnimalByType() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 1, 44, 1, false);
        Animal FISH2 = new Animal("Gosha2", Animal.Type.FISH, Animal.Sex.M, 7, 44, 7, false);
        Animal SPIDER = new Animal("Jack", Animal.Type.SPIDER, Animal.Sex.M, 7, 31, 7, false);
        Animal SPIDER2 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 1, 1, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 1, 42, 1, true);
        Animal CAT2 = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 7, 42, 7, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT,
            CAT2
        );

        Map<Animal.Type, Animal> result = getHeavyAnimalByType(myZoo);

        assertThat(result).isEqualTo(
            Map.of(
                Animal.Type.FISH, FISH2,
                Animal.Type.SPIDER, SPIDER,
                Animal.Type.CAT, CAT2
            )
        );
    }

    @Test
    void emptyZooHeavyAnimalByType() {
        List<Animal> emptyZoo = List.of(); // Create an empty zoo

        Map<Animal.Type, Animal> result = getHeavyAnimalByType(emptyZoo);

        assertThat(result).isEmpty();
    }

    @Test
    void insufficientDataHeavyAnimalByType() {
        Animal singleFish = new Animal("Lonely Fish", Animal.Type.FISH, Animal.Sex.F, 1, 20, 1, false);

        List<Animal> myZoo = List.of(singleFish);

        Map<Animal.Type, Animal> result = getHeavyAnimalByType(myZoo);

        assertThat(result).containsExactly(Map.entry(Animal.Type.FISH, singleFish));
    }

    private Map<Animal.Type, Animal> getHeavyAnimalByType(List<Animal> myZoo) {
        return myZoo.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.collectingAndThen(
                        Collectors.maxBy(
                            Comparator.comparing(Animal::weight)),
                        Optional::orElseThrow
                    )
                )
            );
    }
}

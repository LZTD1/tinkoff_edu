package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

        var result = myZoo.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.maxBy(
                        Comparator.comparing(Animal::weight)
                    )
                )
            )
            // Необходимо для избавления от Optional
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue().orElseThrow()
                )
            );

        assertThat(result).isEqualTo(
            Map.of(
                Animal.Type.FISH, FISH2,
                Animal.Type.SPIDER, SPIDER,
                Animal.Type.CAT, CAT2
            )
        );
    }
}

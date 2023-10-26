package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

        var result = myZoo.stream()
                .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));

        assertThat(result).isEqualTo( // Почему-то Collectors.counting() возвращается в лонге
            Map.of(
                Animal.Type.FISH, 1L,
                Animal.Type.BIRD, 1L,
                Animal.Type.SPIDER, 2L
            )
        );
    }
}

package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask4 {

    @Test
    void theMostLongName() {
        Animal gosha = new Animal("Gregory", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false);
        Animal petya = new Animal("Pasha", Animal.Type.BIRD, Animal.Sex.F, 3, 31, 1, false);
        Animal sGregory = new Animal("Saint Gregory", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);

        List<Animal> myZoo = List.of(
            gosha,
            petya,
            sGregory
        );

        var result = myZoo.stream()
                .max(Comparator.comparing(Animal::name))
                .orElse(null);

        assertThat(result).isEqualTo(
            sGregory
        );
    }
}

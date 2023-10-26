package edu.hw4;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask18 {

    @Test
    void veryHeavyFishIn2AndOtherLists() {
        Animal FISH = new Animal("a", Animal.Type.FISH, Animal.Sex.M, 0, 100, 2, false);
        Animal FISH2 = new Animal("c", Animal.Type.FISH, Animal.Sex.F, 0, 100, 11, true);
        Animal FISH3 = new Animal("b", Animal.Type.FISH, Animal.Sex.F, 0, 100, 1, true);

        Animal FISH4 = new Animal("a", Animal.Type.FISH, Animal.Sex.M, 0, 100, 5, false);
        Animal FISH5 = new Animal("c", Animal.Type.FISH, Animal.Sex.M, 0, 100, 2, true);
        Animal FISH6 = new Animal("b", Animal.Type.FISH, Animal.Sex.F, 0, 100, 32, false);

        List<List<Animal>> myZoo = List.of(
            List.of(
                FISH,
                FISH2,
                FISH3
            ),
            List.of(
                FISH4,
                FISH5,
                FISH6
            )
        );

        var mostHeavyFish = myZoo.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);

        assertThat(mostHeavyFish).isEqualTo(
            FISH6
        );

    }
}

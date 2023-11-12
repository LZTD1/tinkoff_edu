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

        Animal mostHeavyFish = getMostHeavyFish(myZoo);

        assertThat(mostHeavyFish).isEqualTo(
            FISH6
        );

    }

    @Test
    void veryHeavyFishIn2AndOtherLists_NoOne() {
        Animal DOG = new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 2, false);
        Animal DOG2 = new Animal("c", Animal.Type.DOG, Animal.Sex.F, 0, 100, 11, true);
        Animal DOG3 = new Animal("b", Animal.Type.DOG, Animal.Sex.F, 0, 100, 1, true);

        Animal DOG4 = new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 5, false);
        Animal DOG5 = new Animal("c", Animal.Type.DOG, Animal.Sex.M, 0, 100, 2, true);
        Animal DOG6 = new Animal("b", Animal.Type.DOG, Animal.Sex.F, 0, 100, 32, false);

        List<List<Animal>> myZoo = List.of(
            List.of(
                DOG,
                DOG2,
                DOG3
            ),
            List.of(
                DOG4,
                DOG5,
                DOG6
            )
        );

        Animal mostHeavyFish = getMostHeavyFish(myZoo);

        assertThat(mostHeavyFish).isNull();

    }

    private Animal getMostHeavyFish(List<List<Animal>> myZoo) {
        return myZoo.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }
}

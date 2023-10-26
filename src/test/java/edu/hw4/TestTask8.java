package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask8 {

    @Test
    void veryHeavyAnimal_byHeight() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 5, 100, 1, false);
        Animal FISH2 = new Animal("Gosha2", Animal.Type.FISH, Animal.Sex.M, 3, 100, 6, false);
        Animal SPIDER = new Animal("Jack", Animal.Type.SPIDER, Animal.Sex.M, 7, 100, 7, false);
        Animal SPIDER2 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 120, 54, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 1, 120, 53, true);
        Animal CAT2 = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 12, 120, 55, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT,
            CAT2
        );

        int k = 110;

        Optional<Animal> result = myZoo.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));

        var expected = Optional.of(SPIDER);
        assertThat(result).isEqualTo(
            expected
        );
    }
}

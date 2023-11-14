package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
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

        Optional<Animal> result = findVeryHeavyAnimalByHeight(myZoo);

        var expected = Optional.of(SPIDER);
        assertThat(result).isEqualTo(
            expected
        );
    }

    @Test
    void emptyZooVeryHeavyAnimal_byHeight() {
        List<Animal> emptyZoo = List.of(); // Create an empty zoo

        Optional<Animal> result = findVeryHeavyAnimalByHeight(emptyZoo);

        assertThat(result).isEmpty();
    }

    @Test
    void noVeryHeavyAnimal_byHeight() {
        Animal lightSpider = new Animal("Light Spider", Animal.Type.SPIDER, Animal.Sex.M, 120, 150, 1, false);

        List<Animal> myZoo = List.of(lightSpider);

        Optional<Animal> result = findVeryHeavyAnimalByHeight(myZoo);

        assertThat(result).isEmpty();
    }

    private Optional<Animal> findVeryHeavyAnimalByHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.height() < 110)
            .max(Comparator.comparingInt(Animal::weight));
    }
}

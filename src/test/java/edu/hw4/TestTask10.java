package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask10 {

    @Test
    void pawsNotEqualsAge() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false);
        Animal FISH2 = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 5, 100, 1, false);
        Animal SPIDER = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 8, 54, 2, true);
        Animal SPIDER2 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 1, 54, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 4, 120, 53, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT
        );

        List<Animal> result = ageNotEqualPawsAnimals(myZoo);

        assertThat(result).isEqualTo(
            List.of(
                FISH2,
                SPIDER2
            )
        );
    }

    private List<Animal> ageNotEqualPawsAnimals(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .collect(Collectors.toList());
    }
}

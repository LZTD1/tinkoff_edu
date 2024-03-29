package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask14 {

    @Test
    void existsHeightOverN() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false);
        Animal FISH2 = new Animal("Gosha", Animal.Type.DOG, Animal.Sex.M, 5, 25, 66, false);
        Animal SPIDER = new Animal("Sister`s Jack  Junior", Animal.Type.DOG, Animal.Sex.F, 8, 54, 2, true);
        Animal SPIDER2 = new Animal("Sister`s Jack Middle", Animal.Type.SPIDER, Animal.Sex.F, 1, 54, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 4, 5, 53, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT
        );

        var result = isExistsHeightOverN(myZoo, 30);
        assertThat(result).isEqualTo(
            true
        );
    }

    @Test
    void existsHeightOverN_NoOne() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false);
        Animal FISH2 = new Animal("Gosha", Animal.Type.DOG, Animal.Sex.M, 5, 25, 66, false);
        Animal SPIDER = new Animal("Sister`s Jack  Junior", Animal.Type.DOG, Animal.Sex.F, 8, 54, 2, true);
        Animal SPIDER2 = new Animal("Sister`s Jack Middle", Animal.Type.SPIDER, Animal.Sex.F, 1, 54, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 4, 5, 53, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT
        );

        var result2 = isExistsHeightOverN(myZoo, 300);
        assertThat(result2).isEqualTo(
            false
        );
    }

    private boolean isExistsHeightOverN(List<Animal> myZoo, int limit) {
        return myZoo.stream()
            .anyMatch(animal -> animal.height() > limit);
    }
}

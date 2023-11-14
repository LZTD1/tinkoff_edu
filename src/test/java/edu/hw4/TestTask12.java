package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask12 {

    @Test
    void countAnimalsWeightBiggerThanHeight() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false);
        Animal FISH2 = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 5, 2, 66, false);
        Animal SPIDER = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 8, 54, 2, true);
        Animal SPIDER2 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 1, 54, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 4, 5, 53, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT
        );

        long result = getCountAnimalsWeightBiggerThanHeight(myZoo);

        assertThat(result).isEqualTo(
            2
        );
    }

    @Test
    void countAnimalsWeightBiggerThanHeight_NoOne() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false);
        Animal FISH2 = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 5, 100, 1, false);
        Animal SPIDER = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 8, 100, 1, true);
        Animal SPIDER2 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 1, 100, 1, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 4, 100, 1, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT
        );

        long result = getCountAnimalsWeightBiggerThanHeight(myZoo);

        assertThat(result).isZero();
    }

    private long getCountAnimalsWeightBiggerThanHeight(List<Animal> myZoo) {
        return myZoo.stream()
            .filter(animal -> animal.weight() > animal.height()).count();
    }
}

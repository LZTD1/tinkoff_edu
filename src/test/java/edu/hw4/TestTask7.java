package edu.hw4;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask7 {
    static Animal FINDED_CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 12, 42, 7, true);
    static Animal FINDED_FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 5, 44, 1, false);
    private static List<Animal> myZoo;

    @BeforeAll static void setUp() {
        Animal FISH2 = new Animal("Gosha2", Animal.Type.FISH, Animal.Sex.M, 3, 44, 7, false);
        Animal SPIDER = new Animal("Jack", Animal.Type.SPIDER, Animal.Sex.M, 7, 31, 7, false);
        Animal SPIDER2 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 1, 1, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 1, 42, 1, true);

        myZoo = List.of(
            FINDED_FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT,
            FINDED_CAT
        );
    }

    @Test
    void veryOldAnimal() {

        // Не до конца понятна формулировка задания, если просто самое старое животное
        var result = myZoo.stream()
            .max(Comparator.comparingInt(Animal::age))
            .orElse(null);

        assertThat(result).isEqualTo(
            FINDED_CAT
        );
    }

    @Test
    void veryOldByNumber() {
        // Если какое то по номеру старое
        int finded = 3;

        var result2 = myZoo.stream()
            .sorted(Comparator.comparingInt(Animal::age))
            .skip(finded)
            .findFirst()
            .orElse(null);

        assertThat(result2).isEqualTo(
            FINDED_FISH
        );
    }
}

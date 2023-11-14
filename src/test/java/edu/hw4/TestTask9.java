package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask9 {

    @Test
    void allPaws() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 5, 100, 1, false);
        Animal SPIDER2 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 120, 54, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 1, 120, 53, true);

        List<Animal> myZoo = List.of(
            FISH,
            SPIDER2,
            CAT
        );

        int result = totalPawsInZoo(myZoo);

        assertThat(result).isEqualTo(
            12
        );
    }

    @Test
    void emptyZooPawsCount() {
        List<Animal> emptyZoo = List.of(); // Create an empty zoo

        int result = totalPawsInZoo(emptyZoo);

        assertThat(result).isZero();
    }

    private int totalPawsInZoo(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }
}

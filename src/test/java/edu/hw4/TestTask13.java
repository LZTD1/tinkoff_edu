package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask13 {

    @Test
    void animalsNameOver2Words() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false);
        Animal FISH2 = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 5, 2, 66, false);
        Animal SPIDER = new Animal("Sister`s Jack  Junior", Animal.Type.SPIDER, Animal.Sex.F, 8, 54, 2, true);
        Animal SPIDER2 = new Animal("Sister`s Jack Middle", Animal.Type.SPIDER, Animal.Sex.F, 1, 54, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 4, 5, 53, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            SPIDER,
            SPIDER2,
            CAT
        );

        List<Animal> result = getAnimalsNameOver2Words(myZoo);

        assertThat(result).isEqualTo(
            List.of(
                SPIDER,
                SPIDER2
            )
        );
    }

    @Test
    void animalsNameOver2Words_NoOne() {

        List<Animal> myZoo = List.of(
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false)
        );

        List<Animal> result = getAnimalsNameOver2Words(myZoo);

        assertThat(result).isEmpty();
    }

    private List<Animal> getAnimalsNameOver2Words(List<Animal> myZoo) {
        return myZoo.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }
}

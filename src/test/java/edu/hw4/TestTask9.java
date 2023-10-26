package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
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

        var result = myZoo.stream()
            .reduce(0, (accumulator, element) -> accumulator + element.paws(), Integer::sum);


        assertThat(result).isEqualTo(
            12
        );
    }
}

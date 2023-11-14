package edu.hw4;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask16 {

    @Test
    void sorted3() {
        Animal FISH = new Animal("a", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false);
        Animal FISH1 = new Animal("c", Animal.Type.FISH, Animal.Sex.F, 0, 100, 1, false);
        Animal FISH2 = new Animal("b", Animal.Type.FISH, Animal.Sex.F, 0, 100, 1, false);

        Animal DOG = new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 1, false);
        Animal DOG1 = new Animal("c", Animal.Type.DOG, Animal.Sex.M, 0, 100, 1, false);
        Animal DOG2 = new Animal("b", Animal.Type.DOG, Animal.Sex.F, 0, 100, 1, false);

        Animal CAT = new Animal("a", Animal.Type.CAT, Animal.Sex.M, 0, 100, 1, false);
        Animal CAT1 = new Animal("c", Animal.Type.CAT, Animal.Sex.F, 0, 100, 1, false);
        Animal CAT2 = new Animal("b", Animal.Type.CAT, Animal.Sex.M, 0, 100, 1, false);

        List<Animal> myZoo = List.of(
            FISH,
            CAT,
            FISH2,
            DOG,
            FISH1,
            CAT1,
            DOG1,
            DOG2,
            CAT2
        );

        var result = myZoo.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            )
            .toList();

        assertThat(result).isEqualTo(
            List.of(
                CAT,
                CAT2,
                CAT1,
                DOG,
                DOG1,
                DOG2,
                FISH,
                FISH2,
                FISH1
            )
        );

    }
}

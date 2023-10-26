package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask17 {

    @Test
    void SpidersBitesMoreThatDogs() {
        Animal SPIDER = new Animal("a", Animal.Type.SPIDER, Animal.Sex.M, 0, 100, 1, false);
        Animal SPIDER1 = new Animal("c", Animal.Type.SPIDER, Animal.Sex.F, 0, 100, 1, true);
        Animal SPIDER2 = new Animal("b", Animal.Type.SPIDER, Animal.Sex.F, 0, 100, 1, true);

        Animal DOG = new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 1, false);
        Animal DOG1 = new Animal("c", Animal.Type.DOG, Animal.Sex.M, 0, 100, 1, true);
        Animal DOG2 = new Animal("b", Animal.Type.DOG, Animal.Sex.F, 0, 100, 1, false);

        List<Animal> myZoo = List.of(
            SPIDER,
            SPIDER1,
            SPIDER2,
            DOG,
            DOG1,
            DOG2
        );

        var bitesSpiders = myZoo.stream()
            .filter(animal -> animal.bites() && animal.type() == Animal.Type.SPIDER).count();

        var bitesDogs = myZoo.stream()
            .filter(animal -> animal.bites() && animal.type() == Animal.Type.DOG).count();

        boolean result = bitesSpiders > bitesDogs; // Если данных не достаточно (равное кол-во) вернет false

        assertThat(result).isEqualTo(
            true
        );

    }
}

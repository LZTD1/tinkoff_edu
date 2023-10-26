package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask5 {

    @Test
    void whoBiggerBySex() {
        Animal gosha = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 3, 44, 3, false);
        Animal jack = new Animal("Jack", Animal.Type.SPIDER, Animal.Sex.M, 3, 31, 1, false);
        Animal sisterJack0 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);
        Animal sisterJack1 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);
        Animal sisterJack2 = new Animal("Sister`s Jack", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);

        List<Animal> myZoo = List.of(
            gosha,
            jack,
            sisterJack0,
            sisterJack1,
            sisterJack2
        );

        var female = myZoo.stream()
            .filter(animal -> animal.sex() == Animal.Sex.F)
            .count();
        var male = myZoo.stream()
            .filter(animal -> animal.sex() == Animal.Sex.F)
            .count();

        var result = male > female ? Animal.Sex.M : Animal.Sex.F;

        assertThat(result).isEqualTo(
            Animal.Sex.F
        );
    }
}

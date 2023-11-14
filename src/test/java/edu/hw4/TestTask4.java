package edu.hw4;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import javax.annotation.processing.SupportedSourceVersion;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask4 {

    @Test
    void theMostLongName() {
        Animal gosha = new Animal("Gregory", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false);
        Animal petya = new Animal("Pasha", Animal.Type.BIRD, Animal.Sex.F, 3, 31, 1, false);
        Animal sGregory = new Animal("Saint Gregory", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);

        List<Animal> myZoo = List.of(
            gosha,
            petya,
            sGregory
        );

        Animal result = getMostLongAnimalName(myZoo);

        assertThat(result).isEqualTo(
            sGregory
        );
    }

    @Test
    void theMostLongName_AllLongNames() {
        Animal gosha = new Animal("Gregory", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false);
        Animal petya = new Animal("Gregory", Animal.Type.BIRD, Animal.Sex.F, 3, 31, 1, false);
        Animal sGregory = new Animal("Gregory", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);
        Animal badGregory = new Animal("grgy", Animal.Type.SPIDER, Animal.Sex.F, 3, 42, 2, true);

        List<Animal> myZoo = List.of(
            gosha,
            petya,
            badGregory,
            sGregory
        );

        Animal result = getMostLongAnimalName(myZoo);
        assertThat(result).isIn(
            List.of(
                gosha,
                petya,
                sGregory
            )
        );
    }

    private Animal getMostLongAnimalName(List<Animal> myZoo) {
        return myZoo.stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }
}

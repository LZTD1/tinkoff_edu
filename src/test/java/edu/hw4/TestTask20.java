package edu.hw4;

import edu.hw4.Validations.Validator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static edu.hw4.Validations.Validator.getValidateReadable;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask20 {

    @NotNull private static Map<String, String> getErrorAnimalsConcatenate(List<Animal> myZoo) {
        return myZoo.stream()
            .filter(animal -> !getValidateReadable(animal).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                Validator::getValidateReadable
            ));
    }

    @Test
    void errorAnimals_Concatenate() {
        Animal FISH = new Animal("a", Animal.Type.FISH, Animal.Sex.M, 100, 100, 2, false);
        Animal FISH2 = new Animal("c", Animal.Type.FISH, Animal.Sex.F, 0, 100, 10000, true);
        Animal FISH3 = new Animal("b", Animal.Type.FISH, Animal.Sex.F, 0, 100, 1, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            FISH3
        );

        Map<String, String> errorAnimals = getErrorAnimalsConcatenate(myZoo);

        assertThat(errorAnimals).isEqualTo(
            Map.of(
                "c", "Too much weight!",
                "a", "Too much age!"
            )
        );

    }

    @Test
    void errorAnimals_ConcatenateNoOne() {
        Animal FISH3 = new Animal("b", Animal.Type.FISH, Animal.Sex.F, 0, 100, 1, true);

        List<Animal> myZoo = List.of(
            FISH3
        );

        Map<String, String> errorAnimals = getErrorAnimalsConcatenate(myZoo);

        assertThat(errorAnimals).isEmpty();

    }
}

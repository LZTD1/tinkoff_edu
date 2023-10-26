package edu.hw4;

import edu.hw4.Validations.AgeError;
import edu.hw4.Validations.ValidationError;
import edu.hw4.Validations.Validator;
import edu.hw4.Validations.WeightError;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static edu.hw4.Validations.Validator.getValidate;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask19 {

    @Test
    void errorAnimals() {
        Animal FISH = new Animal("a", Animal.Type.FISH, Animal.Sex.M, 100, 100, 2, false);
        Animal FISH2 = new Animal("c", Animal.Type.FISH, Animal.Sex.F, 0, 100, 10000, true);
        Animal FISH3 = new Animal("b", Animal.Type.FISH, Animal.Sex.F, 0, 100, 1, true);

        List<Animal> myZoo = List.of(
            FISH,
            FISH2,
            FISH3
        );

        Map<String, Set<ValidationError>> errorAnimals = myZoo.stream()
            .filter(animal -> !getValidate(animal).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                Validator::getValidate
            ));

        assertThat(errorAnimals).isEqualTo(
            Map.of(
                "c", Set.of(
                    new WeightError("Too much weight!", 100)
                ),
                "a", Set.of(
                    new AgeError("Too much age!", 101)
                )
            )
        );

    }
}

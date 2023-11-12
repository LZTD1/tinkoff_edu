package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask15 {

    @Test
    void sumWeightBy_k_and_i_paramsAge() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 0, 100, 1, false);
        Animal DOG = new Animal("Gosha", Animal.Type.DOG, Animal.Sex.M, 3, 25, 66, false);
        Animal DOG2 = new Animal("Sister`s Jack  Junior", Animal.Type.DOG, Animal.Sex.F, 5, 54, 2, true);
        Animal SPIDER2 = new Animal("Sister`s Jack Middle", Animal.Type.SPIDER, Animal.Sex.F, 1, 54, 2, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 4, 4, 50, true);
        Animal CAT2 = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 4, 4, 50, true);

        List<Animal> myZoo = List.of(
            FISH,
            DOG,
            DOG2,
            SPIDER2,
            CAT,
            CAT2
        );

        int k = 3;
        int i = 6;

        Map<Animal.Type, Integer> result = getSumWeightBy_k_and_i_paramsAge(myZoo, k, i);

        assertThat(result).isEqualTo(
            Map.of(
                Animal.Type.DOG, 2,
                Animal.Type.CAT, 100
            )
        );
    }

    @Test
    void sumWeightBy_k_and_i_paramsAge_NoOne() {
        Animal FISH = new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 7, 100, 66, false);
        Animal DOG = new Animal("Gosha", Animal.Type.DOG, Animal.Sex.M, 7, 25, 66, false);
        Animal DOG2 = new Animal("Sister`s Jack  Junior", Animal.Type.DOG, Animal.Sex.F, 7, 54, 66, true);
        Animal SPIDER2 = new Animal("Sister`s Jack Middle", Animal.Type.SPIDER, Animal.Sex.F, 7, 54, 66, true);
        Animal CAT = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 7, 4, 66, true);
        Animal CAT2 = new Animal("Sister`s Jack", Animal.Type.CAT, Animal.Sex.F, 7, 4, 66, true);

        List<Animal> myZoo = List.of(
            FISH,
            DOG,
            DOG2,
            SPIDER2,
            CAT,
            CAT2
        );

        int k = 3;
        int i = 6;

        Map<Animal.Type, Integer> result = getSumWeightBy_k_and_i_paramsAge(myZoo, k, i);

        assertThat(result).isEmpty();
    }

    private Map<Animal.Type, Integer> getSumWeightBy_k_and_i_paramsAge(List<Animal> myZoo, int k, int i) {
        return myZoo.stream()
            .filter(a -> a.age() > k && a.age() < i)
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.summingInt(Animal::weight)
                ));
    }
}

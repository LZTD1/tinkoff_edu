package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask5 {

    @Test
    void whoBiggerBySex() {
        List<Animal> myZoo = List.of(
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 3, 44, 3, false),
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false),
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false),
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false)
        );

        Animal.Sex result = getWhoBiggerBySex(myZoo);

        assertThat(result).isEqualTo(
            Animal.Sex.F
        );
    }

    @Test
    void whoBiggerBySex_ifSame() {
        List<Animal> myZoo = List.of(
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 3, 44, 3, false),
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.M, 3, 44, 3, false),
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false),
            new Animal("Gosha", Animal.Type.FISH, Animal.Sex.F, 3, 44, 3, false)
        );

        Animal.Sex result = getWhoBiggerBySex(myZoo);

        assertThat(result).isNull();
    }

    private Animal.Sex getWhoBiggerBySex(List<Animal> myZoo) {
        Map<Boolean, List<Animal>> myZooSex = myZoo.stream().collect(
            Collectors.partitioningBy(animal -> animal.sex() == Animal.Sex.F));

        if (myZooSex.get(false).size() == myZooSex.get(true).size()) {
            return null;
        }
        return myZooSex.get(false).size() > myZooSex.get(true).size() ? Animal.Sex.M : Animal.Sex.F;
    }
}

package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask17 {

    @Test
    void SpidersBitesMoreThatDogsTest() {
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

        var result = spidersBitesMoreThatDogs(myZoo);

        assertThat(result).isEqualTo(
            true
        );

    }

    @Test
    void SpidersBitesMoreThatDogsTest_AllBites() {
        Animal SPIDER = new Animal("a", Animal.Type.SPIDER, Animal.Sex.M, 0, 100, 1, true);
        Animal SPIDER1 = new Animal("c", Animal.Type.SPIDER, Animal.Sex.F, 0, 100, 1, true);
        Animal SPIDER2 = new Animal("b", Animal.Type.SPIDER, Animal.Sex.F, 0, 100, 1, true);

        Animal DOG = new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 1, true);
        Animal DOG1 = new Animal("c", Animal.Type.DOG, Animal.Sex.M, 0, 100, 1, true);
        Animal DOG2 = new Animal("b", Animal.Type.DOG, Animal.Sex.F, 0, 100, 1, true);

        List<Animal> myZoo = List.of(
            SPIDER,
            SPIDER1,
            SPIDER2,
            DOG,
            DOG1,
            DOG2
        );

        var result = spidersBitesMoreThatDogs(myZoo);

        assertThat(result).isEqualTo(
            false
        );

    }

    @Test
    void SpidersBitesMoreThatDogsTest_NoOneBites() {
        Animal SPIDER = new Animal("a", Animal.Type.SPIDER, Animal.Sex.M, 0, 100, 1, false);
        Animal SPIDER1 = new Animal("c", Animal.Type.SPIDER, Animal.Sex.F, 0, 100, 1, false);
        Animal SPIDER2 = new Animal("b", Animal.Type.SPIDER, Animal.Sex.F, 0, 100, 1, false);

        Animal DOG = new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 1, false);
        Animal DOG1 = new Animal("c", Animal.Type.DOG, Animal.Sex.M, 0, 100, 1, false);
        Animal DOG2 = new Animal("b", Animal.Type.DOG, Animal.Sex.F, 0, 100, 1, false);

        List<Animal> myZoo = List.of(
            SPIDER,
            SPIDER1,
            SPIDER2,
            DOG,
            DOG1,
            DOG2
        );

        var result = spidersBitesMoreThatDogs(myZoo);

        assertThat(result).isEqualTo(
            false
        );

    }

    private boolean spidersBitesMoreThatDogs(List<Animal> myZoo) {
        var bitesSpiders = myZoo.stream()
            .filter(animal -> animal.bites() && animal.type() == Animal.Type.SPIDER).count();
        var allSpiders = myZoo.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER).count();

        var bitesDogs = myZoo.stream()
            .filter(animal -> animal.bites() && animal.type() == Animal.Type.DOG).count();
        var allDogs = myZoo.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG).count();

        return (double) bitesSpiders / allSpiders > (double) bitesDogs / allDogs;
    }
}

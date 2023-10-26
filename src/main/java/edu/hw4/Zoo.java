package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zoo {

    private final List<Animal.Type> typeAnimals = new ArrayList<>() {{
        add(Animal.Type.CAT);
        add(Animal.Type.DOG);
        add(Animal.Type.BIRD);
        add(Animal.Type.FISH);
        add(Animal.Type.SPIDER);
    }};
    private final List<Animal.Sex> sexAnimals = new ArrayList<>() {{
        add(Animal.Sex.M);
        add(Animal.Sex.F);
    }};
    private final List<String> namesAnimals = new ArrayList<>() {{
        add("Robby");
        add("Bobby");
        add("Jack");
        add("Mackwey");
        add("Ronaldo");
        add("Paddington");
        add("Ash");
        add("Key");
    }};
    Random randomizer = new Random();
    private List<Animal> zoo;

    public Zoo(int countAnimal) {
        for (int i = 0; i < countAnimal; i++) {
            String name = namesAnimals.get(randomizer.nextInt(namesAnimals.size()));
            Animal.Sex sex = sexAnimals.get(randomizer.nextInt(sexAnimals.size()));
            Animal.Type type = typeAnimals.get(randomizer.nextInt(typeAnimals.size()));
            int age = randomizer.nextInt(1, 10);
            int height = randomizer.nextInt(10, 50);
            int weight = randomizer.nextInt(1, 5);
            boolean bites = type != Animal.Type.BIRD && type != Animal.Type.FISH;

            zoo.add(
                new Animal(
                    name,
                    type,
                    sex,
                    age,
                    height,
                    weight,
                    bites
                )
            );
        }
    }

    public List<Animal> getZoo() {
        return zoo;
    }
}

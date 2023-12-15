package edu.project5;

import java.lang.invoke.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import static java.lang.invoke.MethodType.methodType;

public class LambdaExample {

    public static void main(String[] args) throws Throwable {
        // Define a simple record class
        record Person(String name, int age) {
        }

        // Create a LambdaMetafactory
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.findVirtual(Person.class, "name", methodType(String.class));

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            "get",
            MethodType.methodType(Supplier.class, Person.class),
            MethodType.methodType(String.class, Person.class),
            methodHandle,
            MethodType.methodType(String.class)
        );
        Person person = new Person("John", 30);

        MethodHandle factory = callSite.getTarget();
        Supplier<String> nameExtractor = (Supplier<String>) factory.invokeExact(person);

        // Create an instance of the record
        System.out.println("Extracted Name: " + nameExtractor.get());
    }

}

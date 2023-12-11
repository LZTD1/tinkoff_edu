package edu.hw10.Task2;

import edu.hw10.Task2.Calculator.FibCalculator;
import edu.hw10.Task2.Calculator.SimpleFibCalculator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestTask2 {

    FibCalculator fibCache = (FibCalculator) new CacheProxy(
        new SimpleFibCalculator()
    ).getInstance();
    private final FibCalculator fib = new SimpleFibCalculator();

    @ParameterizedTest
    @CsvSource({
        "1, 1",
        "5, 5",
        "15, 610"
    })
    public void testFibonacci(int input, int expected) {
        var actual = (int) fib.fib(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1",
        "5, 5",
        "15, 610"
    })
    public void testCaching(int input, int expected) throws NoSuchMethodException, IOException {
        var actual = (int) fibCache.fib(input);

        var directory = Path.of(getNameDirectory(new SimpleFibCalculator()));
        var fileName = directory.resolve(getFileName(
            new SimpleFibCalculator(),
            "fib",
            input
        ));
        if (!Files.exists(directory)) {
            fail("Directory for caching is not created!");
        }
        if (!Files.exists(fileName)) {
            fail("File for caching is not created!");
        }

        assertEquals(expected, actual);

        Files.delete(fileName);
        Files.delete(directory);
    }

    private String getFileName(FibCalculator object, String method, Object... args) throws NoSuchMethodException {

        return String.valueOf(
            (object.getClass().getDeclaredMethod(method, int.class).getName()
                + Arrays.toString(args)
            ).hashCode()
        );
    }

    private String getNameDirectory(FibCalculator object) {
        return String.valueOf(object.getClass().getName().hashCode());
    }
}

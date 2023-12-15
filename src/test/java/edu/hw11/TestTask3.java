package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw11.Task3.FibGenerator.generateFibClass;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask3 {

    private static final Class<?> fibClass = generateFibClass();
    private static Method fibMethod;

    @BeforeAll
    public static void setUp() throws NoSuchMethodException {
        fibMethod = fibClass.getMethod("fib", int.class);
    }

    @ParameterizedTest
    @CsvSource({ // in, out
        "5, 5",
        "6, 8",
        "9, 34",
        "16, 987",
        "20, 6765",
    })
    void fibTest(int in, int out) throws InvocationTargetException, IllegalAccessException {
        assertThat(fibMethod.invoke(null, in)).isEqualTo(out);
    }
}

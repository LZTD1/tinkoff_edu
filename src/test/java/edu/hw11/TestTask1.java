package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {
    @Test
    void modify() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        var helloString = "Hello, ByteBuddy!";

        Class<?> modified = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(helloString))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        var constructor = modified.getConstructors()[0];
        var instanse = constructor.newInstance();

        assertThat(instanse.toString()).isEqualTo(helloString);
    }
}

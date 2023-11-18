package edu.hw5;

import java.util.regex.Pattern;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask4 {

    private static final Pattern pattern = Pattern.compile("[~!@#$%^&*|]");

    @ParameterizedTest
    @ValueSource(strings = {"helloWorld~", "hel&loWorld", "helloW^orld", "|helloWorld"})
    void testRegexTrue(String word) {
        assertThat(
            pattern.matcher(word).find()
        ).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"helloWorld", "alexandra"})
    void testRegexFalse(String word) {
        assertThat(
            pattern.matcher(word).find()
        ).isFalse();
    }
}

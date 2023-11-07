package edu.hw5;

import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask4 {
    private static Pattern pattern;

    @BeforeAll
    static void setUp() {
        pattern = Pattern.compile("[~!@#$%^&*|]");
    }

    @Test
    void testRegex() {
        assertThat(
            pattern.matcher("helloWorld~").find()
        ).isTrue();

        assertThat(
            pattern.matcher("hel&loWorld").find()
        ).isTrue();

        assertThat(
            pattern.matcher("helloW^orld").find()
        ).isTrue();

        assertThat(
            pattern.matcher("|helloWorld").find()
        ).isTrue();
    }

}

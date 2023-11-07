package edu.hw5;

import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask5 {
    private static Pattern pattern;

    @BeforeAll
    static void setUp() {
        pattern = Pattern.compile("^[АВЕКМНОРСТУХВ]{1}\\d{3}[АВЕКМНОРСТУХВ]{2}\\d{2,3}$");
    }

    @Test
    void testRegex() {
        assertThat(
            pattern.matcher("А123ВЕ777").find()
        ).isTrue();

        assertThat(
            pattern.matcher("О777ОО177").find()
        ).isTrue();

        assertThat(
            pattern.matcher("123АВЕ777").find()
        ).isFalse();
        assertThat(
            pattern.matcher("А123ВГ77").find()
        ).isFalse();
        assertThat(
            pattern.matcher("А123ВЕ7777").find()
        ).isFalse();
    }
}

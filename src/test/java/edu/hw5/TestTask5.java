package edu.hw5;

import java.util.regex.Pattern;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask5 {

    private static final Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");

    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177"})
    void testRegexTrue(String number) {
        assertThat(
            pattern.matcher(number).find()
        ).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777"})
    void testRegexFalse(String number) {
        assertThat(
            pattern.matcher(number).find()
        ).isFalse();
    }
}

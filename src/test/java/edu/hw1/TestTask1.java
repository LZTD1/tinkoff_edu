package edu.hw1;

import edu.hw1.Exceptions.InvalidFormatTimeException;
import edu.hw1.Exceptions.InvalidSecondFormatException;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task1.minutesToSeconds;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTask1 {
    @Test
    void sucessfullConvertTest() throws InvalidSecondFormatException, InvalidFormatTimeException {
        // given
        String str = "12:12";

        // when
        int converted = minutesToSeconds(str);

        // then
        assertThat(converted).isEqualTo(732);
    }

    @Test
    void sucessfullConvertTest_2() throws InvalidSecondFormatException, InvalidFormatTimeException {
        // given
        String str = "10:00";

        // when
        int converted = minutesToSeconds(str);

        // then
        assertThat(converted).isEqualTo(600);
    }

    @Test
    void InvalidFormatTimeTest() throws InvalidSecondFormatException, InvalidFormatTimeException {
        // given
        String str = "12:12:12";

        // when
        assertThrows(InvalidFormatTimeException.class, () -> {
           minutesToSeconds(str);
        });
    }

    @Test
    void InvalidFormatTimeTest_2() throws InvalidSecondFormatException, InvalidFormatTimeException {
        // given
        String str = "12";

        // when
        assertThrows(InvalidFormatTimeException.class, () -> {
            minutesToSeconds(str);
        });
    }

    @Test
    void InvalidSecondFormatTest() throws InvalidSecondFormatException, InvalidFormatTimeException {
        // given
        String str = "12:60";

        // when
        assertThrows(InvalidSecondFormatException.class, () -> {
            minutesToSeconds(str);
        });
    }

    @Test
    void NumberFormatExceptionTest() throws InvalidSecondFormatException, InvalidFormatTimeException {
        // given
        String str = "12:ABC";

        // when
        assertThrows(NumberFormatException.class, () -> {
            minutesToSeconds(str);
        });
    }
}

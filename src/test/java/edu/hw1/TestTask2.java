package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.Task2.countDigits;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {
    @Test
    void sucessfullLenTest() {
        // given
        int str = 333;

        // when
        int converted = countDigits(str);

        // then
        assertThat(converted).isEqualTo(3);
    }

    @Test
    void sucessfullLenTest_2() {
        // given
        int str = 1414241;

        // when
        int converted = countDigits(str);

        // then
        assertThat(converted).isEqualTo(7);
    }

    @Test
    void sucessfullLenTest_3() {
        // given
        int str = 1;

        // when
        int converted = countDigits(str);

        // then
        assertThat(converted).isEqualTo(1);
    }
}

package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.Task5.isPalindromeDescendant;
import static edu.hw1.Task5.reduceArray;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask5 {
    @Test
    void isPalindromeOK() {
        // given
        int decimal = 11211230;

        // when
        boolean result = isPalindromeDescendant(decimal);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void isPalindromeOK_2() {
        // given
        int decimal = 13001120;

        // when
        boolean result = isPalindromeDescendant(decimal);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void isPalindromeOK_3() {
        // given
        int decimal = 23336014;

        // when
        boolean result = isPalindromeDescendant(decimal);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void isPalindromeOK_4() {
        // given
        int decimal = 11;

        // when
        boolean result = isPalindromeDescendant(decimal);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void reduceArrayOK() {
        // given
        int numbers = 11211230;

        // when
        int result = reduceArray(numbers);

        // then
        assertThat(result).isEqualTo(2333);
    }

    @Test
    void reduceArrayOK_2() {
        // given
        int numbers = 2333;

        // when
        int result = reduceArray(numbers);

        // then
        assertThat(result).isEqualTo(56);
    }

    @Test
    void reduceArrayOK_3() {
        // given
        int numbers = 13001120;

        // when
        int result = reduceArray(numbers);

        // then
        assertThat(result).isEqualTo(4022);
    }
}

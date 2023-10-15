package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.Task7.rotateLeft;
import static edu.hw1.Task7.rotateRight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask7 {
    @Test
    void rotateRight_OK() {
        // given
        int numberToRotate = 8;
        int shift = 1;

        // when
        int rotated = rotateRight(numberToRotate, shift);

        // then
        assertThat(rotated).isEqualTo(4);
    }

    @Test
    void rotateLeft2_OK() {
        // given
        int numberToRotate = 16;
        int shift = 1;

        // when
        int rotated = rotateLeft(numberToRotate, shift);

        // then
        assertThat(rotated).isEqualTo(1);
    }

    @Test
    void rotateLeft3_OK() {
        // given
        int numberToRotate = 17;
        int shift = 2;

        // when
        int rotated = rotateLeft(numberToRotate, shift);

        // then
        assertThat(rotated).isEqualTo(6);
    }
}

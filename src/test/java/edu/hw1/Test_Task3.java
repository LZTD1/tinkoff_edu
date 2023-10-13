package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.task_3.isNestable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test_Task3 {
    @Test
    void sucessfullTest() {
        // given
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};

        // when
        boolean result = isNestable(a1, a2);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void sucessfullTest_2() {
        // given
        int[] a1 = {3, 1};
        int[] a2 = {4, 0};

        // when
        boolean result = isNestable(a1, a2);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void sucessfullTest_3() {
        // given
        int[] a1 = {9, 9, 8};
        int[] a2 = {8, 9};

        // when
        boolean result = isNestable(a1, a2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    void sucessfullTest_4() {
        // given
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {2, 3};

        // when
        boolean result = isNestable(a1, a2);

        // then
        assertThat(result).isEqualTo(false);
    }

}

package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.task_6.countK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test_Task6 {
    @Test
    void CountK_OK(){
        // given
        int numbers = 1234;

        // when
        int result = countK(numbers);

        // then
        assertThat(result).isEqualTo(3);
    }
    @Test
    void CountK_OK_2(){
        // given
        int numbers = 6554;

        // when
        int result = countK(numbers);

        // then
        assertThat(result).isEqualTo(4);
    }
    @Test
    void CountK_OK_3(){
        // given
        int numbers = 6621;

        // when
        int result = countK(numbers);

        // then
        assertThat(result).isEqualTo(5 );
    }

}

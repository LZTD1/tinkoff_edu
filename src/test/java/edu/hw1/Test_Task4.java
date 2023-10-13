package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.task_4.fixString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test_Task4 {
    @Test
    void swappingTest(){
        // given
        String stringToSwap = "123456";

        // when
        String converted = fixString(stringToSwap);

        // then
        assertThat(converted).isEqualTo("214365");
    }
    @Test
    void swappingTest_2(){
        // given
        String stringToSwap = "hTsii  s aimex dpus rtni.g";

        // when
        String converted = fixString(stringToSwap);

        // then
        assertThat(converted).isEqualTo("This is a mixed up string.");
    }
    @Test
    void swappingTest_3(){
        // given
        String stringToSwap = "badce";

        // when
        String converted = fixString(stringToSwap);

        // then
        assertThat(converted).isEqualTo("abcde");
    }
}

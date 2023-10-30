package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask4 {

    @Test
    void TestConvert_2(){
        int number = 2;

        String res = convertToRoman(number);

        assertThat(res).isEqualTo("II");
    }
    @Test
    void TestConvert_12(){
        int number = 12;

        String res = convertToRoman(number);

        assertThat(res).isEqualTo("XII");
    }
    @Test
    void TestConvert_16(){
        int number = 16;

        String res = convertToRoman(number);

        assertThat(res).isEqualTo("XVI");
    }
    @Test
    void TestConvert_10(){
        int number = 10;

        String res = convertToRoman(number);

        assertThat(res).isEqualTo("X");
    }

}

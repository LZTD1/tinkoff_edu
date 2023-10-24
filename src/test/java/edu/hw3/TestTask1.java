package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task1.atbash;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {

    @Test
    void atbashTest(){
        String result = atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler");

        assertThat(result).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }
    @Test
    void atbashTest_2(){
        String result = atbash("Hello world!");

        assertThat(result).isEqualTo("Svool dliow!");
    }

}

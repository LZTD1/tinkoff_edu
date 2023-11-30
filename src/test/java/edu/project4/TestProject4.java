package edu.project4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TestProject4 {
    @Test
    void testFlameGenerateValidMatrix(){
        var myFlame = new Flame(2, 10, 100, 1.0, false, 1);

        short[][][] before = myFlame.getMatrixDisplay();
        myFlame.render();
        short[][][] after = myFlame.getMatrixDisplay();

        assertThat(before).isNotEqualTo(after);
    }
    @Test
    void testAfterCorrection(){
        var myFlame = new Flame(2, 10, 100, 1.0, false, 1);

        myFlame.render();
        short[][][] before = myFlame.getMatrixDisplay();
        short[][][] image = myFlame.postRendering();

        assertThat(before).isNotEqualTo(image);
    }
}

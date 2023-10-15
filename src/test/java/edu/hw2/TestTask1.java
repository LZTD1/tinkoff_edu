package edu.hw2;

import edu.hw2.Task1.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask1 {
    @Test
    void ConstantTest() {
        double number = 4;
        var object = new Constant(number);
        assertThat(object).isInstanceOf(Expr.class);
    }

    @Test
    void NegateTest() {
        double number = 4;

        var object = new Negate(new Constant(number));

        assertThat(object.evaluate()).isEqualTo(-4);
    }
    @Test
    void AdditionTest() {
        var two = new Constant(2);
        var four = new Constant(4);

        var object = new Addition(two, four);

        assertThat(object.evaluate()).isEqualTo(6);
    }
    @Test
    void MultiplicationTest() {
        var two = new Constant(2);
        var four = new Constant(4);

        var object = new Multiplication(two, four);

        assertThat(object.evaluate()).isEqualTo(8);
    }
    @Test
    void ExponentTest_ObjectToDouble() {
        var two = new Constant(2);

        var object = new Exponent(two, 4);

        assertThat(object.evaluate()).isEqualTo(16);
    }
    @Test
    void ExponentTest_ObjectToObject() {
        var two = new Constant(2);
        var four = new Constant(4);

        var object = new Exponent(two, four);

        assertThat(object.evaluate()).isEqualTo(16);
    }

}

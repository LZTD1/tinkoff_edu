package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        var react_area = rect.setWidth(20).setHeight(10);

        assertThat(react_area.area()).isEqualTo(200.0);
    }

    @Test
    void rectangleArea_setWidth() {
        var rect = new Rectangle(10, 10);

        var react_area = rect.setWidth(20).area();

        assertThat(react_area).isEqualTo(200.0);
    }

    @Test
    void rectangleArea_setHeight() {
        var rect = new Rectangle(10, 10);

        var react_area = rect.setHeight(20).area();

        assertThat(react_area).isEqualTo(200.0);
    }

    @Test
    void squareArea_setSize() {
        var square = new Square(10);

        var square_area = square.setSide(20).area();

        assertThat(square_area).isEqualTo(400);
    }

    @Test
    void squareArea_setWidth() {
        var square = new Square(10);

        var square_area = square.setWidth(20).area();

        assertThat(square_area).isEqualTo(200);
    }

    @Test
    void Square_isInstanceOf_Rectangle() {
        var square = new Square();

        assertThat(square).isInstanceOf(Rectangle.class);
    }
}

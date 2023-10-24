package edu.hw3;

import edu.hw3.Task2.Exceptions.InvalidClusterize;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task2.Task2.clusterize;
import static edu.hw3.Task2.Task2.isValidString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTask2 {
    @Test
    void checkIsValidStringOk() {
        String s = "()()()";

        var result = isValidString(s);

        assertThat(result).isTrue();
    }

    @Test
    void checkIsValidStringOk_2() {
        String s = "((()))";

        var result = isValidString(s);

        assertThat(result).isTrue();
    }

    @Test
    void checkIsValidStringOk_3() {
        String s = "((()))(())()()(()())";

        var result = isValidString(s);

        assertThat(result).isTrue();
    }

    @Test
    void checkIsValidStringOk_4() {
        String s = "((())())(()(()()))";

        var result = isValidString(s);

        assertThat(result).isTrue();
    }

    @Test
    void checkIsValidStringFail() {
        String s = "((()))())(()(()()))";

        var result = isValidString(s);

        assertThat(result).isFalse();
    }

    @Test
    void checkIsValidStringFail_1() {
        String s = "(";

        var result = isValidString(s);

        assertThat(result).isFalse();
    }

    @Test
    void checkIsValidStringFail_2() {
        String s = "(()()()";

        var result = isValidString(s);

        assertThat(result).isFalse();
    }

    @Test
    void checkClusterize_Fail() {
        String s = "(()()()";

        assertThrows(InvalidClusterize.class, () -> clusterize(s));
    }

    @Test
    void checkClusterize_Ok() {
        String s = "()()()";

        var result = clusterize(s);
        List<String> clusterized = new ArrayList<>() {{
            add("()");
            add("()");
            add("()");
        }};

        assertThat(result).isEqualTo(clusterized);
    }

    @Test
    void checkClusterize_Ok2() {
        String s = "((()))(())()()(()())";

        var result = clusterize(s);
        List<String> clusterized = new ArrayList<>() {{
            add("((()))");
            add("(())");
            add("()");
            add("()");
            add("(()())");
        }};

        assertThat(result).isEqualTo(clusterized);
    }

    @Test
    void checkClusterize_Ok3() {
        String s = "((())())(()(()()))";

        var result = clusterize(s);
        List<String> clusterized = new ArrayList<>() {{
            add("((())())");
            add("(()(()()))");
        }};

        assertThat(result).isEqualTo(clusterized);
    }
}

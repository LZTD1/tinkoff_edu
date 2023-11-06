package edu.hw5;

import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task1.getAverageDurationSession;
import static edu.hw5.Task1.getSessionDuration;
import static edu.hw5.Task1.getSplitedDiapazon;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {
    @Test
    void testRegexSplit() {
        var a = getSplitedDiapazon("2022-04-01, 21:30 - 2022-04-02, 01:20");

        assertThat(!a.isEmpty()).isTrue();
        assertThat(a.size()).isEqualTo(2);
        assertThat(a).isEqualTo(List.of(
            "2022-04-01, 21:30",
            "2022-04-02, 01:20"
        ));
    }

    @Test
    void testRegexSplitError() {
        var a = getSplitedDiapazon("2022-04-01, 21:30 - 2022-04-02, 01:20 - 2022-04-02, 01:20");

        assertThat(a.isEmpty()).isTrue();
    }

    @Test
    void testRegexSplitError2() {
        List<String> a = getSplitedDiapazon("2022-04-01, 21:30 04-02, 01:20");

        assertThat(a.isEmpty()).isTrue();
    }

    @Test
    void getSessionDurationTest_Failed() {
        var a = getSessionDuration("2022;04-01, 21:30 - 2022-04-01, 21:32");

        assertThat(a).isNull();
    }

    @Test
    void getSessionDurationTest() {
        Long a = getSessionDuration("2022-04-01, 21:30 - 2022-04-01, 21:32");

        assertThat(a).isEqualTo(120L);
    }

    @Test
    void getAverageDurationSessionTest() {
        String a = getAverageDurationSession("2022-03-12, 20:20 - 2022-03-12, 23:50\n" +
            "2022-04-01, 21:30 - 2022-04-02, 01:20");

        assertThat(a).isNotNull();
        assertThat(a).isEqualTo("3ч 40м");
    }

}

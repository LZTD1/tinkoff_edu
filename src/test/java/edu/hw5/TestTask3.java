package edu.hw5;

import edu.hw5.Task3.DateFormats.DateFormat;
import edu.hw5.Task3.DateFormats.DateFormat2;
import edu.hw5.Task3.DateFormats.DateFormat3;
import edu.hw5.Task3.DateFormats.DateFormat4;
import edu.hw5.Task3.DateFormats.DateFormat5;
import edu.hw5.Task3.DateFormats.DateFormat6;
import edu.hw5.Task3.FormatDate;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask3 {
    private static FormatDate formatDates;

    @BeforeAll
    public static void setUp() {
        formatDates = FormatDate.link(
            new DateFormat(),
            new DateFormat2(),
            new DateFormat3(),
            new DateFormat4(),
            new DateFormat5(),
            new DateFormat6()
        );
    }

    @Test
    void testConvert() {
        assertThat(formatDates.getDateFromString("2020-10-10")).isEqualTo(
            Optional.of(
                LocalDate.of(2020, 10, 10)
            )
        );
    }

    @Test
    void testConvert_2() {
        assertThat(formatDates.getDateFromString("2020-12-2")).isEqualTo(
            Optional.of(
                LocalDate.of(2020, 12, 2)
            )
        );
    }

    @Test
    void testConvert_3() {
        assertThat(formatDates.getDateFromString("1/3/1976")).isEqualTo(
            Optional.of(
                LocalDate.of(1976, 1, 3)
            )
        );
    }

    @Test
    void testConvert_4() {
        assertThat(formatDates.getDateFromString("1/3/20")).isEqualTo(
            Optional.of(
                LocalDate.of(2020, 1, 3)
            )
        );
    }

    @Test
    void testConvert_5() {
        assertThat(formatDates.getDateFromString("tomorrow")).isEqualTo(
            Optional.of(
                LocalDate.now().plusDays(1)
            )
        );
    }

    @Test
    void testConvert_6() {
        assertThat(formatDates.getDateFromString("today")).isEqualTo(
            Optional.of(
                LocalDate.now()
            )
        );
    }

    @Test
    void testConvert_7() {
        assertThat(formatDates.getDateFromString("yesterday")).isEqualTo(
            Optional.of(
                LocalDate.now().minusDays(1)
            )
        );
    }

    @Test
    void testConvert_8() {
        assertThat(formatDates.getDateFromString("1 day ago\n")).isEqualTo(
            Optional.of(
                LocalDate.now().minusDays(1)
            )
        );
    }

    @Test
    void testConvert_9() {
        assertThat(formatDates.getDateFromString("10 day ago\n")).isEqualTo(
            Optional.of(
                LocalDate.now().minusDays(10)
            )
        );
    }

    @Test
    void testConvert_10() {
        assertThat(formatDates.getDateFromString("10 day before\n")).isEqualTo(
            Optional.of(
                LocalDate.now().plusDays(10)
            )
        );
    }
}

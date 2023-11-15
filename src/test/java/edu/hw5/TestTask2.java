package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task2.getAllScaryFridayOnTheYear;
import static edu.hw5.Task2.getNearestScaryFriday;
import static org.assertj.core.api.Assertions.assertThat;

class TestTask2 {

    @Test
    void getAllScaryFridayOnTheYearTest() {
        int year = 1925;

        List<String> result = getAllScaryFridayOnTheYear(year);

        assertThat(result).isEqualTo(
            List.of(
                "1925-02-13",
                "1925-03-13",
                "1925-11-13"
            )
        );
    }

    @Test
    void getAllScaryFridayOnTheYearTest_2() {
        int year = 2024;

        List<String> result = getAllScaryFridayOnTheYear(year);

        assertThat(result).isEqualTo(
            List.of(
                "2024-09-13",
                "2024-12-13"
            )
        );
    }

    @Test
    void getAllScaryFridayOnTheYearTest_Failed() {
        int year = 1925;

        List<String> result = getAllScaryFridayOnTheYear(year);

        assertThat(result).isNotEqualTo(
            List.of(
                "2024-09-13",
                "2024-12-13",
                "1925-11-13"
            )
        );
    }

    @Test
    void getNearestScaryFridayTest(){
        LocalDate myDate = LocalDate.of(2024, 9, 14);

        var result = getNearestScaryFriday(myDate);

        assertThat(result).isEqualTo(
            LocalDate.of(2024, 12, 13)
        );
    }
}

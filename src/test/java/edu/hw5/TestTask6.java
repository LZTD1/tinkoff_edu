package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw5.Task6.isSubsequence;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask6 {
    @ParameterizedTest
    @CsvSource({
        "abc, achfdbaabgabcaabg",
        "hello, hheelllloo",
        "'', acb",
        "'', ''",
        "123, 112233",
        "aaa, aaaaa"
    })
    public void testIsSubsequenceTrue(String substring, String superstring) {
        assertTrue(isSubsequence(substring, superstring));
    }

    @ParameterizedTest
    @CsvSource({
        "abc, ''",
        "123, 321",
        "abc, acb",
        "aaa, aa"
    })
    public void testIsSubsequenceFalse(String substring, String superstring) {
        assertFalse(isSubsequence(substring, superstring));
    }
}

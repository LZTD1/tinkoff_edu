package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.isSubsequence;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask6 {
    @Test
    public void testIsSubsequence() {
        assertTrue(isSubsequence("abc", "achfdbaabgabcaabg"));
        assertTrue(isSubsequence("hello", "hheelllloo"));
        assertFalse(isSubsequence("abc", "acb"));
        assertTrue(isSubsequence("", "acb"));  // пустая строка является подпоследовательностью любой строки
        assertFalse(isSubsequence("abc", ""));  // непустая строка не может быть подпоследовательностью пустой строки
        assertTrue(isSubsequence("", ""));  // пустая строка является подпоследовательностью пустой строки
        assertTrue(isSubsequence("123", "112233"));
        assertFalse(isSubsequence("123", "321"));
        assertTrue(isSubsequence("aaa", "aaaaa"));
        assertFalse(isSubsequence("aaa", "aa"));
    }
}

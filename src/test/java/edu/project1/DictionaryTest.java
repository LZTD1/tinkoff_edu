package edu.project1;

import edu.project1.Dictionary;
import edu.project1.Exceptions.ErrorPersionJsonString;
import edu.project1.Exceptions.ReadFileWrongException;
import edu.project1.Model.WordsObjectModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class DictionaryTest {
    @AfterAll
    static void cleanUp() {
        var dictObj = new Dictionary("src/test/resources/dict.json");
        dictObj.deleteWord("tested");
    }

    @Test
    void initializateDictionary_OK() {
        var words = new Dictionary().getDictionaryObject();

        assertThat(words).isInstanceOf(WordsObjectModel.class);
    }

    @Test
    void initializateDictionary_Failed() {
        assertThrows(ReadFileWrongException.class, () -> new Dictionary("null"));
    }

    @Test
    void initializateDictionary_FailedJson() {
        assertThrows(ErrorPersionJsonString.class, () -> new Dictionary("src/test/resources/dict_failed.json"));
    }

    @Test
    void getRandomWordTest() {
        var dictObj = new Dictionary();
        var myWord = dictObj.getRandomWord();

        assertThat(myWord).isInstanceOf(String.class);

        boolean containsString = false;
        for (String str : dictObj.getDictionaryObject().words) {
            if (str.equals(myWord)) {
                containsString = true;
                break;
            }
        }

        if (!containsString) {
            fail("An unexpected word!\n" +
                "This string is not contained in the array!");
        }
    }

    @Test
    void addWordTest_OK() {
        var dictObj = new Dictionary("src/test/resources/dict.json");

        boolean result = dictObj.addOwnWord("tested");

        assertThat(result).isTrue();
    }

    void addWordTest_AlreadyExists() {
        var dictObj = new Dictionary("src/test/resources/dict.json");

        boolean result = dictObj.addOwnWord("tested");

        assertThat(result).isFalse();
    }

    void deleteWordTest_OK() {
        var dictObj = new Dictionary("src/test/resources/dict.json");

        dictObj.deleteWord("tested");
        boolean result = dictObj.addOwnWord("tested");

        assertThat(result).isTrue();
    }
}

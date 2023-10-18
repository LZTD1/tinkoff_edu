package edu.project1;

import edu.project1.Exceptions.PutFileWrongException;
import edu.project1.Exceptions.ReadFileWrongException;
import org.junit.jupiter.api.Test;
import static edu.project1.Utils.GetTextFromFile.getTextFromFile;
import static edu.project1.Utils.PutTextToFile.putTextToFile;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilTest {
    @Test
    void getTextFromFileTest_OK() {
        var returned = getTextFromFile("src/test/resources/dict.json");

        assertThat(returned).isInstanceOf(String.class);
    }

    @Test
    void getTextFromFileTest_Failed() {
        assertThrows(ReadFileWrongException.class, () -> getTextFromFile("null"));
    }

    @Test
    void putTextToFileTest_FailedWrongPath() {
        String json = getTextFromFile("src/test/resources/dict.json");

        assertThrows(PutFileWrongException.class, () -> putTextToFile(json, "src/test/resources/dict_d1k2.json"));
    }

    @Test
    void putTextToFileTest_OK() {
        String json = getTextFromFile("src/test/resources/dict.json");

        putTextToFile(json, "src/test/resources/dict.json");
    }
}

package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static edu.hw3.Task3.freqDict;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask3 {

    @Test
    void freqDictTest_String(){
        List<String> myStrings = new ArrayList<>(){{
           add("a");
           add("bb");
           add("a");
           add("bb");
        }};

        var result = freqDict(myStrings);

        assertThat(result).isEqualTo(new HashMap<Object, Integer>(){{
            put("a", 2);
            put("bb", 2);
        }});

    }
    @Test
    void freqDictTest_String2(){
        List<String> myStrings = new ArrayList<>(){{
            add("this");
            add("and");
            add("that");
            add("and");
        }};

        var result = freqDict(myStrings);

        assertThat(result).isEqualTo(new HashMap<Object, Integer>(){{
            put("that", 1);
            put("and", 2);
            put("this", 1);
        }});
    }
    @Test
    void freqDictTest_String3(){
        List<String> myStrings = new ArrayList<>(){{
            add("код");
            add("код");
            add("код");
            add("bug");
        }};

        var result = freqDict(myStrings);

        assertThat(result).isEqualTo(new HashMap<Object, Integer>(){{
            put("код", 3);
            put("bug", 1);
        }});
    }
    @Test
    void freqDictTest_Integer(){
        List<Integer> myStrings = new ArrayList<>(){{
            add(1);
            add(1);
            add(2);
            add(2);
        }};

        var result = freqDict(myStrings);

        assertThat(result).isEqualTo(new HashMap<Object, Integer>(){{
            put(1, 2);
            put(2, 2);
        }});
    }
}

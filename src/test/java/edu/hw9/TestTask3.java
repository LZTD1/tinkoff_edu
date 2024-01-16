package edu.hw9;

import edu.hw9.Task3.DeepSearch;
import edu.hw9.Task3.Entities.Node;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import static edu.hw9.Task3.Utils.generateTree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask3 {

    @Test
    void testGenerateNode(){
        Node result = generateTree(5, 20);

        assertThat(result.getNodes()).isNotEmpty();
        assertThat(result.getNodes()).isNotNull();
    }
    @Test
    void searchValue(){
        Node node = generateTree(5, 5);

        try(ForkJoinPool pool = new ForkJoinPool()){
            Boolean result = pool.invoke(new DeepSearch(node, 1));

            assertTrue(result);
        }
    }
    @Test
    void searchValueFail(){
        Node node = generateTree(5, 5);

        try(ForkJoinPool pool = new ForkJoinPool()){
            Boolean result = pool.invoke(new DeepSearch(node, 6));

            assertFalse(result);
        }
    }
}

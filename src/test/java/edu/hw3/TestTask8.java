package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask8 {

    @Test
    void testBackwardIterator(){
        var a =  new BackwardIterator<>(List.of(1,2,3));

        assertThat(a.next()).isEqualTo(3);
        assertThat(a.next()).isEqualTo(2);
        assertThat(a.next()).isEqualTo(1);
    }
}

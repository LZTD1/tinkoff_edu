package edu.project2;

import edu.project2.Interfaces.MazeGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMaze {
    @Test
    void testFillMaze(){
        Maze myMaze = new Maze(5);
        MazeGenerator mg = new SimplifyMazeGenerator();

        mg.fillMaze(myMaze);

        for(int[] rows : myMaze.getMaze()){
            System.out.println(Arrays.toString(rows));
            assertThat(rows).containsOnly(1);
        }

    }
}

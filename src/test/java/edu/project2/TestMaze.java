package edu.project2;

import edu.project2.Interfaces.MazeGenerator;
import edu.project2.Interfaces.SimplifyMazeGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMaze {
    @Test
    void testFillMaze(){
        Maze myMaze = new Maze(10);
        MazeGenerator mazeGen = new SimplifyMazeGenerator();

        var filledMaze = mazeGen.fillMaze(myMaze);

        for(int[] rows : filledMaze.getMaze()){
            assertThat(rows).containsOnly(1);
        }
        for(int[] rows : myMaze.getMaze()){
            assertThat(rows).containsOnly(0);
        }
    }
    @Test
    void testPrintMaze(){
        Maze myMaze = new Maze(30, 100);
        MazeGenerator mg = new SimplifyMazeGenerator();

        var filledMaze = mg.fillMaze(myMaze);
        var genMaze = mg.generateMaze(filledMaze);


    }
}

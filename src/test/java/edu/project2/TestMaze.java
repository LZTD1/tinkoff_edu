package edu.project2;

import edu.project2.Interfaces.MazeGenerator;
import edu.project2.Interfaces.SimplifyGenerator.SimplifyMazeGeneratorWithStack;
import org.junit.jupiter.api.Test;

import static edu.project2.ConsoleDrawer.drawMaze;
import static org.assertj.core.api.Assertions.assertThat;

public class TestMaze {
    @Test
    void testFillMaze(){
        Maze myMaze = new Maze(10);
        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();

        var filledMaze = mazeGen.fillMaze(myMaze);

        for(Maze.MazeValues[] rows : filledMaze.getMaze()){
            assertThat(rows).containsOnly(Maze.MazeValues.WALL);
        }
        for(Maze.MazeValues[] rows : myMaze.getMaze()){
            assertThat(rows).containsOnly((Maze.MazeValues) null);
        }
    }
    @Test
    void isValidGenerated(){
        Maze myMaze = new Maze(15);
        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();

        var filledMaze = mazeGen.fillMaze(myMaze);
        var genMaze = mazeGen.generateMaze(filledMaze);

        var isGeneratedOk = mazeGen.isMazeValid(genMaze);
        var isGeneratedFalse = mazeGen.isMazeValid(filledMaze);

        drawMaze(genMaze);

        assertThat(isGeneratedOk).isTrue();
        assertThat(isGeneratedFalse).isFalse();
    }
}

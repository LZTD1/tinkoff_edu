package edu.project2.RecursionMazeGenerator;

import edu.project2.Interfaces.MazeGenerator;
import edu.project2.Maze;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static edu.project2.ConsoleDrawer.drawMaze;

public class RecursionMazeGenerator implements MazeGenerator {

    private Maze maze;
    private RecursionTank tank;

    @Override
    public Maze generateMaze(Maze maze) {
        Maze newMaze = getNewObjectMaze(maze);
        this.maze = newMaze;

        RecursionTank tank = new RecursionTank(
            newMaze,
            List.of(
                newMaze.getWidth() / 2,
                newMaze.getHeight() / 2
            )
        );
        this.tank = tank;

        List<Integer> cr = this.tank.getCurrentPosition();
        tank.move(cr, newMaze);

        return newMaze;
    }
    @Override
    public Maze fillMaze(Maze originalMaze) {
        Maze newMaze = getNewObjectMaze(originalMaze);

        for (Maze.MazeValues[] rows : newMaze.getMaze()) {
            Arrays.fill(rows, Maze.MazeValues.WALL);
        }

        return newMaze;
    }

    @Override
    public Maze getNewObjectMaze(Maze originalMaze) {
        int height = originalMaze.getHeight();
        int width = originalMaze.getWidth();

        Maze.MazeValues[][] oMaze = originalMaze.getMaze();
        Maze.MazeValues[][] newMaze = new Maze.MazeValues[height][width];

        for (int i = 0; i < height; i++) {
            System.arraycopy(oMaze[i], 0, newMaze[i], 0, width);
        }

        return new Maze(
            newMaze
        );
    }

    @Override
    public boolean isMazeValid(Maze maze) {
        Maze.MazeValues[][] myMaze = maze.getMaze();
        for (int i = 1; i < myMaze.length - 1; i += 2) {
            for (int j = 1; j < myMaze[i].length - 1; j += 2) {
                if (myMaze[i][j] == Maze.MazeValues.WALL) {
                    return false;
                }
            }
        }

        return true;
    }
}

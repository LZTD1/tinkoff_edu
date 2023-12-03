package edu.project2.MazeGenerators.RecursionMazeGenerator;

import edu.project2.Interfaces.MazeGenerator;
import edu.project2.Maze;
import java.util.Arrays;
import java.util.List;

public class RecursionMazeGenerator implements MazeGenerator {

    private Maze maze;
    private RecursionTank tank;

    @Override
    public Maze generateMazeWithDraw(Maze maze) {
        return generateMaze(maze, true);
    }

    @Override
    public Maze generateMaze(Maze maze, boolean renderPerFrame) {
        this.maze = getNewObjectMaze(maze);

        this.tank = new RecursionTank(
            this.maze,
            List.of(
                this.maze.getWidth() / 2,
                this.maze.getHeight() / 2
            )
        );

        List<Integer> cr = this.tank.getCurrentPosition();
        tank.move(cr, this.maze, renderPerFrame);

        return this.maze;
    }

    @Override
    public Maze generateMaze(Maze maze) {
        return generateMaze(maze, false);
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

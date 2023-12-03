package edu.project2.MazeGenerators.SimplifyGenerator;

import edu.project2.Interfaces.MazeGenerator;
import edu.project2.Maze;
import java.util.Arrays;
import java.util.List;
import static edu.project2.ConsoleDrawer.drawMaze;

public class SimplifyMazeGeneratorWithStack implements MazeGenerator {

    public SimplifyMazeGeneratorWithStack() {
    }

    @Override
    public Maze generateMazeWithDraw(Maze maze) {
        return generateMaze(maze, true);
    }

    @Override
    public Maze generateMaze(Maze maze, boolean renderPerFrame) {
        Maze newMaze = getNewObjectMaze(maze);

        Tank tank = new Tank(
            newMaze,
            List.of(
                newMaze.getWidth() / 2,
                newMaze.getHeight() / 2
            )
        );

        List<Integer> cr = tank.getCurrentPosition();
        newMaze.setTank(
            cr.get(0),
            cr.get(1)
        );

        while (!isMazeValid(newMaze)) {
            newMaze.setEmpty(
                cr.get(0),
                cr.get(1)
            );
            cr = tank.move(newMaze);

            List<Integer> lastMovment = tank.getLastMovment();
            newMaze.setTank(
                cr.get(0),
                cr.get(1)
            );
            newMaze.setEmpty(
                cr.get(0) - lastMovment.get(0) / 2,
                cr.get(1) - lastMovment.get(1) / 2
            );
            if (renderPerFrame) {
                drawMaze(newMaze);
            }
        }
        newMaze.setEmpty(
            cr.get(0),
            cr.get(1)
        );
        return newMaze;
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

package edu.project2.Interfaces;

import edu.project2.Interfaces.MazeGenerator;
import edu.project2.Maze;
import edu.project2.Tank;
import java.util.Arrays;
import java.util.List;
import static edu.project2.ConsoleDrawer.drawMaze;

public class SimplifyMazeGenerator implements MazeGenerator {

    @Override
    public Maze generateMaze(Maze maze) {
        Maze newMaze = getNewObjectMaze(maze);

        var tank = new Tank(
            newMaze,
            List.of(
                newMaze.getWidth() / 2,
                newMaze.getHeight() / 2
            )

        );

        List<Integer> cr = tank.getCurrentPosition();
        System.out.println(cr);
        newMaze.setTank(
            cr.get(0),
            cr.get(1)
        );

        int numSteps = 1000;

        for (int i = 0; i < numSteps; i++) {
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
                cr.get(1)  - lastMovment.get(1) / 2
            );

            drawMaze(newMaze);
            System.out.println();
        }

        return newMaze;
    }

    public Maze fillMaze(Maze originalMaze) {
        Maze newMaze = getNewObjectMaze(originalMaze);

        for (int[] ints : newMaze.getMaze()) {
            Arrays.fill(ints, 1);
        }

        return newMaze;
    }

    @Override
    public Maze getNewObjectMaze(Maze originalMaze) {
        int height = originalMaze.getHeight();
        int width = originalMaze.getWidth();

        int[][] oMaze = originalMaze.getMaze();
        int[][] newMaze = new int[height][width];

        for (int i = 0; i < height; i++) {
            System.arraycopy(oMaze[i], 0, newMaze[i], 0, width);
        }

        return new Maze(
            newMaze
        );
    }
}

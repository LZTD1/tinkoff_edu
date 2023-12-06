package edu.project2.MazeGenerators.RecursionMazeGenerator;

import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static edu.project2.ConsoleDrawer.drawMaze;

public class RecursionTank {
    private static final int GOTO_BACK = -2;
    private static final int GOTO_FRONT = 2;
    private final int mazeWidth;
    private final int mazeHeight;
    private final Random rand = new Random();
    private final Maze maze;
    private List<Integer> currentPosition = new ArrayList<Integer>();

    public RecursionTank(Maze maze, List<Integer> currentPosition) {
        this.maze = maze;
        this.mazeWidth = this.maze.getWidth();
        this.mazeHeight = this.maze.getHeight();
        this.currentPosition = List.of(
            currentPosition.get(0) % 2 == 0 ? currentPosition.get(0) + 1 : currentPosition.get(0),
            currentPosition.get(1) % 2 == 0 ? currentPosition.get(1) + 1 : currentPosition.get(1)
        );
    }

    public RecursionTank(Maze maze) {
        this.maze = maze;
        this.mazeWidth = this.maze.getWidth();
        this.mazeHeight = this.maze.getHeight();
        this.currentPosition = List.of(
            1,
            1
        );
    }

    public void move(List<Integer> currentPosition, Maze maze, boolean renderPerFrame) {
        List<List<Integer>> variablesMovments = new ArrayList<>();

        variablesMovments.add(List.of(GOTO_FRONT, 0));
        variablesMovments.add(List.of(0, GOTO_FRONT));
        variablesMovments.add(List.of(GOTO_BACK, 0));
        variablesMovments.add(List.of(0, GOTO_BACK));

        Collections.shuffle(variablesMovments);

        for (List<Integer> variablesMovment : variablesMovments) {

            int newX = currentPosition.get(0) + variablesMovment.get(0);
            int newY = currentPosition.get(1) + variablesMovment.get(1);

            if (isValid(newX, newY)) {
                if (maze.getValueOfPosition(newX, newY) == Maze.MazeValues.WALL) {
                    maze.setEmpty(newX, newY);
                    maze.setEmpty(
                        currentPosition.get(0) + variablesMovment.get(0) / 2,
                        currentPosition.get(1) + variablesMovment.get(1) / 2
                    );
                    drawMaze(maze);
                    move(List.of(newX, newY), maze, renderPerFrame);
                }

            }
        }
    }

    private boolean isValid(int x, int y) {
        return x > 0 && x < this.maze.getWidth() - 1 && y > 0 && y < this.maze.getHeight() - 1;
    }

    public List<Integer> getCurrentPosition() {
        return currentPosition;
    }
}

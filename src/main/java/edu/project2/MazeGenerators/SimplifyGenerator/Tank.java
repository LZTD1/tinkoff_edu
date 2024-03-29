package edu.project2.MazeGenerators.SimplifyGenerator;

import edu.project2.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class Tank {
    private static final int GOTO_BACK = -2;
    private static final int GOTO_FRONT = 2;
    private final int mazeWidth;
    private final int mazeHeight;
    private final Random rand = new Random();
    private final Deque<List<Integer>> movment = new ArrayDeque<>();
    private final Maze maze;
    private List<Integer> currentPosition = new ArrayList<Integer>();

    public Tank(Maze maze, List<Integer> currentPosition) {
        this.maze = maze;
        this.mazeWidth = this.maze.getWidth();
        this.mazeHeight = this.maze.getHeight();
        this.currentPosition = List.of(
            currentPosition.get(0) % 2 == 0 ? currentPosition.get(0) + 1 : currentPosition.get(0),
            currentPosition.get(1) % 2 == 0 ? currentPosition.get(1) + 1 : currentPosition.get(1)
        );
    }

    public Tank(Maze maze) {
        this.maze = maze;
        this.mazeWidth = this.maze.getWidth();
        this.mazeHeight = this.maze.getHeight();
        this.currentPosition = List.of(
            1,
            1
        );
    }

    public List<Integer> move(Maze maze) {
        List<List<Integer>> variablesMovments = new ArrayList<>();

        if (this.currentPosition.get(0) + 2 < this.mazeWidth) {
            if (maze.getValueOfPosition(currentPosition.get(0) + 2, currentPosition.get(1)) != Maze.MazeValues.EMPTY) {
                variablesMovments.add(List.of(GOTO_FRONT, 0));
            }
        }
        if (this.currentPosition.get(1) + 2 < this.mazeHeight) {
            if (maze.getValueOfPosition(currentPosition.get(0), currentPosition.get(1) + 2) != Maze.MazeValues.EMPTY) {
                variablesMovments.add(List.of(0, GOTO_FRONT));
            }
        }
        if (this.currentPosition.get(0) - 2 > 0) {
            if (maze.getValueOfPosition(currentPosition.get(0) - 2, currentPosition.get(1)) != Maze.MazeValues.EMPTY) {
                variablesMovments.add(List.of(GOTO_BACK, 0));
            }
        }
        if (this.currentPosition.get(1) - 2 > 0) {
            if (maze.getValueOfPosition(currentPosition.get(0), currentPosition.get(1) - 2) != Maze.MazeValues.EMPTY) {
                variablesMovments.add(List.of(0, GOTO_BACK));
            }
        }
        if (!variablesMovments.isEmpty()) {
            var toMovment = variablesMovments.get(rand.nextInt(variablesMovments.size()));
            this.currentPosition = List.of(
                currentPosition.get(0) + toMovment.get(0),
                currentPosition.get(1) + toMovment.get(1)
            );
            this.movment.push(toMovment);
        } else {
            if (!isStackMovmentEmpty()) {
                var myElem = this.movment.pop();
                this.currentPosition = List.of(
                    currentPosition.get(0) - myElem.get(0),
                    currentPosition.get(1) - myElem.get(1)
                );
            }
        }

        return currentPosition;
    }

    public List<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public List<Integer> getLastMovment() {
        return this.movment.peek();
    }

    public boolean isStackMovmentEmpty() {
        return this.movment.isEmpty();
    }
}

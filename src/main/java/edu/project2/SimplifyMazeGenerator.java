package edu.project2;

import edu.project2.Interfaces.MazeGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimplifyMazeGenerator implements MazeGenerator {
    @Override
    public Maze generateMaze(Maze maze) {
        return maze;
    }

    public void fillMazeMutable(Maze maze){
        int[][] myMaze = maze.getMaze();

        for (int[] ints : myMaze) {
            Arrays.fill(ints, 1);
        }
    }

    @Override
    public Maze fillMaze(Maze maze) {
        var tempMaze = Arrays.copyOf(maze.getMaze(), maze.getMaze().length);

        for (int[] ints : tempMaze) {
            Arrays.fill(ints, 1);
        }

        return new Maze(tempMaze);
    }
}

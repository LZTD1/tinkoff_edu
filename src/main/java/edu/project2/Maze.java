package edu.project2;

import java.util.ArrayList;

public class Maze {

    private int size;
    private int[][] maze;

    public Maze(int size) {
        this.size = size;
        this.maze = new int[size][size];
    }
    public Maze(int[][] maze) {
        this.size = maze.length;
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze;
    }
}

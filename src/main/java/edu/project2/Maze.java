package edu.project2;

import edu.project2.Exceptions.InvalidMazeConstructorError;

public class Maze {

    private int width;
    private int height;
    private MazeValues[][] maze;

    public Maze(int size) {
        mazeFabricSize(size);
    }

    public Maze(int height, int width) {
        mazeFabricSize(height, width);
    }

    public Maze(MazeValues[][] maze) {
        mazeFabricSize(maze);
    }

    private void mazeFabricSize(int size) {
        this.height = size;
        this.width = size;
        if (size % 2 == 0) {
            this.height += 1;
            this.width += 1;
        }
        this.maze = new MazeValues[this.height][this.width];
    }

    private void mazeFabricSize(MazeValues[][] maze) {
        if (maze.length == 0 || maze[0].length == 0) {
            throw new InvalidMazeConstructorError("Maze is invalid! Because length or width uncorrect!");
        }
        if (maze.length % 2 == 0 || maze[0].length % 2 == 0) {
            throw new InvalidMazeConstructorError("Maze is invalid! Because the maze must be even");
        }

        this.height = maze.length;
        this.width = maze[0].length;
        this.maze = maze;
    }

    private void mazeFabricSize(int height, int width) {
        this.height = height;
        this.width = width;
        if (height % 2 == 0) {
            this.height += 1;
        }
        if (width % 2 == 0) {
            this.width += 1;
        }
        this.maze = new MazeValues[this.height][this.width];
    }

    public MazeValues[][] getMaze() {
        return maze;
    }

    public void setEmpty(int x, int y) {
        this.maze[y][x] = MazeValues.EMPTY;
    }

    public void setTank(int x, int y) {
        this.maze[y][x] = MazeValues.TANK;
    }

    public void setExplorer(int x, int y) {
        this.maze[y][x] = MazeValues.EXPLORER;
    }

    public void setFinish(int x, int y) {
        this.maze[y][x] = MazeValues.FINISH;
    }

    public MazeValues getValueOfPosition(int x, int y) {
        return this.maze[y][x];
    }

    private boolean equalsMaze(MazeValues[][] o) {
        if (o == this.maze) {
            return true;
        }
        if (o == null) {
            return false;
        }

        return o.length == maze.length && maze[0].length == o[0].length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public enum MazeValues {
        EMPTY,
        WALL,
        EXPLORER,
        FINISH, TANK
    }
}

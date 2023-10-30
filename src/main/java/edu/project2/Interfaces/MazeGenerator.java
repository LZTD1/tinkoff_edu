package edu.project2.Interfaces;

import edu.project2.Maze;

public interface MazeGenerator {
    Maze generateMaze(Maze maze);

    Maze fillMaze(Maze originalMaze);

    Maze getNewObjectMaze(Maze originalMaze);

    boolean isMazeValid(Maze maze);
}

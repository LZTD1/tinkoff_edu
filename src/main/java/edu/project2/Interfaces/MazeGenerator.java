package edu.project2.Interfaces;

import edu.project2.Maze;

public interface MazeGenerator {
    Maze generateMaze(Maze maze);
    Maze fillMaze(Maze maze);
    Maze getNewObjectMaze(Maze originalMaze);
}

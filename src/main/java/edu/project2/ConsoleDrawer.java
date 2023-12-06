package edu.project2;

import java.util.Deque;
import java.util.List;

public class ConsoleDrawer {

    private ConsoleDrawer() {
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava") // Для аутпута в консоль
    public static void drawMaze(Maze maze) {
        Maze.MazeValues[][] myMaze = maze.getMaze();
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[i].length; j++) {

                if (myMaze[i][j] == Maze.MazeValues.WALL) {
                    System.out.print("■");
                } else if (myMaze[i][j] == Maze.MazeValues.TANK) {
                    System.out.print("@");
                } else if (myMaze[i][j] == Maze.MazeValues.EXPLORER) {
                    System.out.print("?");
                } else if (myMaze[i][j] == Maze.MazeValues.FINISH) {
                    System.out.print("☑");
                } else if (myMaze[i][j] == Maze.MazeValues.ROUTE) {
                    System.out.print("·");
                } else if (myMaze[i][j] == Maze.MazeValues.POINT) {
                    System.out.print("⦿");
                } else {
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
    }

    public static void drawRoute(int startX, int startY, Deque<List<Integer>> route, Maze maze) {
        var currentX = startX;
        var currentY = startY;

        maze.setPoint(currentX, currentY);
        for (var direction : route) {
            currentX += direction.get(0);
            currentY += direction.get(1);
            maze.setRoute(currentX, currentY);
        }
        maze.setPoint(currentX, currentY);
    }
}

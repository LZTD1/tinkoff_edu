package edu.project2;

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
                }else if (myMaze[i][j] == Maze.MazeValues.EXPLORER) {
                    System.out.print("?");
                } else {
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
    }
}

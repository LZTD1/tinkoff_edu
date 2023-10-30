package edu.project2;

public class ConsoleDrawer {
    public static void drawMaze(Maze maze){
        int[][] myMaze = maze.getMaze();
        for(int i=0; i<myMaze.length; i++){
            for(int j=0; j< myMaze[i].length; j++){

                if(myMaze[i][j] == 1){
                    System.out.print("■");
                }else if(myMaze[i][j] == 2){
                    System.out.print("@");
                }else{
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
    }
}

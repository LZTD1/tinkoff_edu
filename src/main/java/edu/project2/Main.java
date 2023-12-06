package edu.project2;

import edu.project2.Explorers.Explorer.SimplifyExplorer;
import edu.project2.Interfaces.MazeGenerator;
import edu.project2.MazeGenerators.RecursionMazeGenerator.RecursionMazeGenerator;
import edu.project2.MazeGenerators.SimplifyGenerator.SimplifyMazeGeneratorWithStack;
import java.util.Deque;
import java.util.List;
import static edu.project2.ConsoleDrawer.drawMaze;
import static edu.project2.ConsoleDrawer.drawRoute;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        Maze myMaze = new Maze(15);

        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();
        MazeGenerator recGen = new RecursionMazeGenerator();

        var filledMaze = mazeGen.fillMaze(myMaze);
        var genMaze = mazeGen.generateMaze(filledMaze);

        var myExplorer = new SimplifyExplorer(genMaze);
        Deque<List<Integer>> myRoute;
        myRoute = myExplorer.getRoute(
            List.of(
                List.of(1, 1),
                List.of(13, 13)
            )
        );

        drawMaze(genMaze);
        drawRoute(1, 1, myRoute, genMaze);
        drawMaze(genMaze);
    }
}

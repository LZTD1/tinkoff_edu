package edu.project2;

import edu.project2.Explorers.Explorer.SimplifyExplorer;
import edu.project2.Interfaces.MazeGenerator;
import edu.project2.MazeGenerators.RecursionMazeGenerator.RecursionMazeGenerator;
import edu.project2.MazeGenerators.SimplifyGenerator.SimplifyMazeGeneratorWithStack;
import java.util.Deque;
import java.util.List;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        Maze myMaze = new Maze(15);

        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();
        MazeGenerator recGen = new RecursionMazeGenerator();

        var filledMaze = mazeGen.fillMaze(myMaze);
        var genMaze = mazeGen.generateMazeWithDraw(filledMaze); // Добавил метод для
        // визуального просмотра генерации на каждом этапе

//        drawMaze(genMaze);

        var myExplorer = new SimplifyExplorer(genMaze);
        Deque<List<Integer>> myRoute;
        myRoute = myExplorer.getRoute(
            List.of(
                List.of(1, 1), // From
                List.of(13, 13) // To
            )
        );
        System.out.println(myRoute);
    }
}

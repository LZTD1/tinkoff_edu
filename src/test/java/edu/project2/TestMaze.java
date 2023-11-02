package edu.project2;

import edu.project2.Exceptions.IncorrectRoutePoints;
import edu.project2.Exceptions.InvalidMazeConstructor;
import edu.project2.Exceptions.RouteCalculationError;
import edu.project2.Explorer.Explorer;
import edu.project2.Interfaces.MazeGenerator;
import edu.project2.RecursionMazeGenerator.RecursionMazeGenerator;
import edu.project2.RecursiveExplorer.RecuresiveExplorer;
import edu.project2.SimplifyGenerator.SimplifyMazeGeneratorWithStack;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static edu.project2.ConsoleDrawer.drawMaze;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class TestMaze {
//    @Test
//    void testFillMaze(){
//        Maze myMaze = new Maze(10);
//        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();
//
//        var filledMaze = mazeGen.fillMaze(myMaze);
//
//        for(Maze.MazeValues[] rows : filledMaze.getMaze()){
//            assertThat(rows).containsOnly(Maze.MazeValues.WALL);
//        }
//        for(Maze.MazeValues[] rows : myMaze.getMaze()){
//            assertThat(rows).containsOnly((Maze.MazeValues) null);
//        }
//    }
//    @Test
//    void isValidGenerated(){
//        Maze myMaze = new Maze(15);
//        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();
//
//        var filledMaze = mazeGen.fillMaze(myMaze);
//        var genMaze = mazeGen.generateMaze(filledMaze);
//
//        var isGeneratedOk = mazeGen.isMazeValid(genMaze);
//        var isGeneratedFalse = mazeGen.isMazeValid(filledMaze);
//
//        assertThat(isGeneratedOk).isTrue();
//        assertThat(isGeneratedFalse).isFalse();
//    }
//    @Test
//    void testRoute(){
//        Maze myMaze = new Maze(15);
//        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();
//
//        var filledMaze = mazeGen.fillMaze(myMaze);
//        var genMaze = mazeGen.generateMaze(filledMaze);
//
//        var myExplorer = new Explorer(genMaze);
//        Stack<List<Integer>> route = myExplorer.getRoute(List.of(
//            List.of(1, 1), // From
//            List.of(13, 13) // To
//        ));
//
//        assertThat(route.isEmpty()).isFalse();
//        assertThat(myExplorer.getCurrentPosition()).isEqualTo(List.of(13, 13));
//    }
//
//    @Test
//    void testTouteFailed_RouteOutBounds(){
//        Maze myMaze = new Maze(15);
//        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();
//
//        var filledMaze = mazeGen.fillMaze(myMaze);
//        var genMaze = mazeGen.generateMaze(filledMaze);
//
//        var myExplorer = new Explorer(genMaze);
//
//        assertThrows(IncorrectRoutePoints.class, ()->{
//            myExplorer.getRoute(List.of(
//                List.of(1, 1), // From
//                List.of(255, 255) // To
//            ));
//        });
//    }
//    @Test
//    void testTouteFailed_RouteInWall(){
//        Maze myMaze = new Maze(15);
//        MazeGenerator mazeGen = new SimplifyMazeGeneratorWithStack();
//
//        var filledMaze = mazeGen.fillMaze(myMaze);
//        var genMaze = mazeGen.generateMaze(filledMaze);
//
//        var myExplorer = new Explorer(genMaze);
//
//        assertThrows(IncorrectRoutePoints.class, ()->{
//            myExplorer.getRoute(List.of(
//                List.of(1, 1), // From
//                List.of(10, 10) // To
//            ));
//        });
//    }
//    @Test
//    void testRouteFailed_NoRoutesExists(){
//
//        /*
//            Plan:
//            ■■■■■■■■■
//            ■?  ■■■■■
//            ■   ■■■■■
//            ■ ■■■■■■■
//            ■ ■■■■■■■
//            ■   ■■■■■
//            ■   ■■ ■■<- Need to come here
//            ■■■■■■■■■
//            ■■■■■■■■■
//
//            No routes :)
//
//         */
//
//
//        List<List<Maze.MazeValues>> myMazeMatrix = List.of(
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)
//        );
//        // Convert to mazeArray
//        Maze.MazeValues[][] mazeArray = myMazeMatrix.stream()
//            .map(row -> row.toArray(new Maze.MazeValues[0]))
//            .toArray(Maze.MazeValues[][]::new);
//
//        Maze myMaze = new Maze(mazeArray);
//
//        var myExplorer = new Explorer(myMaze);
//
//        assertThrows(RouteCalculationError.class, ()->{
//            myExplorer.getRoute(List.of(
//                List.of(1, 1), // From
//                List.of(6, 6) // To
//            ));
//        });
//    }
//    @Test
//    void testInsertInvalidMaze(){
//
//        List<List<Maze.MazeValues>> myMazeMatrix = List.of(
//          List.of(Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//          List.of(Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//          List.of(Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//          List.of(Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)
//        );
//        // Convert to mazeArray
//        Maze.MazeValues[][] mazeArray = myMazeMatrix.stream()
//            .map(row -> row.toArray(new Maze.MazeValues[0]))
//            .toArray(Maze.MazeValues[][]::new);
//
//        assertThrows(InvalidMazeConstructor.class, ()->{
//            new Maze(mazeArray);
//        });
//    }
//    @Test
//    void testInsertOwnMaze(){
//
//        /*
//            Plan:
//            ■■■■■■■■■
//            ■?  ■■■■■
//            ■   ■■■■■
//            ■ ■■■■■■■
//            ■ ■■■■■■■
//            ■   ■■■■■
//            ■   ■■ ■■<- Need to come here
//            ■■■■■■■■■
//            ■■■■■■■■■
//
//            No routes :)
//
//         */
//
//
//        List<List<Maze.MazeValues>> myMazeMatrix = List.of(
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.EMPTY, Maze.MazeValues.WALL, Maze.MazeValues.WALL)  ,
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL),
//            List.of(Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL, Maze.MazeValues.WALL)
//        );
//        // Convert to mazeArray
//        Maze.MazeValues[][] mazeArray = myMazeMatrix.stream()
//            .map(row -> row.toArray(new Maze.MazeValues[0]))
//            .toArray(Maze.MazeValues[][]::new);
//
//        Maze myMaze = new Maze(mazeArray);
//
//        assertThat(myMaze.getMaze()).isEqualTo(mazeArray);
//    }
//
//    @Test
//    void getRecursionMaze(){
//        Maze myMaze = new Maze(20);
//        MazeGenerator mazeGen = new RecursionMazeGenerator();
//
//        var filledMaze = mazeGen.fillMaze(myMaze);
//        var genMaze = mazeGen.generateMaze(filledMaze);
//
//        var isGeneratedOk = mazeGen.isMazeValid(genMaze);
//        var isGeneratedFalse = mazeGen.isMazeValid(filledMaze);
//
//        assertThat(isGeneratedOk).isTrue();
//        assertThat(isGeneratedFalse).isFalse();
//    }
    @Test
    void testRoute2(){
        Maze myMaze = new Maze(15);
        MazeGenerator mazeGen = new RecursionMazeGenerator();

        var filledMaze = mazeGen.fillMaze(myMaze);
        var genMaze = mazeGen.generateMaze(filledMaze);

        var myExplorer = new Explorer(genMaze);
        Stack<List<Integer>> route = myExplorer.getRoute(List.of(
            List.of(1, 1), // From
            List.of(13, 13) // To
        ));

        assertThat(route.isEmpty()).isFalse();
        assertThat(myExplorer.getCurrentPosition()).isEqualTo(List.of(13, 13));
    }
    @Test
    void testRoute_Recursion(){
        Maze myMaze = new Maze(15);
        MazeGenerator mazeGen = new RecursionMazeGenerator();

        var filledMaze = mazeGen.fillMaze(myMaze);
        var genMaze = mazeGen.generateMaze(filledMaze);

        var myExplorer = new RecuresiveExplorer(genMaze);
        Stack<List<Integer>> route = myExplorer.getRoute(List.of(
            List.of(1, 1), // From
            List.of(9, 9) // To
        ));

        assertThat(route.isEmpty()).isFalse();
        assertThat(myExplorer.getCurrentPosition()).isEqualTo(List.of(9, 9));
    }
}

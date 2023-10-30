package edu.project2.Explorer;

import edu.project2.Exceptions.IncorrectRoutePoints;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import static edu.project2.ConsoleDrawer.drawMaze;

public class Explorer {

    private final Random rand = new Random();
    private final Maze maze;
    private List<Integer> currentPosition;
    private Stack<List<Integer>> movmentExplorer = new Stack<>();

    public Explorer(Maze maze) {
        this.maze = maze;
    }

    public Maze getRoute(List<List<Integer>> routePoints) {
        List<List<Integer>> visitedPoints = new ArrayList<>();
        this.currentPosition = new ArrayList<>();
        var myMaze = this.maze.getMaze();

        int fromX = routePoints.get(0).get(0);
        int fromY = routePoints.get(0).get(1);

        int toX = routePoints.get(1).get(0);
        int toY = routePoints.get(1).get(1);

        checkPointsAboveBorders(fromX, fromY, toX, toY);
        checkCorrectRoutedPoints(fromX, fromY, toX, toY);

        this.currentPosition.add(0, fromX);
        this.currentPosition.add(1, fromY);
        // // // // //
        maze.setExplorer(this.currentPosition.get(0), this.currentPosition.get(1));
        drawMaze(this.maze);
        System.out.println();
        // // // //
        while (this.currentPosition.get(0) != toX || this.currentPosition.get(1) != toY) {

            List<Integer> direction = whereToGo(visitedPoints);
            if(this.maze.getValueOfPosition(this.currentPosition.get(0), this.currentPosition.get(1))
                == Maze.MazeValues.EXPLORER){
                maze.setEmpty(this.currentPosition.get(0), this.currentPosition.get(1));
            }else{
                maze.setExplorer(this.currentPosition.get(0), this.currentPosition.get(1));
            }


            System.out.println(direction);

            drawMaze(this.maze);
        }

        return null;
    }

    private List<Integer> whereToGo(List<List<Integer>> visitedPoints) {
        List<List<Integer>> directions = new ArrayList<>();

        if (this.maze.getValueOfPosition(this.currentPosition.get(0) + 1, this.currentPosition.get(1))
            != Maze.MazeValues.WALL ) {
            // Значит можно идти в право
            if (!visitedPoints.contains(List.of(this.currentPosition.get(0) + 1, this.currentPosition.get(1)))) {
                directions.add(List.of( 1, 0 ));
            }
        }
        if (this.maze.getValueOfPosition(this.currentPosition.get(0) - 1, this.currentPosition.get(1))
            != Maze.MazeValues.WALL) {
            // Значит можно идти в влево
            if (!visitedPoints.contains(List.of(this.currentPosition.get(0) - 1, this.currentPosition.get(1)))) {
                directions.add(List.of( - 1, 0));
            }
        }
        if (this.maze.getValueOfPosition(this.currentPosition.get(0), this.currentPosition.get(1) + 1)
            != Maze.MazeValues.WALL) {
            // Значит можно идти в вверх
            if (!visitedPoints.contains(List.of(this.currentPosition.get(0), this.currentPosition.get(1) + 1))) {
                directions.add(List.of(0, 1));
            }
        }
        if (this.maze.getValueOfPosition(this.currentPosition.get(0), this.currentPosition.get(1) - 1)
            != Maze.MazeValues.WALL) {
            // Значит можно идти в вниз
            if (!visitedPoints.contains(List.of(this.currentPosition.get(0), this.currentPosition.get(1) - 1))) {
                directions.add(List.of(0, -1));
            }
        }
        if(!directions.isEmpty()) {
            System.out.println("NotEmpty");
            var direction = directions.get(rand.nextInt(directions.size()));

            this.currentPosition = List.of(
                this.currentPosition.get(0) + direction.get(0),
                this.currentPosition.get(1) + direction.get(1)
            );
            visitedPoints.add(
                this.currentPosition
            );
            this.movmentExplorer.push(direction);

            return direction;
        }else{
            System.out.println("Empty");
            List<Integer> myElem = this.movmentExplorer.pop();

            this.currentPosition = List.of(
                this.currentPosition.get(0) - myElem.get(0),
                this.currentPosition.get(1) - myElem.get(1)
            );

            return myElem;
        }
    }

    private void checkPointsAboveBorders(int fromX, int fromY, int toX, int toY) {
        var mazeHeight = this.maze.getHeight();
        var mazeWidth = this.maze.getWidth();

        if (fromX > 0 && fromX < mazeWidth
            && toX > 0 && toX < mazeWidth
            && fromY > 0 && fromY < mazeHeight
            && toY > 0 && toY < mazeHeight) {

        } else {
            throw new IncorrectRoutePoints("You have entered incorrect route points, they do not exist in matrix!");
        }
    }

    private void checkCorrectRoutedPoints(int fromX, int fromY, int toX, int toY) {
        if (!this.maze.getValueOfPosition(fromX, fromY).equals(Maze.MazeValues.EMPTY)
            || !this.maze.getValueOfPosition(toX, toY).equals(Maze.MazeValues.EMPTY)) {

            throw new IncorrectRoutePoints("You have entered incorrect route points, there are walls!");
        }
    }
}

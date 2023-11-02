package edu.project2.RecursiveExplorer;

import edu.project2.Exceptions.IncorrectRoutePoints;
import edu.project2.Exceptions.RouteCalculationError;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RecuresiveExplorer {
    private final Random rand = new Random();
    private final Maze maze;
    private final Stack<List<Integer>> movmentExplorer = new Stack<>();
    private List<Integer> currentPosition;

    public RecuresiveExplorer(Maze maze) {
        this.maze = maze;
    }

    public Stack<List<Integer>> getRoute(List<List<Integer>> routePoints) {
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

        while (this.currentPosition.get(0) != toX || this.currentPosition.get(1) != toY) {
            List<List<Integer>> directions = possibleRoutes(visitedPoints);
            var direction = getRandomDirection(directions);

            if (direction == null) {
                moveExplorerBack();
            } else {
                moveExplorer(direction, visitedPoints);
            }
        }

        return this.movmentExplorer;
    }

    private void moveExplorer(List<Integer> direction, List<List<Integer>> visitedPoints) {
        List<List<Integer>> variablesMovments = new ArrayList<>();

        variablesMovments.add(List.of(1, 0));
        variablesMovments.add(List.of(0, 1));
        variablesMovments.add(List.of(-1, 0));
        variablesMovments.add(List.of(0, -1));

        Collections.shuffle(variablesMovments);

        for (List<Integer> variablesMovment : variablesMovments) {

            int newX = currentPosition.get(0)+variablesMovment.get(0);
            int newY = currentPosition.get(1)+variablesMovment.get(1);

            if(isValidPosition(newX, newY)){
                if(maze.getValueOfPosition(newX, newY) == Maze.MazeValues.WALL){
                    moveExplorer(List.of(newX, newY), maze);
                }
                // TODO давать стек !копию! эесплореру
            }
        }
    }

    private List<Integer> getRandomDirection(List<List<Integer>> directions) {
        if (!directions.isEmpty()) {
            return directions.get(rand.nextInt(directions.size()));
        } else {
            return null;
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x > 0 && x < this.maze.getWidth() - 1 && y > 0 && y < this.maze.getHeight() - 1;
    }

    private void checkPointsAboveBorders(int fromX, int fromY, int toX, int toY) {
        var mazeHeight = this.maze.getHeight();
        var mazeWidth = this.maze.getWidth();

        if (fromX > 0 && fromX < mazeWidth
            && toX > 0 && toX < mazeWidth
            && fromY > 0 && fromY < mazeHeight
            && toY > 0 && toY < mazeHeight) {
            return;
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

    public List<Integer> getCurrentPosition() {
        return currentPosition;
    }
}

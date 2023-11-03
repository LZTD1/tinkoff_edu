package edu.project2.Explorers.RecursiveExplorer;

import edu.project2.Exceptions.IncorrectRoutePoints;
import edu.project2.Exceptions.RouteCalculationError;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RecuresiveExplorer {
    private final Random rand = new Random();
    private final Maze maze;
    private List<Integer> currentPosition;
    private Integer toX;
    private Integer toY;
    private boolean isCome;
    private List<List<Integer>> visitPoints;

    public RecuresiveExplorer(Maze maze) {
        this.maze = maze;
    }

    public Stack<List<Integer>> getRoute(List<List<Integer>> routePoints) {
        initializeRoute(routePoints);
        Stack<List<Integer>> movementExplorer = new Stack<>();
        Stack<List<Integer>> route = moveExplorer(this.currentPosition, movementExplorer);

        maze.setEmpty(this.toX, this.toY);

        if (route == null) {
            throw new RouteCalculationError(
                "It is not possible to determine the route, perhaps there is no route to the final point!");
        }
        return route;
    }

    private void initializeRoute(List<List<Integer>> routePoints) {
        this.currentPosition = new ArrayList<>();
        var myMaze = this.maze.getMaze();

        int fromX = routePoints.get(0).get(0);
        int fromY = routePoints.get(0).get(1);

        this.toX = routePoints.get(1).get(0);
        this.toY = routePoints.get(1).get(1);
        this.visitPoints = new ArrayList<>();

        setExplorerInEnd(false);

        checkPointsAboveBorders(fromX, fromY, this.toX, this.toY);
        checkCorrectRoutedPoints(fromX, fromY, this.toX, this.toY);

        this.currentPosition.add(0, fromX);
        this.currentPosition.add(1, fromY);
    }

    private Stack<List<Integer>> moveExplorer(List<Integer> currentPosition, Stack<List<Integer>> movement) {
        List<List<Integer>> variableMovements = List.of(List.of(1, 0), List.of(0, 1), List.of(-1, 0), List.of(0, -1));

        for (List<Integer> variableMovement : variableMovements) {
            if (!isExplorerInEnd()) {
                int oldX = currentPosition.get(0);
                int oldY = currentPosition.get(1);

                int newX = oldX + variableMovement.get(0);
                int newY = oldY + variableMovement.get(1);

                if (isValidNewPosition(newX, newY)) {
                    this.currentPosition = List.of(newX, newY);
                    movement.push(List.of(variableMovement.get(0), variableMovement.get(1)));

                    if (isExplorerPositionInEnd(newX, newY)) {
                        setExplorerInEnd(true);
                        return movement;
                    }
                    visitPoints.add(List.of(newX, newY));

                    Stack<List<Integer>> result = moveExplorer(List.of(newX, newY), movement);
                    if (result != null) {
                        return result;
                    }
                    movement.pop();
                }
            }
        }
        return null;
    }

    private boolean isPositionValidForExploration(int x, int y) {
        return maze.getValueOfPosition(x, y) != Maze.MazeValues.WALL && !visitPoints.contains(List.of(x, y));
    }

    private boolean isValidPosition(int x, int y) {
        return x > 0 && x < this.maze.getWidth() - 1 && y > 0 && y < this.maze.getHeight() - 1;
    }

    private boolean isValidNewPosition(int x, int y) {
        return isValidPosition(x, y) && isPositionValidForExploration(x, y);
    }

    private boolean isExplorerPositionInEnd(int x, int y) {
        return x == this.toX && y == this.toY;
    }

    private boolean isExplorerInEnd() {
        return this.isCome;
    }

    private void setExplorerInEnd(boolean isEnd) {
        this.isCome = isEnd;
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

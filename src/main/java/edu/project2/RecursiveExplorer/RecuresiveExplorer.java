package edu.project2.RecursiveExplorer;

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
        List<List<Integer>> visitedPoints = new ArrayList<>();
        this.currentPosition = new ArrayList<>();
        var myMaze = this.maze.getMaze();

        int fromX = routePoints.get(0).get(0);
        int fromY = routePoints.get(0).get(1);

        this.toX = routePoints.get(1).get(0);
        this.toY = routePoints.get(1).get(1);
        this.visitPoints = new ArrayList<>();
        this.isCome = false;

        checkPointsAboveBorders(fromX, fromY, toX, toY);
        checkCorrectRoutedPoints(fromX, fromY, toX, toY);

        this.currentPosition.add(0, fromX);
        this.currentPosition.add(1, fromY);

        Stack<List<Integer>> movmentExplorer = new Stack<>();
        Stack<List<Integer>> route = moveExplorer(this.currentPosition, movmentExplorer);

        maze.setEmpty(this.toX, this.toY);

        if (route == null) {
            throw new RouteCalculationError(
                "It is not possible to determine the route, perhaps there is no route to the final point!");
        }
        return route;
    }

    private Stack<List<Integer>> moveExplorer(List<Integer> currentPosition, Stack<List<Integer>> movment) {
        List<List<Integer>> variablesMovments = new ArrayList<>();

        variablesMovments.add(List.of(1, 0));
        variablesMovments.add(List.of(0, 1));
        variablesMovments.add(List.of(-1, 0));
        variablesMovments.add(List.of(0, -1));

        for (List<Integer> variablesMovment : variablesMovments) {
            if (!this.isCome) {
                int oldX = currentPosition.get(0);
                int oldY = currentPosition.get(1);

                int newX = oldX + variablesMovment.get(0);
                int newY = oldY + variablesMovment.get(1);

                if (isValidPosition(newX, newY)) {
                    if (maze.getValueOfPosition(newX, newY) != Maze.MazeValues.WALL
                        && !visitPoints.contains(List.of(newX, newY))
                    ) {
                        this.currentPosition = List.of(
                            newX,
                            newY
                        );
                        movment.push(List.of(
                            variablesMovment.get(0),
                            variablesMovment.get(1)
                        ));
                        if (newX == this.toX && newY == this.toY) {
                            this.isCome = true;
                            return movment;
                        }
                        visitPoints.add(List.of(newX, newY));

                        Stack<List<Integer>> result = moveExplorer(List.of(newX, newY), movment);
                        if (result != null) {
                            return result;
                        }
                        movment.pop();
                    }
                }
            }
        }
        return null;
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

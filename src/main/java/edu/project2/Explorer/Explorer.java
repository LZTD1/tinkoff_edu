package edu.project2.Explorer;

import edu.project2.Exceptions.IncorrectRoutePoints;
import edu.project2.Exceptions.RouteCalculationError;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Explorer {

    private final Random rand = new Random();
    private final Maze maze;
    private final Stack<List<Integer>> movmentExplorer = new Stack<>();
    private List<Integer> currentPosition;

    public Explorer(Maze maze) {
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
        this.currentPosition = List.of(
            this.currentPosition.get(0) + direction.get(0),
            this.currentPosition.get(1) + direction.get(1)
        );
        visitedPoints.add(
            this.currentPosition
        );
        this.movmentExplorer.push(direction);
    }

    private void moveExplorerBack() {
        if (!this.movmentExplorer.isEmpty()) {

            List<Integer> myElem = this.movmentExplorer.pop();

            this.currentPosition = List.of(
                this.currentPosition.get(0) - myElem.get(0),
                this.currentPosition.get(1) - myElem.get(1)
            );

        } else {
            throw new RouteCalculationError(
                "It is not possible to determine the route, perhaps there is no route to the final point!");
        }
    }

    private List<Integer> getRandomDirection(List<List<Integer>> directions) {
        if (!directions.isEmpty()) {
            return directions.get(rand.nextInt(directions.size()));
        } else {
            return null;
        }
    }

    private List<List<Integer>> possibleRoutes(List<List<Integer>> visitedPoints) {
        List<List<Integer>> directions = new ArrayList<>();

        if (this.maze.getValueOfPosition(this.currentPosition.get(0) + 1, this.currentPosition.get(1))
            != Maze.MazeValues.WALL) {
            // Значит можно идти в право
            if (!visitedPoints.contains(List.of(this.currentPosition.get(0) + 1, this.currentPosition.get(1)))) {
                directions.add(List.of(1, 0));
            }
        }
        if (this.maze.getValueOfPosition(this.currentPosition.get(0) - 1, this.currentPosition.get(1))
            != Maze.MazeValues.WALL) {
            // Значит можно идти в влево
            if (!visitedPoints.contains(List.of(this.currentPosition.get(0) - 1, this.currentPosition.get(1)))) {
                directions.add(List.of(-1, 0));
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

        return directions;
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

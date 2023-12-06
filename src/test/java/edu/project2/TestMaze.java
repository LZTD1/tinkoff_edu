package edu.project2;

import edu.project2.Exceptions.IncorrectRoutePointsError;
import edu.project2.Exceptions.InvalidMazeConstructorError;
import edu.project2.Exceptions.RouteCalculationError;
import edu.project2.Explorers.Explorer.SimplifyExplorer;
import edu.project2.Explorers.RecursiveExplorer.RecuresiveExplorer;
import edu.project2.Interfaces.Explorer;
import edu.project2.Interfaces.MazeGenerator;
import edu.project2.MazeGenerators.RecursionMazeGenerator.RecursionMazeGenerator;
import edu.project2.MazeGenerators.SimplifyGenerator.SimplifyMazeGeneratorWithStack;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class TestMaze {

    @Nested
    class TestMazeGenerators {
        @ParameterizedTest
        @MethodSource("mazeGeneratorProvider")
        void testFillMaze(MazeGenerator mazeGen) {
            Maze myMaze = new Maze(10);

            var filledMaze = mazeGen.fillMaze(myMaze);

            for (Maze.MazeValues[] rows : filledMaze.getMaze()) {
                assertThat(rows).containsOnly(Maze.MazeValues.WALL);
            }
            for (Maze.MazeValues[] rows : myMaze.getMaze()) {
                assertThat(rows).containsOnly((Maze.MazeValues) null);
            }
        }

        @ParameterizedTest
        @MethodSource("mazeGeneratorProvider")
        void isValidGenerated(MazeGenerator mazeGen) {
            Maze myMaze = new Maze(20);

            var filledMaze = mazeGen.fillMaze(myMaze);
            var genMaze = mazeGen.generateMaze(filledMaze);

            var isGeneratedOk = mazeGen.isMazeValid(genMaze);
            var isGeneratedFalse = mazeGen.isMazeValid(filledMaze);

            assertThat(isGeneratedOk).isTrue();
            assertThat(isGeneratedFalse).isFalse();
        }

        static Stream<Arguments> mazeGeneratorProvider() {
            return Stream.of(
                Arguments.of(new SimplifyMazeGeneratorWithStack()),
                Arguments.of(new RecursionMazeGenerator())
            );
        }
    }

    @Nested
    class TestMazeObject {
        @Test
        void testInsertInvalidMaze() {

            List<List<Maze.MazeValues>> myMazeMatrix = List.of(
                List.of(
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                )
            );
            // Convert to mazeArray
            Maze.MazeValues[][] mazeArray = myMazeMatrix.stream()
                .map(row -> row.toArray(new Maze.MazeValues[0]))
                .toArray(Maze.MazeValues[][]::new);

            assertThrows(InvalidMazeConstructorError.class, () -> {
                new Maze(mazeArray);
            });
        }

        @Test
        void testInsertOwnMaze() {

        /*
            Plan:
            ■■■■■■■■■
            ■?  ■■■■■
            ■   ■■■■■
            ■ ■■■■■■■
            ■ ■■■■■■■
            ■   ■■■■■
            ■   ■■ ■■<- Need to come here
            ■■■■■■■■■
            ■■■■■■■■■

            No routes :)

         */

            List<List<Maze.MazeValues>> myMazeMatrix = List.of(
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.EMPTY,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                ),
                List.of(
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL,
                    Maze.MazeValues.WALL
                )
            );
            // Convert to mazeArray
            Maze.MazeValues[][] mazeArray = myMazeMatrix.stream()
                .map(row -> row.toArray(new Maze.MazeValues[0]))
                .toArray(Maze.MazeValues[][]::new);

            Maze myMaze = new Maze(mazeArray);

            assertThat(myMaze.getMaze()).isEqualTo(mazeArray);
        }
    }

    @Nested
    class TestExplorers {
        static Maze myMaze = new Maze(15);
        static MazeGenerator mazeGen = new RecursionMazeGenerator();
        static Maze filledMaze = mazeGen.fillMaze(myMaze);
        static Maze genMaze = mazeGen.generateMaze(filledMaze);

        @ParameterizedTest
        @MethodSource("explorerProvider")
        void testRoute(Explorer myExplorer) {

            Deque<List<Integer>> route = myExplorer.getRoute(List.of(
                List.of(1, 1), // From
                List.of(13, 13) // To
            ));

            assertThat(route.isEmpty()).isFalse();
            assertThat(myExplorer.getCurrentPosition()).isEqualTo(List.of(13, 13));
        }

        @ParameterizedTest
        @MethodSource("explorerProvider")
        void testTouteFailed_RouteOutBounds(Explorer myExplorer) {

            assertThrows(IncorrectRoutePointsError.class, () -> {
                myExplorer.getRoute(List.of(
                    List.of(1, 1), // From
                    List.of(255, 255) // To
                ));
            });
        }

        @ParameterizedTest
        @MethodSource("explorerProvider")
        void testTouteFailed_RouteInWall(Explorer myExplorer) {

            assertThrows(IncorrectRoutePointsError.class, () -> {
                myExplorer.getRoute(List.of(
                    List.of(1, 1), // From
                    List.of(10, 10) // To
                ));
            });
        }

        static Stream<Arguments> explorerProvider() {
            return Stream.of(
                Arguments.of(new SimplifyExplorer(genMaze)),
                Arguments.of(new RecuresiveExplorer(genMaze))
            );
        }
    }

    @Nested
    class InvalidRouteGenerateTest {
        static List<List<Maze.MazeValues>> myMazeMatrix = List.of(
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            ),
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            ),
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            ),
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            ),
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            ),
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            ),
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.EMPTY,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            ),
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            ),
            List.of(
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL,
                Maze.MazeValues.WALL
            )
        );
        // Convert to mazeArray
        static Maze.MazeValues[][] maze = myMazeMatrix.stream()
            .map(row -> row.toArray(new Maze.MazeValues[0]))
            .toArray(Maze.MazeValues[][]::new);
        static Maze mockInvalidMaze = new Maze(maze);

        @ParameterizedTest
        @MethodSource("explorerProvider")
        void testRouteFailed_NoRoutesExists(Explorer myExplorer) {

        /*
            Plan:
            ■■■■■■■■■
            ■?  ■■■■■
            ■   ■■■■■
            ■ ■■■■■■■
            ■ ■■■■■■■
            ■   ■■■■■
            ■   ■■ ■■<- Need to come here
            ■■■■■■■■■
            ■■■■■■■■■

            No routes :)

         */

            assertThrows(RouteCalculationError.class, () -> {
                myExplorer.getRoute(List.of(
                    List.of(1, 1), // From
                    List.of(6, 6) // To
                ));
            });
        }

        static Stream<Arguments> explorerProvider() {
            return Stream.of(
                Arguments.of(new SimplifyExplorer(mockInvalidMaze)),
                Arguments.of(new RecuresiveExplorer(mockInvalidMaze))
            );
        }
    }

}


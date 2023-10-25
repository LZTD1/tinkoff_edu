package edu.hw1;

public class Task8 {
    private Task8() {
    }

    private static final int[][] MOVES = {
        {-2, -1}, {-2, 1},
        {2, -1}, {2, 1},
        {-1, -2}, {-1, 2},
        {1, -2}, {1, 2}
    };
    private static final int SIZE = 8;

    public static boolean knightBoardCapture(int[][] array) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (array[x][y] == 1) {
                    for (int[] move : MOVES) {
                        int xTemp = x + move[0];
                        int yTemp = y + move[1];

                        if (xTemp >= 0 && xTemp < SIZE && yTemp >= 0 && yTemp < SIZE && array[xTemp][yTemp] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}

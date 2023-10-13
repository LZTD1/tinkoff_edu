package edu.hw1;

public class Task8 {
    public static boolean knightBoardCapture(int[][] array) {
        int[][] moves = {
            {-2, -1}, {-2, 1},
            {2, -1}, {2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}
        };

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (array[x][y] == 1) {
                    for (int[] move : moves) {
                        int x_temp = x + move[0];
                        int y_temp = y + move[1];

                        if (x_temp >= 0 && x_temp < 8 && y_temp >= 0 && y_temp < 8 && array[x_temp][y_temp] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }
}

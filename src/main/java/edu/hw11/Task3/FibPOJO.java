package edu.hw11.Task3;

public class FibPOJO {
    public FibPOJO() {
    }

    public int Fib(int number) {
        if (number < 3) {
            return 1;
        }

        return Fib(number - 2) + Fib(number - 1);
    }

}

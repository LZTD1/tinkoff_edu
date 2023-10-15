package edu.hw2.Task1;

public record Multiplication(Expr... objects) implements Expr {
    @Override
    public double evaluate() {
        double total = 1;
        for (Expr obj : objects) {
            total *= obj.evaluate();
        }
        return total;
    }
}

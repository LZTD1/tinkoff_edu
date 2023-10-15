package edu.hw2.Task1;

public record Addition(Expr... objects) implements Expr {
    @Override
    public double evaluate() {
        double total = 0;
        for (Expr obj : objects) {
            total += obj.evaluate();
        }
        return total;
    }
}

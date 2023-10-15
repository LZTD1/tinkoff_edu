package edu.hw2.Task1;

public final class Exponent implements Expr {
    private final Expr obj;
    private final double pow;

    public Exponent(Expr obj, double pow) {
        this.obj = obj;
        this.pow = pow;
    }

    public Exponent(Expr obj, Expr pow) {
        this.obj = obj;
        this.pow = pow.evaluate();
    }

    @Override
    public double evaluate() {
        return Math.pow(obj.evaluate(), pow);
    }
}

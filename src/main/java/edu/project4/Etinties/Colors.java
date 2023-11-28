package edu.project4.Etinties;

public enum Colors {
    RED(new double[] {123.0, 0.0, 0.0}),
    GREEN(new double[] {0.0, 255.0, 0.0}),
    BLUE(new double[] {0.0, 0.0, 255.0}),
    VIOLET(new double[] {238.0, 130.0, 238.0}),
    PINK(new double[] {125.0, 86.0, 103.0});

    private final double[] colorValues;

    Colors(double[] colorValues) {
        this.colorValues = colorValues;
    }

    public double[] getColorValues() {
        return this.colorValues;
    }
}

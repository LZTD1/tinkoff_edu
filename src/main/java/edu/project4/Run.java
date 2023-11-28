package edu.project4;

@SuppressWarnings("all")
public class Run {
    public static void main(String[] args) {
        var flame = new Flame(10, 50_000, 30_000, 1, true);
        flame.render();
        flame.getMatrixDisplay();
        var image = flame.postRendering();

        var drawer = new Drawer(1920, 1080);
        drawer.drawFlame(image);
    }
}

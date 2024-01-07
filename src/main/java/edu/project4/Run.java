package edu.project4;

@SuppressWarnings("all")
public class Run {
    public static void main(String[] args) {
        var flame = new Flame(12, 100_000, 100_000, 1, true, 4);
        flame.render();
        flame.getMatrixDisplay();
        var image = flame.postRendering();

        var drawer = new Drawer(1920, 1080);
        drawer.drawFlame(image);
        drawer.saveToFile();
    }
}

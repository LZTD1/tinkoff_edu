package edu.project4;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Drawer extends JFrame {
    private final BufferedImage image;

    public Drawer(int width, int height) {
        super("Pixel Coloring");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void drawPixelInPoint(int x, int y, int color) {
        image.setRGB(x, y, color);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public void drawFlame(short[][][] image) {
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                var red = image[y][x][0];
                var green = image[y][x][1];
                var blue = image[y][x][2];
                @SuppressWarnings("MagicNumber")
                int rgb = (red << 16 | green << 8 | blue);
                drawPixelInPoint(x, y, rgb);
                repaint();
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;

class HexagonGridPanel extends JPanel {

    private static final int HEX_SIZE = 30;  // Size of the hexagon (side length)
    private static final int GRID_SIZE_X = 80;  // Number of hexagons in the x-direction
    private static final int GRID_SIZE_Y = 100;  // Number of hexagons in the y-direction

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int yOffset = HEX_SIZE * 10 / 6;  // Vertical offset for odd rows

        for (int i = 0; i < GRID_SIZE_X; i++) {
            for (int j = 0; j < GRID_SIZE_Y; j++) {
                int x = i * HEX_SIZE * 3 / 2;
                int y = j * yOffset;

                if (i % 2 == 1) {
                    y += yOffset / 2;
                }

                drawHexagon(g2d, x, y, HEX_SIZE);
            }
        }
    }

    private void drawHexagon(Graphics2D g2d, int x, int y, int size) {
        double angle;
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            angle = Math.toRadians(60 * i);
            xPoints[i] = x + (int) (size * Math.cos(angle));
            yPoints[i] = y + (int) (size * Math.sin(angle));
        }

        Polygon hexagon = new Polygon(xPoints, yPoints, 6);
        g2d.draw(hexagon);
    }
}
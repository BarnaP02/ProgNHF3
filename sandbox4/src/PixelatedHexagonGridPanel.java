import javax.swing.*;
import java.awt.*;

public class PixelatedHexagonGridPanel extends JPanel {

    private static final int HEX_SIZE = 30;  // Size of the hexagon (side length)
    private static final int GRID_SIZE_X = 40;  // Number of hexagons in the x-direction
    private static final int GRID_SIZE_Y = 25;  // Number of hexagons in the y-direction

    private ImageIcon imageIcon;

    public PixelatedHexagonGridPanel() {
        // Load an image (replace "path/to/your/image.png" with the actual path)
        imageIcon = new ImageIcon("tilecube 60x60.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int xOffset = HEX_SIZE * 10 / 6;  // Vertical offset for odd rows

        for (int i = 0; i < GRID_SIZE_X; i++) {
            for (int j = 0; j < GRID_SIZE_Y; j++) {
                int x = i * xOffset * 44 / 40;
                int y = j * HEX_SIZE * 3 / 2;

                if (j % 2 == 1) {
                    x += xOffset / 2 * 44 / 40;
                    //y += HEX_SIZE / 2;
                }

                drawPixelatedHexagon(g2d, x, y, HEX_SIZE);
            }
        }
    }

    private void drawPixelatedHexagon(Graphics2D g2d, int x, int y, int size) {
        double angle;
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            angle = Math.toRadians(60 * i + 30);
            xPoints[i] = x + (int) (size * Math.cos(angle));
            yPoints[i] = y + (int) (size * Math.sin(angle));
        }

        Polygon hexagon = new Polygon(xPoints, yPoints, 6);
        g2d.draw(hexagon);

        // Draw the image inside the hexagon
        Image image = imageIcon.getImage();
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();
        //
        int centerX = x + size / 2 - imageWidth / 2;
        int centerY = y + size / 2 - imageHeight / 2;
        g2d.drawImage(image, centerX, centerY, imageWidth, imageHeight, this);

    }
}

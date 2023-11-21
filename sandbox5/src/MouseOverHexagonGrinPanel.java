import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MouseOverHexagonGridPanel extends JPanel {

    private static final int HEX_SIZE = 30;  // Size of the hexagon (side length)
    private static final int GRID_SIZE_X = 10;  // Number of hexagons in the x-direction
    private static final int GRID_SIZE_Y = 10;  // Number of hexagons in the y-direction

    private ImageIcon imageIcon;
    private int hoveredHexagonX = -1;
    private int hoveredHexagonY = -1;

    public MouseOverHexagonGridPanel() {
        // Load an image (replace "path/to/your/image.png" with the actual path)
        imageIcon = new ImageIcon("titlecube.png");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Check if the mouse is above any hexagon
                checkMouseOverHexagon(e.getX(), e.getY());
            }
        });
    }

    private void checkMouseOverHexagon(int mouseX, int mouseY) {
        int yOffset = HEX_SIZE * 3 / 2;

        for (int i = 0; i < GRID_SIZE_X; i++) {
            for (int j = 0; j < GRID_SIZE_Y; j++) {
                int x = i * HEX_SIZE * 3 / 2;
                int y = j * yOffset;

                if (i % 2 == 1) {
                    y += yOffset;
                }

                if (isMouseOverHexagon(mouseX, mouseY, x, y, HEX_SIZE)) {
                    hoveredHexagonX = i;
                    hoveredHexagonY = j;
                    repaint();
                    return;
                }
            }
        }

        // If the mouse is not above any hexagon
        hoveredHexagonX = -1;
        hoveredHexagonY = -1;
        repaint();
    }

    private boolean isMouseOverHexagon(int mouseX, int mouseY, int hexagonX, int hexagonY, int size) {
        // Check if the mouse coordinates are within the hexagon boundaries
        Polygon hexagon = createHexagon(hexagonX, hexagonY, size);
        return hexagon.contains(mouseX, mouseY);
    }

    private Polygon createHexagon(int x, int y, int size) {
        double angle;
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            angle = Math.toRadians(60 * i);
            xPoints[i] = x + (int) (size * Math.cos(angle));
            yPoints[i] = y + (int) (size * Math.sin(angle));
        }

        return new Polygon(xPoints, yPoints, 6);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int yOffset = HEX_SIZE * 3 / 2;

        for (int i = 0; i < GRID_SIZE_X; i++) {
            for (int j = 0; j < GRID_SIZE_Y; j++) {
                int x = i * HEX_SIZE * 3 / 2;
                int y = j * yOffset;

                if (i % 2 == 1) {
                    y += yOffset;
                }

                if (i == hoveredHexagonX && j == hoveredHexagonY) {
                    g2d.setColor(Color.YELLOW); // Highlight the hovered hexagon
                } else {
                    g2d.setColor(Color.BLACK);
                }

                drawHexagon(g2d, x, y, HEX_SIZE);
            }
        }

        // Draw the image centered in the hovered hexagon
        if (hoveredHexagonX != -1 && hoveredHexagonY != -1) {
            int x = hoveredHexagonX * HEX_SIZE * 3 / 2;
            int y = hoveredHexagonY * yOffset;

            if (hoveredHexagonX % 2 == 1) {
                y += yOffset;
            }

            drawImageHexagon(g2d, x, y, HEX_SIZE);
        }
    }

    private void drawHexagon(Graphics2D g2d, int x, int y, int size) {
        Polygon hexagon = createHexagon(x, y, size);
        g2d.draw(hexagon);
    }

    private void drawImageHexagon(Graphics2D g2d, int x, int y, int size) {
        Polygon hexagon = createHexagon(x, y, size);
        g2d.draw(hexagon);

        // Draw the image centered inside the hexagon
        Image image = imageIcon.getImage();
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();

        // Calculate the position to center the image within the hexagon
        int centerX = x + size / 2 - imageWidth / 2;
        int centerY = y + size / 2 - imageHeight / 2;

        g2d.drawImage(image, centerX, centerY, imageWidth, imageHeight, this);
    }
}

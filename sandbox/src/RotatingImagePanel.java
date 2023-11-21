import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RotatingImagePanel extends JPanel {
    private ImageIcon imageIcon;
    private double angle = 0; // Initial angle of rotation

    public RotatingImagePanel() {
        // Load an image (replace "path/to/your/image.png" with the actual path)
        imageIcon = new ImageIcon("dante yea.gif");

        // Create a timer to trigger repaint at regular intervals for animation
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle += Math.toRadians(5); // Increment the angle for rotation (adjust as needed)
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the width and height of the panel
        int width = getWidth();
        int height = getHeight();

        // Calculate the center of the panel
        int centerX = width / 2;
        int centerY = height / 2;

        // Set the rotation angle
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(angle, centerX, centerY);

        // Draw the rotated image centered in the panel
        Image image = imageIcon.getImage();
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();
        int x = centerX - imageWidth / 2;
        int y = centerY - imageHeight / 2;
        g2d.drawImage(image, x, y, imageWidth, imageHeight, this);

        // Dispose of the rotated graphics context
        g2d.dispose();
    }
}
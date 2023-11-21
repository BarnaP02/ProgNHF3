import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PixelateImage extends JFrame {

    private BufferedImage originalImage;
    private BufferedImage pixelatedImage;

    private JButton pixelateButton;

    public PixelateImage() {
        setTitle("Pixelate Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadImage();
        createUI();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadImage() {
        ImageIcon icon = new ImageIcon("path/to/your/image.jpg"); // Replace with the path to your image
        originalImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = originalImage.getGraphics();
        icon.paintIcon(null, g, 0, 0);
    }

    private void createUI() {
        setLayout(new BorderLayout());

        pixelateButton = new JButton("Pixelate");
        pixelateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pixelateImage();
                repaint();
            }
        });

        add(pixelateButton, BorderLayout.SOUTH);
    }

    private void pixelateImage() {
        int pixelSize = 10; // Adjust this value to control the pixelation level

        pixelatedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = pixelatedImage.getGraphics();

        for (int y = 0; y < originalImage.getHeight(); y += pixelSize) {
            for (int x = 0; x < originalImage.getWidth(); x += pixelSize) {
                int pixel = originalImage.getRGB(x, y);
                for (int yd = 0; yd < pixelSize && y + yd < originalImage.getHeight(); yd++) {
                    for (int xd = 0; xd < pixelSize && x + xd < originalImage.getWidth(); xd++) {
                        pixelatedImage.setRGB(x + xd, y + yd, pixel);
                    }
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (pixelatedImage != null) {
            g.drawImage(pixelatedImage, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PixelateImage();
            }
        });
    }
}

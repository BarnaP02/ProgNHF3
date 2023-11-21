import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Change Non-Transparent Pixels Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        try {
            BufferedImage originalImage = ImageIO.read(Main.class.getResource("/tankH_4.png")); // Load your PNG image here

            // Create a copy of the original image
            BufferedImage modifiedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // Apply a color change to non-transparent pixels
            for (int x = 0; x < originalImage.getWidth(); x++) {
                for (int y = 0; y < originalImage.getHeight(); y++) {
                    int rgb = originalImage.getRGB(x, y);
                    int alpha = (rgb >> 24) & 0xFF;
                    boolean isGray = false;

                    if (alpha > 0) { // Check if the pixel is not transparent
                        int red = (rgb >> 16) & 0xFF;
                        int green = (rgb >> 8) & 0xFF;
                        int blue = rgb & 0xFF;

                        // Define thresholds for black and white in terms of grayscale values
                        if (Math.abs(red-blue) < 20 && Math.abs(blue-green) < 20 && Math.abs(green-red) < 20){
                            isGray = true;
                        }

                        if (isGray){
                            // For pixels that are the shades of gray, maintain them
                            modifiedImage.setRGB(x, y, rgb);
                        }
                        else {
                            // Modify the color components
                            red = Math.max(0, red - 180);
                            green = Math.max(0, green - 180);
                            blue = Math.min(255, blue + 90);

                            // Combine modified color components with original alpha
                            int modifiedRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                            modifiedImage.setRGB(x, y, modifiedRGB);
                        }
                    } else {
                        // Preserve transparency for transparent pixels
                        modifiedImage.setRGB(x, y, rgb);
                    }
                }
            }

            JLabel label = new JLabel(new ImageIcon(modifiedImage));
            frame.add(label);
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

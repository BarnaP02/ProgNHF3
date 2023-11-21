import javax.swing.*;
import java.awt.*;


public class PixelatedHexagonGridApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Pixelated Hexagon Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PixelatedHexagonGridPanel pixelatedHexagonGridPanel = new PixelatedHexagonGridPanel();
        frame.getContentPane().add(pixelatedHexagonGridPanel);

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

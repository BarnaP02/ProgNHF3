import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hexagon Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HexagonGridPanel hexagonGridPanel = new HexagonGridPanel();
        frame.getContentPane().add(hexagonGridPanel);

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
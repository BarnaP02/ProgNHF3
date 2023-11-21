import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;


public class MouseOverHexagonGridApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Mouse Over Hexagon Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MouseOverHexagonGridPanel hexagonGridPanel = new MouseOverHexagonGridPanel();
        frame.getContentPane().add(hexagonGridPanel);

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

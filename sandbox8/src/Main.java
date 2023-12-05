import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    public Main() {
        JFrame frame = new JFrame("Panel Hover");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement mouseClicked event if needed
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Implement mousePressed event if needed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement mouseReleased event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Color.YELLOW); // Change color when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.WHITE); // Change color when mouse exits
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LayeredPane with Layouts Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

        // Create components with layouts
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setBackground(Color.BLUE);
        panel1.add(new JButton("Button 1"));
        panel1.add(new JButton("Button 2"));

        JPanel panel2 = new JPanel(new GridLayout(2, 2));
        panel2.setBackground(Color.RED);
        panel2.add(new JButton("Button 3"));
        panel2.add(new JButton("Button 4"));
        panel2.add(new JButton("Button 5"));
        panel2.add(new JButton("Button 6"));

        // Set bounds for each component
        panel1.setBounds(50, 50, 200, 100);
        panel2.setBounds(100, 120, 200, 150);

        // Add components to the layered pane
        layeredPane.add(panel1, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panel2, JLayeredPane.PALETTE_LAYER);

        frame.setVisible(true);
    }
}

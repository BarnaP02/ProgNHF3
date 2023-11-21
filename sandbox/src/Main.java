import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Swing");

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of the custom panel (RotatingImagePanel)
        RotatingImagePanel rotatingImagePanel = new RotatingImagePanel();

        // Add the custom panel to the frame
        frame.getContentPane().add(rotatingImagePanel);

        // Set the size of the frame
        frame.setSize(300, 300);

        // Make the frame visible
        frame.setVisible(true);
    }
}

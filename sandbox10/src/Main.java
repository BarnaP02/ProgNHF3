import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private ImageIcon icon1;
    private ImageIcon icon2;
    private Timer timer;
    private boolean isIcon1 = true;

    public Main() {
        setTitle("Icon Changer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load your images
        icon1 = new ImageIcon("../EndWar/res/icons/battleship1.png");
        icon2 = new ImageIcon("../EndWar/res/icons/battleship2.png");

        // Set an initial icon
        setIconImage(icon1.getImage());

        // Create a timer to switch icons every 3 seconds (adjust as needed)
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isIcon1) {
                    setIconImage(icon2.getImage());
                } else {
                    setIconImage(icon1.getImage());
                }
                isIcon1 = !isIcon1;
            }
        });
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

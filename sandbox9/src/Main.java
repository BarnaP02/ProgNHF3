import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private JPanel gamePanel;
    private JLabel titleLabel;
    private Timer animationTimer;
    private int square1X, square2X;
    private boolean isTransitioning;
    int squareWidth = 400;

    public Main() {
        setTitle("Turn Transition Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSquares(g);
            }
        };
        gamePanel.setPreferredSize(new Dimension(400, 200));
        add(gamePanel, BorderLayout.CENTER);

        titleLabel = new JLabel("Attack Phase");
        try {
            // Load the custom pixelated font from the extracted folder
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("Megrim-Regular.ttf")).deriveFont(70f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);

            // Set the button's font to the pixelated font
            titleLabel.setFont(pixelFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // If there's an error loading the font, use the default font
            //button.setFont(new Font("Arial", Font.PLAIN, 16));
            titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 80));
        }
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED);

        titleLabel.setVisible(false); // Initially invisible

        gamePanel.add(titleLabel, BorderLayout.CENTER);
        //add(titleLabel, BorderLayout.NORTH); // Add the title label to the frame directly


        animationTimer = new Timer(10, new AnimationListener());

        // Start the animation when the game starts (you can trigger this as needed)
        //pack();
        startTurnTransition();
    }

    private void startTurnTransition() {
        if (!isTransitioning) {
            isTransitioning = true;
            square1X = -squareWidth;
            //square2X = gamePanel.getWidth();
            square2X = 400;
            animationTimer.start();
        }
    }

    private void drawSquares(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(square1X, 0, squareWidth, 500); // Square 1
        g.fillRect(square2X, 0, squareWidth, 500); // Square 2
    }

    private class AnimationListener implements ActionListener {
        private int titleDisplayTime = 2000; // Display title for 2 seconds

        @Override
        public void actionPerformed(ActionEvent e) {
            if (square1X < gamePanel.getWidth() / 2 - squareWidth/2) {
                square1X += 20;
                square2X -= 20;
            } else {
                if (titleDisplayTime > 0) {
                    System.out.println("ÁOÁ");
                    //gamePanel.add(titleLabel, BorderLayout.CENTER);
                    titleLabel.setVisible(true); // Make the title label visible

                    titleDisplayTime -= 20; // Decrease display time
                } else {
                    //gamePanel.remove(titleLabel);
                    titleLabel.setVisible(false); // Make the title label visible
                    animationTimer.stop();
                    isTransitioning = false;
                    reverseAnimation();
                }
            }
            gamePanel.repaint();
        }
    }

    private void reverseAnimation() {
        Timer reverseTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (square1X > -squareWidth) {
                    square1X -= 20;
                    square2X += 20;
                } else {
                    ((Timer) e.getSource()).stop();
                }
                gamePanel.repaint();
            }
        });
        reverseTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main example = new Main();
            example.pack();
            example.setVisible(true);
        });
    }
}

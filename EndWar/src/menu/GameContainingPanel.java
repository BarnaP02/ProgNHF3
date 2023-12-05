package menu;

import main.CycleInfoPanel;
import main.GamePanel;
import main.InfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameContainingPanel extends JPanel{
    protected JPanel centerGamePanel;
    protected GamePanel gamePanel;

    public CycleInfoPanel cycleInfoPanel;
    public InfoPanel infoPanel;
    public GameContainingPanel(MainFrame mainFrame){
        PanelManager panM = new PanelManager();

        centerGamePanel = new JPanel();
        centerGamePanel.setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setFocusable(false);

        gamePanel = new GamePanel(panM);
        cycleInfoPanel = gamePanel.cycleInfoPanel;
        infoPanel = gamePanel.infoPanel;
        centerGamePanel.add(gamePanel, BorderLayout.CENTER);
        centerGamePanel.add(cycleInfoPanel, BorderLayout.SOUTH);
        cycleInfoPanel = new CycleInfoPanel(gamePanel.screenWidth, panM);
        cycleInfoPanel.update(1, gamePanel.getTeam1Name(), "MOVE");

        //infoPanel = new InfoPanel(gamePanel, gamePanel.screenHeight + cycleInfoPanel.getHeight());
        add(centerGamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
        gamePanel.setupGame();
        gamePanel.startGameThread();

        // Set focus traversal policy to customize the focus order
        FocusTraversalPolicy policy = new FocusTraversalPolicy() {
            @Override
            public Component getComponentAfter(Container container, Component component) {
                if (component == gamePanel && gamePanel.gameState == GamePanel.playState &&
                        ((gamePanel.getCycleState() == GamePanel.Cycle.T1MOVE) ||
                                (gamePanel.getCycleState() == GamePanel.Cycle.T2MOVE))) {
                //    return infoPanel.getSlot1(); // After game panel, focus goes to button1
                //} else if (component == gamePanel && gamePanel.gameState != GamePanel.playState) {
                    return gamePanel.infoPanel.getSlot1(); // focus returns to game panel
                //} else if ((component == gamePanel || container == gamePanel) && gamePanel.gameState == GamePanel.pauseState){
                //    return gamePanel; // Default traversal order
                }
                else {
                    return gamePanel;
                }
            }

            @Override
            public Component getComponentBefore(Container aContainer, Component aComponent) {
                if (aComponent == gamePanel && gamePanel.gameState == GamePanel.playState) {
                    return infoPanel.getSlot6(); // After game panel, focus goes to button1
                    //} else if (component == gamePanel && gamePanel.gameState != GamePanel.playState) {
                    //    return gamePanel; // focus returns to game panel
                    //} else if ((component == gamePanel || container == gamePanel) && gamePanel.gameState == GamePanel.pauseState){
                    //    return gamePanel; // Default traversal order
                }
                else {
                    return gamePanel;
                }
            }

            @Override
            public Component getFirstComponent(Container aContainer) {
                return null;
            }

            @Override
            public Component getLastComponent(Container aContainer) {
                return null;
            }

            @Override
            public Component getDefaultComponent(Container aContainer) {
                return gamePanel;
            }

            // Override other methods like getFirstComponent, getLastComponent, and getComponentBefore similarly
            // to complete the FocusTraversalPolicy interface
        };

        setFocusTraversalPolicy(policy);
    }
}

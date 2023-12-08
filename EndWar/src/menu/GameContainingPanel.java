package menu;

import entity.ImageStore;
import main.CycleInfoPanel;
import main.GamePanel;
import main.InfoPanel;
import main.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class GameContainingPanel extends JPanel{
    protected JPanel centerGamePanel;
    protected GamePanel gamePanel;
    public transient KeyHandler keyH;
    private int width;
    private int height;
    public CycleInfoPanel cycleInfoPanel;
    public InfoPanel infoPanel;
    public PanelManager panM;
    public final int tileHeight = 40;
    public final int tileWidth = 42;
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 19;
    public final int screenWidth = tileWidth * maxScreenCol + tileWidth/2;
    public final int screenHeight = (tileHeight * (maxScreenRow - 1)) / 4*3 + tileHeight;

    /***
     * this is the JPanel that contains every panel related to the game
     */
    public GameContainingPanel(){
        panM = new PanelManager();

        centerGamePanel = new JPanel();
        centerGamePanel.setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setLayout(new FlowLayout());
        setFocusable(false);
        setVisible(false);

        width = screenWidth + 270;
        height = screenHeight + 90;

        // Set focus traversal policy to customize the focus order
        FocusTraversalPolicy policy = new FocusTraversalPolicy() {
            @Override
            public Component getComponentAfter(Container container, Component component) {
                if (component == gamePanel && gamePanel.gameState == GamePanel.playState &&
                        ((gamePanel.getCycleState() == GamePanel.Cycle.T1MOVE) ||
                                (gamePanel.getCycleState() == GamePanel.Cycle.T2MOVE))) {
                    return gamePanel.infoPanel.getSlot1(); // focus returns to game panel
                }
                else {
                    return gamePanel;
                }
            }

            @Override
            public Component getComponentBefore(Container aContainer, Component aComponent) {
                if (aComponent == gamePanel && gamePanel.gameState == GamePanel.playState) {
                    return infoPanel.getSlot6(); // After game panel, focus goes to button1
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

    /***
     * this is how the initialisation finishes if we start a new game instead of loading the last save
     */
    public void newGame(){
        gamePanel = new GamePanel(panM);
        this.keyH = gamePanel.keyH;
        cycleInfoPanel = gamePanel.cycleInfoPanel;
        infoPanel = gamePanel.infoPanel;
        centerGamePanel.add(gamePanel, BorderLayout.CENTER);
        centerGamePanel.add(cycleInfoPanel, BorderLayout.SOUTH);
        cycleInfoPanel.update(1, gamePanel.getTeam1Name(), "MOVE");
        add(centerGamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
        setVisible(false);
    }

    /***
     * this is how the initialisation finishes if we load the last save instead of starting a new one
     */
    public void newGameLoaded(){
        loadGame();
        this.keyH = gamePanel.keyH;
        cycleInfoPanel = gamePanel.cycleInfoPanel;
        infoPanel = gamePanel.infoPanel;
        centerGamePanel.add(gamePanel, BorderLayout.CENTER);
        centerGamePanel.add(cycleInfoPanel, BorderLayout.SOUTH);
        cycleInfoPanel.update(1, gamePanel.getTeam1Name(), "MOVE");

        add(centerGamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
        setVisible(false);
    }

    /***
     * loads the content of last_save.ser into gamePanel
     * writes if the loading was successful or not
     */
    public void loadGame(){

        String filePath = "res/last_save.ser";

        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            // Deserialize and read the GamePanel object from the file
            gamePanel = (GamePanel) objectIn.readObject();
            System.out.println("GamePanel object has been loaded.    YEAAAAA");
            gamePanel.reloadGame(panM);

            // Now you have the loaded gamePanel object, you can use it as needed

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading the object: " + e.getMessage());
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}

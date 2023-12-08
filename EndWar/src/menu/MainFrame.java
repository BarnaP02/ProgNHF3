package menu;

import jdk.jfr.SettingControl;
import main.GamePanel;
import main.InfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import static main.Main.fontGallery;

public class MainFrame extends JFrame{
    protected int universeState;
    public static final int startMenuState = 0;
    public static final int conflictSettingsState = 1;
    public static final int teamSettingState = 2;
    public static final int teamSettingCustomColorState = 3;
    public static final int settingState = 4;
    public static final int gamerState = 5;
    private boolean canLoad;
    private JLayeredPane universe = new JLayeredPane();
    private JLabel mainTitle;
    private JLabel mainTitle2;
    private JLabel mainTitle3;
    private JPanel menuPanel = new JPanel();

    private JPanel conflictSettingsPanel = new JPanel();
    private JPanel teamSettingPanel = new JPanel();
    private JPanel settingsPanel = new JPanel();
    private JPanel gaCoPaPlaceFiller = new JPanel();
    private GameContainingPanel gaCoPa = new GameContainingPanel();

    /***
     * this is the JFrame that holds everything
     */
    public MainFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("EndWar");


        // Set the icon
        ImageIcon icon = new ImageIcon("res/icons/battleship1.png");
        setIconImage(icon.getImage());

        gaCoPa.setBounds(0, 0, gaCoPa.getWidth(), gaCoPa.getHeight());
        setSize(gaCoPa.getWidth(),gaCoPa.getHeight());
        gaCoPa.setBorder(BorderFactory.createEtchedBorder());
        universe.add(gaCoPa, JLayeredPane.DEFAULT_LAYER);
        setBackground(Color.BLACK);
        universe.setBackground(Color.BLACK);

        mainTitle = gaCoPa.panM.createLabel("EndWar", fontGallery.getFontMap().get(0).get(7), Color.WHITE);
        mainTitle.setBackground(Color.BLACK);
        mainTitle.setText("EndWar");
        mainTitle.setBounds(500, 20, 800, 200);
        universe.add(mainTitle);

        mainTitle2 = gaCoPa.panM.createLabel("EndWar", fontGallery.getFontMap().get(0).get(7), Color.GRAY);
        mainTitle2.setBackground(Color.BLACK);
        mainTitle2.setText("EndWar");
        mainTitle2.setBounds(503, 20, 800, 200);
        universe.add(mainTitle2);

        mainTitle3 = gaCoPa.panM.createLabel("EndWar", fontGallery.getFontMap().get(0).get(7), Color.DARK_GRAY);
        mainTitle3.setBackground(Color.BLACK);
        mainTitle3.setText("EndWar");
        mainTitle3.setBounds(506, 20, 800, 200);
        universe.add(mainTitle3);

        menuPanel.setBounds(50, 200, 350, 425);
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        menuPanel.setLayout(new GridLayout(4,1));
        menuPanel.setBackground(Color.BLACK);

        JPanel newGameButton = new JPanel();
        JLabel newGameButtonLabel = gaCoPa.panM.createLabel("New Game", fontGallery.getFontMap().get(3).get(5), Color.WHITE);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        newGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement mouseClicked event if needed
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Implement mousePressed event if needed
                universeState = conflictSettingsState;
                universeState = -1;
                gaCoPa.newGame();
                universeStateChange();
                gaCoPa.gamePanel.requestFocus();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement mouseReleased event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                newGameButton.setBackground(Color.WHITE); // Change color when mouse enters
                newGameButtonLabel.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newGameButton.setBackground(Color.BLACK); // Change color when mouse exits
                newGameButtonLabel.setForeground(Color.WHITE);
            }
        });
        newGameButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newGameButtonLabel.setVerticalAlignment(SwingConstants.CENTER);
        newGameButton.add(newGameButtonLabel);
        newGameButton.setBackground(Color.BLACK);
        menuPanel.add(newGameButton);

        JPanel loadGameButton = new JPanel();
        JLabel loadGameButtonLabel;
        File file = new File("res/last_save.ser");
        if (file.exists()) {
            canLoad = true;
            loadGameButtonLabel = gaCoPa.panM.createLabel("Load Game", fontGallery.getFontMap().get(3).get(5), Color.WHITE);
        } else {
            canLoad = false;
            loadGameButtonLabel = gaCoPa.panM.createLabel("Load Game", fontGallery.getFontMap().get(3).get(5), Color.DARK_GRAY);
        }
        loadGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        loadGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement mouseClicked event if needed
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Implement mousePressed event if needed
                if (canLoad){
                    universeState = conflictSettingsState;
                    gaCoPa.newGameLoaded();
                    universeState = -1;
                    universeStateChange();
                    gaCoPa.gamePanel.requestFocus();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement mouseReleased event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (canLoad){
                    loadGameButton.setBackground(Color.WHITE); // Change color when mouse enters
                    loadGameButtonLabel.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (canLoad){
                    loadGameButton.setBackground(Color.BLACK); // Change color when mouse exits
                    loadGameButtonLabel.setForeground(Color.WHITE);
                }

            }
        });
        loadGameButton.add(loadGameButtonLabel);
        newGameButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newGameButtonLabel.setVerticalAlignment(SwingConstants.CENTER);
        menuPanel.add(loadGameButton);
        loadGameButton.setBackground(Color.BLACK);


        JPanel settingButton = new JPanel();
        settingButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        JLabel settingButtonLabel = gaCoPa.panM.createLabel("Settings", fontGallery.getFontMap().get(3).get(5), Color.WHITE);newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        settingButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement mouseClicked event if needed
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Implement mousePressed event if needed
                universeState = settingState;
                universeStateChange();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement mouseReleased event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                settingButton.setBackground(Color.WHITE); // Change color when mouse enters
                settingButtonLabel.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingButton.setBackground(Color.BLACK); // Change color when mouse exits
                settingButtonLabel.setForeground(Color.WHITE);
            }
        });
        settingButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingButtonLabel.setVerticalAlignment(SwingConstants.CENTER);
        settingButton.add(settingButtonLabel);
        settingButton.setBackground(Color.BLACK);
        menuPanel.add(settingButton);

        JPanel exitButton = new JPanel();
        exitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        JLabel exitButtonLabel = gaCoPa.panM.createLabel("Exit", fontGallery.getFontMap().get(3).get(5), Color.WHITE);newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement mouseClicked event if needed
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
                // Implement mousePressed event if needed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement mouseReleased event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.WHITE); // Change color when mouse enters
                exitButtonLabel.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(Color.BLACK); // Change color when mouse exits
                exitButtonLabel.setForeground(Color.WHITE);
            }
        });
        exitButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exitButtonLabel.setVerticalAlignment(SwingConstants.CENTER);
        exitButton.add(exitButtonLabel);
        exitButton.setBackground(Color.BLACK);
        menuPanel.add(exitButton);

        universe.add(menuPanel);

        conflictSettingsPanel.setBounds(50, 50, 450, 575);
        universe.add(conflictSettingsPanel);
        conflictSettingsPanel.setBackground(Color.BLACK);

        teamSettingPanel.setBounds(50, 50, 450, 575);
        universe.add(teamSettingPanel);
        teamSettingPanel.setBackground(Color.BLACK);

        settingsPanel.setBounds(50, 50, 450, 575);
        universe.add(settingsPanel);
        settingsPanel.setBackground(Color.BLACK);

        gaCoPaPlaceFiller.setBounds(0, 0, (gaCoPa.getWidth()-16), (gaCoPa.getHeight())-39);
        gaCoPaPlaceFiller.setBackground(Color.BLACK);
        gaCoPaPlaceFiller.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        universe.add(gaCoPaPlaceFiller);

        add(universe);

        setLocationRelativeTo(null);
        setVisible(true);

    }

    /***
     * this makes the menu work, while using JLayeredPane
     * this is sets which panels should be in front or at the back
     */
    public void universeStateChange(){
        switch (universeState){
            case startMenuState:
                universe.setLayer(menuPanel, JLayeredPane.POPUP_LAYER);
                menuPanel.setVisible(true);
                universe.setLayer(mainTitle, JLayeredPane.POPUP_LAYER);
                mainTitle.setVisible(true);
                universe.setLayer(mainTitle2, JLayeredPane.POPUP_LAYER);
                mainTitle2.setVisible(true);
                universe.setLayer(mainTitle3, JLayeredPane.POPUP_LAYER);
                mainTitle3.setVisible(true);
                universe.setLayer(gaCoPaPlaceFiller, JLayeredPane.PALETTE_LAYER);
                gaCoPaPlaceFiller.setVisible(true);
                universe.setLayer(conflictSettingsPanel, JLayeredPane.DEFAULT_LAYER);
                conflictSettingsPanel.setVisible(false);
                universe.setLayer(teamSettingPanel, JLayeredPane.DEFAULT_LAYER);
                teamSettingPanel.setVisible(false);
                universe.setLayer(settingsPanel, JLayeredPane.DEFAULT_LAYER);
                settingsPanel.setVisible(false);
                universe.setLayer(gaCoPa, JLayeredPane.DEFAULT_LAYER);
                gaCoPa.setVisible(false);
                break;
            case conflictSettingsState:
                universe.setLayer(conflictSettingsPanel, JLayeredPane.POPUP_LAYER);
                conflictSettingsPanel.setVisible(true);
                universe.setLayer(mainTitle, JLayeredPane.POPUP_LAYER);
                mainTitle.setVisible(true);
                universe.setLayer(mainTitle2, JLayeredPane.POPUP_LAYER);
                mainTitle2.setVisible(true);
                universe.setLayer(mainTitle3, JLayeredPane.POPUP_LAYER);
                mainTitle3.setVisible(true);
                universe.setLayer(gaCoPaPlaceFiller, JLayeredPane.PALETTE_LAYER);
                gaCoPaPlaceFiller.setVisible(true);
                universe.setLayer(menuPanel, JLayeredPane.DEFAULT_LAYER);
                menuPanel.setVisible(false);
                universe.setLayer(teamSettingPanel, JLayeredPane.DEFAULT_LAYER);
                teamSettingPanel.setVisible(false);
                universe.setLayer(settingsPanel, JLayeredPane.DEFAULT_LAYER);
                settingsPanel.setVisible(false);
                universe.setLayer(gaCoPa, JLayeredPane.DEFAULT_LAYER);
                gaCoPa.setVisible(false);
                break;
            case teamSettingState, teamSettingCustomColorState:
                universe.setLayer(teamSettingPanel, JLayeredPane.POPUP_LAYER);
                teamSettingPanel.setVisible(true);
                universe.setLayer(mainTitle, JLayeredPane.POPUP_LAYER);
                mainTitle.setVisible(true);
                universe.setLayer(mainTitle2, JLayeredPane.POPUP_LAYER);
                mainTitle2.setVisible(true);
                universe.setLayer(mainTitle3, JLayeredPane.POPUP_LAYER);
                mainTitle3.setVisible(true);
                universe.setLayer(gaCoPaPlaceFiller, JLayeredPane.PALETTE_LAYER);
                gaCoPaPlaceFiller.setVisible(true);
                universe.setLayer(conflictSettingsPanel, JLayeredPane.DEFAULT_LAYER);
                conflictSettingsPanel.setVisible(false);
                universe.setLayer(menuPanel, JLayeredPane.DEFAULT_LAYER);
                menuPanel.setVisible(false);
                universe.setLayer(settingsPanel, JLayeredPane.DEFAULT_LAYER);
                settingsPanel.setVisible(false);
                universe.setLayer(gaCoPa, JLayeredPane.DEFAULT_LAYER);
                gaCoPa.setVisible(false);
                break;
            case settingState:
                universe.setLayer(settingsPanel, JLayeredPane.POPUP_LAYER);
                settingsPanel.setVisible(true);
                universe.setLayer(mainTitle, JLayeredPane.POPUP_LAYER);
                mainTitle.setVisible(true);
                universe.setLayer(mainTitle2, JLayeredPane.POPUP_LAYER);
                mainTitle2.setVisible(true);
                universe.setLayer(mainTitle3, JLayeredPane.POPUP_LAYER);
                mainTitle3.setVisible(true);
                universe.setLayer(gaCoPaPlaceFiller, JLayeredPane.PALETTE_LAYER);
                gaCoPaPlaceFiller.setVisible(true);
                universe.setLayer(conflictSettingsPanel, JLayeredPane.DEFAULT_LAYER);
                conflictSettingsPanel.setVisible(false);
                universe.setLayer(teamSettingPanel, JLayeredPane.DEFAULT_LAYER);
                teamSettingPanel.setVisible(false);
                universe.setLayer(menuPanel, JLayeredPane.DEFAULT_LAYER);
                menuPanel.setVisible(false);
                universe.setLayer(gaCoPa, JLayeredPane.DEFAULT_LAYER);
                gaCoPa.setVisible(false);
                break;
            default:
                universe.setLayer(gaCoPa, JLayeredPane.POPUP_LAYER);
                gaCoPa.setVisible(true);
                universe.setLayer(gaCoPaPlaceFiller, JLayeredPane.PALETTE_LAYER);
                gaCoPaPlaceFiller.setVisible(false);
                universe.setLayer(mainTitle, JLayeredPane.DEFAULT_LAYER);
                mainTitle.setVisible(true);
                universe.setLayer(mainTitle2, JLayeredPane.DEFAULT_LAYER);
                mainTitle2.setVisible(true);
                universe.setLayer(mainTitle3, JLayeredPane.DEFAULT_LAYER);
                mainTitle3.setVisible(true);
                universe.setLayer(conflictSettingsPanel, JLayeredPane.DEFAULT_LAYER);
                conflictSettingsPanel.setVisible(false);
                universe.setLayer(teamSettingPanel, JLayeredPane.DEFAULT_LAYER);
                teamSettingPanel.setVisible(false);
                universe.setLayer(settingsPanel, JLayeredPane.DEFAULT_LAYER);
                settingsPanel.setVisible(false);
                universe.setLayer(menuPanel, JLayeredPane.DEFAULT_LAYER);
                menuPanel.setVisible(false);
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                gaCoPa.gamePanel.setupGame();
                gaCoPa.gamePanel.startGameThread();
                break;
        }
    }
}

package menu;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    protected int appState;
    public static final int startMenuState = 0;
    public static final int conflictSettingsState = 1;
    public static final int teamSettingPanelState = 2;
    public static final int settingPanelState = 3;
    public static final int gamerState = 4;
    public static final int pauseMenuState = 5;
    public MainFrame(){

        //JFrame window = new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("EndWar");
        //window.setLayout(BorderLayout.CENTER);
        GameContainingPanel gaCoPa = new GameContainingPanel(this);
        add(gaCoPa);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);

    }
}

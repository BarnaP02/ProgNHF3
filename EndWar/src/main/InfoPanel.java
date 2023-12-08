package main;

import com.sun.nio.sctp.NotificationHandler;
import entity.ImageStore;
import entity.SuperUnit;
import entity.*;
import menu.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.NoRouteToHostException;

import static javax.swing.UIManager.getIcon;
import static main.Main.fontGallery;

public class InfoPanel extends JPanel {
    private PanelManager panM;
    private SuperUnit inspectedUnit;
    private JLabel cycleNumberField = new JLabel();
    private JLabel currentHighlighted = new JLabel();
    private JLabel currentTeamField = new JLabel();
    private JLabel currentMoving = new JLabel();
    private JLabel currentActed = new JLabel();
    private JLabel currentPhaseField = new JLabel();


    private JPanel slot1 = new JPanel();
    private JLabel slot1Label;
    private JPanel slot2 = new JPanel();
    private JLabel slot2Label;
    private JPanel slot3 = new JPanel();
    private JLabel slot3Label;
    private JPanel slot4 = new JPanel();
    private JLabel slot4Label;
    private JPanel slot5 = new JPanel();
    private JLabel slot5Label;
    private JPanel slot6 = new JPanel();
    private JLabel slot6Label;
    private ImageIcon prev1Icon = new ImageIcon();
    private ImageIcon prev2Icon = new ImageIcon();
    private ImageIcon prev3Icon = new ImageIcon();
    private ImageIcon prev4Icon = new ImageIcon();
    private ImageIcon prev5Icon = new ImageIcon();
    private ImageIcon prev6Icon = new ImageIcon();
    private JPanel team1Stats;
    private JPanel team2Stats;
    private JLabel team1GroundStats;
    private JLabel team1AirStats;
    private JLabel team1SeaStats;
    private JLabel team2GroundStats;
    private JLabel team2AirStats;
    private JLabel team2SeaStats;
    private int t1Ground = 0;
    private int t1Air = 0;
    private int t1Sea = 0;
    private int t2Ground = 0;
    private int t2Air = 0;
    private int t2Sea = 0;
    private int oldT1Ground = 0;
    private int oldT1Air = 0;
    private int oldT1Sea = 0;
    private int oldT2Ground = 0;
    private int oldT2Air = 0;
    private int oldT2Sea = 0;
    private ImageIcon unitIcon;
    private ImageIcon unitIconPrev;
    private JLabel unitImageLabel;
    private JLabel unitHP;
    private JLabel unitXP;
    private JLabel unitARgrd;
    private JLabel unitARair;
    private JLabel unitARsea;
    private JLabel unitDgrd;
    private JLabel unitDair;
    private JLabel unitDsea;

    /***
     * this is a very useful panel, this shows the stats of the unit that is on the same tile as the cursor
     * also when the cursor is on a structure with the same color as the player,
     * pressing tab the player can iterate between the stored units and while a unit is highlighted,
     * pressing space will select that unit
     * @param gp this panel displays information from the GamePanel
     * @param panM used for creating placeholders
     * @param height the height of this panel
     */
    public InfoPanel(GamePanel gp, PanelManager panM, int height){
        this.setPreferredSize(new Dimension(250, height));
        this.setBackground(Color.YELLOW);
        this.setFocusable(false);
        this.panM = panM;

        setLayout(new GridLayout(2,1));
        JPanel currentUnitPanel = new JPanel();
        currentUnitPanel.setBackground(Color.RED);

        //Unit preview panel
        GridBagConstraints gbcUnitPreview = new GridBagConstraints();
        gbcUnitPreview.gridx = 0;
        gbcUnitPreview.gridy = 0;
        gbcUnitPreview.gridwidth = 2;
        gbcUnitPreview.gridheight = 4;
        gbcUnitPreview.weightx = 1.0;
        gbcUnitPreview.fill = GridBagConstraints.BOTH;
        gbcUnitPreview.anchor = GridBagConstraints.CENTER;
        gbcUnitPreview.insets = new Insets(5, 5, 5, 5);

        //Unit HP and XP panel
        GridBagConstraints gbcUnitXPHP = new GridBagConstraints();
        gbcUnitXPHP.gridx = 2;
        gbcUnitXPHP.gridy = 0;
        gbcUnitXPHP.gridwidth = 1;
        gbcUnitXPHP.gridheight = 4;
        gbcUnitXPHP.weightx = 1;
        gbcUnitXPHP.fill = GridBagConstraints.BOTH;
        gbcUnitXPHP.anchor = GridBagConstraints.CENTER;
        gbcUnitXPHP.insets = new Insets(5, 5, 5, 5);

        //Unit attack range panel
        GridBagConstraints gbcUnitStatLabel = new GridBagConstraints();
        gbcUnitStatLabel.gridx = 0;
        gbcUnitStatLabel.gridy = 4;
        gbcUnitStatLabel.gridwidth = 3;
        gbcUnitStatLabel.gridheight = 1;
        gbcUnitStatLabel.weightx = 1.0;
        gbcUnitStatLabel.fill = GridBagConstraints.BOTH;
        gbcUnitStatLabel.anchor = GridBagConstraints.CENTER;
        gbcUnitStatLabel.insets = new Insets(5, 5, 5, 5);

        //Unit attack range panel
        GridBagConstraints gbcUnitAR = new GridBagConstraints();
        gbcUnitAR.gridx = 0;
        gbcUnitAR.gridy = 5;
        gbcUnitAR.gridwidth = 3;
        gbcUnitAR.gridheight = 1;
        gbcUnitAR.weightx = 1.0;
        gbcUnitAR.fill = GridBagConstraints.BOTH;
        gbcUnitAR.anchor = GridBagConstraints.CENTER;
        gbcUnitAR.insets = new Insets(5, 5, 5, 5);

        //Unit attack damage panel
        GridBagConstraints gbcUnitD = new GridBagConstraints();
        gbcUnitD.gridx = 0;
        gbcUnitD.gridy = 6;
        gbcUnitD.gridwidth = 3;
        gbcUnitD.gridheight = 1;
        gbcUnitD.weightx = 1.0;
        gbcUnitD.fill = GridBagConstraints.BOTH;
        gbcUnitD.anchor = GridBagConstraints.CENTER;
        gbcUnitD.insets = new Insets(5, 5, 5, 5);

        JPanel unitPreviewPanel = panM.createPlaceHolder(Color.DARK_GRAY);
        unitIcon = gp.imagS.getGalleryTeam1BigIcon().get(U_tank_H.class);
        unitIconPrev = unitIcon;
        unitImageLabel = new JLabel();
        unitImageLabel.setIcon(unitIcon);

        unitPreviewPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        unitPreviewPanel.add(unitImageLabel);
        unitPreviewPanel.setPreferredSize(new Dimension(240/3*2, 250/3*2));
        currentUnitPanel.add(unitPreviewPanel, gbcUnitPreview);
        JPanel unitXPHPPanel = panM.createPlaceHolder(Color.BLACK);
        unitXPHPPanel.setPreferredSize(new Dimension(240/3, 250/3*2));
        unitXPHPPanel.setLayout(new GridLayout(4,1));
        unitXPHPPanel.add(panM.createLabel("HP:", fontGallery.getFontMap().get(3).get(3), Color.WHITE));
        unitHP = new JLabel();
        unitHP.setFont(fontGallery.getFontMap().get(3).get(3));
        unitHP.setForeground(Color.GRAY);
        unitXPHPPanel.add(unitHP);
        unitXPHPPanel.add(panM.createLabel("XP:", fontGallery.getFontMap().get(3).get(3), Color.WHITE));
        unitXP = new JLabel();
        unitXP.setFont(fontGallery.getFontMap().get(3).get(3));
        unitXP.setForeground(Color.MAGENTA);
        unitXPHPPanel.add(unitXP);

        currentUnitPanel.add(unitXPHPPanel,gbcUnitXPHP);

        JPanel unitStatLabelPanel = new JPanel(new GridLayout(1,4));
        unitStatLabelPanel.setPreferredSize(new Dimension(250, 40));
        unitStatLabelPanel.setBackground(Color.BLACK);
        JLabel statfiller = new JLabel();
        statfiller.setBackground(Color.BLACK);
        unitStatLabelPanel.add(statfiller);
        statfiller = panM.createLabel("GRD:",fontGallery.getFontMap().get(3).get(2), Color.GREEN);
        unitStatLabelPanel.add(statfiller);
        statfiller = panM.createLabel("AIR:",fontGallery.getFontMap().get(3).get(2), Color.GRAY);
        unitStatLabelPanel.add(statfiller);
        statfiller = panM.createLabel("SEA:",fontGallery.getFontMap().get(3).get(2), Color.CYAN);
        unitStatLabelPanel.add(statfiller);
        currentUnitPanel.add(unitStatLabelPanel,gbcUnitStatLabel);

        JPanel unitARPanel = new JPanel(new GridLayout(1,4));
        unitARPanel.setPreferredSize(new Dimension(250, 40));
        unitARPanel.setBackground(Color.BLACK);
        unitARPanel.add(panM.createLabel("RNG:",fontGallery.getFontMap().get(3).get(2), Color.WHITE));
        unitARgrd = new JLabel();
        unitARgrd.setFont(fontGallery.getFontMap().get(3).get(2));
        unitARgrd.setForeground(Color.GREEN);
        unitARPanel.add(unitARgrd);
        unitARair = new JLabel();
        unitARair.setFont(fontGallery.getFontMap().get(3).get(2));
        unitARair.setForeground(Color.GRAY);
        unitARPanel.add(unitARair);
        unitARsea = new JLabel();
        unitARsea.setFont(fontGallery.getFontMap().get(3).get(2));
        unitARsea.setForeground(Color.CYAN);
        unitARPanel.add(unitARsea);
        currentUnitPanel.add(unitARPanel,gbcUnitAR);

        JPanel unitDPanel = new JPanel(new GridLayout(1,4));
        unitDPanel.setPreferredSize(new Dimension(250, 40));
        unitDPanel.setBackground(Color.BLACK);
        unitDPanel.add(panM.createLabel("DMG:",fontGallery.getFontMap().get(3).get(2), Color.WHITE));
        unitDgrd = new JLabel();
        unitDgrd.setFont(fontGallery.getFontMap().get(3).get(2));
        unitDgrd.setForeground(Color.GREEN);
        unitDPanel.add(unitDgrd);
        unitDair = new JLabel();
        unitDair.setFont(fontGallery.getFontMap().get(3).get(2));
        unitDair.setForeground(Color.GRAY);
        unitDPanel.add(unitDair);
        unitDsea = new JLabel();
        unitDsea.setFont(fontGallery.getFontMap().get(3).get(2));
        unitDsea.setForeground(Color.CYAN);
        unitDPanel.add(unitDsea);
        currentUnitPanel.add(unitDPanel,gbcUnitAR);

        add(currentUnitPanel);

        JPanel currentInventoryPanel = new JPanel(new GridLayout(2, 3));
        currentInventoryPanel.setBackground(Color.CYAN);
        ImageIcon tankIcon = new ImageIcon(gp.imagS.getGallery().get(U_tank_H.class).get(4));
        slot1Label = new JLabel(tankIcon);
        slot1.add(slot1Label);
        slot1.setBackground(Color.BLACK);
        slot1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot1.setFocusable(false);
        // Add space key binding to each panel
        slot1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel1");
        slot1.getActionMap().put("selectPanel1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 1);
                if (slot1.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(0);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot1);
        slot2Label = new JLabel(tankIcon);
        slot2.add(slot2Label);
        slot2.setBackground(Color.BLACK);
        slot2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot2.setFocusable(false);
        // Add space key binding to each panel
        slot2.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel2");
        slot2.getActionMap().put("selectPanel2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 2);
                if (slot2.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(1);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot2);
        slot3Label = new JLabel(tankIcon);
        slot3.add(slot3Label);
        slot3.setBackground(Color.BLACK);
        slot3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot3.setFocusable(false);
        // Add space key binding to each panel
        slot3.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel3");
        slot3.getActionMap().put("selectPanel3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 3);
                if (slot3.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(2);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot3);
        slot4Label = new JLabel(tankIcon);
        slot4.add(slot4Label);
        slot4.setBackground(Color.BLACK);
        slot4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot4.setFocusable(false);
        // Add space key binding to each panel
        slot4.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel4");
        slot4.getActionMap().put("selectPanel4", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 4);
                if (slot4.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(3);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot4);
        slot5Label = new JLabel(tankIcon);
        slot5.add(slot5Label);
        slot5.setBackground(Color.BLACK);
        slot5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot5.setFocusable(false);
        // Add space key binding to each panel
        slot5.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel5");
        slot5.getActionMap().put("selectPanel5", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 5);
                if (slot5.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(4);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot5);
        slot6Label = new JLabel(tankIcon);
        slot6.add(slot6Label);
        slot6.setBackground(Color.BLACK);
        slot6.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot6.setFocusable(false);
        // Add space key binding to each panel
        slot6.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel6");
        slot6.getActionMap().put("selectPanel6", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 6);
                if (slot6.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(5);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot6);

        JPanel otherStats = new JPanel(new GridLayout(2,1));
        otherStats.add(currentInventoryPanel);

        currentUnitPanel.setBackground(Color.BLACK);

        JPanel gameStatsPanel = new JPanel(new GridLayout(6, 1));
        gameStatsPanel.setBackground(Color.BLACK);
        JLabel label = panM.createLabel(gp.getTeam1Name(), fontGallery.getFontMap().get(3).get(2), Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        //row 1
        gameStatsPanel.add(label);
        //row 2
        JPanel team1Labels = new JPanel(new GridLayout(1, 3));
        team1Labels.add(panM.createLabel("GRD:", fontGallery.getFontMap().get(3).get(1), Color.GREEN));
        team1Labels.add(panM.createLabel("AIR:", fontGallery.getFontMap().get(3).get(1), Color.GRAY));
        team1Labels.add(panM.createLabel("SEA:", fontGallery.getFontMap().get(3).get(1), Color.CYAN));
        team1Labels.setBackground(Color.BLACK);
        gameStatsPanel.add(team1Labels);
        //row 3
        team1Stats = new JPanel(new GridLayout(1, 3));
        team1GroundStats = panM.createLabel(""+t1Ground, fontGallery.getFontMap().get(3).get(2), Color.GREEN);
        team1Stats.add(team1GroundStats);
        team1AirStats = panM.createLabel(""+t1Air, fontGallery.getFontMap().get(3).get(2), Color.GRAY);
        team1Stats.add(team1AirStats);
        team1SeaStats = panM.createLabel(""+t1Sea, fontGallery.getFontMap().get(3).get(2), Color.CYAN);
        team1Stats.add(team1SeaStats);
        team1Stats.setBackground(Color.BLACK);
        gameStatsPanel.add(team1Stats);

        label = panM.createLabel(gp.getTeam2Name(), fontGallery.getFontMap().get(3).get(2), Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        //row 4
        gameStatsPanel.add(label);
        //row 5
        JPanel team2Labels = new JPanel(new GridLayout(1, 3));
        team2Labels.add(panM.createLabel("GRD:", fontGallery.getFontMap().get(3).get(1), Color.GREEN));
        team2Labels.add(panM.createLabel("AIR:", fontGallery.getFontMap().get(3).get(1), Color.GRAY));
        team2Labels.add(panM.createLabel("SEA:", fontGallery.getFontMap().get(3).get(1), Color.CYAN));
        team2Labels.setBackground(Color.BLACK);
        gameStatsPanel.add(team2Labels);
        //row 6
        team2Stats = new JPanel(new GridLayout(1, 3));
        team2GroundStats = panM.createLabel(""+t2Ground, fontGallery.getFontMap().get(3).get(2), Color.GREEN);
        team2Stats.add(team2GroundStats);
        team2AirStats = panM.createLabel(""+t2Air, fontGallery.getFontMap().get(3).get(2), Color.GRAY);
        team2Stats.add(team2AirStats);
        team2SeaStats = panM.createLabel(""+t2Sea, fontGallery.getFontMap().get(3).get(2), Color.CYAN);
        team2Stats.add(team2SeaStats);
        team2Stats.setBackground(Color.BLACK);
        gameStatsPanel.add(team2Stats);

        otherStats.add(gameStatsPanel);
        add(otherStats);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("permanentFocusOwner", evt -> {
            Component focused = (Component) evt.getNewValue();
            if (slot1 == focused) {
                System.out.println("Focused panel index: " + 1);
                inspectedUnit = gp.getStructureFromTile(gp.cruser.getHover()).getInventory().get(0);
            }
            else if (slot2 == focused) {
                System.out.println("Focused panel index: " + 2);
                inspectedUnit = gp.getStructureFromTile(gp.cruser.getHover()).getInventory().get(1);
            }
            else if (slot3 == focused) {
                System.out.println("Focused panel index: " + 3);
                inspectedUnit = gp.getStructureFromTile(gp.cruser.getHover()).getInventory().get(2);
            }
            else if (slot4 == focused) {
                System.out.println("Focused panel index: " + 4);
                inspectedUnit = gp.getStructureFromTile(gp.cruser.getHover()).getInventory().get(3);
            }
            else if (slot5 == focused) {
                System.out.println("Focused panel index: " + 5);
                inspectedUnit = gp.getStructureFromTile(gp.cruser.getHover()).getInventory().get(4);
            }
            else if (slot6 == focused) {
                System.out.println("Focused panel index: " + 6);
                inspectedUnit = gp.getStructureFromTile(gp.cruser.getHover()).getInventory().get(5);
            }
            else{
                inspectedUnit = null;
            }
        });
    }

    /***
     * this updated this panel with the current information
     * @param gp for accessing information
     * @param unit if a visible unit is standing on the same tile as the cursor then that unit, otherwise null
     */
    public void update(GamePanel gp, SuperUnit unit){
        if (gp.cycleState == GamePanel.Cycle.T2ATTACK || gp.cycleState == GamePanel.Cycle.T1ATTACK){
            setSlotsFocusable(false);
        }
        if (gp.cruser.getHover().getType().equals("depot") || gp.cruser.getHover().getType().equals("harbor") ||
                gp.cruser.getHover().getType().equals("factory")){

            SuperStructure struc = gp.getStructureFromTile(gp.cruser.getHover());
            if (!struc.getInventory().isEmpty()){
                slot1.setFocusable((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0));
                ImageIcon icon;
                if (!struc.getInventory().get(0).isActed()){
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1Icon().get(struc.getInventory().get(0).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2Icon().get(struc.getInventory().get(0).getClass());
                    }
                }
                else {
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1IconShaded().get(struc.getInventory().get(0).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2IconShaded().get(struc.getInventory().get(0).getClass());
                    }
                }
                if (prev1Icon != icon){
                    slot1Label.setIcon(icon);
                    prev1Icon = icon;
                    slot1Label.repaint();
                }
                slot1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
            else {
                slot1Label.setIcon(null);
                slot1Label.repaint();
                prev1Icon = null;
                slot1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            }
            if (struc.getInventory().size() > 1){
                slot2.setFocusable((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0));
                ImageIcon icon;
                if (!struc.getInventory().get(1).isActed()){
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1Icon().get(struc.getInventory().get(1).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2Icon().get(struc.getInventory().get(1).getClass());
                    }
                }
                else {
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1IconShaded().get(struc.getInventory().get(1).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2IconShaded().get(struc.getInventory().get(1).getClass());
                    }
                }
                if (prev2Icon != icon){
                    slot2Label.setIcon(icon);
                    prev2Icon = icon;
                    slot2Label.repaint();
                }
                slot2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
            else {
                slot2Label.setIcon(null);
                slot2Label.repaint();
                prev2Icon = null;
                slot2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            }
            if (struc.getInventory().size() > 2){
                slot3.setFocusable((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0));
                ImageIcon icon;
                if (!struc.getInventory().get(2).isActed()){
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1Icon().get(struc.getInventory().get(2).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2Icon().get(struc.getInventory().get(2).getClass());
                    }
                }
                else {
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1IconShaded().get(struc.getInventory().get(2).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2IconShaded().get(struc.getInventory().get(2).getClass());
                    }
                }
                if (prev3Icon != icon){
                    slot3Label.setIcon(icon);
                    prev3Icon = icon;
                    slot3Label.repaint();
                }
                slot3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
            else {
                slot3Label.setIcon(null);
                slot3Label.repaint();
                prev3Icon = null;
                slot3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            }
            if (struc.getInventory().size() > 3){
                slot4.setFocusable((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0));
                ImageIcon icon;
                if (!struc.getInventory().get(3).isActed()){
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1Icon().get(struc.getInventory().get(3).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2Icon().get(struc.getInventory().get(3).getClass());
                    }
                }
                else {
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1IconShaded().get(struc.getInventory().get(3).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2IconShaded().get(struc.getInventory().get(3).getClass());
                    }
                }
                if (prev4Icon != icon){
                    slot4Label.setIcon(icon);
                    prev4Icon = icon;
                    slot4Label.repaint();
                }
                slot4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
            else {
                slot4Label.setIcon(null);
                slot4Label.repaint();
                prev4Icon = null;
                slot4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            }
            if (struc.getInventory().size() > 4){
                slot5.setFocusable((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0));
                ImageIcon icon;
                if (!struc.getInventory().get(4).isActed()){
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1Icon().get(struc.getInventory().get(4).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2Icon().get(struc.getInventory().get(4).getClass());
                    }
                }
                else {
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1IconShaded().get(struc.getInventory().get(4).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2IconShaded().get(struc.getInventory().get(4).getClass());
                    }
                }
                if (prev5Icon != icon){
                    slot5Label.setIcon(icon);
                    prev5Icon = icon;
                    slot5Label.repaint();
                }
                slot5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
            else {
                slot5Label.setIcon(null);
                slot5Label.repaint();
                prev5Icon = null;
                slot5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            }
            if (struc.getInventory().size() > 5){
                slot6.setFocusable((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0));
                ImageIcon icon;
                if (!struc.getInventory().get(5).isActed()){
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1Icon().get(struc.getInventory().get(5).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2Icon().get(struc.getInventory().get(5).getClass());
                    }
                }
                else {
                    if (struc.getSide() == 0){
                        icon = gp.imagS.getGalleryTeam1IconShaded().get(struc.getInventory().get(5).getClass());
                    }
                    else {
                        icon = gp.imagS.getGalleryTeam2IconShaded().get(struc.getInventory().get(5).getClass());
                    }
                }
                if (prev6Icon != icon){
                    slot6Label.setIcon(icon);
                    prev6Icon = icon;
                    slot6Label.repaint();
                }
                slot6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
            else {
                slot6Label.setIcon(null);
                slot6Label.repaint();
                prev6Icon = null;
                slot6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            }


            if (slot1.hasFocus()){
                unit = struc.getInventory().get(0);
                slot1.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            }
            if (slot2.hasFocus()){
                unit = struc.getInventory().get(1);
                slot2.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            }
            if (slot3.hasFocus()){
                unit = struc.getInventory().get(2);
                slot3.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            }
            if (slot4.hasFocus()){
                unit = struc.getInventory().get(3);
                slot4.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            }
            if (slot5.hasFocus()){
                unit = struc.getInventory().get(4);
                slot5.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            }
            if (slot6.hasFocus()){
                unit = struc.getInventory().get(5);
                slot6.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            }
        }
        else{
            slot1Label.setIcon(null);
            slot1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            prev1Icon = null;

            slot2Label.setIcon(null);
            slot2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            prev2Icon = null;

            slot3Label.setIcon(null);
            slot3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            prev3Icon = null;

            slot4Label.setIcon(null);
            slot4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            prev4Icon = null;

            slot5Label.setIcon(null);
            slot5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            prev5Icon = null;

            slot6Label.setIcon(null);
            slot6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            prev6Icon = null;
        }


        if (unit != null){
            ImageIcon newUnitIcon;
            SuperStructure struc = gp.getStructureFromTile(gp.cruser.getHover());
            if (struc != null) {
                //in this case that unit color is the color of the structure
                if (struc.getSide() == 0) {
                    newUnitIcon = gp.imagS.getGalleryTeam1BigIcon().get(unit.getClass());
                } else {
                    newUnitIcon = gp.imagS.getGalleryTeam2BigIcon().get(unit.getClass());
                }
            }
            else if (unit.getTeamNum() == 0){
                newUnitIcon = gp.imagS.getGalleryTeam1BigIcon().get(unit.getClass());
            }
            else {
                newUnitIcon = gp.imagS.getGalleryTeam2BigIcon().get(unit.getClass());
            }

            if (newUnitIcon != unitIconPrev){
                unitIcon = newUnitIcon;
                unitIconPrev = unitIcon;
                unitHP.setText(""+unit.getHp());
                unitXP.setText(""+unit.getXp());
                unitImageLabel.setIcon(unitIcon);
                String statfillr;
                if (unit.getAttackRange()[0] == unit.getAttackRange()[1]){
                    statfillr = ""+unit.getAttackRange()[0];
                }
                else {
                    statfillr = unit.getAttackRange()[0]+"-"+unit.getAttackRange()[1];
                }
                unitARgrd.setText(statfillr);

                if (unit.getAttackRange()[2] == unit.getAttackRange()[3]){
                    statfillr = ""+unit.getAttackRange()[2];
                }
                else {
                    statfillr = unit.getAttackRange()[2]+"-"+unit.getAttackRange()[3];
                }
                unitARair.setText(statfillr);

                if (unit.getAttackRange()[4] == unit.getAttackRange()[5]){
                    statfillr = ""+unit.getAttackRange()[4];
                }
                else {
                    statfillr = unit.getAttackRange()[4]+"-"+unit.getAttackRange()[5];
                }
                unitARsea.setText(statfillr);

                unitDgrd.setText(""+unit.getAttackDamage()[0]);
                unitDair.setText(""+unit.getAttackDamage()[1]);
                unitDsea.setText(""+unit.getAttackDamage()[2]);
            }
        }
        else {
            if (unitIconPrev != null){
                unitIcon = null;
                unitIconPrev = null;
                unitHP.setText("");
                unitXP.setText("");
                unitImageLabel.setIcon(unitIcon);
                unitARgrd.setText("~");
                unitARair.setText("~");
                unitARsea.setText("~");
                unitDgrd.setText("~");
                unitDair.setText("~");
                unitDsea.setText("~");
            }
        }

        t1Ground = 0;
        t1Air = 0;
        t1Sea = 0;
        t2Ground = 0;
        t2Air = 0;
        t2Sea = 0;
        for (SuperUnit su : gp.ally){
            if(su.isNavy()){
                t1Sea++;
            }
            else if(su.isAvian()){
                t1Air++;
            }
            else {
                t1Ground++;
            }
        }
        for (SuperUnit su : gp.enemy){
            if(su.isNavy()){
                t2Sea++;
            }
            else if(su.isAvian()){
                t2Air++;
            }
            else {
                t2Ground++;
            }
        }
        if (oldT1Ground != t1Ground || oldT1Air != t1Air || oldT1Sea != t1Sea || oldT2Ground != t2Ground || oldT2Air != t2Air || oldT2Sea != t2Sea){
            refreshTeamStats();
            team1Stats.repaint();
            team2Stats.repaint();
        }
    }

    /***
     * sets all the 6 slots' focus to focus
     * @param focus the value for focus
     */
    public void setSlotsFocusable(boolean focus){
        slot1.setFocusable(focus);
        slot2.setFocusable(focus);
        slot3.setFocusable(focus);
        slot4.setFocusable(focus);
        slot5.setFocusable(focus);
        slot6.setFocusable(focus);
    }

    /***
     * sets the label values to the new values
     */
    public void refreshTeamStats(){
        team1GroundStats.setText(""+t1Ground);
        team1AirStats.setText(""+t1Air);
        team1SeaStats.setText(""+t1Sea);
        team2GroundStats.setText(""+t2Ground);
        team2AirStats.setText(""+t2Air);
        team2SeaStats.setText(""+t2Sea);
    }

    public JPanel getSlot1() {
        return slot1;
    }

    public void setSlot1(JPanel slot1) {
        this.slot1 = slot1;
    }
    public JPanel getSlot2() {
        return slot2;
    }

    public void setSlot2(JPanel slot1) {
        this.slot2 = slot1;
    }
    public JPanel getSlot3() {
        return slot3;
    }

    public void setSlot3(JPanel slot1) {
        this.slot3 = slot1;
    }
    public JPanel getSlot4() {
        return slot4;
    }

    public void setSlot4(JPanel slot1) {
        this.slot4 = slot1;
    }
    public JPanel getSlot5() {
        return slot5;
    }

    public void setSlot5(JPanel slot1) {
        this.slot5 = slot1;
    }
    public JPanel getSlot6() {
        return slot6;
    }

    public void setSlot6(JPanel slot1) {
        this.slot6 = slot1;
    }
}

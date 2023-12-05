package main;

import entity.ImageStore;
import entity.SuperUnit;
import entity.*;
import menu.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static javax.swing.UIManager.getIcon;
import static main.Main.fontGallery;

public class InfoPanel extends JPanel {
    JLabel cycleNumberField = new JLabel();
    JLabel currentHighlighted = new JLabel();
    JLabel currentTeamField = new JLabel();
    JLabel currentMoving = new JLabel();
    JLabel currentActed = new JLabel();
    JLabel currentPhaseField = new JLabel();


    private JPanel slot1 = new JPanel();
    private JPanel slot2 = new JPanel();
    private JPanel slot3 = new JPanel();
    private JPanel slot4 = new JPanel();
    private JPanel slot5 = new JPanel();
    private JPanel slot6 = new JPanel();
    private ImageIcon prev1Icon = new ImageIcon();
    private ImageIcon prev2Icon = new ImageIcon();
    private ImageIcon prev3Icon = new ImageIcon();
    private ImageIcon prev4Icon = new ImageIcon();
    private ImageIcon prev5Icon = new ImageIcon();
    private ImageIcon prev6Icon = new ImageIcon();
    private int t1Ground = 0;
    private int t1Air = 0;
    private int t1Sea = 0;
    private int t2Ground = 0;
    private int t2Air = 0;
    private int t2Sea = 0;


    public InfoPanel(GamePanel gp, PanelManager panM, int height){
        this.setPreferredSize(new Dimension(250, height));
        this.setBackground(Color.BLACK);
        this.setFocusable(false);

        //setBackground(Color.BLACK);

        setLayout(new GridLayout(2, 1));
        JPanel inventorySpacer = new JPanel(new GridLayout(3, 1));

        inventorySpacer.add(panM.createPlaceHolder(Color.BLACK));

        JPanel currentInventoryPanel = new JPanel(new GridLayout(2, 3));
        currentInventoryPanel.setBackground(Color.CYAN);
        ImageIcon tankIcon = new ImageIcon(gp.imagS.getGallery().get(U_tank_H.class).get(4));
        //JPanel slot = new JPanel();
        slot1.add(new JLabel(tankIcon));
        slot1.setBackground(Color.GRAY);
        slot1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot1.setFocusable(false);
        // Add space key binding to each panel
        slot1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel");
        slot1.getActionMap().put("selectPanel", new AbstractAction() {
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
        //slot2 = new JButton(tankIcon);
        slot2.add(new JLabel(tankIcon));
        slot2.setBackground(Color.BLACK);
        slot2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot2.setFocusable(false);
        // Add space key binding to each panel
        slot2.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel");
        slot2.getActionMap().put("selectPanel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 2);
                if (slot2.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(0);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot2);
        //slot3 = new JButton(tankIcon);
        slot3.add(new JLabel(tankIcon));
        slot3.setBackground(Color.GRAY);
        slot3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot3.setFocusable(false);
        // Add space key binding to each panel
        slot3.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel");
        slot3.getActionMap().put("selectPanel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 3);
                if (slot3.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(0);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot3);
        //slot4 = new JButton(tankIcon);
        slot4.add(new JLabel(tankIcon));
        slot4.setBackground(Color.BLACK);
        slot4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot4.setFocusable(false);
        // Add space key binding to each panel
        slot4.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel");
        slot4.getActionMap().put("selectPanel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 4);
                if (slot4.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(0);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot4);
        //slot5 = new JButton(tankIcon);
        slot5.add(new JLabel(tankIcon));
        slot5.setBackground(Color.GRAY);
        slot5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot5.setFocusable(false);
        // Add space key binding to each panel
        slot5.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel");
        slot5.getActionMap().put("selectPanel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 5);
                if (slot5.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(0);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot5);
        //slot6 = new JButton(tankIcon);
        slot6.add(new JLabel(tankIcon));
        slot6.setBackground(Color.BLACK);
        slot6.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        slot6.setFocusable(false);
        // Add space key binding to each panel
        slot6.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "selectPanel");
        slot6.getActionMap().put("selectPanel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space pressed on panel index: " + 6);
                if (slot6.hasFocus()){
                    gp.timeL.setGotNewIndex(true);
                    gp.timeL.setNewIndex(0);
                    gp.requestFocus();
                }
            }
        });
        currentInventoryPanel.add(slot6);
        inventorySpacer.add(currentInventoryPanel);

        inventorySpacer.add(panM.createPlaceHolder(Color.BLACK));

        inventorySpacer.setBackground(Color.BLACK);
        add(inventorySpacer);

        JPanel gameStatsPanel = new JPanel(new GridLayout(6, 1));
        gameStatsPanel.setBackground(Color.BLACK);
        JLabel label = panM.createLabel(gp.getTeam1Name(), fontGallery.getFontMap().get(3).get(2), Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        //row 1
        gameStatsPanel.add(label);
        //row 2
        JPanel team1Labels = new JPanel(new GridLayout(1, 3));
        team1Labels.add(panM.createLabel("GROUND:", fontGallery.getFontMap().get(3).get(1), Color.GREEN));
        team1Labels.add(panM.createLabel("AIR:", fontGallery.getFontMap().get(3).get(1), Color.GRAY));
        team1Labels.add(panM.createLabel("SEA:", fontGallery.getFontMap().get(3).get(1), Color.CYAN));
        team1Labels.setBackground(Color.BLACK);
        gameStatsPanel.add(team1Labels);
        //row 3
        JPanel team1Stats = new JPanel(new GridLayout(1, 3));
        team1Stats.add(panM.createLabel(""+t1Ground, fontGallery.getFontMap().get(3).get(2), Color.GREEN));
        team1Stats.add(panM.createLabel(""+t1Air, fontGallery.getFontMap().get(3).get(2), Color.GRAY));
        team1Stats.add(panM.createLabel(""+t1Sea, fontGallery.getFontMap().get(3).get(2), Color.CYAN));
        team1Stats.setBackground(Color.BLACK);
        gameStatsPanel.add(team1Stats);

        label = panM.createLabel(gp.getTeam2Name(), fontGallery.getFontMap().get(3).get(2), Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        //row 4
        gameStatsPanel.add(label);
        //row 5
        JPanel team2Labels = new JPanel(new GridLayout(1, 3));
        team2Labels.add(panM.createLabel("GROUND:", fontGallery.getFontMap().get(3).get(1), Color.GREEN));
        team2Labels.add(panM.createLabel("AIR:", fontGallery.getFontMap().get(3).get(1), Color.GRAY));
        team2Labels.add(panM.createLabel("SEA:", fontGallery.getFontMap().get(3).get(1), Color.CYAN));
        team2Labels.setBackground(Color.BLACK);
        gameStatsPanel.add(team2Labels);
        //row 6
        JPanel team2Stats = new JPanel(new GridLayout(1, 3));
        team2Stats.add(panM.createLabel(""+t2Ground, fontGallery.getFontMap().get(3).get(2), Color.GREEN));
        team2Stats.add(panM.createLabel(""+t2Air, fontGallery.getFontMap().get(3).get(2), Color.GRAY));
        team2Stats.add(panM.createLabel(""+t2Sea, fontGallery.getFontMap().get(3).get(2), Color.CYAN));
        team2Stats.setBackground(Color.BLACK);
        gameStatsPanel.add(team2Stats);

        add(gameStatsPanel);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("permanentFocusOwner", evt -> {
            Component focused = (Component) evt.getNewValue();
            if (slot1 == focused) {
                System.out.println("Focused panel index: " + 1);
                gp.timeL.setGotNewIndex(true);
                gp.timeL.setNewIndex(0);
                //gp.requestFocus();
            }
            if (slot2 == focused) {
                System.out.println("Focused panel index: " + 2);
                gp.timeL.setGotNewIndex(true);
                gp.timeL.setNewIndex(1);
                //gp.requestFocus();
            }
            if (slot3 == focused) {
                System.out.println("Focused panel index: " + 3);
                gp.timeL.setGotNewIndex(true);
                gp.timeL.setNewIndex(2);
                //gp.requestFocus();
            }
            if (slot4 == focused) {
                System.out.println("Focused panel index: " + 4);
                gp.timeL.setGotNewIndex(true);
                gp.timeL.setNewIndex(3);
                //gp.requestFocus();
            }
            if (slot5 == focused) {
                System.out.println("Focused panel index: " + 5);
                gp.timeL.setGotNewIndex(true);
                gp.timeL.setNewIndex(4);
                //gp.requestFocus();
            }
            if (slot6 == focused) {
                System.out.println("Focused panel index: " + 6);
                gp.timeL.setGotNewIndex(true);
                gp.timeL.setNewIndex(5);
                //gp.requestFocus();
            }
        });




/*
        setLayout(new GridLayout(1, 1)); // Single row, single column
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding

        // Create a nested panel with GridLayout for labels and fields
        JPanel infoPanel = new JPanel(new GridLayout(10, 1)); // 1 row, 10 columns

        // Add labels
        infoPanel.add(new JLabel());
        infoPanel.add(new JLabel("Cycle number:"));
        //JTextField cycleNumberField = new JTextField(5);
        infoPanel.add(cycleNumberField);
        infoPanel.add(currentHighlighted);
        infoPanel.add(new JLabel("Current team:"));
        //JTextField currentTeamField = new JTextField(15);
        infoPanel.add(currentTeamField);
        infoPanel.add(currentActed);
        infoPanel.add(currentMoving);
        infoPanel.add(new JLabel("Current phase:"));
        //JTextField currentPhaseField = new JTextField(10);
        infoPanel.add(currentPhaseField);
        // Add empty spaces for input fields

        // Add input fields

        // Add the nested panel to the bottomPanel
        add(infoPanel);
        //setVisible(true);
*/
    }
    public void update(GamePanel gp, SuperUnit unit){
        if (gp.cycleState == GamePanel.Cycle.T2ATTACK || gp.cycleState == GamePanel.Cycle.T1ATTACK){
            setSlotsFocusable(false);
        }
        if (gp.cruser.getHover().getType().equals("depot") || gp.cruser.getHover().getType().equals("harbor") ||
                gp.cruser.getHover().getType().equals("factory")){
            //setSlotsFocusable(true);
            //cycleNumberField.setText("type: " + gp.cruser.getHover().getType() + " size: " +gp.getStructureFromTile(gp.cruser.getHover()).getInventory().size());
            //currentActed.setText("unitFromStructure: "+gp.timeL.unitFromStructure);
            unit = gp.getUnitFromTile(gp.cruser.getHover());
            if (unit != null) {
                currentTeamField.setText("is visible: " + unit.isVisible());
                currentHighlighted.setText("sideNum of unit: "+ unit.getTeamNum());
            }
            SuperStructure struc = gp.getStructureFromTile(gp.cruser.getHover());
            //int cycletype = 0;
            //if ( &&
            //(gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 2) ||
            //        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 1))
            if (!struc.getInventory().isEmpty()){
                //slot1.repaint();
                if((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0)){
                    slot1.setFocusable(true);
                }
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
                    slot1.removeAll();
                    slot1.add(new JLabel(icon));
                    System.out.println("VAAAA");
                    //slot1.repaint();
                    System.out.println("NEM___ICON");
                    prev1Icon = icon;
                }
                else {
                    boolean print = prev1Icon != icon;
                    //System.out.println("NEM___ICON---NOT     "+print);
                    slot1.setBackground(Color.CYAN);
                    System.out.println("VAAAA");
                    //slot1.repaint();
                    //System.out.println(struc.getInventory());
                    //slot1.repaint();
                }
            }
            else {
                slot1.setFocusable(false);
                if (prev1Icon != null){
                    slot1.removeAll();
                    //slot1.add(new JLabel(icon));
                    prev1Icon = null;
                    System.out.println("VAAAA");
                    slot1.repaint();
                }
            }
            if (struc.getInventory().size() > 1){
                if(((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0))){
                    //slot2.repaint();
                    slot2.setFocusable(true);
                }
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
                    slot2.removeAll();
                    slot2.add(new JLabel(icon));
                    System.out.println("VAAAA");
                    slot2.repaint();
                    prev2Icon = icon;
                }
            }
            else {
                slot2.setFocusable(false);
                if (prev2Icon != null){
                    slot2.removeAll();
                    //slot1.add(new JLabel(icon));
                    prev2Icon = null;
                    System.out.println("VAAAA");
                    slot2.repaint();
                }
            }
            if (struc.getInventory().size() > 2){
                if((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0)){
                    //slot3.repaint();
                    slot3.setFocusable(true);
                }
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
                    slot3.removeAll();
                    slot3.add(new JLabel(icon));
                    System.out.println("VAAAA");
                    slot3.repaint();
                    prev3Icon = icon;
                }
            }
            else {
                slot3.setFocusable(false);
                if (prev3Icon != null){
                    slot3.removeAll();
                    //slot1.add(new JLabel(icon));
                    prev3Icon = null;
                    System.out.println("VAAAA");
                    slot3.repaint();
                }
            }
            if (struc.getInventory().size() > 3){
                if ((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0)){
                    //slot4.repaint();
                    slot4.setFocusable(true);
                }
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
                    slot4.removeAll();
                    slot4.add(new JLabel(icon));
                    System.out.println("VAAAA");
                    slot4.repaint();
                    prev4Icon = icon;
                }
            }
            else {
                slot4.setFocusable(false);
                if (prev4Icon != null){
                    slot4.removeAll();
                    //slot1.add(new JLabel(icon));
                    prev4Icon = null;
                    System.out.println("VAAAA");
                    slot4.repaint();
                }
            }
            if (struc.getInventory().size() > 4){
                if((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0)){
                    //slot5.repaint();
                    slot5.setFocusable(true);
                }
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
                    slot5.removeAll();
                    slot5.add(new JLabel(icon));
                    System.out.println("VAAAA");
                    slot5.repaint();
                    prev5Icon = icon;
                }
            }
            else {
                slot5.setFocusable(false);
                if (prev5Icon != null){
                    slot5.removeAll();
                    //slot1.add(new JLabel(icon));
                    prev5Icon = null;
                    System.out.println("VAAAA");
                    slot5.repaint();
                }
            }
            if (struc.getInventory().size() > 5){
                if ((gp.cycleState == GamePanel.Cycle.T2MOVE && struc.getSide() == 1) ||
                        (gp.cycleState == GamePanel.Cycle.T1MOVE && struc.getSide() == 0)){
                    //slot6.repaint();
                    slot6.setFocusable(true);
                }
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
                    slot6.removeAll();
                    slot6.add(new JLabel(icon));
                    System.out.println("VAAAA");
                    slot6.repaint();
                    prev6Icon = icon;
                }
            }
            else {
                slot6.setFocusable(false);
                if (prev6Icon != null){
                    slot6.removeAll();
                    //slot1.add(new JLabel(icon));
                    prev6Icon = null;
                    System.out.println("VAAAA");
                    slot6.repaint();
                }
            }

            if (slot1.hasFocus()){
                slot1.setBackground(Color.RED);
                slot1.setBackground(Color.BLACK);
                slot1.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            }
            else {
                slot1.setBackground(Color.BLACK);
                slot1.setBorder(BorderFactory.createLineBorder(Color.CYAN, 0));
            }
            System.out.println("VAUUA");
            slot1.repaint();
            if (slot2.hasFocus()){
                slot2.setBackground(Color.RED);
                slot2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
            else {
                slot2.setBackground(Color.BLACK);
            }
            slot2.repaint();
            if (slot3.hasFocus()){
                slot3.setBackground(Color.RED);
                slot3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
            else {
                slot3.setBackground(Color.BLACK);
            }
            slot3.repaint();
            if (slot4.hasFocus()){
                slot4.setBackground(Color.RED);
                slot4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
            else {
                slot4.setBackground(Color.BLACK);
            }
            slot4.repaint();
            if (slot5.hasFocus()){
                slot5.setBackground(Color.RED);
                slot5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
            else {
                slot5.setBackground(Color.BLACK);
            }
            slot5.repaint();
            if (slot6.hasFocus()){
                slot6.setBackground(Color.RED);
                slot6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
            else {
                slot6.setBackground(Color.BLACK);
            }
            //slot6.repaint();

            /*currentHighlighted.setText(unit.getType());
            currentTeamField.setText("in range: " + unit.isInRange());
            currentActed.setText("acted: " + unit.isActed());
            currentMoving.setText("moving: " + unit.isMoving());
            currentPhaseField.setText("" + unit.getXp());*/

        }
        else{
            unit = gp.getUnitFromTile(gp.cruser.getHover());
            setSlotsFocusable(false);
            if (unit != null){
                cycleNumberField.setText("" + unit.getHp());
                currentHighlighted.setText(unit.getType());
                currentTeamField.setText("in range: " + unit.isInRange());
                currentActed.setText("acted: " + unit.isActed());
                currentMoving.setText("moving: " + unit.isMoving());
                currentPhaseField.setText("" + unit.getXp());
            }
            else{
                currentTeamField.setText("");
                currentHighlighted.setText("");
                cycleNumberField.setText("");
                currentPhaseField.setText("");
                currentActed.setText("");
                currentMoving.setText("");
            }
        }
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
        repaint();
    }
    public void setSlotsFocusable(boolean focus){
        slot1.setFocusable(focus);
        slot2.setFocusable(focus);
        slot3.setFocusable(focus);
        slot4.setFocusable(focus);
        slot5.setFocusable(focus);
        slot6.setFocusable(focus);
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

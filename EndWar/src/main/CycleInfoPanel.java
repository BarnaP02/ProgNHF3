package main;

import menu.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static main.Main.fontGallery;

public class CycleInfoPanel extends JPanel {
    PanelManager panM;
    JLabel cycleNumberField = new JLabel();
    JLabel currentTeamField = new JLabel();
    JLabel currentPhaseField = new JLabel();

    /***
     * this is the panel under the game that shows whose turn it is and what they are doing (movement/attack)
     * @param width the width of this panel, this might be a bit unnecessary
     * @param panM this has functions for creating a bit more complicated placeholder JPanels and JLabels
     */
    public CycleInfoPanel(int width, PanelManager panM){
        this.panM = panM;
        this.setPreferredSize(new Dimension(width, 40));
        setFocusable(false);

        setBackground(Color.BLACK);
        setLayout(new GridLayout(1, 1)); // Single row, single column
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding
        setFont(fontGallery.getFontMap().get(3).get(1)); // Set font

        // Create a nested panel with GridLayout for labels and fields
        JPanel infoPanel = new JPanel(new GridLayout(1, 3)); // 1 row, 3 columns

        JPanel cycleNumberPanel = new JPanel(new GridLayout(1, 2));
        cycleNumberPanel.add(panM.createLabel("Cycle number:",fontGallery.getFontMap().get(3).get(1),Color.WHITE));
        cycleNumberField.setForeground(Color.WHITE); // Set the text color
        cycleNumberPanel.setBackground(Color.BLACK); // Just for visualization
        cycleNumberField.setFont(fontGallery.getFontMap().get(3).get(1)); // Set font
        cycleNumberPanel.add(cycleNumberField);
        infoPanel.add(cycleNumberPanel);

        JPanel currentTeamPanel = new JPanel(new GridLayout(1, 2));
        currentTeamPanel.add(panM.createLabel("Current team:",fontGallery.getFontMap().get(3).get(1),Color.WHITE));
        currentTeamField.setForeground(Color.WHITE); // Set the text color
        currentTeamPanel.setBackground(Color.BLACK); // Just for visualization
        currentTeamField.setFont(fontGallery.getFontMap().get(0).get(2)); // Set font
        currentTeamPanel.add(currentTeamField);
        infoPanel.add(currentTeamPanel);

        JPanel currentPhasePanel = new JPanel(new GridLayout(1, 2));
        currentPhasePanel.add(panM.createLabel("Current phase:",fontGallery.getFontMap().get(3).get(1), Color.WHITE));
        currentPhaseField.setForeground(Color.WHITE); // Set the text color
        currentPhasePanel.setBackground(Color.BLACK); // Just for visualization
        currentPhaseField.setFont(fontGallery.getFontMap().get(0).get(2)); // Set font
        currentPhasePanel.add(currentPhaseField);
        infoPanel.add(currentPhasePanel);
        infoPanel.setBackground(Color.BLACK); // Just for visualization
        add(infoPanel);

        setVisible(true);
    }

    /***
     * to update the parameters
     * @param cycleNumber the new value for cycleNumber
     * @param teamName the new value for teamName
     * @param cyclePhase the new value for cyclePhase
     */
    public void update(int cycleNumber, String teamName, String cyclePhase){
        cycleNumberField.setText("" + cycleNumber);
        currentTeamField.setText(teamName);
        currentPhaseField.setText(cyclePhase);
    }
}

package main;

import menu.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static main.Main.fontGallery;

public class CycleInfoPanel extends JPanel {
    PanelManager panM;
    //GamePanel gp;
    JLabel cycleNumberField = new JLabel();
    JLabel currentTeamField = new JLabel();
    JLabel currentPhaseField = new JLabel();
    public CycleInfoPanel(int width, PanelManager panM){
        this.panM = panM;
        this.setPreferredSize(new Dimension(width, 40));
        //this.setBackground(Color.WHITE);
        setFocusable(false);

        setBackground(Color.BLACK);
        setLayout(new GridLayout(1, 1)); // Single row, single column
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding
        setFont(fontGallery.getFontMap().get(3).get(1)); // Set font

        // Create a nested panel with GridLayout for labels and fields
        JPanel infoPanel = new JPanel(new GridLayout(1, 3)); // 1 row, 3 columns

        JPanel cycleNumberPanel = new JPanel(new GridLayout(1, 2));
        //cycleNumberPanel.add(new JLabel("Cycle number:"));
        cycleNumberPanel.add(panM.createLabel("Cycle number:",fontGallery.getFontMap().get(1).get(1),Color.WHITE));
        cycleNumberField.setForeground(Color.WHITE); // Set the text color
        cycleNumberPanel.setBackground(Color.BLACK); // Just for visualization
        cycleNumberField.setFont(fontGallery.getFontMap().get(2).get(1)); // Set font
        cycleNumberPanel.add(cycleNumberField);
        infoPanel.add(cycleNumberPanel);

        JPanel currentTeamPanel = new JPanel(new GridLayout(1, 2));
        //currentTeamPanel.add(new JLabel("Current team:"));
        currentTeamPanel.add(panM.createLabel("Current team:",fontGallery.getFontMap().get(1).get(2),Color.WHITE));
        currentTeamField.setForeground(Color.WHITE); // Set the text color
        currentTeamPanel.setBackground(Color.BLACK); // Just for visualization
        currentTeamField.setFont(fontGallery.getFontMap().get(0).get(2)); // Set font
        currentTeamPanel.add(currentTeamField);
        infoPanel.add(currentTeamPanel);

        JPanel currentPhasePanel = new JPanel(new GridLayout(1, 2));
        //currentPhasePanel.add(new JLabel("Current phase:"));
        currentPhasePanel.add(panM.createLabel("Current phase:",fontGallery.getFontMap().get(1).get(2), Color.WHITE));
        currentPhaseField.setForeground(Color.WHITE); // Set the text color
        currentPhasePanel.setBackground(Color.BLACK); // Just for visualization
        currentPhaseField.setFont(fontGallery.getFontMap().get(0).get(2)); // Set font
        currentPhasePanel.add(currentPhaseField);
        infoPanel.add(currentPhasePanel);
        infoPanel.setBackground(Color.BLACK); // Just for visualization
        add(infoPanel);

        /*
        // Add labels
        infoPanel.add(new JLabel());
        infoPanel.add(new JLabel("Cycle number:"));
        //JTextField cycleNumberField = new JTextField(5);
        //cycleNumberField.setOpaque(false); // Set the label to be transparent
        cycleNumberField.setForeground(Color.WHITE); // Set the text color
        cycleNumberField.setFont(fontGallery.getFonts()[0]); // Set font
        infoPanel.add(cycleNumberField);
        infoPanel.add(new JLabel());

        infoPanel.add(new JLabel("Current team:"));
        //JTextField currentTeamField = new JTextField(15);
        //currentTeamField.setOpaque(false); // Set the label to be transparent
        currentTeamField.setForeground(Color.WHITE); // Set the text color
        currentTeamField.setFont(fontGallery.getFonts()[0]); // Set font
        currentTeamField.setPreferredSize(new Dimension(250, 40));
        infoPanel.add(currentTeamField);
        //infoPanel.add(new JLabel());
        infoPanel.add(new JLabel());

        infoPanel.add(new JLabel("Current phase:"));
        //JTextField currentPhaseField = new JTextField(10);
        //currentPhaseField.setOpaque(false); // Set the label to be transparent
        currentPhaseField.setForeground(Color.WHITE); // Set the text color
        currentPhaseField.setFont(fontGallery.getFonts()[0]); // Set font
        infoPanel.add(currentPhaseField);
        infoPanel.setOpaque(false); // Set the label to be transparent


        // Add empty spaces for input fields

        // Add input fields

        // Add the nested panel to the bottomPanel
        add(infoPanel);
        */

        setVisible(true);
    }
    public void update(int cycleNumber, String teamName, String cyclePhase){
        cycleNumberField.setText("" + cycleNumber);
        currentTeamField.setText(teamName);
        currentPhaseField.setText(cyclePhase);
    }
}

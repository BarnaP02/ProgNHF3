package menu;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    /***
     * creates a new JLabel
     * @param text with this text
     * @param font with this font
     * @param textColor with this text color
     * @return the new JLabel
     */
    public JLabel createLabel(String text, Font font, Color textColor){
        JLabel result = new JLabel();
        result.setFont(font);
        result.setText(text);
        result.setForeground(textColor);
        result.setBackground(Color.BLACK);
        return result;
    }

    /***
     * creates a new JPanel
     * @param color with this color
     * @return the new JPanel
     */
    public JPanel createPlaceHolder(Color color){
        JPanel result = new JPanel();
        result.setFocusable(false);
        result.setBackground(color);
        return result;
    }

    /***
     * creates a JPanel with a JLabel inside it
     * @param color with this color
     * @param text with this text
     * @param font with this fond
     * @param textColor with this text color
     * @return the new JPanel
     */
    public JPanel createPlaceHolder(Color color, String text, Font font, Color textColor){
        JPanel result = new JPanel();
        result.setFocusable(false);
        result.setBackground(color);
        result.add(createLabel(text, font, textColor));
        return result;
    }

    /***
     * creates a new JPanel with an ImageIcon in it
     * @param img this is the ImageIcon
     * @param color this is the background color
     * @return this is the new JPanel
     */
    public JPanel createPlaceHolder(ImageIcon img, Color color){
        JPanel result = new JPanel();
        result.setFocusable(false);
        result.setBackground(color);
        result.add(new JLabel(img));
        return result;

    }
}

package menu;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    public JLabel createLabel(String text, Font font, Color textColor){
        JLabel result = new JLabel();
        result.setFont(font);
        result.setText(text);
        result.setForeground(textColor);
        return result;
    }
    public JPanel createPlaceHolder(Color color){
        JPanel result = new JPanel();
        result.setFocusable(false);
        result.setBackground(color);
        return result;
    }
    public JPanel createPlaceHolder(Color color, String text, Font font, Color textColor){
        JPanel result = new JPanel();
        result.setFocusable(false);
        result.setBackground(color);
        result.add(createLabel(text, font, textColor));
        return result;
    }
}

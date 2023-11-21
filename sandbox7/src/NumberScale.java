import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NumberScale extends JFrame {
    private JSlider slider;
    private JLabel label;

    public NumberScale() {
        super("Number Scale");
        setLayout(null);

        slider = new JSlider(JSlider.HORIZONTAL, -255, 255, 0); // Change range and default value as needed
        slider.setBounds(50, 50, 300, 50); // Adjust position and size
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(51);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider);

        label = new JLabel("Selected Number: " + slider.getValue());
        label.setBounds(50, 120, 200, 30);
        add(label);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                label.setText("Selected Number: " + slider.getValue());
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
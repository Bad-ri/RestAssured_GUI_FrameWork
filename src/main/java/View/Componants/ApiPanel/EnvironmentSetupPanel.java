package View.Componants.ApiPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EnvironmentSetupPanel extends JPanel {
    private JComboBox<String> env_dropdown;
    private JRadioButton cbe_radio;
    private JRadioButton nbe_radio;

    public EnvironmentSetupPanel() {
        // Style
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Environment Setup"),
                new EmptyBorder(5, 5, 5, 5)
        ));

        JPanel env_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        env_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        env_panel.add(new JLabel("Environment:"));
        env_dropdown = new JComboBox<>(new String[]{"SIT", "UAT"});
        env_panel.add(env_dropdown);
        add(env_panel);

        // space between rows
        add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel bank_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        bank_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bank_panel.add(new JLabel("Bank :"));
        cbe_radio = new JRadioButton("CBE");
        nbe_radio = new JRadioButton("NBE");
        ButtonGroup bank_group = new ButtonGroup();
        bank_group.add(cbe_radio);
        bank_group.add(nbe_radio);
        bank_panel.add(cbe_radio);
        bank_panel.add(nbe_radio);
        add(bank_panel);
    }

    public String getSelectedEnvironment() {
        return (String) env_dropdown.getSelectedItem();
    }
    public String getSelectedBank() {
        return cbe_radio.isSelected() ? "CBE" : "NBE";
    }
}
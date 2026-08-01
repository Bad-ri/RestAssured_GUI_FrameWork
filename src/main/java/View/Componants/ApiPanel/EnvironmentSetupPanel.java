package View.Componants.ApiPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * class main functionality -> Building Environment setup panel component
 * ┌─ Environment Setup ───────────────────┐
 * │  Environment:  [ SIT        ▼ ]       │
 * │  Bank :   ( ) CBE      (•) NBE        │
 * └───────────────────────────────────────┘
 */
public class EnvironmentSetupPanel extends JPanel {
    private static final String[] ENV_OPTIONS = {"SIT", "UAT"};
    private static final String CBE = "CBE";
    private static final String NBE = "NBE";
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
        add(buildEnvPanel());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(buildBankPanel());
    }
    private JPanel buildEnvPanel() {
        JPanel env_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        env_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        env_panel.add(new JLabel("Environment:"));
        env_dropdown = new JComboBox<>(ENV_OPTIONS);
        env_panel.add(env_dropdown);
        return env_panel;
    }
    private JPanel buildBankPanel() {
        JPanel bank_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        bank_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bank_panel.add(new JLabel("Bank :"));
        cbe_radio = new JRadioButton(CBE);
        nbe_radio = new JRadioButton(NBE, true);
        ButtonGroup bank_group = new ButtonGroup();
        bank_group.add(cbe_radio);
        bank_group.add(nbe_radio);
        bank_panel.add(cbe_radio);
        bank_panel.add(nbe_radio);
        return bank_panel;
    }
    public String getSelectedEnvironment() {
        return (String) env_dropdown.getSelectedItem();
    }
    public String getSelectedBank() {
        return cbe_radio.isSelected() ? CBE : NBE;
    }
}
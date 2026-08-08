package View.Componants.ApiPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 class main functionality -> Building Api panel component
 * ┌─ Api Setup ──────────────────────────┐
 * │  API:  [ Fund             ▼ ]        │
 * │  (•) Sanity      ( ) Regression      │
 * └──────────────────────────────────────┘
 */
public class APiSetupPanel extends JPanel{
    private static final String[] API_OPTIONS = {"Fund", "Auth", "AccHistory"};
    private static final String SANITY = "Sanity";
    private static final String REGRESSION = "Regression";
    private JComboBox<String> api_dropdown;
    private JRadioButton sanity_radio;
    private JRadioButton regression_radio;
    public APiSetupPanel() {
        // Style
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Api Setup"),
                new EmptyBorder(5, 5, 5, 5)
        ));
        add(buildApiPanel());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(buildModulePanel());
    }
    private JPanel buildApiPanel() {
        JPanel api_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        api_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        api_panel.add(new JLabel("API:"));
        api_dropdown = new JComboBox<>(API_OPTIONS);
        api_panel.add(api_dropdown);
        return api_panel;
    }
    private JPanel buildModulePanel() {
        JPanel module_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        module_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sanity_radio = new JRadioButton(SANITY, true);
        regression_radio = new JRadioButton(REGRESSION);
        ButtonGroup bank_group = new ButtonGroup();
        bank_group.add(sanity_radio);
        bank_group.add(regression_radio);
        module_panel.add(sanity_radio);
        module_panel.add(regression_radio);
        return module_panel;
    }
    public String getSelectedApi() {
        return (String) api_dropdown.getSelectedItem();
    }
    public String getSelectedModule() {
        return sanity_radio.isSelected() ? SANITY : REGRESSION;
    }
}
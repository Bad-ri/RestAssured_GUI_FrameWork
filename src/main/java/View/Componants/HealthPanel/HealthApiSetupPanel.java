package View.Componants.HealthPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * class main functionality -> Building Health Api panel component
 * ┌─ Api Setup ────────────────────────┐
 * │  API:  [ Fund             ▼ ]      │
 * └────────────────────────────────────┘
 */
public class HealthApiSetupPanel extends JPanel{
    private static final String[] API_OPTIONS = {"Fund", "Auth", "AccHistory", "All"};
    private JComboBox<String> api_dropdown;
    public HealthApiSetupPanel() {
        // Style
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Api Setup"),
                new EmptyBorder(5, 5, 5, 5)
        ));
        add(buildApiPanel());
    }
    private JPanel buildApiPanel() {
        JPanel api_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        api_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        api_panel.add(new JLabel("API:"));
        api_dropdown = new JComboBox<>(API_OPTIONS);
        api_panel.add(api_dropdown);
        return api_panel;
    }
    public String getSelectedApi() {
        return (String) api_dropdown.getSelectedItem();
    }
}
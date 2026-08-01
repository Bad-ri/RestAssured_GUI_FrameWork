package View.Componants.HealthPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HealthEnvironmentSetupPanel extends JPanel{
    private JComboBox<String> api_dropdown;
    public HealthEnvironmentSetupPanel() {
        // Style
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Api Setup"),
                new EmptyBorder(5, 5, 5, 5)
        ));

        JPanel api_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        api_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        api_panel.add(new JLabel("API:"));
        api_dropdown = new JComboBox<>(new String[]{"Fund", "Auth", "AccHistory", "All"});
        api_panel.add(api_dropdown);
        add(api_panel);

        // space between rows
        add(Box.createRigidArea(new Dimension(0, 5)));

    }
    public String getSelectedApi() {
        return (String) api_dropdown.getSelectedItem();
    }
}

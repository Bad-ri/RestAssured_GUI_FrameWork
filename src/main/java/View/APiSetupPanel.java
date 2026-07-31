package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class APiSetupPanel extends JPanel{
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

        JPanel api_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        api_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        api_panel.add(new JLabel("API:"));
        api_dropdown = new JComboBox<>(new String[]{"Fund", "Auth", "AccHistory", "All"});
        api_panel.add(api_dropdown);
        add(api_panel);

        // space between rows
        add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel module_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        module_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        //module_panel.add(new JLabel("Module :"));
        sanity_radio = new JRadioButton("Sanity");
        regression_radio = new JRadioButton("Regression");
        ButtonGroup bank_group = new ButtonGroup();
        bank_group.add(sanity_radio);
        bank_group.add(regression_radio);
        module_panel.add(sanity_radio);
        module_panel.add(regression_radio);
        add(module_panel);
    }
    public String getSelectedApi() {
        return (String) api_dropdown.getSelectedItem();
    }
    public String getSelectedModule() {
        return sanity_radio.isSelected() ? "Sanity" : "Regression";
    }
}

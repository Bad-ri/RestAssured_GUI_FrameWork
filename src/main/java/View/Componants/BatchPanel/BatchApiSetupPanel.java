package View.Componants.BatchPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BatchApiSetupPanel extends JPanel{
    private JComboBox<String> api_dropdown;
    private JRadioButton Sequential_radio;
    private JRadioButton FireForget_radio;

    public BatchApiSetupPanel() {
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
        Sequential_radio = new JRadioButton("Sequential",true);
        FireForget_radio = new JRadioButton("FireForget");
        ButtonGroup bank_group = new ButtonGroup();
        bank_group.add(Sequential_radio);
        bank_group.add(FireForget_radio);
        module_panel.add(Sequential_radio);
        module_panel.add(FireForget_radio);
        add(module_panel);
    }
    public String getSelectedApi() {
        return (String) api_dropdown.getSelectedItem();
    }
    public String getSelectedModule() {
        return Sequential_radio.isSelected() ? "Sequential" : "Fire&Forget";
    }
}

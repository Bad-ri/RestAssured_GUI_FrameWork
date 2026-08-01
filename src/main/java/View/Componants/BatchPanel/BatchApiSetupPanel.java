package View.Componants.BatchPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * class main functionality -> Building Batch Api panel component
 * ┌─ Api Setup ──────────────────────────────┐
 * │  API:  [ Fund             ▼ ]            │
 * │  (•) Sequential      ( ) FireForget      │
 * └──────────────────────────────────────────┘
 */
public class BatchApiSetupPanel extends JPanel{
    private static final String[] API_OPTIONS = {"Fund", "Auth", "AccHistory", "All"};
    private static final String SEQUENTIAL = "Sequential";
    private static final String FIRE_FORGET = "FireForget";
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
        Sequential_radio = new JRadioButton(SEQUENTIAL, true);
        FireForget_radio = new JRadioButton(FIRE_FORGET);
        ButtonGroup bank_group = new ButtonGroup();
        bank_group.add(Sequential_radio);
        bank_group.add(FireForget_radio);
        module_panel.add(Sequential_radio);
        module_panel.add(FireForget_radio);
        return module_panel;
    }
    public String getSelectedApi() {
        return (String) api_dropdown.getSelectedItem();
    }
    public String getSelectedModule() {
        return Sequential_radio.isSelected() ? SEQUENTIAL : FIRE_FORGET;
    }
}
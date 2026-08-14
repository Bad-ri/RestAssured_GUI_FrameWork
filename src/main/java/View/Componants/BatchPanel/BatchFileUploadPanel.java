package View.Componants.BatchPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

/**
 * class main functionality -> Building Batch File upload panel component
 * ┌─ File upload ──────────────────────────┐
 * │  data :        [ Upload ]              │
 * │  (•) Payload      ( ) Data             │
 * └────────────────────────────────────────┘
 */
public class BatchFileUploadPanel extends JPanel {
    private static final String PAYLOAD = "Payload";
    private static final String DATA = "Data";
    private static final int MAX_NAME_LENGTH = 7;
    private JButton test_data_button;
    private File test_data_file;
    private JRadioButton payload_radio;
    private JRadioButton data_radio;
    public BatchFileUploadPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("File upload"),
                new EmptyBorder(5, 5, 5, 5)
        ));
        add(buildFileRow());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(buildModulePanel());
    }
    private JPanel buildFileRow() {
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel label = new JLabel("data :");
        label.setPreferredSize(new Dimension(50, 22));
        row1.add(label);
        test_data_button = createUploadButton();
        test_data_button.addActionListener(e -> {
            File selected = chooseFile();
            if (selected != null) {
                test_data_file = selected;
                setButtonFileText(test_data_button, test_data_file);
            }
        });
        row1.add(test_data_button);
        return row1;
    }
    private JPanel buildModulePanel() {
        JPanel module_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        module_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        payload_radio = new JRadioButton(PAYLOAD, true);
        data_radio = new JRadioButton(DATA);
        ButtonGroup bank_group = new ButtonGroup();
        bank_group.add(payload_radio);
        bank_group.add(data_radio);
        module_panel.add(payload_radio);
        module_panel.add(data_radio);
        return module_panel;
    }
    private JButton createUploadButton() {
        JButton btn = new JButton("Upload");
        btn.setPreferredSize(new Dimension(65, 22));
        btn.setFont(btn.getFont().deriveFont(11f));
        btn.setMargin(new Insets(2, 2, 2, 2));
        return btn;
    }
    private void setButtonFileText(JButton button, File file) {
        String fileName = file.getName();
        button.setToolTipText(fileName); // Hovering still shows full file name
        if (fileName.length() > MAX_NAME_LENGTH) {
            button.setText(fileName.substring(0, MAX_NAME_LENGTH - 2) + "..");
        } else {
            button.setText(fileName);
        }
    }
    private File chooseFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }
    public void clearUploadButton() {
        this.test_data_file = null;
        this.test_data_button.setText("Upload");
        this.test_data_button.setToolTipText(null);
        revalidate();
        repaint();
    }
    public File getTest_data_file() {
        return test_data_file;
    }
    public String getSelectedModule() {
        return payload_radio.isSelected() ? PAYLOAD : DATA;
    }
}
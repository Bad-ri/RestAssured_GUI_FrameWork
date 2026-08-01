package View.Componants.BatchPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class BatchFileUploadPanel extends JPanel{
    private JButton test_data_button;
    private JRadioButton payload_radio;
    private File test_data_file;
    private JRadioButton data_radio;

    public BatchFileUploadPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("File upload"),
                new EmptyBorder(5, 5, 5, 5)
        ));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row1.setAlignmentX(Component.LEFT_ALIGNMENT);
        row1.add(new JLabel("data :"));
        test_data_button = new JButton("Upload");
        test_data_button.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                test_data_file = chooser.getSelectedFile();
                test_data_button.setText(test_data_file.getName());
            }
        });
        row1.add(test_data_button);
        add(row1);

        add(Box.createRigidArea(new Dimension(0, 5)));
        JPanel module_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        module_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        payload_radio = new JRadioButton("Payload",true);
        data_radio = new JRadioButton("Data");
        ButtonGroup bank_group = new ButtonGroup();
        bank_group.add(payload_radio);
        bank_group.add(data_radio);
        module_panel.add(payload_radio);
        module_panel.add(data_radio);
        add(module_panel);
    }

    public File getTest_data_file() {
        return test_data_file;
    }
    public String getSelectedModule() {
        return payload_radio.isSelected() ? "Payload" : "Data";
    }
}

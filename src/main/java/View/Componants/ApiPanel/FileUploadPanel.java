package View.Componants.ApiPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class FileUploadPanel extends JPanel {
    private JButton test_data_button;
    private JButton payload_button;
    private File test_data_file;
    private File payload_file;

    public FileUploadPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("File upload"),
                new EmptyBorder(5, 5, 5, 5)
        ));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row1.setAlignmentX(Component.LEFT_ALIGNMENT);
        row1.add(new JLabel("Test data :"));
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

        // Space between rows
        add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row2.setAlignmentX(Component.LEFT_ALIGNMENT);
        row2.add(new JLabel("Payload :  "));
        payload_button = new JButton("Upload");
        payload_button.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                payload_file = chooser.getSelectedFile();
                payload_button.setText(payload_file.getName());
            }
        });
        row2.add(payload_button);
        add(row2);
    }

    public File getTest_data_file() {
        return test_data_file;
    }
    public File getPayload_file() {
        return payload_file;
    }
}
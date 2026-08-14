package View.Componants.ApiPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.function.Consumer;

/**
 * class main functionality -> Building File upload panel component
 * ┌─ File upload ───────────────┐
 * │  Test data :   [ Upload ]   │
 * │  Payload   :   [ Upload ]   │
 * └─────────────────────────────┘
 */
public class FileUploadPanel extends JPanel {
    private JButton test_data_button;
    private JButton payload_button;
    private File test_data_file;
    private File payload_file;
    private static final int MAX_NAME_LENGTH = 7;
    public FileUploadPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("File upload"),
                new EmptyBorder(5, 5, 5, 5)
        ));
        test_data_button = createUploadButton();
        payload_button = createUploadButton();
        add(buildUploadRow("Test data :", test_data_button, file -> test_data_file = file));
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(buildUploadRow("Test Case :", payload_button, file -> payload_file = file));
    }
    private JPanel buildUploadRow(String labelText, JButton button, Consumer<File> onFileSelected) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(70, 22));
        row.add(label);
        button.addActionListener(e -> {
            File selected = chooseFile();
            if (selected != null) {
                onFileSelected.accept(selected);
                setButtonFileText(button, selected);
            }
        });
        row.add(button);
        return row;
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
        this.payload_file = null;
        this.test_data_button.setText("Upload");
        this.test_data_button.setToolTipText(null);
        this.payload_button.setText("Upload");
        this.payload_button.setToolTipText(null);
        revalidate();
        repaint();
    }

    public File getTest_data_file() {
        return test_data_file;
    }
    public File getPayload_file() {
        return payload_file;
    }
}
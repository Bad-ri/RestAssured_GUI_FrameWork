package View;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    public MainForm() {
        setTitle("Automation Framework Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EnvironmentSetupPanel environmentSetupPanel = new EnvironmentSetupPanel();
        APiSetupPanel api_setup_panel = new APiSetupPanel();
        FileUploadPanel file_upload_panel = new FileUploadPanel();

        add(environmentSetupPanel, BorderLayout.WEST);
        add(api_setup_panel, BorderLayout.CENTER);
        add(file_upload_panel, BorderLayout.EAST);

        pack(); // Auto-fit window to component size
        setLocationRelativeTo(null); // Center on screen
    }


}
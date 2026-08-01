package View.Taps;

import View.Componants.ApiPanel.APiSetupPanel;
import View.Componants.ApiPanel.EnvironmentSetupPanel;
import View.Componants.ApiPanel.FileUploadPanel;

import javax.swing.*;
import java.awt.*;

public class SingleApiTab extends JPanel{
    public SingleApiTab(EnvironmentSetupPanel environment_setup_panel, APiSetupPanel api_setup_panel, FileUploadPanel file_upload_panel) {
        JPanel center_top_container = new JPanel(new BorderLayout(10, 0));
        center_top_container.add(environment_setup_panel, BorderLayout.WEST);
        center_top_container.add(api_setup_panel, BorderLayout.CENTER);
        center_top_container.add(file_upload_panel, BorderLayout.EAST);
        add(center_top_container);
    }
}

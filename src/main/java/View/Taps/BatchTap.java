package View.Taps;

import View.Componants.BatchPanel.BatchApiSetupPanel;
import View.Componants.BatchPanel.BatchEnvironmentSetupPanel;
import View.Componants.BatchPanel.BatchFileUploadPanel;

import javax.swing.*;
import java.awt.*;

public class BatchTap extends JPanel{
    public BatchTap(BatchEnvironmentSetupPanel batchEnvPanel, BatchApiSetupPanel batchApiPanel, BatchFileUploadPanel batchFilePanel) {
        JPanel center_top_container = new JPanel(new BorderLayout(10, 0));
        center_top_container.add(batchEnvPanel, BorderLayout.WEST);
        center_top_container.add(batchApiPanel, BorderLayout.CENTER);
        center_top_container.add(batchFilePanel, BorderLayout.EAST);
        add(center_top_container);
    }
}

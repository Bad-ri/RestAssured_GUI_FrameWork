package View.Tabs;

import View.Componants.HealthPanel.HealthEnvSetupPanel;
import View.Componants.HealthPanel.HealthApiSetupPanel;
import View.Componants.HealthPanel.HealthResultPanel;
import javax.swing.*;
import java.awt.*;

/**
 class main functionality -> GUI View tab builder.
 1 - assemble tab view (Health & Sanity)
 */
public class HealthTab extends JPanel{
    public HealthTab(HealthApiSetupPanel healthPanel, HealthResultPanel healthResultPanel, HealthEnvSetupPanel healthApiPanel) {
        JPanel center_top_container = new JPanel(new BorderLayout(10, 0));
        center_top_container.add(healthPanel, BorderLayout.WEST);
        center_top_container.add(healthResultPanel, BorderLayout.EAST);
        center_top_container.add(healthApiPanel, BorderLayout.CENTER);
        add(center_top_container);
    }
}

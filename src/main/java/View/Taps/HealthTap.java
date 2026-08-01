package View.Taps;

import View.Componants.HealthPanel.HealthApiSetupPanel;
import View.Componants.HealthPanel.HealthEnvironmentSetupPanel;
import View.Componants.HealthPanel.HealthResultPanel;

import javax.swing.*;
import java.awt.*;

public class HealthTap extends JPanel{
    public HealthTap(HealthEnvironmentSetupPanel healthPanel, HealthResultPanel healthResultPanel, HealthApiSetupPanel healthApiPanel) {
        JPanel center_top_container = new JPanel(new BorderLayout(10, 0));
        center_top_container.add(healthPanel, BorderLayout.WEST);
        center_top_container.add(healthResultPanel, BorderLayout.EAST);
        center_top_container.add(healthApiPanel, BorderLayout.CENTER);
        add(center_top_container);
    }
}

package View;

import View.Componants.ApiPanel.APiSetupPanel;
import View.Componants.ApiPanel.EnvironmentSetupPanel;
import View.Componants.ApiPanel.FileUploadPanel;
import View.Componants.BatchPanel.BatchApiSetupPanel;
import View.Componants.BatchPanel.BatchEnvironmentSetupPanel;
import View.Componants.BatchPanel.BatchFileUploadPanel;
import View.Componants.HealthPanel.HealthEnvSetupPanel;
import View.Componants.HealthPanel.HealthApiSetupPanel;
import View.Componants.HealthPanel.HealthResultPanel;
import View.Componants.MainPanel.ActionPanel;
import View.Componants.MainPanel.HeaderPanel;
import View.Componants.MainPanel.HeaderTabPanel;
import View.Componants.MainPanel.TestStatusPanel;
import View.Taps.BatchTap;
import View.Taps.HealthTap;
import View.Taps.SingleApiTab;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout centerCardLayout = new CardLayout();
    private JPanel center_container = new JPanel(centerCardLayout);
    private ActionPanel actionPanel;

    public MainFrame() {
        setTitle("Automation Framework Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        // Initialize Header & Tabs
        HeaderPanel header_panel = new HeaderPanel();
        HeaderTabPanel header_tab_panel = new HeaderTabPanel();
        EnvironmentSetupPanel envPanel = new EnvironmentSetupPanel();
        APiSetupPanel apiPanel = new APiSetupPanel();
        FileUploadPanel filePanel = new FileUploadPanel();
        TestStatusPanel statusPanel = new TestStatusPanel();
        BatchEnvironmentSetupPanel batchEnvPanel = new BatchEnvironmentSetupPanel();
        BatchApiSetupPanel batchApiPanel = new BatchApiSetupPanel();
        BatchFileUploadPanel batchFilePanel = new BatchFileUploadPanel();
        HealthApiSetupPanel healthPanel = new HealthApiSetupPanel();
        HealthEnvSetupPanel healthApiPanel = new HealthEnvSetupPanel();
        HealthResultPanel healthResultPanel = new HealthResultPanel();
        actionPanel = new ActionPanel(envPanel, apiPanel, filePanel, statusPanel, batchEnvPanel, batchApiPanel, batchFilePanel, healthPanel, healthApiPanel);

        // Register Center Cards & Action Commands
        setupTab(header_tab_panel.getSingleApiTab(), "SINGLE_API", new SingleApiTab(envPanel, apiPanel, filePanel));
        setupTab(header_tab_panel.getBatchSuiteTab(), "BATCH_SUITE", new BatchTap(batchEnvPanel, batchApiPanel, batchFilePanel));
        setupTab(header_tab_panel.getHealthSanityTab(), "HEALTH_SANITY", new HealthTap(healthPanel, healthResultPanel,healthApiPanel));
        setupTab(header_tab_panel.getPortalValidationTab(), "PORTAL_VALIDATION", createPlaceholder("Portal Validation View"));

        // 4. Top Container
        JPanel top_container = new JPanel();
        top_container.setLayout(new BoxLayout(top_container, BoxLayout.Y_AXIS));
        top_container.add(header_panel);
        top_container.add(header_tab_panel);
        JPanel bottom_container = new JPanel();
        bottom_container.setLayout(new BoxLayout(bottom_container, BoxLayout.Y_AXIS));
        bottom_container.add(statusPanel);
        bottom_container.add(Box.createRigidArea(new Dimension(0, 5)));
        bottom_container.add(actionPanel);
        add(top_container, BorderLayout.NORTH);
        add(center_container, BorderLayout.CENTER);
        add(bottom_container, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void setupTab(JButton button, String cardKey, Component panel) {
        center_container.add(panel, cardKey);
        button.setActionCommand(cardKey);
        actionPanel.set_current_tap("SINGLE_API");
        button.addActionListener(e -> {
            String selectedTab = e.getActionCommand();
            centerCardLayout.show(center_container, selectedTab);
            actionPanel.set_current_tap(selectedTab);
        });
    }

    private JPanel createPlaceholder(String title) {
        JPanel p = new JPanel(new GridBagLayout());
        p.add(new JLabel("=== " + title + " ==="));
        return p;
    }

}
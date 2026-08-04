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
import View.Componants.MainPanel.TabPanel;
import View.Componants.MainPanel.MetricsPanel;
import View.Componants.MainPanel.SplashScreen;
import View.Tabs.BatchTab;
import View.Tabs.HealthTab;
import View.Tabs.SingleApiTab;
import javax.swing.*;
import java.awt.*;

/**
 * class main functionality -> GUI View Main builder.
 *  1 - assemble tab views (Single API, Batch Suite, Health & Sanity, Portal Validation)
 *  2 - assemble main frame (header, header tabs and status/action bar)
 */
public class MainFrame extends JFrame {
    private static final int SPLASH_DURATION_MS = 2200;
    private static final String TAB_SINGLE_API = "SINGLE_API";
    private static final String TAB_BATCH_SUITE = "BATCH_SUITE";
    private static final String TAB_HEALTH_SANITY = "HEALTH_SANITY";
    private static final String TAB_PORTAL_VALIDATION = "PORTAL_VALIDATION";
    private final CardLayout centerCardLayout = new CardLayout();
    private final JPanel centerContainer = new JPanel(centerCardLayout);
    private final ActionPanel actionPanel;

    public static void showApp() {
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(SPLASH_DURATION_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            SwingUtilities.invokeLater(() -> {
                new MainFrame().setVisible(true);
                splash.dispose();
            });
        }).start();
    }

    public MainFrame() {
        setTitle("Automation Framework Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        HeaderPanel headerPanel = new HeaderPanel();
        TabPanel headerTabPanel = new TabPanel();
        MetricsPanel statusPanel = new MetricsPanel();
        EnvironmentSetupPanel envPanel = new EnvironmentSetupPanel();
        APiSetupPanel apiPanel = new APiSetupPanel();
        FileUploadPanel filePanel = new FileUploadPanel();
        BatchEnvironmentSetupPanel batchEnvPanel = new BatchEnvironmentSetupPanel();
        BatchApiSetupPanel batchApiPanel = new BatchApiSetupPanel();
        BatchFileUploadPanel batchFilePanel = new BatchFileUploadPanel();
        HealthApiSetupPanel healthPanel = new HealthApiSetupPanel();
        HealthEnvSetupPanel healthApiPanel = new HealthEnvSetupPanel();
        HealthResultPanel healthResultPanel = new HealthResultPanel();

        // we pass the obj through constructor to use the same instance
        actionPanel = new ActionPanel(envPanel, apiPanel, filePanel, statusPanel,
                batchEnvPanel, batchApiPanel, batchFilePanel, healthPanel, healthApiPanel);
        actionPanel.set_current_tap(TAB_SINGLE_API);

        // creating window tabs
        registerTab(headerTabPanel.getSingleApiTab(), TAB_SINGLE_API,
                new SingleApiTab(envPanel, apiPanel, filePanel));
        registerTab(headerTabPanel.getBatchSuiteTab(), TAB_BATCH_SUITE,
                new BatchTab(batchEnvPanel, batchApiPanel, batchFilePanel));
        registerTab(headerTabPanel.getHealthSanityTab(), TAB_HEALTH_SANITY,
                new HealthTab(healthPanel, healthResultPanel, healthApiPanel));
        registerTab(headerTabPanel.getPortalValidationTab(), TAB_PORTAL_VALIDATION,
                placeholderPanel("Portal Validation View"));

        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.add(headerPanel);
        topContainer.add(headerTabPanel);

        JPanel bottomContainer = new JPanel();
        bottomContainer.setLayout(new BoxLayout(bottomContainer, BoxLayout.Y_AXIS));
        bottomContainer.add(statusPanel);
        bottomContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        bottomContainer.add(actionPanel);

        add(topContainer, BorderLayout.NORTH);
        add(centerContainer, BorderLayout.CENTER);
        add(bottomContainer, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void registerTab(JButton tabButton, String tabKey, Component panel) {
        centerContainer.add(panel, tabKey);
        tabButton.setActionCommand(tabKey);
        tabButton.addActionListener(e -> {
            centerCardLayout.show(centerContainer, e.getActionCommand());
            actionPanel.set_current_tap(e.getActionCommand());
        });
    }

    private JPanel placeholderPanel(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(new JLabel("=== " + title + " ==="));
        return panel;
    }
}
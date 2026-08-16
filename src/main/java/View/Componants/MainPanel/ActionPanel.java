package View.Componants.MainPanel;

import Controller.Controller;
import Helper.CurrentSession;
import View.Componants.ApiPanel.APiSetupPanel;
import View.Componants.ApiPanel.EnvironmentSetupPanel;
import View.Componants.ApiPanel.FileUploadPanel;
import View.Componants.BatchPanel.BatchApiSetupPanel;
import View.Componants.BatchPanel.BatchEnvironmentSetupPanel;
import View.Componants.BatchPanel.BatchFileUploadPanel;
import View.Componants.HealthPanel.HealthEnvSetupPanel;
import View.Componants.HealthPanel.HealthApiSetupPanel;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * class main functionality -> Building Action panel component (export/pause/execute controls)
 * ┌─ Actions ──────────────────────────────────────────────┐
 * │  [Export report] [Export from DB]    [Pause] [Execute] │
 * └────────────────────────────────────────────────────────┘
 */
public class ActionPanel extends JPanel {
    private JButton export_report_btn;
    private JButton export_db_btn;
    private JButton pause_btn;
    private JButton execute_btn;
    private EnvironmentSetupPanel environment_setup_panel;
    private APiSetupPanel api_setup_panel;
    private FileUploadPanel file_upload_panel;
    private MetricsPanel status_panel;
    private String current_tap_selected;
    private Controller controller;
    private BatchEnvironmentSetupPanel batch_env_panel;
    private BatchApiSetupPanel batch_api_setup;
    private BatchFileUploadPanel batch_file;
    private HealthEnvSetupPanel health_env_panel;
    private HealthApiSetupPanel health_api_setup;

    public ActionPanel(EnvironmentSetupPanel environmentSetupPanel, APiSetupPanel api_setup_panel, FileUploadPanel file_upload_panel, MetricsPanel status_panel, BatchEnvironmentSetupPanel batchEnvPanel, BatchApiSetupPanel batchApiPanel, BatchFileUploadPanel batchFilePanel, HealthApiSetupPanel healthApiSetupPanel, HealthEnvSetupPanel healthEnvPanel) {
        controller = new Controller();
        this.environment_setup_panel = environmentSetupPanel;
        this.api_setup_panel = api_setup_panel;
        this.file_upload_panel = file_upload_panel;
        this.status_panel = status_panel;
        this.batch_env_panel = batchEnvPanel;
        this.batch_api_setup = batchApiPanel;
        this.batch_file = batchFilePanel;
        this.health_env_panel = healthEnvPanel;
        this.health_api_setup = healthApiSetupPanel;
        setLayout(new BorderLayout(0, 10));
        setMaximumSize(new Dimension(590, 90));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, 0, 15, 0),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Actions"),
                        new EmptyBorder(5, 5, 5, 5)
                )
        ));
        add(buildLeftControls(), BorderLayout.WEST);
        add(buildRightControls(), BorderLayout.EAST);
    }
    private JPanel buildLeftControls() {
        JPanel left_controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        export_report_btn = new JButton("Export report");
        export_db_btn = new JButton("Export from DB");

        export_report_btn.addActionListener(e -> takeAction("case_report"));
        export_db_btn.addActionListener(e -> takeAction("db_report"));

        left_controls.add(export_report_btn);
        left_controls.add(export_db_btn);
        return left_controls;
    }
    private JPanel buildRightControls() {
        JPanel right_controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pause_btn = new JButton("Pause");
        execute_btn = new JButton("Execute");
        execute_btn.putClientProperty(FlatClientProperties.STYLE, "background: #2e7d32; foreground: #ffffff;");

        execute_btn.addActionListener(e -> takeAction("execute"));
        pause_btn.addActionListener(e -> takeAction("pause"));

        right_controls.add(pause_btn);
        right_controls.add(execute_btn);
        return right_controls;
    }
    public void takeAction(String action) {
        switch (action) {
            case "execute":
                AbstractData();
                break;
            case "pause":
                break;
            case "case_report":
                break;
            case "db_report":
                break;
            default:
        }
    }
    public void AbstractData() {
        switch (current_tap_selected) {
            case "SINGLE_API":
                CurrentSession.SESSION.set(
                        environment_setup_panel.getSelectedBank(),
                        environment_setup_panel.getSelectedEnvironment(),
                        api_setup_panel.getSelectedApi(),
                        api_setup_panel.getSelectedModule(),
                        file_upload_panel.getTest_data_file() != null && file_upload_panel.getTest_data_file().exists(),
                        file_upload_panel.getPayload_file() != null && file_upload_panel.getPayload_file().exists(),
                        file_upload_panel.getTest_data_file(),
                        file_upload_panel.getPayload_file()
                );
                controller.single_api_session(
                        environment_setup_panel.getSelectedEnvironment(),
                        environment_setup_panel.getSelectedBank(),
                        api_setup_panel.getSelectedApi(),
                        api_setup_panel.getSelectedModule(),
                        file_upload_panel.getTest_data_file(),
                        file_upload_panel.getPayload_file()
                );
                file_upload_panel.clearUploadButton();
                break;
            case "BATCH_SUITE":
                controller.batch_api_session(
                        batch_env_panel.getSelectedEnvironment(),
                        batch_env_panel.getSelectedBank(),
                        batch_api_setup.getSelectedApi(),
                        batch_api_setup.getSelectedModule(),
                        batch_file.getTest_data_file(),
                        batch_file.getSelectedModule()
                );
                batch_file.clearUploadButton();
                break;
            case "HEALTH_SANITY":
                controller.health_session(
                        health_env_panel.getSelectedEnvironment(),
                        health_env_panel.getSelectedBank(),
                        health_api_setup.getSelectedApi()
                );
                break;
            default:
        }
    }
    public void set_current_tap(String current_tap) {
        this.current_tap_selected = current_tap;
    }
}
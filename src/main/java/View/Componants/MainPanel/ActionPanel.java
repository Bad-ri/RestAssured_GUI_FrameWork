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
 * ActionPanel is  responsible for building the action panel GUI components*
 * ┌─ Actions ──────────────────────────────────────────────┐
 * │  [Export report] [Export from DB]    [Pause] [Execute] │
 * └────────────────────────────────────────────────────────┘
 */
public class ActionPanel extends JPanel {
    private JButton export_report_btn;
    private JButton export_db_btn;
    private JButton pause_btn;
    private JButton execute_btn;
    private CurrentSession currentSession = CurrentSession.SESSION;
    private String current_tap_selected;
    private final Controller controller;

    public ActionPanel(
            EnvironmentSetupPanel environmentSetupPanel,
            APiSetupPanel api_setup_panel,
            FileUploadPanel file_upload_panel,
            MetricsPanel status_panel,
            BatchEnvironmentSetupPanel batchEnvPanel,
            BatchApiSetupPanel batchApiPanel,
            BatchFileUploadPanel batchFilePanel,
            HealthApiSetupPanel healthApiSetupPanel,
            HealthEnvSetupPanel healthEnvPanel) {

        this.controller = new Controller(
                environmentSetupPanel,
                api_setup_panel,
                file_upload_panel,
                batchEnvPanel,
                batchApiPanel,
                batchFilePanel,
                healthEnvPanel,
                healthApiSetupPanel
        );

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
        //export_report_btn.addActionListener(e -> controller.exportReport(current_tap_selected));
        //export_db_btn.addActionListener(e -> controller.exportDbReport(current_tap_selected));

        left_controls.add(export_report_btn);
        left_controls.add(export_db_btn);
        return left_controls;
    }

    private JPanel buildRightControls() {
        JPanel right_controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pause_btn = new JButton("Pause");
        pause_btn.putClientProperty(FlatClientProperties.STYLE, "background: #1565c0; foreground: #ffffff;");
        execute_btn = new JButton("Execute");
        execute_btn.putClientProperty(FlatClientProperties.STYLE, "background: #2e7d32; foreground: #ffffff;");

        execute_btn.addActionListener(e -> {
            execute_btn.setEnabled(false);
            new Thread(() -> {
                try {
                    controller.executeSession(current_tap_selected);
                } finally {
                    SwingUtilities.invokeLater(() -> execute_btn.setEnabled(true));
                }
            }).start();
        });
        pause_btn.addActionListener(e -> clickPause());

        right_controls.add(pause_btn);
        right_controls.add(execute_btn);
        return right_controls;
    }

    private void clickPause() {
        if(currentSession.isPause()) {
            currentSession.setPause(false);
        }else
            currentSession.setPause(true);
        if (currentSession.isPause()) {
            pause_btn.setText("Resume");
            pause_btn.putClientProperty(FlatClientProperties.STYLE, "background: #ef6c00; foreground: #ffffff;");
        } else {
            pause_btn.setText("Pause");
            pause_btn.putClientProperty(FlatClientProperties.STYLE, "background: #1565c0; foreground: #ffffff;");

        }
    }

    public void set_current_tap(String current_tap) {
        this.current_tap_selected = current_tap;
    }
}

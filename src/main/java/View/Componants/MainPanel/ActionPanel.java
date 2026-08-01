package View.Componants.MainPanel;

import View.Componants.ApiPanel.APiSetupPanel;
import View.Componants.ApiPanel.EnvironmentSetupPanel;
import View.Componants.ApiPanel.FileUploadPanel;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ActionPanel extends JPanel {
    private JButton export_report_btn;
    private JButton export_db_btn;
    private JButton pause_btn;
    private JButton execute_btn;
    private EnvironmentSetupPanel environment_setup_panel;
    private APiSetupPanel api_setup_panel;
    private FileUploadPanel file_upload_panel;
    private TestStatusPanel status_panel;
    public ActionPanel(EnvironmentSetupPanel environmentSetupPanel, APiSetupPanel api_setup_panel, FileUploadPanel file_upload_panel, TestStatusPanel status_panel) {
        this.environment_setup_panel = environmentSetupPanel;
        this.api_setup_panel = api_setup_panel;
        this.file_upload_panel = file_upload_panel;
        this.status_panel = status_panel;

        setLayout(new BorderLayout(0, 10));
        setMaximumSize(new Dimension(590, 90));
        setAlignmentX(Component.CENTER_ALIGNMENT); // Prevents 100% width stretching

        setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, 0, 15, 0), // 15px bottom spacing
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Actions"),
                        new EmptyBorder(5, 5, 5, 5)
                )
        ));
        // Left Controls
        JPanel left_controls = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        export_report_btn = new JButton("Export report");
        export_db_btn = new JButton("Export from DB");
        left_controls.add(export_report_btn);
        left_controls.add(export_db_btn);

        // Right Controls
        JPanel right_controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pause_btn = new JButton("Pause");
        execute_btn = new JButton("Execute");
        execute_btn.putClientProperty(FlatClientProperties.STYLE, "background: #2e7d32; foreground: #ffffff;");
        execute_btn.addActionListener(e -> {
            takeAction("execute");
        });
        pause_btn.addActionListener(e -> {
            takeAction("pause");
        });

        right_controls.add(pause_btn);
        right_controls.add(execute_btn);

        add(left_controls, BorderLayout.WEST);
        add(right_controls, BorderLayout.EAST);
    }
    public void takeAction (String action){
        switch (action)
        {
            case "execute":
                status_panel.updateProgress(5,10,2,3,"Fund","1:00:00");
                break;
            case "pause":
                status_panel.updateProgress(10,10,0,10,"Fund","00:00:00");
                break;
            default:
        }
    }
}

package View.Componants.MainPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TestStatusPanel extends JPanel {
    private JLabel current_test_label;
    private JLabel progress_label;
    private JLabel passed_label;
    private JLabel failed_label;
    private JLabel duration_label;
    private JProgressBar progress_bar;
    public TestStatusPanel() {
        setLayout(new BorderLayout(0, 10));
        setMaximumSize(new Dimension(590, 90));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Test Execution Status"),
                new EmptyBorder(5, 5, 5, 5)
        ));

        JPanel card_panel = new JPanel();
        card_panel.setLayout(new BoxLayout(card_panel, BoxLayout.Y_AXIS));
        JPanel metrics_panel = new JPanel(new GridLayout(1, 5, 10, 0));
        current_test_label = createMetricLabel("Current scope", "N/A", metrics_panel);
        progress_label     = createMetricLabel("Progress", "0/0", metrics_panel);
        passed_label       = createMetricLabel("Passed", "0", metrics_panel);
        failed_label       = createMetricLabel("Failed", "0", metrics_panel);
        duration_label     = createMetricLabel("Duration", "00:00:00", metrics_panel);
        card_panel.add(metrics_panel);
        card_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        progress_bar = new JProgressBar(0, 100);
        progress_bar.setValue(0);
        card_panel.add(progress_bar);
        add(card_panel, BorderLayout.CENTER);
    }
    private JLabel createMetricLabel(String title, String defaultText, JPanel parent) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        JLabel title_label = new JLabel(title);
        title_label.setFont(title_label.getFont().deriveFont(Font.PLAIN, 11f));
        title_label.setForeground(UIManager.getColor("Label.disabledForeground"));
        JLabel value_label = new JLabel(defaultText);
        value_label.setFont(value_label.getFont().deriveFont(Font.BOLD, 14f));
        box.add(title_label);
        box.add(Box.createRigidArea(new Dimension(0, 4)));
        box.add(value_label);
        parent.add(box);
        return value_label;
    }
    public void updateProgress(int current, int total, int passed, int failed, String current_test, String duration) {
        current_test_label.setText(current_test);
        progress_label.setText(current + " / " + total);
        passed_label.setText(String.valueOf(passed));
        failed_label.setText(String.valueOf(failed));
        duration_label.setText(duration);
        int percent = total > 0 ? (current * 100) / total : 0;
        progress_bar.setValue(percent);
    }

}
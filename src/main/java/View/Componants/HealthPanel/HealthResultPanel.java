package View.Componants.HealthPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * class main functionality -> Building Health Result panel component
 * ┌─ Health Results ────┐
 * │  Fund:      UP      │
 * │  Auth:      UP      │
 * │  History:   UP      │
 * │  Overall:   OK      │
 * └─────────────────────┘
 */
public class HealthResultPanel extends JPanel {
    private JLabel fundStatus;
    private JLabel authStatus;
    private JLabel historyStatus;
    private JLabel overallStatus;
    public HealthResultPanel() {
        setLayout(new GridLayout(4, 1, 0, 4));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Health Results"),
                new EmptyBorder(5, 10, 5, 10)
        ));
        fundStatus    = createStatusLabel("Fund:", "-", true);
        authStatus    = createStatusLabel("Auth:", "-", true);
        historyStatus = createStatusLabel("History:", "-", true);
        overallStatus = createStatusLabel("Overall:", "-", true);
        add(fundStatus);
        add(authStatus);
        add(historyStatus);
        add(overallStatus);
    }
    private JLabel createStatusLabel(String labelText, String statusText, boolean isWorking) {
        JLabel label = new JLabel(buildLabelHtml(labelText, statusText, isWorking));
        label.setFont(label.getFont().deriveFont(12f));
        return label;
    }
    private String buildLabelHtml(String labelText, String statusText, boolean isWorking) {
        return String.format("<html><b>%s</b> %s</html>",
                labelText,
                formatBadge(statusText, isWorking));
    }
    private String formatBadge(String text, boolean isWorking) {
        String color = isWorking ? "#2e7d32" : "#d32f2f"; // Dark Green vs Soft Red
        return String.format("<span style='color: %s; font-weight: bold;'>%s</span>", color, text);
    }
    public void updateResults(boolean fund, boolean auth, boolean history) {
        fundStatus.setText(buildLabelHtml("Fund:", fund ? "Working" : "Not Working", fund));
        authStatus.setText(buildLabelHtml("Auth:", auth ? "Working" : "Not Working", auth));
        historyStatus.setText(buildLabelHtml("History:", history ? "Working" : "Not Working", history));

        boolean allOk = fund && auth && history;
        overallStatus.setText(buildLabelHtml("Overall:", allOk ? "All OK" : "Issues Found", allOk));
    }
}
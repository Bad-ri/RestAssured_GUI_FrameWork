package View.Componants.HealthPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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

        // Create status rows
        fundStatus    = createStatusLabel("Fund:", "Working", true);
        authStatus    = createStatusLabel("Auth:", "Working", true);
        historyStatus = createStatusLabel("History:", "Not Working", false);
        overallStatus = createStatusLabel("Overall:", "Issues Found", false);

        add(fundStatus);
        add(authStatus);
        add(historyStatus);
        add(overallStatus);
    }
    private JLabel createStatusLabel(String labelText, String statusText, boolean isWorking) {
        JLabel label = new JLabel(String.format("<html><b>%s</b> %s</html>",
                labelText,
                formatBadge(statusText, isWorking)));
        label.setFont(label.getFont().deriveFont(12f));
        return label;
    }

    private String formatBadge(String text, boolean isWorking) {
        String color = isWorking ? "#2e7d32" : "#d32f2f"; // Dark Green vs Soft Red
        return String.format("<span style='color: %s; font-weight: bold;'>%s</span>", color, text);
    }

    // Method to dynamically update results later from your execution runner
    public void updateResults(boolean fund, boolean auth, boolean history) {
        fundStatus.setText("<html><b>Fund:</b> " + formatBadge(fund ? "Working" : "Not Working", fund) + "</html>");
        authStatus.setText("<html><b>Auth:</b> " + formatBadge(auth ? "Working" : "Not Working", auth) + "</html>");
        historyStatus.setText("<html><b>History:</b> " + formatBadge(history ? "Working" : "Not Working", history) + "</html>");

        boolean allOk = fund && auth && history;
        overallStatus.setText("<html><b>Overall:</b> " + formatBadge(allOk ? "All OK" : "Issues Found", allOk) + "</html>");
    }
}
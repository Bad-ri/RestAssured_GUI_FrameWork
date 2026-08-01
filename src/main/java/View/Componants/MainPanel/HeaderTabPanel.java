package View.Componants.MainPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderTabPanel extends JPanel {

    private JButton single_api_tab;
    private JButton batch_suite_tab;
    private JButton health_sanity_tab;
    private JButton portal_validation_tab;

    public HeaderTabPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        setBorder(new EmptyBorder(5, 15, 10, 15));

        single_api_tab        = createTabButton("▶  Single API Runner");
        batch_suite_tab       = createTabButton("≡  Batch Suite Runner");
        health_sanity_tab     = createTabButton("❤️  Health & Sanity Check");
        portal_validation_tab = createTabButton("🌐  Portal Validation");

        add(single_api_tab);
        add(batch_suite_tab);
        add(health_sanity_tab);
        add(portal_validation_tab);

        setActiveTab(single_api_tab);
    }

    private JButton createTabButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addActionListener(e -> {
            setActiveTab(btn);
            onTabClicked(btn.getText());
        });

        return btn;
    }

    private void onTabClicked(String buttonTitle) {
        System.out.println("Tab clicked: " + buttonTitle);
    }

    public void setActiveTab(JButton selectedBtn) {
        JButton[] tabs = {single_api_tab, batch_suite_tab, health_sanity_tab, portal_validation_tab};
        Color accentColor = UIManager.getColor("Component.accentColor");
        if (accentColor == null) accentColor = new Color(53, 116, 240);

        for (JButton btn : tabs) {
            boolean isActive = (btn == selectedBtn);
            btn.setFont(btn.getFont().deriveFont(isActive ? Font.BOLD : Font.PLAIN, 13f));
            btn.setForeground(UIManager.getColor(isActive ? "Label.foreground" : "Label.disabledForeground"));

            if (isActive) {
                btn.setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 0, accentColor),
                        new EmptyBorder(4, 0, 4, 0)
                ));
            } else {
                btn.setBorder(new EmptyBorder(6, 0, 4, 0));
            }
        }
        revalidate();
        repaint();
    }

    public JButton getSingleApiTab() { return single_api_tab; }
    public JButton getBatchSuiteTab() { return batch_suite_tab; }
    public JButton getHealthSanityTab() { return health_sanity_tab; }
    public JButton getPortalValidationTab() { return portal_validation_tab; }
}
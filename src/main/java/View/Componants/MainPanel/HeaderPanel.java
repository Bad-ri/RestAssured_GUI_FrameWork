package View.Componants.MainPanel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * class main functionality -> Building Header panel component (logo, title, theme toggle)
 * ┌────────────────────────────────────────────────────────┐
 * │  [icon]  API Test Automation Framework   ☀️ Light Mode │
 * │          REST Runner                                   │
 * └────────────────────────────────────────────────────────┘
 */
public class HeaderPanel extends JPanel {
    private static final String LIGHT_MODE_LABEL = "☀️ Light Mode";
    private static final String DARK_MODE_LABEL = "🌙 Dark Mode";
    private JToggleButton theme_toggle_btn;
    public HeaderPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(15, 15, 10, 15));
        add(buildLeftContainer(), BorderLayout.WEST);
        add(buildThemeToggleButton(), BorderLayout.EAST);
    }
    private JPanel buildLeftContainer() {
        JPanel left_container = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/assets/nbe_logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel icon_label = new JLabel(resizedIcon);
        JPanel title_box = new JPanel();
        title_box.setLayout(new BoxLayout(title_box, BoxLayout.Y_AXIS));
        JLabel title_label = new JLabel("API Test Automation Framework");
        title_label.setFont(title_label.getFont().deriveFont(Font.BOLD, 18f));
        title_label.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel subtitle_label = new JLabel("REST Runner");
        subtitle_label.setFont(subtitle_label.getFont().deriveFont(Font.PLAIN, 12f));
        subtitle_label.setForeground(UIManager.getColor("Label.disabledForeground"));
        subtitle_label.setAlignmentX(Component.LEFT_ALIGNMENT);
        title_box.add(title_label);
        title_box.add(Box.createRigidArea(new Dimension(0, 3)));
        title_box.add(subtitle_label);
        left_container.add(icon_label);
        left_container.add(title_box);
        return left_container;
    }
    private JToggleButton buildThemeToggleButton() {
        theme_toggle_btn = new JToggleButton(LIGHT_MODE_LABEL);
        theme_toggle_btn.setSelected(true);
        theme_toggle_btn.putClientProperty("JButton.buttonType", "roundRect");
        theme_toggle_btn.setFocusPainted(false);
        theme_toggle_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        theme_toggle_btn.addActionListener(e -> {
            boolean isDark = theme_toggle_btn.isSelected();
            if (isDark) {
                FlatMacDarkLaf.setup();
                theme_toggle_btn.setText(LIGHT_MODE_LABEL);
            } else {
                FlatIntelliJLaf.setup();
                theme_toggle_btn.setText(DARK_MODE_LABEL);
            }
            FlatLaf.updateUI();
        });
        return theme_toggle_btn;
    }
}
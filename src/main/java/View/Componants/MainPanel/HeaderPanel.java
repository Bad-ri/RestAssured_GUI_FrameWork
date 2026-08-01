package View.Componants.MainPanel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {

    private JToggleButton theme_toggle_btn;

    public HeaderPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(15, 15, 10, 15));

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

        theme_toggle_btn = new JToggleButton("☀️ Light Mode");
        theme_toggle_btn.setSelected(true);
        theme_toggle_btn.putClientProperty("JButton.buttonType", "roundRect");
        theme_toggle_btn.setFocusPainted(false);
        theme_toggle_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        theme_toggle_btn.addActionListener(e -> {
            boolean isDark = theme_toggle_btn.isSelected();

            if (isDark) {
                FlatMacDarkLaf.setup();
                theme_toggle_btn.setText("☀️ Light Mode");
            } else {
                FlatIntelliJLaf.setup();
                theme_toggle_btn.setText("🌙 Dark Mode");
            }
            FlatLaf.updateUI();
        });
        add(left_container, BorderLayout.WEST);
        add(theme_toggle_btn, BorderLayout.EAST);
    }

}
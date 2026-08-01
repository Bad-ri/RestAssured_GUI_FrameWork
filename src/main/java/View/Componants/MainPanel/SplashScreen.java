package View.Componants.MainPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
public class SplashScreen extends JWindow {
    private static final String LOGO_PATH = "/assets/nbe_logo.png";
    private static final String FALLBACK_TEXT = "NBE";
    public SplashScreen() {
        setContentPane(buildContentPanel());
        pack();
        setSize(220, 210);
        setLocationRelativeTo(null); // Centers window on screen
    }
    private JPanel buildContentPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIManager.getColor("Component.borderColor"), 1),
                new EmptyBorder(25, 25, 20, 25)
        ));
        panel.add(createLogoLabel(), BorderLayout.CENTER);
        panel.add(createProgressBar(), BorderLayout.SOUTH);
        return panel;
    }
    private JLabel createLogoLabel() {
        JLabel logoLabel = new JLabel("", SwingConstants.CENTER);
        URL imgUrl = getClass().getResource(LOGO_PATH);
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image scaledImg = icon.getImage().getScaledInstance(120, -1, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImg));
        } else {
            logoLabel.setText(FALLBACK_TEXT);
            logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        }
        return logoLabel;
    }
    private JProgressBar createProgressBar() {
        JProgressBar progress = new JProgressBar();
        progress.setIndeterminate(true);
        progress.putClientProperty("JComponent.roundRect", true);
        progress.putClientProperty("FlatLaf.style", "height: 6; arc: 6; foreground: #006B3F;");
        return progress;
    }
}
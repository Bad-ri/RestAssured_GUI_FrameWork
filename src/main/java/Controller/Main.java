package Controller;

import View.Componants.MainPanel.SplashScreen;
import View.MainFrame;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.*;
public class Main {
    private static final int SPLASH_DURATION_MS = 2500;

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(Main::launchApplication);
    }
    private static void launchApplication() {
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);
        new Thread(() -> {
            simulateLoading();
            SwingUtilities.invokeLater(() -> {
                new MainFrame().setVisible(true);
                splash.dispose();
            });
        }).start();
    }
    private static void simulateLoading() {
        try {
            Thread.sleep(SPLASH_DURATION_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
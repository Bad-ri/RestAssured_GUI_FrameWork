package Controller;

import View.MainForm;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Apply FlatLaf Dark Theme
        FlatDarkLaf.setup();

        // Launch app directly
        SwingUtilities.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }
}
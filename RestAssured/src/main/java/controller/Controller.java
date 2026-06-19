package controller;

import core.CoreApi;

import javax.swing.*;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.util.function.Consumer;

public class Controller {

    private String selected_file_path = "";
    private String last_results = "";

    public StringBuilder runTests(String bank, String env, String apiMethod) {
        System.out.println(bank+env+apiMethod);
        CoreApi corObj = new CoreApi();
        StringBuilder sb = corObj.apiAutomation(bank,env,apiMethod);
        last_results = sb.toString();
        sb.append("\nTests execution completed successfully!");
        return sb;
    }

    public void browseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            selected_file_path = fileChooser.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(null, "File selected:\n" + selected_file_path);
        }
    }

    public void exportReport() {
        if (last_results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No results to export yet. Run the tests first.");
            return;
        }
        try {
            File folder = new File("reports");
            if (!folder.exists()) folder.mkdir();
            String html = "<html><body>"
                    + "<h2>API Test Report</h2>"
                    + "<pre>" + last_results + "</pre>"
                    + "<p>Generated: " + LocalDateTime.now() + "</p>"
                    + "</body></html>";
            FileWriter writer = new FileWriter("src/main/resources/reports/report.html");
            writer.write(html);
            writer.close();
            JOptionPane.showMessageDialog(null, "Report saved to src/main/resources/reports");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to save report: " + e.getMessage());
        }
    }
}
package Helper.ReportManager;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {

    private static final List<String> reportRows = new ArrayList<>();

    public static void recordExchange(String transactionId, String statusCode) {
        //String statusClass = (statusCode >= 200 && statusCode < 400) ? "pass" : "fail";
        // + "<td class='" + statusClass + "'>" + statusCode + "</td>"
        String row = "<tr>"
                + "<td>" + transactionId + "</td>"
                + "<td>"+ statusCode + "</td>"
                + "</tr>";

        reportRows.add(row);
    }

    public static void generateHtmlReport() {
        File folder = new File("target/reports");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File reportFile = new File(folder, "index.html");

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>API Report</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; margin: 20px; background: #f4f4f9; }");
        html.append("table { width: 100%; border-collapse: collapse; background: #fff; }");
        html.append("th, td { padding: 10px; border: 1px solid #ccc; text-align: left; }");
        html.append("th { background: #333; color: #fff; }");
        html.append(".pass { color: green; font-weight: bold; }");
        html.append(".fail { color: red; font-weight: bold; }");
        html.append("</style></head><body>");

        html.append("<h2>API Execution Summary</h2>");
        html.append("<table>");
        html.append("<tr><th>Transaction ID</th><th>Status Code</th></tr>");

        for (String row : reportRows) {
            html.append(row);
        }

        html.append("</table></body></html>");
        // 1. Write file to disk
        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write(html.toString());
            System.out.println("Report saved to: " + reportFile.getAbsolutePath());
            Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
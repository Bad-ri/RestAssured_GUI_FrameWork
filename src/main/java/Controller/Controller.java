package Controller;

import Engine.BatchExecuter;
import Engine.SingleExecuter;
import Helper.CurrentSession;
import View.Componants.ApiPanel.APiSetupPanel;
import View.Componants.ApiPanel.EnvironmentSetupPanel;
import View.Componants.ApiPanel.FileUploadPanel;
import View.Componants.BatchPanel.BatchApiSetupPanel;
import View.Componants.BatchPanel.BatchEnvironmentSetupPanel;
import View.Componants.BatchPanel.BatchFileUploadPanel;
import View.Componants.HealthPanel.HealthApiSetupPanel;
import View.Componants.HealthPanel.HealthEnvSetupPanel;
import com.sun.net.httpserver.HttpServer;
import io.qameta.allure.ConfigurationBuilder;
import io.qameta.allure.ReportGenerator;
import io.qameta.allure.core.Configuration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Controller handles the orchestration between the GUI View panels
 * and the execution engines (SingleExecuter and BatchExecuter).
 */
public class Controller {
    private SingleExecuter single_executer;
    private BatchExecuter batch_executer;
    private final CurrentSession currentSession = CurrentSession.SESSION;

    // View Panel references for data extraction and control
    private EnvironmentSetupPanel environment_setup_panel;
    private APiSetupPanel api_setup_panel;
    private FileUploadPanel file_upload_panel;
    private BatchEnvironmentSetupPanel batch_env_panel;
    private BatchApiSetupPanel batch_api_setup;
    private BatchFileUploadPanel batch_file;
    private HealthEnvSetupPanel health_env_panel;
    private HealthApiSetupPanel health_api_setup;

    /**
     * Default constructor for backward compatibility or direct instantiation.
     */
    public Controller() {
    }

    /**
     * Overloaded constructor to inject all required view panels.
     */
    public Controller(
            EnvironmentSetupPanel environment_setup_panel,
            APiSetupPanel api_setup_panel,
            FileUploadPanel file_upload_panel,
            BatchEnvironmentSetupPanel batch_env_panel,
            BatchApiSetupPanel batch_api_setup,
            BatchFileUploadPanel batch_file,
            HealthEnvSetupPanel health_env_panel,
            HealthApiSetupPanel health_api_setup) {
        this.environment_setup_panel = environment_setup_panel;
        this.api_setup_panel = api_setup_panel;
        this.file_upload_panel = file_upload_panel;
        this.batch_env_panel = batch_env_panel;
        this.batch_api_setup = batch_api_setup;
        this.batch_file = batch_file;
        this.health_env_panel = health_env_panel;
        this.health_api_setup = health_api_setup;
    }

    /**
     * Launches a single API runner execution.
     */
    public void single_api_session() {
        single_executer = new SingleExecuter();
        single_executer.single_runner();
    }

    /**
     * Launches a batch suite execution
     */
    public void batch_api_session(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, String selected_file) {
        batch_executer = new BatchExecuter();
        batch_executer.batch_runner(selectedEnvironment, selectedBank, selectedApi, selectedModule, testDataFile, selected_file);
    }

    /**
     * Launches a health and sanity execution.
     */
    public void health_session(String selectedEnvironment, String selectedBank, String selectedApi) {
        single_executer = new SingleExecuter();
        single_executer.health_runner(selectedEnvironment, selectedBank, selectedApi);
    }

    /**
     * Maps an action name to its corresponding logic flow.
     */
    public void takeAction(String action, String currentTab) {
        switch (action) {
            case "execute":
                executeSession(currentTab);
                break;
            case "pause":
                if(currentSession.isPause()) {
                    currentSession.setPause(false);
                }else
                    currentSession.setPause(true);
                break;
            case "case_report":
                break;
            case "db_report":
                break;
            default:
        }
    }

/*
    public void exportReport(String currentTab) {
        Path resultsPath = Paths.get("target/allure-results");
        Path reportPath = Paths.get("target/allure-report");

        File resultsFolder = resultsPath.toFile();
        if (!resultsFolder.exists() || resultsFolder.listFiles() == null || resultsFolder.listFiles().length == 0) {
            System.err.println("Cannot generate report: " + resultsFolder.getAbsolutePath() + " is missing or empty.");
            return;
        }

        try {
            // Step 1: Generate Allure Report
            Configuration config = new ConfigurationBuilder().useDefault().build();
            ReportGenerator generator = new ReportGenerator(config);
            generator.generate(reportPath, Collections.singletonList(resultsPath));

            // Step 2: Start lightweight Java local HTTP server (port 0 auto-selects an open port)
            HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
            int port = server.getAddress().getPort();

            server.createContext("/", exchange -> {
                String path = exchange.getRequestURI().getPath();
                if (path.equals("/")) path = "/index.html";

                File file = new File(reportPath.toFile(), path);
                if (file.exists() && !file.isDirectory()) {
                    byte[] bytes = Files.readAllBytes(file.toPath());
                    exchange.sendResponseHeaders(200, bytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(bytes);
                    }
                } else {
                    exchange.sendResponseHeaders(404, -1);
                }
            });

            server.start();

            // Step 3: Open in browser using http:// protocol to bypass CORS
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:" + port + "/index.html"));
            }

        } catch (Exception e) {
            System.err.println("Failed to serve Allure report.");
            e.printStackTrace();
        }
    }
*/

/*
    public void exportReport(String currentTab) {
        Path resultsPath = Paths.get("target/allure-results");
        Path reportPath = Paths.get("target/allure-report");

        File resultsFolder = resultsPath.toFile();
        if (!resultsFolder.exists() || resultsFolder.listFiles() == null || resultsFolder.listFiles().length == 0) {
            System.err.println("Cannot generate report: " + resultsFolder.getAbsolutePath() + " is missing or empty.");
            return;
        }

        try {
            // Step 1: Generate Allure Report using pure Java
            Configuration config = new ConfigurationBuilder().useDefault().build();
            ReportGenerator generator = new ReportGenerator(config);
            generator.generate(reportPath, Collections.singletonList(resultsPath));

            // Step 2: Start local HTTP server with correct MIME types
            HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
            int port = server.getAddress().getPort();

            server.createContext("/", exchange -> {
                String path = exchange.getRequestURI().getPath();
                if (path.equals("/")) path = "/index.html";

                File file = new File(reportPath.toFile(), path);
                if (file.exists() && !file.isDirectory()) {
                    // Set MIME types so the browser parses JSON/JS correctly
                    String mimeType = "text/plain";
                    if (path.endsWith(".html")) mimeType = "text/html; charset=utf-8";
                    else if (path.endsWith(".js")) mimeType = "application/javascript";
                    else if (path.endsWith(".json")) mimeType = "application/json";
                    else if (path.endsWith(".css")) mimeType = "text/css";
                    else if (path.endsWith(".svg")) mimeType = "image/svg+xml";

                    exchange.getResponseHeaders().set("Content-Type", mimeType);

                    byte[] bytes = Files.readAllBytes(file.toPath());
                    exchange.sendResponseHeaders(200, bytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(bytes);
                    }
                } else {
                    exchange.sendResponseHeaders(404, -1);
                }
            });

            server.start();

            // Step 3: Open in browser
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:" + port + "/index.html"));
            }

        } catch (Exception e) {
            System.err.println("Failed to generate/serve Allure report.");
            e.printStackTrace();
        }
    }
*/


    public void executeSession(String currentTab) {
        switch (currentTab) {
            case "SINGLE_API":
                    CurrentSession.SESSION.set(
                            environment_setup_panel.getSelectedBank(),
                            environment_setup_panel.getSelectedEnvironment(),
                            api_setup_panel.getSelectedApi(),
                            api_setup_panel.getSelectedModule(),
                            file_upload_panel.getTest_data_file() != null && file_upload_panel.getTest_data_file().exists(),
                            file_upload_panel.getPayload_file() != null && file_upload_panel.getPayload_file().exists(),
                            file_upload_panel.getTest_data_file(),
                            file_upload_panel.getPayload_file()
                    );
                    file_upload_panel.clearUploadButton();
                    single_api_session();
                break;

            case "BATCH_SUITE":
                    batch_api_session(
                            batch_env_panel.getSelectedEnvironment(),
                            batch_env_panel.getSelectedBank(),
                            batch_api_setup.getSelectedApi(),
                            batch_api_setup.getSelectedModule(),
                            batch_file.getTest_data_file(),
                            batch_file.getSelectedModule()
                    );
                    batch_file.clearUploadButton();
                break;

            case "HEALTH_SANITY":
                    health_session(
                            health_env_panel.getSelectedEnvironment(),
                            health_env_panel.getSelectedBank(),
                            health_api_setup.getSelectedApi()
                    );
                break;

            default:
        }
    }
}

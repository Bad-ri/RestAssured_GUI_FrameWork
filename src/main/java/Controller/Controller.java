package Controller;

import Engine.BatchBuilder;
import Engine.SingleBuilder;
import Helper.EnumManager.UserSelection;
import View.Componants.ApiPanel.APiSetupPanel;
import View.Componants.ApiPanel.EnvironmentSetupPanel;
import View.Componants.ApiPanel.FileUploadPanel;
import View.Componants.BatchPanel.BatchApiSetupPanel;
import View.Componants.BatchPanel.BatchEnvironmentSetupPanel;
import View.Componants.BatchPanel.BatchFileUploadPanel;
import View.Componants.HealthPanel.HealthApiSetupPanel;
import View.Componants.HealthPanel.HealthEnvSetupPanel;

import java.io.File;

/**
 * Controller handles the orchestration between the GUI View panels
 * and the execution engines (SingleExecuter and BatchExecuter).
 */
public class Controller {
    private SingleBuilder single_executer;
    private BatchBuilder batch_executer;
    private final UserSelection userSelection = UserSelection.SESSION;

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
        single_executer = new SingleBuilder();
        single_executer.single_runner();
    }

    /**
     * Launches a batch suite execution
     */
    public void batch_api_session(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, String selected_file) {
        batch_executer = new BatchBuilder();
        batch_executer.batch_runner(selectedEnvironment, selectedBank, selectedApi, selectedModule, testDataFile, selected_file);
    }

    /**
     * Launches a health and sanity execution.
     */
    public void health_session(String selectedEnvironment, String selectedBank, String selectedApi) {
        single_executer = new SingleBuilder();
        single_executer.health_runner(selectedEnvironment, selectedBank, selectedApi);
    }


    /**
     * Stub for database report export.
     */
    public void exportDbReport(String currentTab) {
        System.out.println("Database report export is not implemented yet.");
    }

    /** Generates and opens the custom API execution report. */
    /*public void exportReport(String currentTab) {
        ReportManager reportManager = ReportManager.getInstance();
        if (!reportManager.hasResults()) {
            System.err.println("Cannot generate report: no API requests have been executed.");
            return;
        }

        try {
            Path reportPath = reportManager.generateReport();
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(reportPath.toUri());
            } else {
                System.out.println("Report generated at: " + reportPath);
            }
        } catch (IOException e) {
            System.err.println("Failed to generate report.");
            e.printStackTrace();
        }
    }*/


    public void executeSession(String currentTab) {
        switch (currentTab) {
            case "SINGLE_API":
                    UserSelection.SESSION.set(
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

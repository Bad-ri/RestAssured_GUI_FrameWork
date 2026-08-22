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

/**
 * Controller handles the orchestration between the GUI and the execution engines.
 */
public class Controller {
    private SingleBuilder single_executer = new SingleBuilder();
    private BatchBuilder batch_executer = new BatchBuilder();
    private final UserSelection userSelection = UserSelection.SESSION;

    private EnvironmentSetupPanel environment_setup_panel;
    private APiSetupPanel api_setup_panel;
    private FileUploadPanel file_upload_panel;
    private BatchEnvironmentSetupPanel batch_env_panel;
    private BatchApiSetupPanel batch_api_setup;
    private BatchFileUploadPanel batch_file;
    private HealthEnvSetupPanel health_env_panel;
    private HealthApiSetupPanel health_api_setup;

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
                    single_executer.single_runner();
                break;

            case "BATCH_SUITE":
                userSelection.set(
                        batch_env_panel.getSelectedBank(),
                        batch_env_panel.getSelectedEnvironment(),
                        batch_api_setup.getSelectedApi(),
                        batch_api_setup.getSelectedModule(),
                        batch_file.getData_file() != null && batch_file.getData_file().exists(),
                        false,
                        batch_file.getData_file(),
                        null
                );
                batch_file.clearUploadButton();
                batch_executer.batch_runner();
                break;

            case "HEALTH_SANITY":
                userSelection.set(
                        health_env_panel.getSelectedBank(),
                        health_env_panel.getSelectedEnvironment(),
                        health_api_setup.getSelectedApi(),
                        null,
                        false,
                        false,
                        null,
                        null
                );
                single_executer.health_runner();
                break;

            default:
        }
    }
}

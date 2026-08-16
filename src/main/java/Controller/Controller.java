package Controller;

import Engine.BatchExecuter;
import Engine.SingleExecuter;
import Helper.CurrentSession;

import java.io.File;

public class Controller {
    private SingleExecuter single_executer;
    private BatchExecuter batch_executer;
    private boolean isTestDataFile ;
    private boolean isTestCaseFile ;

    public void single_api_session(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, File payloadFile) {
        isTestDataFile =  false;
        isTestCaseFile =  false;
        if (testDataFile != null && testDataFile.exists())
            isTestDataFile = true;
        if (payloadFile != null && payloadFile.exists())
            isTestCaseFile = true;
        single_executer = new SingleExecuter();
        single_executer.single_runner(selectedEnvironment, selectedBank, selectedApi, selectedModule, testDataFile, payloadFile, isTestDataFile, isTestCaseFile);
    }
    public void batch_api_session(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, String selected_file) {
        batch_executer = new BatchExecuter();
        batch_executer.batch_runner(selectedEnvironment, selectedBank, selectedApi, selectedModule, testDataFile, selected_file);
    }
    public void health_session(String selectedEnvironment, String selectedBank, String selectedApi) {
        single_executer.health_runner(selectedEnvironment, selectedBank, selectedApi);
    }
}

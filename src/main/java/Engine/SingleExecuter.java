package Engine;

import Helper.FileManager.FileManager;
import Helper.SpecBuilder.SpecBuilderManager;
import Helper.TestCaseProvider.TestCasesManager;
import Service.Fund.Fund;

import java.io.File;
import java.util.List;
import java.util.Map;

public class SingleExecuter {
    public void single_runner(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, File payloadFile, boolean isTestDataFile, boolean isTestCaseFile) {
        FileManager file_manager = new FileManager(isTestDataFile,isTestCaseFile, testDataFile, payloadFile);
        executeTestCases(selectedEnvironment, selectedBank, selectedApi, selectedModule, isTestCaseFile);
    }
    public void health_runner(String selectedEnvironment, String selectedBank, String selectedApi) {
        //printSessionParameters(selectedEnvironment, selectedBank, selectedApi);
    }

    public void executeTestCases(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, boolean isTestCaseFile) {
        String result = "";
        SpecBuilderManager specBuilderManager = new SpecBuilderManager();
        Fund fund = new Fund();
        TestCasesManager manager = new TestCasesManager(selectedBank, selectedModule, selectedApi, isTestCaseFile);
        List<Map<String, Object>> testCases = manager.getTestCases();
        for (int i = 0; i < testCases.size(); i++) {
            Map<String, Object> tc = testCases.get(i);
            result = tc.get("req_data").toString();
            fund.singleRunner(specBuilderManager.getRequestSpec(selectedBank, selectedEnvironment, selectedApi, result, isTestCaseFile));
        }

    }


}

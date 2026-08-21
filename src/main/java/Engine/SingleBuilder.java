package Engine;

import Helper.EnumManager.CurrentSession;
import Helper.FileManager.FileManager;
import Helper.SpecBuilder.SpecBuilderManager;
import Helper.TestCaseProvider.TestCasesManager;
import Service.SingleRunner;

import java.util.List;
import java.util.Map;

public class SingleBuilder {
    CurrentSession currentSession = CurrentSession.SESSION;

    public void single_runner() {
        FileManager file_manager = new FileManager();
        executeTestCases();
    }
    public void health_runner(String selectedEnvironment, String selectedBank, String selectedApi) {
        //printSessionParameters(selectedEnvironment, selectedBank, selectedApi);
    }

    public void executeTestCases() {
        SpecBuilderManager specBuilderManager = new SpecBuilderManager();
        SingleRunner fund = new SingleRunner();
        TestCasesManager manager = new TestCasesManager();
        List<Map<String, Object>> testCases = manager.getTestCases();

        for (int i = 0; i < testCases.size(); i++) {
            while (currentSession.isPause()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            Map<String, Object> tc = testCases.get(i);
            String requestData = String.valueOf(tc.get("req_data"));
            fund.singleRunner(specBuilderManager.getRequestSpec(requestData));

        }
    }

}

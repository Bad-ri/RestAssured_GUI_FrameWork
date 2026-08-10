package Engine;

import Helper.ConfigProvider.ConfigManager;
import Helper.TestCaseProvider.TestCasesManager;

import java.io.File;
import java.util.List;
import java.util.Map;

public class SingleExecuter {
    public void single_runner(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, File payloadFile) {
        System.out.println("==================================================");
        System.out.println("               API SESSION PARAMETERS             ");
        System.out.println("==================================================");
        System.out.printf("%-20s : %s%n", "Environment", selectedEnvironment);
        System.out.printf("%-20s : %s%n", "Bank", selectedBank);
        System.out.printf("%-20s : %s%n", "API", selectedApi);
        System.out.printf("%-20s : %s%n", "Module", selectedModule);
        System.out.printf("%-20s : %s%n", "Test Data File", testDataFile != null ? testDataFile.getName() : "N/A");
        System.out.printf("%-20s : %s%n", "Payload File", payloadFile != null ? payloadFile.getName() : "N/A");
        System.out.println("==================================================");
        ConfigManager configManager = new ConfigManager(selectedBank, selectedEnvironment, selectedApi);
        System.out.println(configManager.getUrl());
        System.out.println("==================================================");
        TestCasesManager manager = new TestCasesManager(selectedBank, selectedModule, selectedApi);
        List<Map<String, Object>> testCases = manager.getTestCases();
        for (int i = 0; i < testCases.size(); i++) {
            Map<String, Object> tc = testCases.get(i);
            String id = tc.get("id").toString();
            String title = tc.get("title").toString();
            String debitRef = tc.get("debit_data_ref").toString();
            String creditRef = tc.get("credit_data_ref").toString();
            String expectedStatus = tc.get("expected_status").toString();
            String expectedCode = tc.get("expected_code").toString();
            System.out.println("Running [" + id + "] " + title);
            System.out.println("Debit Account Ref: " + debitRef);
            System.out.println("Credit Account Ref: " + creditRef);
            System.out.println("Expecting: " + expectedStatus + " - " + expectedCode);
            System.out.println("---------------------------------------");
        }
    }
    public void health_runner(String selectedEnvironment, String selectedBank, String selectedApi) {
        System.out.println("==================================================");
        System.out.println("               API SESSION PARAMETERS             ");
        System.out.println("==================================================");
        System.out.printf("%-20s : %s%n", "Environment", selectedEnvironment);
        System.out.printf("%-20s : %s%n", "Bank", selectedBank);
        System.out.printf("%-20s : %s%n", "API", selectedApi);
        System.out.println("==================================================");
    }
}

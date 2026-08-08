package Engine;

import Helper.ConfigProvider.ConfigManager;

import java.io.File;

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

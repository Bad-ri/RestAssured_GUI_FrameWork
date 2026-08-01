package Controller;

import java.io.File;

public class MainController {
    public void single_api_session(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, File payloadFile) {
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
    }
    public void batch_api_session(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, String selected_file) {
        System.out.println("==================================================");
        System.out.println("               API SESSION PARAMETERS             ");
        System.out.println("==================================================");
        System.out.printf("%-20s : %s%n", "Environment", selectedEnvironment);
        System.out.printf("%-20s : %s%n", "Bank", selectedBank);
        System.out.printf("%-20s : %s%n", "API", selectedApi);
        System.out.printf("%-20s : %s%n", "Module", selectedModule);
        System.out.printf("%-20s : %s%n", "Test Data File", testDataFile != null ? testDataFile.getName() : "N/A");
        System.out.printf("%-20s : %s%n", "Payload File", selected_file != null ? selected_file : "N/A");
        System.out.println("==================================================");
    }
    public void health_session(String selectedEnvironment, String selectedBank, String selectedApi) {
        System.out.println("==================================================");
        System.out.println("               API SESSION PARAMETERS             ");
        System.out.println("==================================================");
        System.out.printf("%-20s : %s%n", "Environment", selectedEnvironment);
        System.out.printf("%-20s : %s%n", "Bank", selectedBank);
        System.out.printf("%-20s : %s%n", "API", selectedApi);
        System.out.println("==================================================");
    }
}

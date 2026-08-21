package Engine;

import java.io.File;

public class BatchBuilder {
    public void batch_runner(String selectedEnvironment, String selectedBank, String selectedApi, String selectedModule, File testDataFile, String selected_file) {
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
}

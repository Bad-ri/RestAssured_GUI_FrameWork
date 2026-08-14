package Helper.FileManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileManager {
    public FileManager(boolean isTestDataFile, boolean isTestCaseFile, File testDataFile, File payloadFile) {
        this.deleteUploadedFiles();
        if(isTestDataFile && isTestCaseFile) {
            saveYamlToResources(testDataFile, "temp_data.yaml", isTestDataFile, false);
            saveYamlToResources(payloadFile, "temp_cases.yaml", false, isTestCaseFile);
        }else if(isTestDataFile && isTestCaseFile == false) {
            saveYamlToResources(testDataFile,"temp_data.yaml", isTestDataFile, isTestCaseFile);
        }else if(isTestDataFile == false && isTestCaseFile) {
            saveYamlToResources(payloadFile, "temp_cases.yaml", isTestDataFile, isTestCaseFile);
        }
    }
    private void saveYamlToResources(File sourceFile, String targetFileName, boolean isTestDataFile, boolean isTestCaseFile) {
        try  {
            Path targetDir = null;
            if (isTestDataFile) {
                targetDir = Path.of("src", "main", "resources", "TestData","Upload");
            }else if(isTestCaseFile)
                targetDir = Path.of("src", "main", "resources", "TestCase","Upload");
            // Step C: Copy the uploaded file to target location
            Path destination = targetDir.resolve(targetFileName);
            Files.copy(sourceFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUploadedFiles() {
        String casesPath = "src/main/resources/TestCase/Upload/temp_cases.yaml";
        String dataPath = "src/main/resources/TestData/Upload/temp_data.yaml";
        try {
            Files.deleteIfExists(Path.of(casesPath));
            Files.deleteIfExists(Path.of(dataPath));
        } catch (Exception e) {
            System.err.println("Could not delete file: " + e.getMessage());
        }

    }
}

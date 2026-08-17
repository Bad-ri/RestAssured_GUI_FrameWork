package Helper.FileManager;

import Helper.CurrentSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Save uploaded file & delete old files
 */
public class FileManager {
    private final CurrentSession session = CurrentSession.SESSION;
    private static final Path TEST_DATA_UPLOAD_DIRECTORY =
            Path.of("src", "main", "resources", "TestData", "Upload");
    private static final Path TEST_CASE_UPLOAD_DIRECTORY =
            Path.of("src", "main", "resources", "TestCase", "Upload");
    private static final Path TEST_DATA_FILE_PATH =
            TEST_DATA_UPLOAD_DIRECTORY.resolve("temp_data.yaml");
    private static final Path TEST_CASE_FILE_PATH =
            TEST_CASE_UPLOAD_DIRECTORY.resolve("temp_cases.yaml");

    public FileManager() {
        deleteUploadedFiles();
        if (session.hasTestDataFile()) {
            saveYamlToResources(session.getTestDataFile(), TEST_DATA_FILE_PATH);
        }
        if (session.hasTestCaseFile()) {
            saveYamlToResources(session.getTestCaseFile(), TEST_CASE_FILE_PATH);
        }
    }
    private void saveYamlToResources(File sourceFile, Path destination) {
        try {
            Files.copy(sourceFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteUploadedFiles() {
        try {
            Files.deleteIfExists(TEST_CASE_FILE_PATH);
            Files.deleteIfExists(TEST_DATA_FILE_PATH);
        } catch (Exception e) {
            System.err.println("Could not delete file: " + e.getMessage());
        }
    }
}

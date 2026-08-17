package Helper.TestCaseProvider;

import Helper.CurrentSession;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Loads the test cases file
 */
public class TestCasesManager {
    private final CurrentSession currentSession = CurrentSession.SESSION;
    private final List<Map<String, Object>> testCases;
    private String configPath;

    public TestCasesManager() {
        if (currentSession.hasTestCaseFile()) {
            configPath = "src/main/resources/TestCase/Upload/temp_cases.yaml";
        } else {
            setConfig_path();
        }
        testCases = loadTestCases();
    }
    public void setConfig_path() {
        configPath = "src/main/resources/TestCase/"
                + currentSession.getBank().toUpperCase() + "/"
                + currentSession.getModule().toUpperCase() + "/"
                + currentSession.getApi().toUpperCase() + ".YAML";
    }
    private List<Map<String, Object>> loadTestCases() {
        try (InputStream inputStream = new FileInputStream(configPath)) {
            return new Yaml().load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * return test case list
     */
    public List<Map<String, Object>> getTestCases() {
        return testCases;
    }
}
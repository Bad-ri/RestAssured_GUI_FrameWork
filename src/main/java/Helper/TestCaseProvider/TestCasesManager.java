package Helper.TestCaseProvider;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class TestCasesManager {

    private final List<Map<String, Object>> testCases;
    private Yaml yaml = new Yaml();
    private String config_path;

    public TestCasesManager(String bank, String module, String api, boolean isTestCaseFile) {
        if (isTestCaseFile) {
            this.config_path = "src/main/resources/TestCase/Upload/temp_cases.yaml";
        }else
            setConfig_path(bank.toUpperCase(), api.toUpperCase(), module.toUpperCase());
        try (InputStream inputStream = new FileInputStream(config_path)) {
            this.testCases = yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setConfig_path(String bank, String api, String module) {
        this.config_path = "src/main/resources/TestCase/" + bank + "/" + module + "/" + api + ".YAML";
    }

    public List<Map<String, Object>> getTestCases() {
        return testCases;
    }
}
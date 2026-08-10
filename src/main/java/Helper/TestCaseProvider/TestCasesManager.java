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
    private String bank;
    private String api;
    private String module;

    public TestCasesManager(String bank, String module, String api) {
        this.bank = bank.toUpperCase();
        this.api = api.toUpperCase();
        this.module = module.toUpperCase();
        setConfig_path(bank, api, module);
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
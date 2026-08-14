package Helper.DataProvider;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class TestDataManager {
    private Map<String, String> test_data;
    private Yaml yaml = new Yaml();
    private String config_path;

    public TestDataManager(String bank, String selected_data, boolean isTestDataFile) {
        if (isTestDataFile) {
            config_path = "src/main/resources/TestData/Upload/temp_data.yaml";
        }else
            setConfig_path(bank.toUpperCase());
        try (InputStream inputStream = new FileInputStream(config_path)) {
            Map<String, Map<String, String>> allData = yaml.load(inputStream);
            this.test_data = allData.get(selected_data);
        } catch (Exception e) {
            throw new RuntimeException("Error loading test data from: " + config_path, e);
        }
    }

    public void setConfig_path(String bank) {
        this.config_path = "src/main/resources/TestData/" + bank + "/" + bank + ".yaml";
    }

    public Map<String, String> getTestData() {
        return test_data;
    }
}
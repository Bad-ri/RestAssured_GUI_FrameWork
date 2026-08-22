package Helper.DataProvider;

import Helper.EnumManager.UserSelection;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Loads test data file
 */
public class TestDataManager {
    private final UserSelection userSelection = UserSelection.SESSION;
    private Map<String, String> testData;
    private String configPath;

    public TestDataManager(String selectedData) {
        if (userSelection.hasTestDataFile()) {
            configPath = "src/main/resources/TestData/Upload/temp_data.yaml";
        } else {
            setConfig_path();
        }
        loadTestData(selectedData);
    }

    public void setConfig_path() {
        String bank = userSelection.getBank().toUpperCase();
        configPath = "src/main/resources/TestData/" + bank + "/" + bank + ".yaml";
    }

    private void loadTestData(String selectedData) {
        try (InputStream inputStream = new FileInputStream(configPath)) {
            Map<String, Map<String, String>> allTestData = new Yaml().load(inputStream);
            Map<String, String> selectedTestData = allTestData.get(selectedData);
            if (selectedTestData == null) {
                System.out.println(selectedData + " not found in test case file");
                return;
            }
            testData = selectedTestData;
        } catch (Exception e) {
            throw new RuntimeException("Error loading test data from: " + configPath, e);
        }
    }
    /**
     * return selected test data list
     */
    public Map<String, String> getTestData() {
        return testData;
    }
}

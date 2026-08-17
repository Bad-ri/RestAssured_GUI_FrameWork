package Helper.ConfigProvider;

import Helper.CurrentSession;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Loads the YAML configuration then provides the API URL
 */

public class ConfigManager {
    private final CurrentSession currentSession = CurrentSession.SESSION;
    private final Map<String, Map<String, String>> environmentMap;
    private String configPath;

    public ConfigManager() {
        setConfig_path();
        environmentMap = loadConfiguration();
    }

    public void setConfig_path() {
        configPath = "src/main/resources/Configuration/"
                + currentSession.getEnvironment().toLowerCase() + "Config.YAML";
    }

    private Map<String, Map<String, String>> loadConfiguration() {
        try (InputStream inputStream = new FileInputStream(configPath)) {
            return new Yaml().load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * return API URL
     */
    public String getApiUrl() {
        Map<String, String> bankConfiguration = environmentMap.get(currentSession.getBank().toLowerCase());
        String api = currentSession.getApi().toLowerCase();
        String apiUrl = bankConfiguration.get(api + "_url");
        if (api.toLowerCase().equals("auth")) {
            return apiUrl.concat("||" + bankConfiguration.get("list_url"));
        }
        return apiUrl;
    }

}

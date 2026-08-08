package Helper.ConfigProvider;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ConfigManager {
    private Yaml yaml = new Yaml();
    private String config_path;
    private String bank;
    private String api;
    private Map<String, Map<String, String>> environment_map;

    public ConfigManager(String bank, String environment,  String api) {
        this.bank = bank.toLowerCase();
        this.api = api.toLowerCase() + "_url";
        setConfig_path(environment);
        try (InputStream inputStream = new FileInputStream(config_path)) {
            environment_map = yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setConfig_path(String environment) {
        this.config_path = "src/main/resources/Configuration/" + environment.toLowerCase() + "Config.YAML";
    }

    public String getUrl() {
        if(api.equals("auth_url")) {
            return environment_map.get(bank).get(api).concat("||"+environment_map.get(bank).get("list_url"));
        }
        return environment_map.get(bank).get(api);
    }
}

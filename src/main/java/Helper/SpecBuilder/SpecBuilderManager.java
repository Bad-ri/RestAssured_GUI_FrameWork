package Helper.SpecBuilder;

import Helper.ConfigProvider.ConfigManager;
import Helper.DataProvider.TestDataManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class SpecBuilderManager {
    private String bank;
    private boolean hasTestDataFile;
    public RequestSpecification getRequestSpec(String bank, String env, String api, String test_data,  boolean hasTestCaseFile) {
        this.bank = bank;
        this.hasTestDataFile = hasTestCaseFile;
        ConfigManager configManager = new ConfigManager(bank, env, api);
        return new RequestSpecBuilder()
                .setBaseUri(configManager.getUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .addHeader("Connection", "keep-alive")
                .setBody(getPayload(api, test_data))
                .setRelaxedHTTPSValidation()
                .build();
    }
    public String getPayload(String api, String test_data) {
        Path path = Path.of("src/main/resources/Payload/NBE/"+api.toLowerCase()+".json");
        String payload;
        try {
            payload = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return payloadBuilder(payload, test_data);
    }
    public String payloadBuilder(String requestBody, String test_data) {
        return requestBody
                .replace("${description}", getTestData(test_data))
                .replace("${title}", "titleya3sl");
    }
    public String getTestData(String test_data) {
        TestDataManager test_data_manager = new TestDataManager(bank,test_data,hasTestDataFile);
        Map<String, String> test_data_map = test_data_manager.getTestData();
        return test_data_map.get("description");
    }


    private String resolvePlaceholders(String template, Map<String, String> data) {
        String result = template;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            result = result.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return result;
    }
}

package Helper.SpecBuilder;

import Helper.ConfigProvider.ConfigManager;
import Helper.CurrentSession;
import Helper.DataProvider.TestDataManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Builds the REST Assured request specification for the current API req
 */
public class SpecBuilderManager {
    private final CurrentSession currentSession = CurrentSession.SESSION;

    public RequestSpecification getRequestSpec(String testDataName) {
        ConfigManager configManager = new ConfigManager();
        return new RequestSpecBuilder()
                .setBaseUri(configManager.getApiUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .addHeader("Connection", "keep-alive")
                .setBody(getPayload(testDataName))
                .setRelaxedHTTPSValidation()
                .build();
    }

    public String getPayload(String testDataName) {
        Path payloadPath = Path.of("src/main/resources/Payload/NBE/"
                + currentSession.getApi().toLowerCase() + ".json");
        try {
            String payload = Files.readString(payloadPath);
            return payloadBuilder(payload, testDataName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String payloadBuilder(String requestBody, String testDataName) {
        return requestBody
                .replace("${description}", getTestData(testDataName))
                .replace("${title}", "titleya3sl");
    }

    public String getTestData(String testDataName) {
        return new TestDataManager(testDataName).getTestData().get("description");
        }

}

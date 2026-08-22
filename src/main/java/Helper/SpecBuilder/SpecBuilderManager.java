package Helper.SpecBuilder;

import Helper.ConfigProvider.ConfigManager;
import Helper.EnumManager.ApiParameter;
import Helper.EnumManager.UserSelection;
import Helper.DataProvider.TestDataManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Builds the REST Assured request specification for the current API req
 */
public class SpecBuilderManager {
    private static final String PAYLOAD_DIRECTORY = "src/main/resources/Payload/NBE/";
    private final UserSelection userSelection = UserSelection.SESSION;
    private ApiParameter apiParameter = ApiParameter.PARAMETER;
    PrintStream logStream;
    {
        try {
            logStream = new PrintStream(new FileOutputStream(new File("target/api_execution.log"), true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public RequestSpecification getRequestSpec(String testDataName) {
        String payload = getPayload(testDataName);
        // we need seperate method to handle this
        apiParameter.setReq(JsonPath.from(payload).getString("description"));

        return new RequestSpecBuilder()
                .setBaseUri(new ConfigManager().getApiUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                //.addFilter(new ApiReportFilter())
                .addFilter(new RequestLoggingFilter(logStream))
                .addFilter(new ResponseLoggingFilter(logStream))
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .addHeader("Connection", "keep-alive")
                .setBody(payload)
                .setRelaxedHTTPSValidation()
                .build();
    }

    private String getPayload(String testDataName) {
        Path payloadPath = Path.of(PAYLOAD_DIRECTORY + userSelection.getApi().toLowerCase() + ".json");
        try {
            return payloadBuilder(Files.readString(payloadPath), testDataName);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read payload: " + payloadPath, e);
        }
    }

    private String payloadBuilder(String requestBody, String testDataName) {
        return requestBody
                .replace("${description}", getTestData(testDataName))
                .replace("${title}", "titleya3sl");
    }

    private String getTestData(String testDataName) {
        return new TestDataManager(testDataName).getTestData().get("description");
    }

}

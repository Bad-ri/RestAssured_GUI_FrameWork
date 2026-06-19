package core;

import io.restassured.RestAssured;

public class CoreApi {
    // ── CONFIGURATION ────────────────────────────────────────
    // The base address of the API we are testing
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    // List of tests: each row = { HTTP method, endpoint, expected status code }
    private static final String[][] TESTS = {
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=19", "200" },
            { "GET", "/?page=20", "200" }
    };


    private StringBuilder result = new StringBuilder();
    private int passedCount = 0;
    private int failedCount = 0;

    public StringBuilder apiAutomation(String bank, String env, String apiMethod) {

        RestAssured.baseURI = BASE_URL;

        if (bank.equalsIgnoreCase("NBE")) {
            result.append("###_under_Constraction_###");
        } else if (bank.equalsIgnoreCase("CBE")) {
            switch (apiMethod.toLowerCase()) {
                case "fund":
                    result = fund();
                    break;
                case "auth", "history":
                    result.append("###_under_Constraction_###");
                    break;
            }
        }
       return result;
    }
    public StringBuilder fund(){
        for (String[] test : TESTS) {

            String method    = test[0];                   // e.g. "GET"
            String endpoint  = test[1];                    // e.g. "/?page=19"
            int expectedCode = Integer.parseInt(test[2]);  // e.g. 200

            try {
                // Send the request and get the status code back
                int actualCode = RestAssured
                        .given()
                        .when()
                        .request(method, endpoint)
                        .getStatusCode();

                if (actualCode == expectedCode) {
                    passedCount++;
                    result.append("[PASS] ").append(method).append(" ").append(endpoint)
                            .append(" -> Got: ").append(actualCode).append("\n");
                } else {
                    failedCount++;
                    result.append("[FAIL] ").append(method).append(" ").append(endpoint)
                            .append(" -> Expected: ").append(expectedCode)
                            .append(" but Got: ").append(actualCode).append("\n");
                }

            } catch (Exception e) {
                failedCount++;
                result.append("[FAIL] ").append(method).append(" ").append(endpoint)
                        .append(" -> Error: ").append(e.getMessage()).append("\n");
            }
        }

        // Summary line
        int total = passedCount + failedCount;
        result.append("----------------------------------------\n");
        result.append("Total: ").append(total)
                .append(" | [»] Passed: ").append(passedCount)
                .append(" | [■] Failed: ").append(failedCount);
        return result;
    }
}

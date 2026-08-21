package Service;

import Helper.ReportManager.ReportManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class SingleRunner {

    public void singleRunner(RequestSpecification requestSpec) {
        /*FilterableRequestSpecification filterableSpec = (FilterableRequestSpecification) requestSpec;
        System.out.println("Body: " + filterableSpec.getBody());
        System.out.println("URI: " + filterableSpec.getURI());
        System.out.println("=============================");*/
        Response resp =
                RestAssured
                        .given()
                        .spec(requestSpec)
                        .log().all()
                        .when()
                        .post()
                        .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode = resp.getStatusCode();
        long duration = resp.getTime();
        String transactionId = "";

        ReportManager.recordExchange(transactionId, statusCode, duration);
    }
}
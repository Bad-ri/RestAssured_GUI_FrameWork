package Service;

import Helper.ResponseManager.ResponseManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class RequestSender {
    ResponseManager responseManager = new ResponseManager();

    public void sendRequest(RequestSpecification requestSpec) {
        try {
            Response response = sendPostRequest(requestSpec);
            responseManager.parseResponse(response.getBody().asString());
        } catch (Exception e) {
            responseManager.parseResponse(null);
        }
    }
    public Response sendPostRequest(RequestSpecification requestSpec) {
        return  RestAssured
                .given()
                .spec(requestSpec)
                .log().all()
                .when()
                .post()
                .then()
                .extract()
                .response();
    }
}
package Service.Fund;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class Fund {

    public void singleRunner(RequestSpecification requestSpec) {
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
    }
}
package Service.Fund;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
public class Fund {

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
    }
}
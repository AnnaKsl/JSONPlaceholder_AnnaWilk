package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseEndpoint {

    protected RequestSpecification given() {
        String BASE_URL = "https://jsonplaceholder.typicode.com/";
        return RestAssured.given().baseUri(BASE_URL).port(-1).log().all().contentType(ContentType.JSON);
    }

}

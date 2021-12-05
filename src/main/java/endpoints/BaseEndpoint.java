package endpoints;

import configuration.TestProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;


public class BaseEndpoint {

    protected TestProperties testProperties = ConfigFactory.create(TestProperties.class);

    protected RequestSpecification given() {
        return RestAssured.given().baseUri(testProperties.getBaseUrl()).port(-1).log().all().contentType(ContentType.JSON);
    }

}

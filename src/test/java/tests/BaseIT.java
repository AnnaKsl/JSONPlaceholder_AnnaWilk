package tests;

import com.google.gson.Gson;
import endpoints.PostEndpoint;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import steps.PostSteps;

public class BaseIT {
    protected PostEndpoint postEndpoint = new PostEndpoint();
    protected PostSteps postSteps = new PostSteps();
    protected Gson gson = new Gson();

    protected static void verifyResponseStatusCodeIsEqualToOK(Response response){
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "wrong status code");
    }

    protected static void verifyResponseStatusCodeIsEqualToCreated(Response response){
        Assertions.assertEquals(HttpStatus.SC_CREATED,response.getStatusCode(),"wrong status code");
    }
}

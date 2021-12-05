package tests;

import endpoints.PostEndpoint;
import steps.PostSteps;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

public class BaseIT {
    protected PostEndpoint postEndpoint = new PostEndpoint();
    protected PostSteps postSteps = new PostSteps();
    protected Gson gson = new Gson();

    public void verifyResponseStatusCode(Response response){
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "wrong status code");
    }
}

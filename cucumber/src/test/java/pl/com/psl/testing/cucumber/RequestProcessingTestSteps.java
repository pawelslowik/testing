package pl.com.psl.testing.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Created by psl on 03.03.17.
 */
@ContextConfiguration(classes = TestConfig.class)
public class RequestProcessingTestSteps {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestProcessingTestSteps.class);

    @Autowired
    private RequestProcessingService requestProcessingService;
    private String request;
    private String response;

    @Given("^a request '(.*)'")
    public void givenRequest(String request){
        LOGGER.info("Setting request={}", request);
        this.request = request;
    }

    @When("^the request is processed by RequestProcessingService$")
    public void whenProcessed(){
        response = requestProcessingService.processRequest(request);
        LOGGER.info("Got response={}", response);
    }

    @Then("^the response should be '(.+)'$")
    public void thenResponse(String expectedResponse){
        assertEquals(expectedResponse, response);
    }
}

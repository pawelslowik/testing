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
 * Created by psl on 04.03.17.
 */
@ContextConfiguration(classes = TestConfig.class)
public class MaxValueSelectionTestSteps {

    private static Logger LOGGER = LoggerFactory.getLogger(MaxValueSelectionTestSteps.class);

    @Autowired
    private MaxValueSelectionService maxValueSelectionService;
    private int value1;
    private int value2;
    private int maxValue;

    @Given("^value1 '(-?[0-9]+)'$")
    public void givenValue1(int value1){
        LOGGER.info("Setting value1={}", value1);
        this.value1 = value1;
    }

    @Given("^value2 '(-?[0-9]+)'$")
    public void givenValue2(int value2){
        LOGGER.info("Setting value2={}", value2);
        this.value2 = value2;
    }

    @When("^the max value is selected$")
    public void whenMaxSelected(){
        maxValue = maxValueSelectionService.selectMaxValue(value1, value2);
        LOGGER.info("Selected maxValue={}", maxValue);
    }

    @Then("^the result should be '(-?[0-9]+)'")
    public void thenResult(int expectedMaxValue){
        assertEquals(expectedMaxValue, maxValue);
    }
}

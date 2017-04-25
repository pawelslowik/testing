package pl.com.psl.testing.robotframework.keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import pl.com.psl.testng.robotframework.UppercaseService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by psl on 25.04.17.
 */
@RobotKeywords
public class UppercaseServiceKeywords {

    private UppercaseService service;
    private String request;
    private String actualResponse;

    public UppercaseServiceKeywords() {
        service = new UppercaseService();
    }

    @RobotKeyword
    public void givenRequest(String request) {
        this.request = request;
    }

    @RobotKeyword
    public void whenTheRequestIsProcessedByUppercaseService() {
        actualResponse = service.process(request);
    }

    @RobotKeyword
    public void thenTheResponseShouldBe(String expectedResponse) {
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

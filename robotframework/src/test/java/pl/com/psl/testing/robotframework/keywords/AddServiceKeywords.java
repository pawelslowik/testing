package pl.com.psl.testing.robotframework.keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import pl.com.psl.testng.robotframework.AddService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by psl on 25.04.17.
 */
@RobotKeywords
public class AddServiceKeywords {

    private AddService service;
    private List<BigDecimal> values;
    private BigDecimal actualResult;

    public AddServiceKeywords() {
        service = new AddService();
        values = new ArrayList<>();
    }

    @RobotKeyword
    public void givenValue(String value){
        values.add(new BigDecimal(value));
    }

    @RobotKeyword
    public void whenTheValuesAreProcessedByAddService(){
        actualResult = service.add(values);
        values = new ArrayList<>();
    }

    @RobotKeyword
    public void thenTheResultShouldBe(String expectedResult){
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}

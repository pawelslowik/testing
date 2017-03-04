package pl.com.psl.testing.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by psl on 03.03.17.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/", format = {"pretty", "html:target/cucumber-reports"}, monochrome = true)
public class TestRunner {
}

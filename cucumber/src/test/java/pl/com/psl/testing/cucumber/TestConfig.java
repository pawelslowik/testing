package pl.com.psl.testing.cucumber;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by psl on 03.03.17.
 */
@Configuration
@ComponentScan(basePackageClasses = TestConfig.class)
public class TestConfig {
}

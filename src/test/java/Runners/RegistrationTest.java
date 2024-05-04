package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\main\\resources\\features\\Registration.feature",
        glue = "stepDefinition",
        tags = "@Regression"
)
public class RegistrationTest {
}
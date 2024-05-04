package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\main\\resources\\features\\Login.feature",
        glue = "stepDefinition",
        tags = "@Regression"
        ,plugin = {"pretty"
        , "me.jvt.cucumber.report.PrettyReports:target/cucumber/LoginReport"}
)

public class LoginTest {
}

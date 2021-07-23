package stepDefinitions.runner.ui.HONDA;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(features = "@src/test/resources/HONDAprojectFailedReport/failed-UI-Report/failed.txt",
        glue = "HONDA.hondaStepDefinitions",
        plugin = {"pretty", "json:target/cucumber-rerun-reports/Cucumber.json"},
        dryRun = false,
        monochrome = true,
        strict = true,
        tags = {"@Smoke"})

public class HONDAUIReRunnerTest {
}

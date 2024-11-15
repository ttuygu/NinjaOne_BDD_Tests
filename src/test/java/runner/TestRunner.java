package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        tags = "@login and not @ignore",
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        plugin = {"pretty",
                "html:target/cucumber-jvm-reports/cucumber-jvm-report.html",
                "junit:target/cucumber-jvm-reports/cucumber.xml",
                "json:target/json-report/cucumber.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        publish = true,
        dryRun = false
)
public class TestRunner {

}
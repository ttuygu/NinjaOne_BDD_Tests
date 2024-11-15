package stepDefinitions;

import com.ninja.constants.TestEnvironment;
import com.ninja.core.CoreObjects;
import com.ninja.core.Driver;
import com.ninja.utilities.BrowserUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

public class Hooks extends CoreObjects {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    private static boolean setUpIsDone = true;

    @Before(order = 1)
    public void onStart() {
        if (setUpIsDone) {
            log.info("===============================================================");
            log.info("|     Demo is Starting...");
            log.info("|     URL: " + TestEnvironment.LOGIN_URL);
            log.info("|     Browser: " + TestEnvironment.BROWSER);
            log.info("|     Operating System : " + System.getProperty("os.name"));
            log.info("|     Tested by: Tugba Tuygu");
            log.info("===============================================================\n");
            setUpIsDone = false;
        }
    }

    @Before(order = 2)
    public void initializeDriver() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        initObjects();
    }

    @Before(order = 3)
    public void onTestStart(Scenario scenario) {
        log.info("===============================================================");
        log.info("|          Scenario Name: " + scenario.getName());
        log.info("===============================================================");
    }

    @AfterStep
    public void onTestFailure(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            BrowserUtils.takeScreenshot(scenario.getName());
        }
    }

    @After(order = 2, value = "not @hookless")
    public void tearDown() {
        Driver.closeDriver();
    }

    public void initObjects() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }
}

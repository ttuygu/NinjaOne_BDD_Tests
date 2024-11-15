package com.ninja.core;

import com.ninja.constants.TestEnvironment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver extends CoreObjects {
    private static String browser = "";

    private static final Logger log = LoggerFactory.getLogger(Driver.class);

    private Driver() {
        //Leave empty to prevent Driver class initialization
    }

    public synchronized static WebDriver getDriver() {      // only one thread can access the method at a time
        if (driver == null) {
            browser = System.getProperty("browser") != null ? System.getProperty("browser") : TestEnvironment.BROWSER;
        }
        return getDriver(browser);
    }

    public static WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                log.info("ChromeDriver initialized");
                break;
            case "chromeHeadless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                driver = new ChromeDriver(options);
                log.info("ChromeDriver initialized in headless mode");
                break;
            default:
                throw new RuntimeException("Illegal browser type!");
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null)
            driver.quit();
        driver = null;
        log.info("driver closed");
    }
}


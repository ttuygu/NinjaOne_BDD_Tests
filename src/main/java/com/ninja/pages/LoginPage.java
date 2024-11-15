package com.ninja.pages;

import com.ninja.core.CoreObjects;
import com.ninja.pojo.User;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ninja.constants.TestEnvironment.LOGIN_URL;

public class LoginPage extends CoreObjects {
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    private static final By emailInputField = By.xpath("//input[@name='email']");
    private static final By paswordInputField = By.xpath("//input[@name='password']");
    private static final By submit = By.xpath("//button[@type='submit']");
    private static final By warning = By.cssSelector(".alert");

    @FindBy(xpath = "//h2[.='Multi-Factor Authentication']")
    public WebElement MFA;

    public void openLoginPage() {
        driver.get(LOGIN_URL);
        log.info("Navigated to {}", LOGIN_URL);
    }

    public void login(DataTable credentials) {
        User user = new User(credentials);
        login(user.getEmail(), user.getPassword());
    }

    private void login(String email, String password) {
        driver.findElement(emailInputField).sendKeys(email);
        driver.findElement(paswordInputField).sendKeys(password);
        driver.findElement(submit).click();
        log.info("Login attempt with \"{}\"", email);
    }

    public String getWarningMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(warning));
        String text = driver.findElement(warning).getText();
        log.info("Warning message: {}", text);
        return text;
    }

    public void clickOnLink(String link) {
        log.info("Attempting to click on link: {}", link);
        switch (link) {
            case "Forgot your password?":
                driver.findElement(By.cssSelector("a[href='#/resetPassword'")).click();
                log.info("Clicked on 'Forgot your password?' link");
                break;
            case "Do not have an account?":
                driver.findElement(By.cssSelector("a[href='#/register'")).click();
                log.info("Clicked on 'Do not have an account?' link");
                break;
            default:
                log.error("Invalid link: {}", link);
                throw new IllegalArgumentException("Invalid link: " + link);
        }
    }

}

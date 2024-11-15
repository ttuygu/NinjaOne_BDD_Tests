package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import static com.ninja.constants.TestEnvironment.BASE_URL;
import static com.ninja.utilities.BrowserUtils.*;

public class Stepdefs  {

    @Given("the user is on the NinjaOne login page")
    public void theUserIsOnTheNinjaOneLoginPage() {
        loginPage.openLoginPage();
    }

    @When("user logs in to the website")
    public void user_login_to_the_website(DataTable credential) {
        loginPage.login(credential);
    }

    @Then("verify alert message is displayed")
    public void verifyAlertMessageIsDisplayed(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, loginPage.getWarningMessage());
    }

    @Then("verify user is navigated to {} page")
    public void verifyUserIsNavigatedToResetPasswordPage(String path) {
        System.out.println("Visited page: " + getCurrentUrl());
        Assertions.assertEquals(getCurrentUrl(), BASE_URL + path);
    }

    @When("user clicks on {string} link")
    public void userClicksOnLink(String link) {
        loginPage.clickOnLink(link);
    }

    @When("user navigates back to login page")
    public void userNavigatesBackToLoginPage() {
        driver.navigate().back();
    }

    @Then("verify Multi-Factor Authentication page is displayed")
    public void verifyMultiFactorAuthenticationPageIsDisplayed() {
        Assertions.assertTrue( loginPage.MFA.isDisplayed() );
    }

    // created for demo purposes
    @When("wait for {int} seconds")
    public void waitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

}

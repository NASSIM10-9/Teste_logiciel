package StepDefinitions;

import base.TestBase;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.logoutPage;
import com.aventstack.extentreports.Status;

public class LogoutStep {

    private logoutPage logoutPage;

    public LogoutStep() {
        // Initialize logoutPage with the WebDriver managed by TestBase
        this.logoutPage = new logoutPage(TestBase.getDriver());
    }

    @When("the user clicks on the logout button")
    public void userClicksLogoutButton() {
        try {
            logoutPage.clickLogoutButton();
            Hooks._scenario.log(Status.PASS, "The user clicks on the logout button");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "The user clicks on the logout button");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
            throw e;
        }
    }

    @Then("the user should see a logout success message")
    public void userSeesLogoutSuccessMessage() {
        try {
            String logoutMessage = logoutPage.getLogoutMessage();
            Assertions.assertTrue(logoutMessage.contains("You logged out of the secure area!"),
                    "Expected logout success message not found");
            Hooks._scenario.log(Status.PASS, "The user should see a logout success message: " + logoutMessage);
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "The user should see a logout success message");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }
}

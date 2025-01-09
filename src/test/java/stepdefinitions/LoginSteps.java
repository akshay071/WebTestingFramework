package stepdefinitions;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import utilities.LoginPage;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        driver.get("https://example.com/login");
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("password");
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the homepage")
    public void iShouldBeRedirectedToTheHomepage() {
        // Add assertion to verify the homepage URL or content
    }
}


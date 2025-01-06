package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.ConfigUtils;
import utilities.LocatorUtil;
import utilities.InvokeBrowser;

public class GoogleSteps {
    InvokeBrowser invokeBrowserInstance = new InvokeBrowser();
    LocatorUtil locatorUtilInstance = new LocatorUtil("src/test/resources/xpath/locators.xml");
    WebDriver driver;
    String baseUrl = ConfigUtils.getProperty("base.url");
    @Given("I open the browser")
    public void iOpenTheBrowser() {
        invokeBrowserInstance.setUp();
    }

    @When("I navigate to Google")
    public void iNavigateToGoogle() {
        // Get URL from config.properties
        invokeBrowserInstance.openURL(baseUrl);

    }

    @When("I enter text {string} in {string} on {string} screen")
    public void iSearchFor(String query, String objectId, String screen) throws Exception {
        // Get the XPath from the XML and send keys to the search field
        invokeBrowserInstance.sendKeys(query, objectId, screen);

    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
        invokeBrowserInstance.closeBrowser();
    }

    @Then("I wait for {int} seconds")
    public void waitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Then("I switch {string} frame on {string} screen")
    public void switchToFrame(String xpathFrame, String screen) {
        invokeBrowserInstance.switchToFrameByXPath(xpathFrame, screen);
    }

    @Then("I switch back to default content")
    public void switchToDefaultContent() {
        invokeBrowserInstance.switchToDefaultContent();
    }

    @When("I click {string} on {string} screen")
    public void closePopUp(String xpath, String screen) throws Exception {
        invokeBrowserInstance.click(xpath, screen);
    }

//    @Then("I hit emter on {string} screen")
//    public void hitEnter(String screen) {
//        invokeBrowserInstance.hitEnter(screen);
//    }

}

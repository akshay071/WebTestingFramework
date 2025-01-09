package utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;

public class InvokeBrowser {
    private WebDriver driver;
    private WebDriverWait wait;
    LocatorUtil locatorUtilInstance = new LocatorUtil("src/test/resources/xpath/locators.xml");

    // Constructor to initialize WebDriver and WebDriverWait
    public InvokeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void setUp() {
        WebDriverManager.chromedriver().browserVersion("latest").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Method to open a URL
    public void openURL(String url) {
        driver.get(url);
    }

    // Method to click on an element
    public void click(String objectId, String screen) {
        String xpath = locatorUtilInstance.getLocator(objectId, screen);
        driver.findElement(By.xpath(xpath)).click();

    }

    // Method to send keys to an input field
    public void sendKeys(String text, String locator, String screen) {
        String xpath = locatorUtilInstance.getLocator(locator, screen);
        driver.findElement(By.xpath(xpath)).sendKeys(text);
        driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
    }

    // Method to retrieve text from an element
    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Method to check if an element is displayed
    public boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Method to wait for a page title
    public boolean waitForTitle(String title) {
        return wait.until(ExpectedConditions.titleIs(title));
    }

    // Method to retrieve the current page URL
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    // Method to close the browser
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Method to scroll into view of an element
    public void scrollIntoView(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Method to take a screenshot
    public void takeScreenshot(String filePath) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        srcFile.renameTo(destFile);
    }

    public void switchToFrameByXPath(String frameXPath , String screen) {
        String locator = locatorUtilInstance.getLocator(frameXPath, screen);
        System.out.println("Switching to frame with XPath: " + locator);
        driver.switchTo().frame(driver.findElement(By.xpath(locator)));
    }

    // Method to switch back to the default content
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void hitEnter(String xpath, String screen) {
        String locator = locatorUtilInstance.getLocator(xpath, screen);
        driver.findElement(By.xpath(locator)).sendKeys(Keys.ENTER);
    }

}


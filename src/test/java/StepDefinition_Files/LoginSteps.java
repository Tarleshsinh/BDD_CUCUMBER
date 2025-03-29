package StepDefinition_Files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import io.cucumber.java.en.*;

public class LoginSteps {

    WebDriver driver;
    WebDriverWait wait;

    // ✅ Screenshot folder path
    String screenshotDirectory = "C:\\Users\\Admin\\eclipse-workspace\\BDD_CUCUMBER\\Screenshot";

    @Given("I open the login page")
    public void i_open_the_login_page() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) throws InterruptedException {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        userField.clear();
        userField.sendKeys(username);
    }

    @And("I enter password {string}")
    public void i_enter_password(String password) {
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passField.clear();
        passField.sendKeys(password);
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        loginBtn.click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String expectedMessage) throws InterruptedException, IOException {
        String actualMessage = "";
        WebElement targetElement = null;

        // 👀 Hard wait to observe UI before screenshot
        Thread.sleep(5000);

        try {
            targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.post-title")));
            actualMessage = targetElement.getText();
        } catch (Exception e) {
            targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
            actualMessage = targetElement.getText();
        }

        // ✨ Highlight the element before screenshot
        highlightElement(targetElement);

        // 📸 Take screenshot
        takeScreenshot("LoginValidation");

        // ✅ Assert the result
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected: " + expectedMessage + " but got: " + actualMessage);

        driver.quit();
    }

    // 📷 Save screenshot to your specified path
    public void takeScreenshot(String fileNamePrefix) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File screenshotFolder = new File(screenshotDirectory);
        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdirs();
        }

        String screenshotPath = screenshotDirectory + "\\" + fileNamePrefix + "_" + timestamp + ".png";
        File destFile = new File(screenshotPath);
        FileHandler.copy(srcFile, destFile);

        System.out.println("📸 Screenshot saved at: " + screenshotPath);
    }

    // 🎨 Highlight a web element with green border and dark green text
    public void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid green'; arguments[0].style.color='darkgreen';", element);
        } catch (Exception e) {
            System.out.println("❗ Could not highlight element: " + e.getMessage());
        }
    }
}

package StepDefinition_Files.Background_Keyword;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class Background_Keyword {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Selenium 4 syntax

    // This step is common across all scenarios due to the Background keyword
    @Given("I am on the Sauce Demo login page")
    public void i_am_on_the_sauce_demo_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    // Step to enter valid username and password
    @Given("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    // Step to enter invalid username and password
    @Given("I enter invalid username and password")
    public void i_enter_invalid_username_and_password() {
        driver.findElement(By.id("user-name")).sendKeys("invalid_user");
        driver.findElement(By.id("password")).sendKeys("invalid_pass");
    }

    // Step to enter empty username and password
    @Given("I enter empty username and password")
    public void i_enter_empty_username_and_password() {
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
    }

    // Step to click the login button
    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        driver.findElement(By.id("login-button")).click();
    }

    // Step to validate that homepage elements are displayed
    @Then("I should see the homepage elements")
    public void i_should_see_the_homepage_elements() {
        WebElement productsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_link")));
        assert productsSection.isDisplayed();
        assert cartIcon.isDisplayed();
    }

    // Step to validate that username is displayed
    @Then("I should see my username displayed")
    public void i_should_see_my_username_displayed() {
        WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header_user_name")));
        assert userName.isDisplayed();
    }

    // Step to check if error message is displayed for invalid credentials
    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        assert errorMessageElement.getText().equals(errorMessage);
    }

    // Step to close the browser
    public void closeBrowser() {
        driver.quit();
    }
}

package StepDefinition_Files;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Switch_Window {
    public WebDriver driver;

    @Given("I launch URL")
    public void i_launch_url() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/demo-site/");
        driver.manage().window().maximize();
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    @When("I validate presence of element")
    public void i_validate_presence_of_element() {
        driver.findElement(By.xpath("//*[@class='title  ']/child::span/child::strong[1]")).isDisplayed();
    }

    @Then("I click on frames")
    public void i_click_on_frames() {
        driver.findElement(By.xpath("//*[text()='Frames']")).click();
    }

    @Then("I validate display message")
    public void i_validate_display_message() {
        driver.findElement(By.xpath("//*[contains(text(),'Click below button to open a new tab in the same w')]")).isDisplayed();
    }

    @Then("I simulate opening multiple windows")
    public void i_simulate_opening_multiple_windows() throws InterruptedException {
        // Adding a hard wait before opening new tabs
        Thread.sleep(2000); // Wait for 2 seconds

        // Click the 'Click Here' button to open new windows
        driver.findElement(By.xpath("//div[@class='single_tab_div resp-tab-content resp-tab-content-active']/child::a[@class='button e.g. button_hilite button_pale small_button']")).click();
        Thread.sleep(2000); // Wait for 2 seconds before opening another tab

        driver.findElement(By.xpath("//div[@class='single_tab_div resp-tab-content resp-tab-content-active']/child::a[@class='button e.g. button_hilite button_pale small_button']")).click();
        Thread.sleep(2000); // Wait for 2 seconds before opening another tab

        driver.findElement(By.xpath("//div[@class='single_tab_div resp-tab-content resp-tab-content-active']/child::a[@class='button e.g. button_hilite button_pale small_button']")).click();
        Thread.sleep(2000); // Wait for 2 seconds before proceeding
    }

    @Then("I switch back to parent frames")
    public void i_switch_back_to_parent_frames() throws InterruptedException {
        String parentWindowHandle = driver.getWindowHandle(); // Get the parent window handle
        Set<String> allWindowHandles = driver.getWindowHandles(); // Get all window handles

        for (String handle : allWindowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle); // Switch to the child window
                Thread.sleep(2000); // Adding a wait before closing the window
                driver.close(); // Close the child window
            }
        }

        // Switch back to the parent window
        driver.switchTo().window(parentWindowHandle);
        Thread.sleep(2000); // Adding a wait before quitting the driver
        driver.quit(); // Close the browser
    }
}

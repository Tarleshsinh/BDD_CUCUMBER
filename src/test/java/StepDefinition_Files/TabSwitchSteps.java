package StepDefinition_Files;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TabSwitchSteps {

    WebDriver driver;
    String mainTab;
    List<String> tabs;

    @Given("I open the W3Schools main site")
    public void i_open_the_main_site() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/");
        mainTab = driver.getWindowHandle();
        tabs = new ArrayList<String>(driver.getWindowHandles());
    }

    @When("I simulate middle-click on the {string} links")
    public void i_simulate_middle_click_on_links(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Define the XPath for the "Try it Yourself" links
        String xpath = "//a[contains(@href, 'tryit.asp?filename=" + linkText + "')]";

        // Wait for the element to be clickable
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        // JavaScript to open the link in a new tab
        String script = "window.open(arguments[0], '_blank');";
        ((JavascriptExecutor) driver).executeScript(script, element.getAttribute("href"));
    }

    @Then("I switch to each new tab and validate elements")
    public void i_switch_to_tabs_and_validate_elements() {
        tabs = new ArrayList<>(driver.getWindowHandles()); // Refresh the tabs list

        // Loop through each tab and switch to it
        for (int i = 1; i < tabs.size(); i++) {
            driver.switchTo().window(tabs.get(i));  // Switch to the new tab

            // Example validation: Ensure the page title is not empty
            String title = driver.getTitle();
            System.out.println("Tab Title: " + title);
            assertTrue(title.length() > 0, "Page title is empty in tab " + i);

            // Example validation: Check if a specific element is present in the tab
            WebElement element = driver.findElement(By.tagName("body"));
            assertTrue(element.isDisplayed(), "The body element is not visible in tab " + i);
        }
    }

    @Then("I close all the tabs except the main one")
    public void i_close_all_tabs_except_main() {
        // Close all tabs except the main tab
        for (String handle : tabs) {
            if (!handle.equals(mainTab)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(mainTab);
        driver.quit(); // Close the main tab and quit the driver
    }
}

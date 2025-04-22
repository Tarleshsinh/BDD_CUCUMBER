package StepDefinition_Files;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Switch_Window {
    public WebDriver driver;

    @Given("I launch URL")
    public void i_launch_url() {
        WebDriverManager.chromedriver().setup();

        // Disable notifications and infobars
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);
        driver.get("https://www.globalsqa.com/demo-site/");

        // Handle pop-up alert or modal immediately after launch
        handleUnexpectedAlert();
        handlePossibleModalPopup();

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
        Thread.sleep(2000);

        // Click the 'Click Here' button 3 times
        for (int i = 0; i < 3; i++) {
            try {
                driver.findElement(By.xpath("//div[@class='single_tab_div resp-tab-content resp-tab-content-active']/child::a[contains(text(),'Click Here')]")).click();
                Thread.sleep(2000);
                handleUnexpectedAlert();
            } catch (Exception e) {
                System.out.println("Exception while opening window: " + e.getMessage());
            }
        }
    }

    @Then("I switch back to parent frames")
    public void i_switch_back_to_parent_frames() throws InterruptedException {
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String handle : allWindowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                Thread.sleep(2000);
                driver.close();
            }
        }

        driver.switchTo().window(parentWindowHandle);
        Thread.sleep(2000);
        driver.quit();
    }

    // Handle JavaScript alert popups (e.g. alert(), confirm())
    public void handleUnexpectedAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Unexpected alert found: " + alert.getText());
            alert.dismiss(); // or alert.accept()
        } catch (Exception e) {
            // No JS alert present
        }
    }

    // Handle HTML modal popups (e.g. newsletter, ads, GDPR modals)
    public void handlePossibleModalPopup() {
        try {
            // Try common modal close buttons
            WebElement closeBtn = driver.findElement(By.cssSelector(".popup-close, .close-button, .close, .elementor-popup-modal .dialog-close-button"));
            if (closeBtn.isDisplayed()) {
                closeBtn.click();
                System.out.println("Modal popup closed.");
            }
        } catch (Exception e) {
            // No modal found
        }
    }
}

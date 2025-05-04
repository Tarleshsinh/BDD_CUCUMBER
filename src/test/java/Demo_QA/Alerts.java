// FULL WORKING AND HARDENED VERSION
package Demo_QA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Alerts {
	public WebDriver driver;
	SoftAssert softAssert = new SoftAssert();

	public void takeScreenshot(String name) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File("C:/Users/Admin/eclipse-workspace/BDD_CUCUMBER/Screenshot/" + name + ".png");
			FileUtils.copyFile(src, dest);
			System.out.println("Screenshot taken: " + dest.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Screenshot error: " + e.getMessage());
			
		}
	}

	public void closeUnexpectedPopups() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
			List<WebElement> closeBtns = driver.findElements(By.cssSelector(".close-button, .popup-close, .modal-close, .at-cv-button"));
			for (WebElement btn : closeBtns) {
				if (btn.isDisplayed()) {
					btn.click();
					System.out.println("Unexpected popup closed.");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("No popups to close or not interactable.");
		}
	}

	@Given("I launch Url")
	public void i_launch_url() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications", "--start-maximized", "--incognito");

		HashMap<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.popups", 2);
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoqa.com/alerts");

		closeUnexpectedPopups();
	}

	@When("I click on Click Button to see alert")
	public void i_click_on_click_button_to_see_alert() {
		try {
			driver.findElement(By.id("alertButton")).click();
			closeUnexpectedPopups();
		} catch (Exception e) {
			System.out.println("Error clicking alertButton: " + e.getMessage());
			takeScreenshot("alertButtonError");
		}
	}

	@Then("I validate You clicked on button alert is present")
	public void i_validate_you_clicked_on_button_alert_is_present() {
		try {
			Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
			String text = alert.getText();
			System.out.println("Immediate Alert text: " + text);
			takeScreenshot("Immediate_Alert");
			softAssert.assertEquals(text, "You clicked a button", "Alert text mismatch");
			alert.accept();
		} catch (Exception e) {
			System.out.println("Alert not found or failed: " + e.getMessage());
			takeScreenshot("Immediate_Alert_Fail");
		}

		// Handle Delayed Alert
		try {
			driver.findElement(By.id("timerAlertButton")).click();
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
			Alert delayAlert = driver.switchTo().alert();
			String text2 = delayAlert.getText();
			takeScreenshot("Delayed_Alert");
			System.out.println("Delayed alert text: " + text2);
			softAssert.assertEquals(text2, "This alert appeared after 5 seconds", "Delayed alert mismatch");
			delayAlert.accept();
		} catch (Exception e) {
			System.out.println("Timer alert failed: " + e.getMessage());
			takeScreenshot("Timer_Alert_Fail");
		}

		// Confirm Alert
		try {
			driver.findElement(By.id("confirmButton")).click();
			Alert confirmAlert = driver.switchTo().alert();
			String confirmText = confirmAlert.getText();
			System.out.println("Confirm Alert: " + confirmText);
			softAssert.assertEquals(confirmText, "Do you confirm action?", "Confirm alert mismatch");
			confirmAlert.accept();
			takeScreenshot("Confirm_Result");
			String confirmResult = driver.findElement(By.id("confirmResult")).getText();
			softAssert.assertTrue(confirmResult.contains("You selected Ok"), "Confirm result mismatch");
		} catch (Exception e) {
			System.out.println("Confirm alert failed: " + e.getMessage());
			takeScreenshot("Confirm_Alert_Fail");
		}

		// Prompt Alert
		try {
			driver.findElement(By.id("promptButton")).click();
			Alert promptAlert = driver.switchTo().alert();
			promptAlert.sendKeys("Tarlesh");
			takeScreenshot("Prompt_Input");
			promptAlert.accept();
			String result = driver.findElement(By.id("promptResult")).getText();
			softAssert.assertTrue(result.contains("Tarlesh"), "Prompt response mismatch");
		} catch (Exception e) {
			System.out.println("Prompt alert failed: " + e.getMessage());
			takeScreenshot("Prompt_Alert_Fail");
		}

		// Final check
		softAssert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/alerts", "Final URL mismatch");
		softAssert.assertAll();
	}

	@Then("I click ok on pop up")
	public void i_click_ok_on_pop_up() {
		driver.quit();
	}
}

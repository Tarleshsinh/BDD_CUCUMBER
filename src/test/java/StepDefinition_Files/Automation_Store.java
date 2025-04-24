package StepDefinition_Files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Automation_Store {

	public WebDriver driver;

	@Given("I launch the applicaton")
	public void i_launch_the_applicaton() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://automationstore.com/pcf6-g3/#");

		// Navigate and perform actions
		driver.findElement(By.xpath("//*[@class='addthis_button_twitter socialLinks__link icon icon--twitter']")).click();
		driver.findElement(By.xpath("//i[@class='icon']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Create New Wish List']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Forgot your password?')]/parent::*")).click();
		Thread.sleep(2000); // Allow time for page to load

		// Validate Reset Password Button
		WebElement ResetPassword = driver.findElement(By.xpath("//input[@id='email']/following-sibling::input"));
		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(ResetPassword.isDisplayed(), "Reset Password Button is not displayed");
		softassert.assertTrue(ResetPassword.isEnabled(), "Reset Password Button is not enabled");
		System.out.println("âœ… Reset Password button is visible and enabled");

		try {
			softassert.assertAll(); // This triggers the assertions
		} catch (AssertionError e) {
			System.out.println("â�Œ Assertion Failed: " + e.getMessage());
			takeScreenshot("AssertionFailure");
			throw e; // re-throwing so it still fails in reports
		}

		// Take screenshot after "Forgot your password?" click
		takeScreenshot("ForgotPasswordPage");

		driver.quit();
	}

	@Then("I click on Twitter Logo")
	public void i_click_on_twitter_logo() {
		// Placeholder step
	}

	// Utility method for screenshot
	public void takeScreenshot(String fileName) throws IOException {
		String folderPath = "C:\\Users\\Admin\\eclipse-workspace\\BDD_CUCUMBER\\Screenshot";

		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File(folderPath + "\\" + fileName + "_" + timeStamp + ".png");

		FileUtils.copyFile(screenshot, destination);

		System.out.println("ðŸ“¸ Screenshot saved at: " + destination.getAbsolutePath());
		
		
        	}
}

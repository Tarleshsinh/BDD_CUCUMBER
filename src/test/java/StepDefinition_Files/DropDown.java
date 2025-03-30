package StepDefinition_Files;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDown {

	public WebDriver driver;
	WebDriverWait wait;

	@Given("when i launch chrome browser")
	public void when_i_launch_chrome_browser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@Then("I enter URL")
	public void i_enter_url() {
		driver.get("https://www.amazon.com");
	}

	@Then("enter credential in Amazon serach bar {string}")
	public void enter_credential_in_amazon_serach_bar(String searchTerm) {
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
		search.clear();
		search.sendKeys("recliners for");

		String text;
		do {
			search.sendKeys(Keys.ARROW_DOWN);
			text = search.getAttribute("value");
			System.out.println("Current suggestion: " + text);
			if (text.equalsIgnoreCase("recliners for kids")) {
				search.sendKeys(Keys.ENTER);
				break;
			}
		} while (!text.isEmpty());
	}

	@Then("Recliner for kids gets displayed")
	public void recliner_for_kids_gets_displayed() {
		WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(text(),'recliners for kids')]")));
		Assert.assertTrue("Result not displayed!", result.isDisplayed());
		driver.quit();
	}

	@Given("I launch chrome and launch google url")
	public void i_launch_chrome_and_launch_google_url() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get("https://www.google.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@When("I enter switzerland Package")
	public void i_enter_switzerland_package() throws InterruptedException {
		WebElement inputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='APjFqb']")));
		inputBox.sendKeys("switzerlandholiday Package");
		Thread.sleep(2000); // Optional, in case suggestions take a second

		List<WebElement> list = driver.findElements(By.xpath("//li[@class='sbct']//div[@class='eIPGRd']//div[1]/span"));
		System.out.println("Auto-suggestions count: " + list.size());

		for (WebElement switzerlandpkg : list) {
			if (switzerlandpkg.getText().contains("switzerland holiday packages from south africa")) {
				switzerlandpkg.click();
				break;
			}
		}
	}

	@Then("switzerland package option shows in google")
	public void switzerland_package_option_shows_in_google() {
		WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h3[contains(text(),'Switzerland Holidays & Tours from South Africa')]")));
		Assert.assertTrue("Google result not found!", result.isDisplayed());
		driver.quit();
	}
}

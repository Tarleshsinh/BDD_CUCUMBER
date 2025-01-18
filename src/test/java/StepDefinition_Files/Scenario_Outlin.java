package StepDefinition_Files;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario_Outlin {

	public WebDriver driver;
	
	@Given("user is already on Login Page")
	public void user_is_already_on_login_page() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\Desktop\\Sarthak Selenium\\Browsers drivers\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.rediff.com");
		driver.findElement(By.xpath("//a[@class='signin']")).click();
	}

	@Then("user enters {string} and {string}")
	public void user_enters_username_and_password(String username, String password) {
	    driver.findElement(By.xpath("//input[@id='login1']")).sendKeys(username);
	    driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	    driver.findElement(By.xpath("//input[@class='signinbtn']")).click();
	    driver.close();
	}

	@Then("user clicks on login button")
	public void user_clicks_on_login_button() {
	   
	}

	

}
	



	
   

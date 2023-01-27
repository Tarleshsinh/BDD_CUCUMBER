package StepDefinition_Files;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BackgroundKeyword {
	public WebDriver driver;
	
	@Given("I launch Browsre and URL")
	public void i_launch_browsre_and_url() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.rediff.com");
		
	}

	@Then("I enter {string} and {string}")
	public void i_enter_and(String Username, String Password) {
		
		
		driver = new ChromeDriver();
		driver.findElement(By.xpath("//a[@class='signin']")).click();
		driver.findElement(By.xpath("//input[@id='login1']")).clear();
		driver.findElement(By.xpath("//input[@id='login1']")).sendKeys(Username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Password);
		driver.findElement(By.xpath("")).click();
		
		
	}
	



	
   
}
package StepDefinition_Files;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CSS_Value_validate_Colour {

	public WebDriver driver;
	
	@Given("I launch URl")
	public void i_launch_u_rl() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.blazedemo.com");
		

	}

	@Given("I validate the color of text")
	public void i_take_rgb_value() {
		
		WebElement B=driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		String bckgroundcolour=B.getCssValue("background-color");
		System.out.println(bckgroundcolour);
		
		String Hexavalue=Color.fromString(bckgroundcolour).asHex();
		System.out.println(Hexavalue);
		
		  if (Hexavalue.equals("#006dcc"))
		  System.out.println("Test for colour validation is PASSED"); else
		  System.out.println("Test for colour validation is failed");
		 
		  driver.close();
	
	}
	

}

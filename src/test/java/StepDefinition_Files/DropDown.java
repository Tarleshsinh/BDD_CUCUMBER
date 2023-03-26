package StepDefinition_Files;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDown {

	public WebDriver driver;
	@Given("when i launch chrome browser")
	public void when_i_launch_chrome_browser() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver(co);
	    driver.manage().window().maximize();
	    driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	    
	}

	@Then("I enter URL")
	public void i_enter_url() {
		driver.get("https://www.amazon.com");
	}

	@Then("enter credential in Amazon serach bar {string}")
	public void enter_credential_in_amazon_serach_bar(String string) {
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.clear();
		search.sendKeys("recliners for");
		
		String text;
		do {
			search.sendKeys(Keys.ARROW_DOWN);
			text= search.getAttribute("value");
			System.out.println(text + "This is the get attirubte text ");
			if(text.equals("recliners for kids")) {
				search.sendKeys(Keys.ENTER);
				break;
			}
		}
		while(!text.isEmpty());
	}

	@Then("Recliner for kids gets displayed")
	public void recliner_for_kids_gets_displayed() {
		
		driver.findElement(By.xpath("//span[contains(text(),'recliners for kids')]")).isDisplayed();
		driver.quit();
	    
	}
	
	@Given("I launch chrome and launch google url")
	public void i_launch_chrome_and_launch_google_url() {
		  WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    driver.manage().window().maximize();
		    driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		    driver.get("https://www.google.com");
	}

	@When("I enter switzerland Package")
	public void i_enter_switzerland_package() throws Exception {
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("switzerlandholiday Package");
		Thread.sleep(2000);

		List<WebElement>list= driver.findElements(By.xpath("//li[@class='sbct']//div[@class='eIPGRd']//div[1]/span"));
			System.out.println("Size of Autosuggestion list------" + list.size());
			
			for(WebElement switzerlandpkg:list)
			{
				if (switzerlandpkg.getText().contains ("switzerland holiday packages from south africa")) {
					switzerlandpkg.click();
					break;
				}
			}
		
	}

	@Then("switzerland package option shows in google")
	public void switzerland_package_option_shows_in_google() {
	    
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Switzerland Holidays & Tours from South Africa')]")).isDisplayed());
		driver.quit();
	}
	
	
	
}

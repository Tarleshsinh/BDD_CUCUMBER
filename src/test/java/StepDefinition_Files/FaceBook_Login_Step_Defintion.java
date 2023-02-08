package StepDefinition_Files;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class FaceBook_Login_Step_Defintion {
public static WebDriver driver;

	
	@Given("I launch Browser")
	public static void i_launch_browser() {
	    WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver();
	    driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
	   
	    

	    driver.get("https://www.facebook.com");
	    driver.manage().window().maximize();
	    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("lamin_bada@yahoo.com");
	    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Bansda10");
	    driver.findElement(By.xpath("//button[@name='login']")).click();
	     //driver.switchTo().alert().accept();
	    //alert.dismiss();
	    String parentwindow = driver.getWindowHandle();
	    System.out.println(parentwindow);
	    driver.switchTo().window(parentwindow);
	    WebElement search =driver.findElement(By.xpath("//input[@class='x1i10hfl xggy1nq x1s07b3s x1kdt53j x1yc453h xhb22t3 xb5gni xcj1dhv x2s2ed0 xq33zhf xjyslct xjbqb8w x6umtig x1b1mbwd xaqea5y xav7gou xnwf7zb x40j3uw x1s7lred x15gyhx8 x9f619 xzsf02u xdl72j9 x1iyjqo2 xs83m0k xjb2p0i x6prxxf xeuugli x1a2a7pz x1n2onr6 x15h3p50 xm7lytj x1sxyh0 xdvlbce xurb0ha x1vqgdyp xo6swyp x1ad04t7 x1glnyev x1ix68h3 x19gujb8']"));
	    
	    
	    search.clear();
	    search.sendKeys("Piyush Patel");
	    
	   
	    
	}

	@When("enter user id and password")
	public static void enter_user_id_and_password() {
	    
	}

	@Then("I reach on home page")
	public static void i_reach_on_home_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I click on logout")
	public static void i_click_on_logout() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I log out and come to face book log in page")
	public static void i_log_out_and_come_to_face_book_log_in_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
}

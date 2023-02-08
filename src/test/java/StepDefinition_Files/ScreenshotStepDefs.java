package StepDefinition_Files;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotStepDefs {
  private WebDriver driver;

  public ScreenshotStepDefs(WebDriver driver) {
    this.driver = driver;
  }

  @After
  public void afterScenario(Scenario scenario) {
    if (scenario.isFailed()) {
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot, "image/png", "screenshot");
    }
  }
}



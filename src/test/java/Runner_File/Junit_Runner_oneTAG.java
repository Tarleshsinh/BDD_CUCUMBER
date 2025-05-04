package Runner_File;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    dryRun=false,
    monochrome=true,
    features= {"src/test/resources/Cucumber","src/test/resources/Cucumber/Demo_QA/Alerts_Frame_Windows"},
    glue= {"StepDefinition_Files","Demo_QA"},
    plugin={"pretty","html:target/site/cucumber-html", "json:target/cucumber1.json"},
    tags= "@Log_In_Steps",
    publish = true // Activates publishing of Cucumber reports
)

public class Junit_Runner_oneTAG {
    
}

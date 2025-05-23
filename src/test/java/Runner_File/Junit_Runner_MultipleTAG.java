package Runner_File;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	
		dryRun=false,
		monochrome=true,
		features= {"src/test/resources/Cucumber"},
		glue= {"StepDefinition_Files"},
		plugin={"pretty","html:target/site/cucumber-html",
				"json:target/cucumber1.json"},
		tags= "@Log_In_Steps"
		
		)


public class Junit_Runner_MultipleTAG {

	
	
}

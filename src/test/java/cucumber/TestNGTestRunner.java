package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		  features="src/test/java/cucumber/SubmitOrder.feature",  // Run only this feature
		  glue="akkiFrameworks.stepDefinitions",
		  monochrome=true,
		  tags="@Regression and @SpecificScenario",
		  plugin={"html:target/cucumber.html"}
		)
		public class TestNGTestRunner extends AbstractTestNGCucumberTests {
											 						
		}
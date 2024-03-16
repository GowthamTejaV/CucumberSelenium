package Org.testrunner;

import org.testng.annotations.DataProvider;

import Org.Util.Constants;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "@target/rerunFailedTests.txt" }, glue = { "Org.StepDefs",
		"ApplicationHooks" }, plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "timeline:target/test-output/",
				"rerun:target/rerunFailedTests.txt",
				"junit:target/cucumber-results.xml" })
public class ReRunFailedTests extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		// TODO Auto-generated method stub
		return super.scenarios();
	}
}

package Org.testrunner;


import org.testng.annotations.DataProvider;

import Org.Util.Constants;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { Constants.FEATURUE_FILE_PATH }, glue = { "Org.StepDefs", "ApplicationHooks" }, plugin = {
		"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"timeline:target/test-output/",
		"rerun:target/rerunFailedTests.txt",
		"junit:target/cucumber-results.xml"}, monochrome = true,tags ="@ignore",dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override()
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
	
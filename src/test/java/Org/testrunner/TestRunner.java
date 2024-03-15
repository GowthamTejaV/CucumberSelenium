package Org.testrunner;


import org.testng.annotations.DataProvider;

import Org.Util.Constants;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { Constants.FEATURUE_FILE_PATH }, glue = { "Org.StepDefs", "ApplicationHooks" }, plugin = {
		"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"timeline:target/test-output/",
		"junit:target/cucumber-results.xml"}, monochrome = true,tags ="@tag",dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override()
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
	
package Org.StepDefs;

import java.util.Properties;

import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.primitives.Bytes;

import Org.Util.ConfigReader;
import Org.Util.Constants;
import Org.Util.ExcelUtil;
import Org.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	private DriverFactory driverfactory = null;
	private Properties prop = null;
	private ConfigReader config = null;
	private WebDriver driver = null;

	@Before(order = 0)
	public void getProperties() {
		config = new ConfigReader();
		prop = config.read_properties(Constants.GLOBAL_PROPERTY_FILE_PATH);
	}

	@Before(order = 1)
	public void launch_browser() {
		driverfactory = new DriverFactory();
		String browser = prop.getProperty("browser");
		System.out.println("INITIALIZE BROWSER ::" + browser);
		driver = driverfactory.init_driver(browser);
	}

//	@Before(order = 2)
//	public void skipScenarios(Scenario scenario) {
//		Object[][] scenarios = ExcelUtil.readScenarios(Constants.TEST_DATA_FILE);
//		for (Object[] scenarioData : scenarios) {
//			String scenarioName = (String) scenarioData[0];
//			String tags = (String) scenarioData[1];
//			if (scenario.getName().equals(scenarioName) && tags.contains("@Skip")) {
//				scenario.log("Skipping scenario: " + scenarioName);
//				Assume.assumeTrue(false); // Skip scenario
//			}
//		}
//	}

	@AfterStep(order = 0)
	public void afterStep(Scenario scenario) {
		byte sourcePath[] = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(sourcePath, "image/png", "DEMO_SCENARIO");
	}

	@After(order = 0)
	public void quiteBroswer() {
		 driver.quit();
	}

}

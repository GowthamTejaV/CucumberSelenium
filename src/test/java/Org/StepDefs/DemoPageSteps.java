package Org.StepDefs;

import org.testng.Assert;

import Org.Util.ElementUtil;
import Org.factory.DriverFactory;
import io.cucumber.java.en.Given;

public class DemoPageSteps {
	
	@Given("Navigate to the flipcart page")
	public void navigate_to_the_flipcart_page() {
		//DriverFactory.getDriver().navigate().to("https://www.flipkart.com/");
	   ElementUtil.navigateToURL("https://www.flipkart.com/", false);
	   
	   try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

@Given("Demo on failed step")
public void demo_on_failed_step() {
	Assert.assertTrue(true);
}
}

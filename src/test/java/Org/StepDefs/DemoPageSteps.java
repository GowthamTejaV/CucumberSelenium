package Org.StepDefs;

import Org.Util.ElementUtil;
import io.cucumber.java.en.Given;

public class DemoPageSteps {
	
	@Given("Navigate to the flipcart page")
	public void navigate_to_the_flipcart_page() {
	   ElementUtil.navigateToURL("https://www.flipkart.com/", false);
	}

}

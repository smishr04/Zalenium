package StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class DemoStepDefiniton extends GlobalStepDefinition{
	
	
	@Given("^I launch the \"([^\"]*)\" page$")
	public void i_launch_the_page(String arg1) throws Throwable {
	    
		InvokeDriver(arg1);
	}

	@Then("^I verify the page$")
	public void i_verify_the_page() throws Throwable {
	   
	}

}

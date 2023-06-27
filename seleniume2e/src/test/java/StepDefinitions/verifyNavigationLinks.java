package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.AuthenticationWelcomePage;
import Pages.Dashboard;
import helper.ActionHelper;
import helper.DriverRunner;
import io.cucumber.java.en.Then;

public class verifyNavigationLinks {

	
	DriverRunner driverRunnerObject=DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	AuthenticationWelcomePage aw= new AuthenticationWelcomePage();
	
	ActionHelper helper=new ActionHelper();
	Dashboard dash=new Dashboard();
	
	@Then("verify that the link opened is {string}")
	public void verify_that_the_link_opened_is(String string) {
	    dash.verifyCurrentURLContains(string);
	}
	@Then("page title contains {string}")
	public void page_title_contains(String string) {
	    helper.verifyPageTitleContains(string);
	}
	@Then("On navigating to RoundPe Docs the link opened is {string}")
	public void on_navigating_to_round_pe_docs_the_link_opened_is(String string) {
	    helper.clickWithText("Go to Round");
	    helper.waitForElementToLoad(By.xpath("//*[contains(text(),'Introduction to RoundPe')]"), 7);
	    dash.verifyCurrentURLContains(string);
	}
	@Then("page has text {string}")
	public void page_has_text(String string) {
	   helper.verifyText(string, string);
	}	
	@Then("I am Signed Out")
	public void i_am_signed_out() {
	    aw.verifyOnLogInAuthenticationPage();
	}
}

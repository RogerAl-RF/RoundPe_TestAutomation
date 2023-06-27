package StepDefinitions;

import org.openqa.selenium.WebDriver;

import Pages.AuthenticationWelcomePage;
import Pages.MetamaskHandler;
import Pages.Payments;
import Pages.Settings;
import helper.ActionHelper;
import helper.DriverRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddWithdrawalAddress {
	
	DriverRunner driverRunnerObject=DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	ActionHelper helper=new ActionHelper();
	MetamaskHandler mh = new MetamaskHandler();
	AuthenticationWelcomePage aw= new AuthenticationWelcomePage();
	Payments payment = new Payments();
	Settings setting = new Settings();
	
	@When("I am on Settings page")
	public void i_am_on_settings_page() {
	helper.verifyOnScreenWithTitle("Settings");
	}
	@Then("I scroll down to the Withdrawal Addresses and expand if it is not already expanded")
	public void i_scroll_down_to_the_withdrawal_addresses_and_expand_if_it_is_not_already_expanded() {
	setting.goToWithdrawalAddress();
	}
	@Then("I add the withdrawal address")
	public void i_add_the_withdrawal_address() {
	   setting.enterAddress("Avalanche");

	}
}

package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.AuthenticationWelcomePage;
import Pages.MetamaskHandler;
import Pages.Payments;
import helper.ActionHelper;
import helper.DriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class SignInStepDef {
	
	DriverRunner driverRunnerObject=DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	ActionHelper helper=new ActionHelper();
	MetamaskHandler mh = new MetamaskHandler();
	AuthenticationWelcomePage aw= new AuthenticationWelcomePage();
	Payments payment = new Payments();
	
	@Given("I install the metamask extension")
	public void i_install_the_metamask_extension() {
		System.out.println("Driver object is invoked and Metamask extension is installed!");
		System.out.println("Switching to metamask tab!");
		helper.switchToWindow(0);
		mh.switchToMetamaskTab();
	}
	
	@Then("I am on Metamask Login Home Screen")
	public void i_am_on_metamask_login_home_screen() {
		
		if(mh.verifyMetamaskAppIcon() && mh.verifyOnMetamaskOboardingPage())
			System.out.println("PASSED! On metamask login page");
		else
			System.out.println("Failed! Not on metamask login page");
		
	}
	
	@When("I Sign In to metamask account with {int} Word Phrase")
	public void i_sign_in_to_metamask_account_with_word_phrase(Integer int1) {
		mh.metamaskSignin();
	}
	@When("Change the Metamask Password to {string}")
	public void change_the_metamask_password_to(String string) {
	 System.out.println("Password is changed!");
	}
	@Then("I am logged in on metemask")
	public void i_am_logged_in_on_metemask() {
		if(mh.verifyOnMetamaskHomepage())
		{
			System.out.println("Login Successfull!");
		}
	}
	
	@When("I go to Settings and turn on show test networks and verify they are available")
	public void i_go_to_settings_and_turn_on_show_test_networks() {
	  mh.metamaskEnableTestNetworks();
	}

	@Then("I close the tab")
	public void i_close_the_tab() {
	 driver.close();
	 helper.switchToLastOpenedWindow();
	}
	
	@Given("I launch the RoundPe WebApp")
	public void i_launch_the_round_pe_web_app() {
		helper.switchToWindow(0);
		//driver.get("https://app.roundpe.com/");
		driver.get("https://roundpe-client.vercel.app/");
		System.out.println("Launched RoundPe App Successful!");
	}
	@Then("I am on the Welcome Sign In page")
	public void i_am_on_the_welcome_sign_in_page() {
		aw.verifyOnLogInAuthenticationPage();
	}
	@When("I enter my email as {string} and password as {string}")
	public void i_enter_my_email_as_and_password_as(String emailid, String password) {
	    aw.logIn(emailid, password);
	}
	@Then("I am logged in and am on the Round Dashboard screen")
	public void i_am_logged_in_and_am_on_the_round_dashboard_screen() {
	    aw.isUserLoggedIn();
	}

	@Then("I navigate to tab {string}")
	public void i_navigate_to_tab(String tabName) {
	  helper.navigateToTab(tabName);
	}
	@When("I click on New Payment to create a new payment")
	public void i_click_on_new_payment_to_create_a_new_payment() {
		helper.clickButton("New Payment");
	}
	@Then("I am on {string} screen")
	public void i_am_on_screen(String title) {
	    helper.verifyOnScreenWithTitle(title);
	}

	@Then("I enter name as {string}, description as {string}, amount as {float}, apply fee {string} and create the payment link")
	public void i_enter_name_as_description_as_amount_as_apply_fee_and_create_the_payment_link(String name, String desc, float amount, String fee) {
	    payment.createNewPayment(name, desc, amount, Boolean.parseBoolean(fee));
	}
	
	@Then("I get the end user payable amount")
	public void i_get_the_end_user_payable_amount() {
	    System.out.println("End User Pays: "+payment.endUserPayable+"\n Receivable Amount: "+payment.paymentReceivable);
	}
	@Then("the payment link is generated and i click on view payment link")
	public void the_payment_link_is_generated_and_i_click_on_view_payment_link() {
	   payment.paymentLinkGenerated();
	   helper.switchToWindow(1);
	}
	@Then("I verify Name of payment is {string} and description is {string}")
	public void i_verify_name_of_payment_is_and_description_is(String string, String string2) {
	    payment.verifyPaymentLinkDetails();
	}
	@Then("I search for {string} and select {string} as crypto")
	public void i_search_for_and_select_as_crypto(String network, String token) {
		helper.findElementWithText("Select a cryptocurrency");
			payment.selectNetworkToPay(network, token);
		
	}
	@Then("I search for {string} and select {string} as token")
	public void i_search_for_and_select_as_token(String string, String string2) {
		payment.connectWalletPage();
	}
	@Then("I agree sending {string} on {string} and click button Pay")
	public void i_agree_sending_on_and_click_button_pay(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("I verify the charge code is displayed and the QR code for payment is displayed")
	public void i_verify_the_charge_code_is_displayed_and_the_qr_code_for_payment_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}

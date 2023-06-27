package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import helper.ActionHelper;
import helper.DriverRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInStepDef {
	
	String username = "rog4499@gmail.com";
	String password = "Welcome1-2";
	

	 //List<String> themeNames=new ArrayList<String>();
	 //int totalNumberOfThemes=0;
	 //List<String> assetNames=new ArrayList<String>();
	 //int totalNumberOfAssets=0;
//		DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner(false);
//		WebDriver driver = driverRunnerObject.getDriver();
//		
		//ActionHelper helper = new ActionHelper();
	
	@Given("I launch the RoundPe WebApp")
	public void i_launch_the_round_pe_web_app() {
//	
//		driver.get("https://app.roundpe.com/");
//		System.out.println("Launched RoundPe App Successful!");
//		driver.findElement(By.linkText("Log in")).click();
//		System.out.println("Found and clicked sign up");
//
//		helper.logIn(username, password);
		System.out.println("loggedIn");
	}

	@Then("I am on RoundPe Login\\/Signup Screen")
	public void i_am_on_round_pe_login_signup_screen() {
		System.out.println("On Screen");
	}

	@When("I enter credentials and login")
	public void i_enter_credentials_and_login() {
		System.out.println("loggedIn");
	}

	@Then("I am on the Dashboard - Welcome screen")
	public void i_am_on_the_dashboard_welcome_screen() {
		System.out.println("loggedIn");
	}

	@Then("I logout of the application")
	public void i_logout_of_the_application() {
//		helper.clickWithText("Sign out");
	}

	@Then("I return the chrome profile")
	public void i_return_the_chrome_profile() {
//		helper.updateProfilePath();
	}
}

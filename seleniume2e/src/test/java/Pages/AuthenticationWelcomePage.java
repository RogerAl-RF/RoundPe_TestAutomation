package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.ActionHelper;
import helper.DriverRunner;

public class AuthenticationWelcomePage {
	ActionHelper helper = new ActionHelper();
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();

	public void signUp() {
		try {
			driver.findElement(By.xpath("//p[contains(text(),'Sign up')]"));
			System.out.println("Element with text sign up found on UI. PASSED!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Element with text sign up not found, not on sign up UI. FAILED!");
		}

	}

	public void logIn(String email, String password) {

		helper.sendKeysById("username", email);
		helper.sendKeysById("password", password);
//		driver.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys(username);
//		driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys(password);
		helper.clickButton("Continue");
//		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();

		//verifyText("Your Details", "Your Details");

	}
	
	public boolean isUserLoggedIn()
	{
		if(helper.isElementPresent(By.xpath("//title[text()='RoundPe']")) 
				&& helper.isElementPresent(By.xpath("//*[contains(text(),'Hey')]"))
				&& helper.isElementPresent(By.xpath("//*[contains(@class,'SignoutButton')]")))
		{
			System.out.println("Hey, Signout Button and RoundPe present in page title, PASSED!");
			return true;
		}
		
		else {
			System.out.println("Not on dashboard, not logged in, Failure");
				return false;
			}
	}
	public boolean verifyOnLogInAuthenticationPage()
	{
		if(helper.isElementPresent(By.id("prompt-logo-center"))
				&& helper.isElementPresent(By.xpath("//*[contains(text(),'Welcome')]"))
				&& helper.isElementPresent(By.xpath("//title[contains(text(),'Log in')]")))
		{
			System.out.println("Logo, Welcome and Log in present in page title, PASSED!");
			return true;
		}
		else {
		System.out.println("Not on LogIn Authentication Page, Failure");
			return false;
		}
	}
	
	public boolean verifyOnSignUpAuthenticationPage()
	{
		if(helper.isElementPresent(By.id("prompt-logo-center"))
				&& helper.isElementPresent(By.xpath("//*[contains(text(),'Welcome')]"))
				&& helper.isElementPresent(By.xpath("//title[contains(text(),'Sign up')]")))
		{
			System.out.println("Logo, Welcome and Sign up present in page title, PASSED!");
			return true;
		}
		else {
		System.out.println("Not on SignUp Authentication Page, Failure");
			return false;
		}
	}
}

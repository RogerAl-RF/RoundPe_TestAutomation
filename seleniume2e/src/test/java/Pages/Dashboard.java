package Pages;

import org.openqa.selenium.WebDriver;

import helper.ActionHelper;
import helper.DriverRunner;

public class Dashboard {
	ActionHelper helper = new ActionHelper();
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	
	public void verifyCurrentURLContains(String expectedURL)
	{
		String url=	driver.getCurrentUrl();
		if(url.equalsIgnoreCase(expectedURL))
		{
			System.out.println("PASSED, Current URL is as expected: "+url);
		}
		else
			System.out.println("FAILED, Current URL is not the same as expected. \nExpected: "+expectedURL+"\nCurrentURl: "+url);
	}

	
}

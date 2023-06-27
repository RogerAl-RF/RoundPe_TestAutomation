package Pages;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.ActionHelper;
import helper.DriverRunner;

public class MetamaskHandler {
	ActionHelper helper = new ActionHelper();
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();

	static String secretKey= "pulse napkin heart confirm patient click unaware slow duck page animal swamp";
	
	public void metamaskSignin()
	{
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'onboarding-import-wallet')]"), 10);
		helper.clickWithDataTestId("onboarding-import-wallet");
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'metametrics-i-agree')]"), 10);
		helper.clickWithDataTestId("metametrics-i-agree");
		helper.isElementPresent(By.xpath("//*[contains(@data-testid,'import-srp')]"));
		
		int i=0;
		for(String phrase:secretKey.split(" ")) {
		driver.findElement(By.id("import-srp__srp-word-"+i)).sendKeys(phrase);
		i++;
		}
		
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'import-srp-confirm')]"), 10);
		helper.clickWithDataTestId("import-srp-confirm");
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'create-password-new')]"), 10);
		helper.SendKeysWithDataTestId("create-password-new","Welcome1-2");
		helper.SendKeysWithDataTestId("create-password-confirm","Welcome1-2");
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'create-password-terms')]"), 10);
		helper.clickWithDataTestId("create-password-terms");
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'create-password-import')]"), 10);
		helper.clickWithDataTestId("create-password-import");
		
		try {
			Thread.sleep(Duration.ofSeconds(4));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'onboarding-complete-done')]"), 10);
		helper.clickWithJS(driver.findElement(By.xpath("//*[contains(@data-testid,'onboarding-complete-done')]")));
		//helper.clickWithDataTestId("onboarding-complete-done");
	
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'pin-extension-next')]"), 10);
		helper.clickWithDataTestId("pin-extension-next");
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'pin-extension-done')]"), 10);
		helper.clickWithDataTestId("pin-extension-done");
		
	}
		
	public boolean verifyOnMetamaskHomepage()
	{
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'app-header-logo')]"), 10);
		
		if(helper.isElementPresent(By.xpath("//*[contains(@data-testid,'selected-account-click')]"))
				&& helper.isElementPresent(By.xpath("//*[contains(@data-testid,'network-display')]")))
				{
				System.out.println("Both Account Info and Networks are displayed on screen!\n On Metamask Home page");
				return true;
				}
		
		else {
			System.out.println("Couldn't locate Metamask homePage elements, FAILURE!");
			return false;
		}
	}
	
	
	
	public void metamaskEnableTestNetworks()
	{
		helper.clickWithDataTestId("account-menu-icon");
		helper.clickWithText("Settings");
		driver.findElement(By.id("search-settings")).sendKeys("Show test networks");
		driver.findElement(By.id("menu-section_/settings/advanced#show-testnets")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Show test networks')]/../following-sibling::div//input/..")).click();
		helper.clickWithDataTestId("app-header-logo"); 
		helper.clickWithDataTestId("network-display"); 
		
		helper.waitForElementToLoad(By.xpath("//*[@class='network-dropdown-list']/li[2]"),4);
		
		if(helper.verifyXPathReturnsMoreThanOneElement("//*[@class='network-dropdown-list']/li"))
		{
			System.out.println("Test Passed! Test Networks enabled on metamask");
		}
		else
			System.out.println("Failure! Test networks not enabled!");
	}

	
	public void switchToMetamaskTab()
	{
		if(driver.getTitle().equalsIgnoreCase("New Tab"))
		{	
			System.out.println(driver.getTitle());
			System.out.println("Currently on new chrome tab, hence switching the window!");
			
			try {
			helper.switchToWindow(1);
			System.out.println(driver.getTitle());
			}
			catch(Exception e)
			{
				try {
					Thread.sleep(Duration.ofSeconds(10));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Waiting for 10 seconds to wait for tab to load");
				helper.switchToWindow(1);
				System.out.println(driver.getTitle());
			}
		}
		
		else
		{
			System.out.print("Already on metamask tab!");
		}
	}

	
	public boolean verifyMetamaskAppIcon() {
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'app-header-logo')]"), 40);
		if (helper.isElementPresent(By.xpath("//*[contains(@data-testid,'app-header-logo')]"))) {
			System.out.println("On Metamask Page, PASS");
			return true;
		} else {
			System.out.println("Not present on Metamask page! FAIL");
			return false;
		}
	}

	public boolean verifyOnMetamaskOboardingPage(){
		helper.waitForElementToLoad(By.xpath("//*[contains(@data-testid,'onboarding-welcome')]"), 40);
		if(helper.isElementPresent(By.xpath("//*[contains(@data-testid,'onboarding-welcome')]")))
		{
		System.out.println("On Metamask onboarding page, continue to login, PASS");
		return true;
		}
		else {
			System.out.println("Not on Metamask onboarding page! FAIL");
			return false;
		}
	
	}
	
	
	
}

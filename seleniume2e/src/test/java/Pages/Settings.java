package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helper.ActionHelper;
import helper.DriverRunner;

public class Settings {
	ActionHelper helper = new ActionHelper();
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	String AvalancheAddress="0x653033Edc105110953E4bCa205EcDeFD4b971b59";
	
	public void goToWithdrawalAddress()
	{
		WebElement element = driver.findElement(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'Withdrawal Addresses')]"));
		helper.scrollToElement(element);
		toggleSettingToExpand("Withdrawal Addresses");
		System.out.println("Expanded Withdrawal Addresses!");
	}
	
	public void enterAddress(String tokenName)
	{
		WebElement withdrawalElement = driver.findElement(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'Withdrawal Addresses')]"));
		
		deleteAllAddressIfExists(withdrawalElement);
		WebElement withdrawal_tokenElement= withdrawalElement.findElement(By.xpath("./../../../following-sibling::div/descendant::*[contains(text(),'"+tokenName+"')]"));
		WebElement input_tokenElement=withdrawal_tokenElement.findElement(By.xpath("./../following-sibling::input"));
		
		input_tokenElement.sendKeys(AvalancheAddress);
		helper.clickWithJS(input_tokenElement.findElement(By.xpath("./following-sibling::div/descendant::*[contains(text(),'Save')]")));
		verifyToast("Changes saved");
		
	}
	
	public void deleteAllAddressIfExists(WebElement withdrawalElement)
	{
		List<WebElement> editableElements=driver.findElements(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'Withdrawal Addresses')]"
				+ "/../../../following-sibling::div/descendant::div[contains(@class,'Withdrawals_icon')]"));
		
		if(editableElements.size()>0)
		{
			for(int i=1; i<editableElements.size(); i+=2)
			{
			try {
				helper.clickWithJS(editableElements.get(1));
				helper.waitForElementToLoad(By.xpath("//button[contains(text(),'Delete')]"),7);
				helper.verifyOnScreenWithTitle("Delete withdrawal");
				helper.clickWithJS(driver.findElement(By.xpath("//button[contains(text(),'Delete')]")));
//				Thread.sleep(Duration.ofSeconds(7));
				
				helper.waitForElementToLoad(By.xpath("//div[@id='toast']/descendant::*[contains(text(),'Changes saved')]"),8);
				helper.waitForElementToBeInvisible(driver.findElement(By.xpath("//div[@id='toast']/descendant::*[contains(text(),'Changes saved')]")), 8);
				
				editableElements=driver.findElements(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'Withdrawal Addresses')]"
						+ "/../../../following-sibling::div/descendant::div[contains(@class,'Withdrawals_icon')]"));
			}
			
			catch(Exception e)
			{
				System.out.println("Couldn't click the editable element to delete index: " + i);
				e.printStackTrace();
			}
			
			}
		}
		
		else
		{
			System.out.println("No existing addresses to be deleted!");
		}
	}
	
	public void verifyToast(String text)
	{
		try {
			helper.waitForElementToLoad(By.xpath("//div[@id='toast']/descendant::*[contains(text(),'"+text+"')]"), 7);
			driver.findElement(By.xpath("//div[@id='toast']/descendant::*[contains(text(),'"+text+"')]"));
			System.out.println("PASSED, Found toast with message "+text);
		}
		catch(Exception e)
		{
			System.out.println("FAILED, Couldn't find toast message: "+ text);
			e.printStackTrace();
		}
	}

	public void toggleSettingToExpand(String nameOfSetting)
	{
		try {
		driver.findElement(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'"+nameOfSetting+"')]"));
		System.out.println("Found the setting, now expanding "+nameOfSetting);
		
		List<WebElement> divs=driver.findElements(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'"+nameOfSetting+"')]/../../../../child::div"));
		System.out.println(divs.size());
		
		if(divs.size()>2)
		{
			System.out.println("List is collapsed, Expanding now!");
		//	driver.findElement(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'"+nameOfSetting+"')]/../../following-sibling::div")).click();
			helper.clickWithJS(driver.findElement(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'"+nameOfSetting+"')]/../../following-sibling::div")));
		}
		else
		{
			System.out.println("Setting "+nameOfSetting+" is already expanded!");
		}
		
	}
		catch(Exception e)
		{
		System.out.println("Couldn't find Setting please check "+nameOfSetting +"\n"+e);
		e.printStackTrace();
		}
		
		
		//*[contains(@class,'Typography_title') and contains(text(),'Withdrawal Addresses')]/../../..
	}
}

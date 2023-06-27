package Pages;

import java.time.Duration;
import java.util.List;

import javax.swing.JList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helper.ActionHelper;
import helper.DriverRunner;

public class Payments {
	ActionHelper helper = new ActionHelper();
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	public static String endUserPayable="";
	public static String paymentReceivable=""; //default amount
	public static String paymentName="";
	public static String paymentLink="";
	public static String paymentDesc="";
	
	public static String chargeCode="";
	public static boolean applyFee;
	
	public static String totalAmountInCrypto="";
	public static String switchNetworkMetamaskName="";
	
	public void createNewPayment(String pName, String description, float amount, boolean applyFeeB)
	{
		paymentName=pName+DriverRunner.TS;
		paymentDesc=description+DriverRunner.TS;
		
		applyFee=applyFeeB;
		
		helper.findInputWithText("Name",paymentName);
		helper.findInputWithText("Description",paymentDesc);
		helper.findInputWithText("Amount",Float.toString(amount));
		
		
		if(applyFee) {
			
			driver.findElement(By.id("fee-input")).click();
			
			helper.waitForElementToLoad(By.xpath("//*[contains(text(),'will pay')]/b"), 5);

			helper.verifyWithTextInBold("will pay");
			
			
//			try {
//				Thread.sleep(Duration.ofMillis(5000));
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			List<WebElement> amountList = driver.findElements(By.xpath("//*[contains(text(),'will pay')]/b")); 
			
			if(helper.isElementPresent(By.xpath("//*[contains(text(),'Waiting')]")))
			{
				amountList=driver.findElements(By.xpath("//*[contains(text(),'will pay')]/b")); 
			}
			
			if(amountList.size()==2)
			{
				helper.sleep(5);
				//helper.waitForAmount(amountList.get(0),10);
				//giving stale retrieving with js
				System.out.println("Found user Payable amount!");
				endUserPayable=helper.jsTextRetrieve(amountList.get(0));
				paymentReceivable=helper.jsTextRetrieve(amountList.get(1));
			}
			else
				System.out.println("FAILED, Amount Payable did not retrieve 2 elements after clicking apply fee!");
		}
		
		else
			paymentReceivable="$"+amount;	
		
		
		driver.findElement(By.xpath("//button[contains(text(),'Create Payment')]")).click();
	}
	
	public void paymentLinkGenerated()
	{
		helper.verifyOnScreenWithTitle("Payment Link");
		helper.verifyOnScreenWithTitle("Link generated");
		
		try {
			paymentLink=driver.findElement(By.xpath("//*[contains(@class,'SharePayment_hostedURL')]/p")).getText();
			System.out.println("PaymentLink: "+paymentLink);
		}
		catch(Exception e)
		{
			System.out.println("FAILED, Couldn't find payment url! Payment Link not generated!");
		}
		
		chargeCode=driver.findElement(By.xpath("//*[contains(@class,'SharePayment_chargeCode')]")).getText();
		System.out.println("ChargeCode: "+chargeCode);
		helper.clickWithText("View Payment Link");
	}
	
	
	public void verifyPaymentLinkDetails()
	{
		
		helper.verifyOnScreenWithTitle("Payment");
		helper.isElementPresent(helper.findElementWithText(paymentName),true);
		helper.isElementPresent(helper.findElementWithText(paymentDesc), true);
		helper.isElementPresent(helper.findElementWithText(chargeCode), true);
		
		
		
		if(driver.getCurrentUrl().equalsIgnoreCase(paymentLink))
		{
			System.out.println("Payment Link is same! Passed!");
		}
		else
			System.out.println("Payment Link is not same as provided earlier! Failure!");
		
		if(applyFee) {
			
			helper.verifyOnScreenWithTitle(endUserPayable); //verifying total where fee is applied
			
			if(helper.findElementWithText("View Price Breakdown")==null)
				System.out.println("Failure! Price breakdown not available");
			else
			{
				System.out.println("Passed! Price breakdown available as expected!");
				helper.clickWithText("View Price Breakdown");
				
				String reqAmount = driver.findElement(By.xpath("//*[contains(text(),'Amount Requested')]/following-sibling::p")).getText();
				String transactionFee = driver.findElement(By.xpath("//*[contains(text(),'Transaction Fee')]/following-sibling::p")).getText();
				
				if(reqAmount.equals(paymentReceivable))
					System.out.println("Passed! Amount Requested is equal to payment receivable! " +reqAmount );
				else
					System.out.println("Failed! Amount Requested is not equal to payment receivable! "+ reqAmount+" "+paymentReceivable);
				
				System.out.println("Applied Transaction Fee is: "+transactionFee);
					
			}
		}
		else
			helper.verifyOnScreenWithTitle(paymentReceivable); //verifying total where fee is not applied
		//verify Payment Name and charge code
	}
	
	public void selectNetworkToPay(String networkName, String tokenName)
	{
		driver.findElement(By.xpath("//*[@class='Dropdown_card__VV7l-']/descendant::input[1]")).sendKeys(networkName);
		
		if(networkName.equalsIgnoreCase("ether"))
			driver.findElement(By.xpath("(//input[@type='radio' and contains(@value,'ether')])[2]")).click();
		else
		driver.findElement(By.xpath("//input[@type='radio' and contains(@value,'"+networkName.toLowerCase()+"')]")).click();
		
		driver.findElement(By.xpath("//*[@class='Dropdown_card__VV7l-']/descendant::input[1]")).sendKeys(tokenName);
		driver.findElement(By.xpath("//input[@type='radio' and contains(@value,'"+tokenName.toLowerCase()+"')]")).click();
		
		helper.waitForElementToLoad(By.xpath("//label[@for='agree-checkbox']"), 8);
		driver.findElement(By.xpath("//input[@id='agree-checkbox']")).click();
		
		helper.waitForElementToLoad(By.xpath("//button[contains(text(),'Pay with')]"), 8);
		helper.clickButton("Pay with");
		
	}
	
	public void connectWalletPage()
	{
		helper.verifyText("QR code", "connectWallet");
		totalAmountInCrypto=driver.findElement(By.xpath("//*[contains(text(),'Total Amount')]/following-sibling::p")).getText();
		System.out.println("Total amount to be paid in crypto: "+ totalAmountInCrypto);
		String totalAmountOnConnectWalletPage=driver.findElement(By.xpath("(//*[contains(text(),'Total')])[1]/../following-sibling::*")).getText();
		String chargeCodeOnConnectWalletPage= driver.findElement(By.xpath("//*[contains(text(),'Payment')]/following-sibling::*")).getText();
		
		if(endUserPayable.equals(totalAmountOnConnectWalletPage))
		{
			System.out.println("PASSED, endUserPayable: "+endUserPayable+" is equal to total amount on final page: "+totalAmountOnConnectWalletPage);
		}
		else
			System.out.println("FAILED,  endUserPayable: "+endUserPayable+" is not equal to total amount on final page: "+totalAmountOnConnectWalletPage);
		
		if(chargeCode.equals(chargeCodeOnConnectWalletPage))
		{
			System.out.println("PASSED, chargeCode: "+chargeCode+" is equal to total amount on final page: "+chargeCodeOnConnectWalletPage);
		}
		else
			System.out.println("FAILED, chargeCode: "+chargeCode+" is not equal to total amount on final page: "+chargeCodeOnConnectWalletPage);
		
			
		try {
			WebElement connectWallet= driver.findElement(By.xpath("//button[contains(text(),'Connect Wallet')]"));
			connectWallet.click();
			helper.waitForElementToLoad(By.xpath("//*[contains(text(),'Select a wallet')]"), 8);
			helper.clickWithText("MetaMask");	
			Thread.sleep(Duration.ofSeconds(8));
			helper.switchToLastOpenedWindow();
			//driver.switchTo().window(chargeCodeOnConnectWalletPage)
			
		}
		catch(Exception e)
		{
			System.out.println("Failed! Could not Connect Wallet on this page!");
			e.printStackTrace();
		}
		
		if(driver.getTitle().equals("MetaMask Notification"))
		{
			System.out.println("Connecting Wallet now! Focus is on Metamask Connect Wallet Window!");
			
			}
		else
		{
			System.out.println("FAILED, Title of current window is not MetaMask Notification. Title returned is: "+driver.getTitle());
		}
		
		
		helper.verifyText("Connect with MetaMask", "Connect metamask wallet");
		helper.clickButton("Next");
		helper.clickButton("Connect");
		//helper.waitForElementToLoad(By.xpath("/*[contains(text(),'Connecting')]"), 5);
		try {
			Thread.sleep(Duration.ofSeconds(5));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		helper.switchToLastOpenedWindow();
		
		//if switch network exists!
		if(helper.isElementPresent(By.xpath("//*[contains(text(),'Switch Network')]")))
		{
			helper.clickWithText("Switch Network");
			
			try {
				Thread.sleep(Duration.ofSeconds(5));
				helper.switchToLastOpenedWindow();
				
				if(driver.getTitle().equals("MetaMask Notification"))
				{
					System.out.println("Connecting Wallet now! Focus is on Metamask Connect Wallet Window!");
					
					}
				else
				{
					System.out.println("FAILED, Title of current window is not MetaMask Notification. Title returned is: "+driver.getTitle());
				}
				
				helper.verifyText("Allow this site to add a network", "Switch Network Metamask");
				switchNetworkMetamaskName=driver.findElement(By.xpath("//*[contains(text(),'Network name')]/following-sibling::*")).getText();
				System.out.println("Network to be switched to: "+switchNetworkMetamaskName);
				
				helper.clickButton("Approve");
				helper.verifyText(switchNetworkMetamaskName,"Switching from network to requested network");
				helper.clickButton("Switch network");
				
				helper.switchToLastOpenedWindow();
			}
			catch(Exception e)
			{
				System.out.println("Switch Network exists but the metamask window is not opened!");
				e.printStackTrace();
			}
		}
		
		
	}
	
	

}
	



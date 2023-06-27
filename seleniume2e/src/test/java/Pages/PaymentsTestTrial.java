package Pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentsTestTrial {
	static WebDriver driver;
	PaymentsTestTrial(){
	System.setProperty("webdriver.chrome.driver", "C://selenium//chromedriver.exe");
	ChromeOptions options= new ChromeOptions();
	options.addExtensions(new File("C:\\Users\\Roger\\Downloads\\metamaskcrx\\extension_10_24_1_0.crx"));
//	 DesiredCapabilities c = DesiredCapabilities.chrome();
//     // set ChromeOptions capability
//     c.setCapability(ChromeOptions.CAPABILITY, options);
    
//	options.addArguments("user-data-dir=C:\\Users\\Roger\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
	
	////options.addArguments("load-extension=C:\\Users\\Roger\\Downloads\\metamaskcrx\\extension_10_24_1_0.crx");
	options.addArguments(
			"user-data-dir=C:\\Users\\Roger\\AppData\\Local\\Google\\Chrome\\automationProfiles\\DefaultRoundAutoDir",
			"--profile-directory=DefaultRoundAutoDir");
	driver = new ChromeDriver(options);
	
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) throws InterruptedException {
		PaymentsTestTrial obj = new PaymentsTestTrial();
		
		driver.get("https://app.roundpe.com/");
		System.out.println("Launched RoundPe App Successful!");
		
		String roundPeWindow=driver.getWindowHandle();
		String roundPePaymentWindow;
		String tokenToPay="avalanche";
		driver.switchTo().window(roundPeWindow);
		
		String username = "rog4499@gmail.com";
		String password = "Welcome1-2";
		String secretKey= "pulse napkin heart confirm patient click unaware slow duck page animal swamp";
		
		logIn(username,password);
		Random objR = new Random();
		objR.nextInt(1000);
		String code;
		code=createPayment("RogerTest"+objR.nextInt(999), "Payment created for automated Test", 1.5f, true);
		System.out.println(code);
	
		switchToWindow(2);
		driver.findElement(By.className("Search_input__PXR0c")).sendKeys(tokenToPay);
		//driver.findElement(By.xpath("//input[@class='Search_input__PXR0c']")).sendKeys(tokenToPay);
		//selecting network radio button
		driver.findElement(By.xpath("//ul[@class='Dropdown_network-token-list__tAw-E']//input[contains(@value,'"+tokenToPay+"')]")).click();
		driver.findElement(By.className("Search_input__PXR0c")).sendKeys(tokenToPay);
		driver.findElement(By.xpath("//ul[@class='Dropdown_network-token-list__tAw-E']//input[contains(@value,'"+tokenToPay+"')]")).click();
		driver.findElement(By.id("agree-checkbox")).click();
		clickWithText("Pay with");
		clickWithText("Connect Wallet");
		clickWithText("MetaMask");
		Thread.sleep(8000);
		switchToWindow(3);
		clickWithText("Import");
		Thread.sleep(3000);
		clickWithText("agree");
		int i=0;
		for(String phrase:secretKey.split(" ")) {
		driver.findElement(By.id("import-srp__srp-word-"+i)).sendKeys(phrase);
		i++;
		}
		clickWithText("Confirm Secret");
		
		SendKeysWithDataTestId("create-password-new","Welcome1-2");
		SendKeysWithDataTestId("create-password-confirm","Welcome1-2");
//		driver.findElement(By.xpath("//*[contains(@data-testid,'create-password-new')]")).sendKeys("Welcome1-2");
//		driver.findElement(By.xpath("//*[contains(@data-testid,'create-password-confirm')]")).sendKeys("Welcome1-2");
		
		clickWithDataTestId("create-password-terms");
		clickWithDataTestId("create-password-import");
		clickWithDataTestId("onboarding-complete-done");
		clickWithDataTestId("pin-extension-next");
		clickWithDataTestId("pin-extension-done");
		clickWithDataTestId("account-menu-icon");
		clickWithText("Settings");
		driver.findElement(By.id("search-settings")).sendKeys("Show test networks");
		driver.findElement(By.id("menu-section_/settings/advanced#show-testnets")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Show test networks')]/../following-sibling::div//input/..")).click();
		clickWithDataTestId("app-header-logo"); 
		clickWithDataTestId("network-display"); 
		
		waitForElementToLoad(By.xpath("//*[@class='network-dropdown-list']/li[2]"),4);
		if(verifyXPathReturnsMoreThanOneElement("//*[@class='network-dropdown-list']/li"))
		{
			System.out.println("Test Passed! Test Networks enabled on metamask");
		}
		else
			System.out.println("Failure! Test networks not enabled!");
//		driver.findElement(By.xpath("//*[contains(@data-testid,'create-password-terms')]")).click();
//		driver.findElement(By.xpath("//*[contains(@data-testid,'create-password-import')]")).click();
//		driver.findElement(By.xpath("//*[contains(@data-testid,'onboarding-complete-done')]")).click();
//		driver.findElement(By.xpath("//*[contains(@data-testid,'pin-extension-next')]")).click();
//		driver.findElement(By.xpath("//*[contains(@data-testid,'pin-extension-done')]")).click();	
		System.out.println("Exiting!");
	}
	
	public static void switchToWindow(int index)
	{
		Set<String> handles=driver.getWindowHandles();
		List<String> tabs = new ArrayList<>(handles);
		System.out.println(tabs);
		driver.switchTo().window(tabs.get(index));
		System.out.println("Index "+index+":\n"+tabs.get(index));
	}
	
	public static WebElement waitForElementToLoad(By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public static boolean verifyXPathReturnsMoreThanOneElement(String xpath) {
	    List<WebElement> elements = driver.findElements(By.xpath(xpath));
	    return elements.size() > 1;
	}
	
	public static void clickWithDataTestId(String id)
	{
		try {
			driver.findElement(By.xpath("//*[contains(@data-testid,'"+id+"')]")).click();
			System.out.println("Clicked test id: "+id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean isElementPresent(By by)
	{
		if(driver.findElements(by).size()>0)
			{
			System.out.println("Element is Present!");
			return true;
			
			}
		else {
			System.out.println("Element is not Present!");
			return false;
		}
	}
	
	public static void SendKeysWithDataTestId(String id, String key)
	{
		try {
			driver.findElement(By.xpath("//*[contains(@data-testid,'"+id+"')]")).sendKeys(key);
			System.out.println("Sent Keys"+key+" \nto test id: "+id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String createPayment(String paymentName, String description, float amount, boolean applyFee)
	{
		navigateToTab("Payments");
		clickWithText("New Payment");
		findInputWithText("Name",paymentName);
		findInputWithText("Description",description);
		findInputWithText("Amount",Float.toString(amount));
		
		if(applyFee)
			driver.findElement(By.xpath("//*[@id='fee-input']")).click();
		verifyWithTextInBold("will pay");
		
		driver.findElement(By.xpath("//button[contains(text(),'Create Payment')]")).click();
		
		try {
			System.out.println(driver.findElement(By.xpath("//*[contains(text(),'Payment Link')]")).getText());
		}
		catch(Exception e)
		{
			System.out.println("Payment Link not generated!");
		}
		
		String code;
		code=driver.findElement(By.xpath("//*[contains(text(),'code is')]/*")).getText();
		
		clickWithText("View Payment Link");
		
		//*[contains(text(),'Name')]/following-sibling::*
		//p[contains(text(),'Amount')]/parent::div//*[contains(@class,'input') and (not (@disabled))]
		
		return code;
		
		
	}
	
	public static void findInputWithText(String text, String input)
	{
		String xpath = "//p[contains(text(),'"+text+"')]/parent::div//*[contains(@class,'input') and (not (@disabled))]";
		try {
			driver.findElement(By.xpath(xpath)).sendKeys(input);
		} catch (Exception e) {
			System.out.println("Couldnt find xpath with text "+ text+"\n"+xpath);
		}

	
	}
	
	public static void verifyWithTextInBold(String text)
	{
		String xpath = "//*[contains(text(),'"+text+"')]/b";
		try {
		driver.findElement(By.xpath(xpath));
		System.out.println("Text Exists!: "+text);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Text does not exist!: "+text);
		}
		
	}
	
	public static void navigateToTab(String tabName)
	{
		String xpath="//a[@class='SidebarItem_link__-1uWj' or 'SidebarItem_active__lHcwI SidebarItem_link__-1uWj']/child::span[contains(text(),'"+tabName+"')]";
		try {
			driver.findElement(By.xpath(xpath)).click();
			System.out.println("Successfully Navigated to "+tabName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failure, Not found: " + tabName+"\n"+xpath);
		}
		
	}
	
	public static void clickWithText(String text)
	{
		String xpath = "//*[contains(text(),'"+text+"')]";
		try {
			driver.findElement(By.xpath(xpath)).click();
		} 
		catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Failure, Couldnt find element with " + text);
			System.err.println(xpath);
		}
	}
	
	public static void logIn(String username, String password) {
		try {
			driver.findElement(By.xpath("//p[contains(text(),'Log in to RoundPe')]"));
			System.out.println("Element with text Log in to RoundPe found on UI. PASSED!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Element with text Log in to RoundPe not found, not on sign up UI. FAILED!");
		}

		driver.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys(username);
		driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();

	}
}


package helper;

import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ActionHelper {
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	
	public void updateProfilePath() {
		
	driver.get("chrome://version");
	String userdir;
    String path=driver.findElement(By.id("profile_path")).getText();
    
    try {
    	userdir=System.getProperty("user.dir");
    	System.out.println(userdir);
        FileWriter fw=new FileWriter(userdir+"/src/test/java/helper/ProfilePath.txt");    
        fw.write(path);    
        fw.close();    
        System.out.println("Profile Path updatedin file: \n"+path);
    } 
    
    catch (Exception e) {
    	System.out.println("PROFILE PATH NOT UPDATED! FAILURE");
        e.printStackTrace();
    }
    
    driver.close();
    
	}

	public String jsTextRetrieve(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("return arguments[0].textContent", js.executeScript(null, document.getElementsByTagName("b")[0]))
		//System.out.println(js.executeScript("return arguments[0].textContent", element));
		String text = (String) js.executeScript("return arguments[0].textContent", element);
		return text;
	}

	public void clickWithJS(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
		js.executeScript("arguments[0].click();", element);
		System.out.println("Element is clicked using JS: "+element);
		}
		catch(Exception e)
		{
			System.out.println("Exception while clicking element using js\n"+e);
		}
		
	}
	
	public void scrollToElement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scrolled to view: "+element);
		}
		catch(Exception e)
		{
			System.out.println("Exception while scrolling\n"+e);
		}
	}
	
	public void sleep(int seconds) {
		try {
		Thread.sleep(Duration.ofSeconds(seconds));
		}
		catch(Exception e) {
			System.out.println("Thread.sleep exception!");
			e.printStackTrace();
		}

	}
	
	public void clickButton(String text) {
		try {
			driver.findElement(By.xpath("//button[contains(text(),'" + text + "')]")).click();
			System.out.println("Passed, Clicked button with text: " + text);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILED, Couldnt find button to click with text " + text);
		}
	}

	public void sendKeysText(String text, String inputValue) {
		try {
			driver.findElement(By.xpath("//input[contains(text(),'" + text + "')]")).sendKeys(inputValue);
			System.out.println("Passed, Found input with text: " + text + ", passed " + inputValue + " as input");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILED, Couldnt find input with text: " + text);
		}
	}

	public void sendKeysById(String id, String inputValue) {
		try {
			driver.findElement(By.id(id)).sendKeys(inputValue);
			System.out.println("Passed, Found input with id: " + id + ", passed " + inputValue + " as input");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILED, Couldnt find input with id: " + id);
		}
	}

	public void verifyText(String text, String ui) {
		try {
			driver.findElement(
					By.xpath("//*[contains(text(),'"+ text + "')]"));
			System.out.println("Found Element with text " + text + " on " + ui + " UI");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to find Element with text " + text + " on " + ui + " UI");
		}
	}
	
	
	
//====================================================XX=========================================================	
	
	
	
	
	public void switchToWindow(int index)
	{
		Set<String> handles=driver.getWindowHandles();
		List<String> tabs = new ArrayList<>(handles);
		System.out.println(tabs);
		driver.switchTo().window(tabs.get(index));
		System.out.println("Index "+index+":\n"+tabs.get(index));
	}
	
	public void switchToLastOpenedWindow()
	{
		Set<String> handles=driver.getWindowHandles();
		List<String> tabs = new ArrayList<>(handles);
		System.out.println(tabs + "\n" + tabs.size());
		driver.switchTo().window(tabs.get(tabs.size()-1));
	}
	
	public WebElement waitForElementToLoad(By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    System.out.println("Waiting for element to load");
	    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public Boolean waitForElementToBeInvisible(WebElement element, int timeout)
	{
//		Duration timeoutSeconds=Duration.ofSeconds(timeout);
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		System.out.println("Waiting for element to be invisible");
		return wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	
	public void waitForAmount(WebElement element, int timeout) {
		//String regex = "^([A-Z]{1,3})\\d+(\\.\\d+)?$";
	//	Pattern pattern = Pattern.compile(regex);
		int halfSeconds=0;
		String text=element.getText();
		
		while(text.length()<2)
		{
			text=element.getText();
			try{
				Thread.sleep(500);
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		halfSeconds++;
		if(halfSeconds>(timeout/2))
			break;
			
		}
		
	}
	
	
	public boolean verifyXPathReturnsMoreThanOneElement(String xpath) {
	    List<WebElement> elements = driver.findElements(By.xpath(xpath));
	    return elements.size() > 1;
	}
	
	public void clickWithDataTestId(String id)
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
	public void SendKeysWithDataTestId(String id, String key)
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
	
	
	public void findInputWithText(String text, String input)
	{
		String xpath = "//p[contains(text(),'"+text+"')]/parent::div//*[contains(@class,'input') and (not (@disabled))]";
		try {
			driver.findElement(By.xpath(xpath)).sendKeys(input);
		} catch (Exception e) {
			System.out.println("Couldnt find xpath with text "+ text+"\n"+xpath);
		}

	
	}
	
	public void verifyWithTextInBold(String text)
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
	
	public void navigateToTab(String tabName)
	{
		String xpath="//a[@class=('SidebarItem_link__-1uWj' or 'SidebarItem_active__lHcwI SidebarItem_link__-1uWj') and contains(@href,'"+tabName.toLowerCase()+"')]";
		String xpath2="//div[contains(@class,'"+tabName.replaceAll(" ","")+"') and contains(@class,'collapsed')]";
		
		if(isElementPresent(By.xpath(xpath2)))
		{
			driver.findElement(By.xpath(xpath2)).click();
			if(!tabName.equalsIgnoreCase("signoutbutton"))
			{
			switchToLastOpenedWindow();
			System.out.println("Successfully Navigated to "+tabName);
			}
			else
				System.out.println("Signing Out from Round! Button clicked: "+tabName);
			
			return;
		}
		
		try {
			driver.findElement(By.xpath(xpath)).click();
			verifyOnScreenWithTitle(tabName);
			System.out.println("Successfully Navigated to "+tabName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failure, Not found: " + tabName+"\n"+xpath);
		}
		
	}
	
	public void verifyOnScreenWithTitle(String title)
	{
		if(isElementPresent(By.xpath("//*[contains(@class,'Typography_title') and contains(text(),'"+title+"')]")))
		{
			System.out.println("On "+title+" Screen!");
		}
		else
			System.out.println("Not on "+title+" Screen! FAILED");
		
	}
	
	public void verifyPageTitleContains(String expectedTitle)
	{
		String title = driver.getTitle();

		
			if(title.contains(expectedTitle))
			{
				System.out.println("PASSED, Current Page Title is as expected: "+title);
			}
			else
				System.out.println("FAILED, Current Page Title is not the same as expected. \nExpected: "+expectedTitle+"\nCurrentTitle: "+title);
			
	}
	
	public void clickWithText(String text)
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
	
	public By findElementWithText(String text)
	{
		By by = null;
		try {
		by= By.xpath("//*[contains(text(),'"+text+"')]");
		}
		catch(Exception e)
		{
			System.out.println("Couldn't find Element with Text" + text);
			e.printStackTrace();
		}
		return by;
	}

	public void scrollToElement(By by)
	{
		WebElement element = driver.findElement(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
	}
	
	public boolean isElementPresent(By by, boolean print)
	{
		if(driver.findElements(by).size()>0)
			{
			System.out.println("Element is Present! "+by);
			return true;
			}
		
		else {
			System.out.println("Element is not Present!"+by);
			return false;
		}
	}
	

	
	public boolean isElementPresent(By by)
	{
		if(driver.findElements(by).size()>0)
			{
			return true;
			}
		
		else {
			return false;
		}
	}
	
	
	
}


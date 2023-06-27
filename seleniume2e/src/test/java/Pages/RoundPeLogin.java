package Pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RoundPeLogin {

	static String continueButton = "Continue";
	static String doLater = "Do this later";

	public static void main(String[] args) throws InterruptedException {

		// Setting system properties of ChromeDriver
		System.setProperty("webdriver.chrome.driver", "C://selenium//chromedriver.exe");

		// Creating an object of ChromeDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Specifiying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		String username = "rog4499@gmail.com";
		String password = "Welcome1-2";

		driver.get("https://app.roundpe.com/");
		System.out.println("Launched RoundPe App Successful!");

		driver.findElement(By.linkText("Sign up")).click();
		System.out.println("Found and clicked sign up");

		signUp(driver);

		driver.findElement(By.linkText("Log in")).click();
		System.out.println("Found and clicked sign up");

		logIn(driver, username, password);

		// verifyText(driver,"Your Details", "Your Details");
		sendKeysById(driver, "firstName", "Roger");
		sendKeysById(driver, "lastName", "Alfred");
		clickButton(driver, continueButton);

		verifyText(driver, "Your Company", "Your Company");
		sendKeysById(driver, "brandName", "Nike");
		clickButton(driver, continueButton);

		verifyText(driver, "Your Addresses", "Your Addresses");
		clickButton(driver, doLater);
		Thread.sleep(Duration.ofSeconds(3));
		verifyText(driver, "all set", "All Set!");
		verifyText(driver, "Welcome", "AllSet - Welcome");

		clickButton(driver, "Get Started");
	}

	public static void signUp(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//p[contains(text(),'Sign up')]"));
			System.out.println("Element with text sign up found on UI. PASSED!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Element with text sign up not found, not on sign up UI. FAILED!");
		}

	}

	public static void logIn(WebDriver driver, String username, String password) {
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

		verifyText(driver, "Your Details", "Your Details");

	}

	public static void clickButton(WebDriver driver, String text) {
		try {
			driver.findElement(By.xpath("//button[contains(text(),'" + text + "')]")).click();
			System.out.println("Passed, Clicked button with text: " + text);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILED, Couldnt find button to click with text " + text);
		}
	}

	public static void sendKeysText(WebDriver driver, String text, String inputValue) {
		try {
			driver.findElement(By.xpath("//input[contains(text(),'" + text + "')]")).sendKeys(inputValue);
			System.out.println("Passed, Found input with text: " + text + ", passed " + inputValue + " as input");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILED, Couldnt find input with text: " + text);
		}
	}

	public static void sendKeysById(WebDriver driver, String id, String inputValue) {
		try {
			driver.findElement(By.id(id)).sendKeys(inputValue);
			System.out.println("Passed, Found input with id: " + id + ", passed " + inputValue + " as input");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILED, Couldnt find input with id: " + id);
		}
	}

	public static void verifyText(WebDriver driver, String text, String ui) {
		try {
			driver.findElement(
					By.xpath("//div[@class='OnboardingScreen_step-wrapper__WXrqb']/descendant::*[contains(text(),'"
							+ text + "')]"));
			System.out.println("Found Element with text " + text + " on " + ui + " UI");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to find Element with text " + text + " on " + ui + " UI");
		}
	}

}
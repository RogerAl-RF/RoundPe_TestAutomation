package helper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverRunner {

	private static DriverRunner instanceOfDriverRunner = null;

	private WebDriver driver;
	public static String TS;
	public static boolean newProfile, addMetaExt;

	// Restrict by using private, so other classes can not create object of driver
	private DriverRunner() {

		try {
			// Initializing driver object
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			createTimeStamp();

//			if (setupNewProfile) {
//				//String timestamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new java.util.Date());
//
//				options.addExtensions(new File("C:\\Users\\Roger\\Downloads\\metamaskcrx\\extension_10_24_1_0.crx"));
//				
//				if(!createDir(TS))
//				{
//					System.out.println("Could not create profile directory! Exiting");
//					System.exit(0);
//				}
//				
//				options.addArguments(
//						"user-data-dir=C:\\Users\\Roger\\AppData\\Local\\Google\\Chrome\\automationProfiles\\newAutoRoundPe"+TS,
//						"--profile-directory=newAutoRoundPe" + TS);
//				System.out.println("Launching chrome profile: newAutoRoundPe" + TS);
//			}
//
//			else {
//				// launching default automation directory
//				System.out.println("Launching chrome profile: DefaultRoundAutoDir");
//				options.addArguments(
//						"user-data-dir=C:\\Users\\Roger\\AppData\\Local\\Google\\Chrome\\automationProfiles\\DefaultRoundAutoDir",
//						"--profile-directory=DefaultRoundAutoDir");
//			}

			if (newProfile) {
				if (!createDir(TS)) {
					System.out.println("Could not create profile directory! Exiting");
					System.exit(0);
				}

				options.addArguments(
						"user-data-dir=C:\\Users\\Roger\\AppData\\Local\\Google\\Chrome\\automationProfiles\\newAutoRoundPe"
								+ TS,
						"--profile-directory=newAutoRoundPe" + TS);
				System.out.println("Launching chrome profile: newAutoRoundPe" + TS);
			} else {
				// launching default automation directory
				System.out.println("Launching chrome profile: DefaultRoundAutoDir");
				options.addArguments(
						"user-data-dir=C:\\Users\\Roger\\AppData\\Local\\Google\\Chrome\\automationProfiles\\DefaultRoundAutoDir",
						"--profile-directory=DefaultRoundAutoDir");
			}

			if (addMetaExt) {
				options.addExtensions(new File("C:\\Users\\Roger\\Downloads\\metamaskcrx\\extension_10_24_1_0.crx"));
			}

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			System.out.println("Chrome Driver launched");
		}

		catch (Exception e) {
			System.out.println("Exception while launching the driver, FAILURE");
			e.printStackTrace();
		}

		// implicit max timeout for all elements
		// Specifiying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

	}

	//Will return the existing driver if driver is already instantiated, if not then it will create a new Driver
	public static DriverRunner getInstanceOfDriverRunner() {
		if (instanceOfDriverRunner == null) {
			getSetupEnvVariables();
			instanceOfDriverRunner = new DriverRunner();
		}
		return instanceOfDriverRunner;
	}

	// public method to return the driver object!
	public WebDriver getDriver() {
		return driver;
	}

	// method to createADirectory for Chrome Profile purposes!
	public static boolean createDir(String timestamp) {
		// creating new contained dir
		File file = new File(
				"C:\\Users\\Roger\\AppData\\Local\\Google\\Chrome\\automationProfiles\\newAutoRoundPe" + timestamp);
		boolean bool = file.mkdir();

		if (bool) {
			System.out.println("Directory created successfully");
		} else {
			System.out.println("Couldn’t create specified directory");
		}

		return bool;
	}

	public static void getSetupEnvVariables() {
		newProfile = Boolean.parseBoolean(System.getProperty("setupNewProfile"));
		addMetaExt = Boolean.parseBoolean(System.getProperty("addMetamask"));
	}

	private void createTimeStamp() {
		TS = new SimpleDateFormat("ddMMyyyyHHmmss").format(new java.util.Date());
	}
}

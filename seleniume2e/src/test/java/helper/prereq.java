package helper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class prereq {
	
	
	public static void main(String[] args) {

//				DriverRunner driverRunnerObject=DriverRunner.getInstanceOfDriverRunner(true);
//				WebDriver driver = driverRunnerObject.getDriver();
		//System.setProperty("webdriver.chrome.driver", "C://selenium//chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		
		
		
		System.setProperty("webdriver.chrome.driver", "C://selenium//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("user-data-dir=C:\\Users\\Roger\\AppData\\Local\\Google\\Chrome\\automationProfiles","--profile-directory=profileABC");
		WebDriver driver = new ChromeDriver(options);
		// Specifiying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		driver.get("https://app.roundpe.com/");
		System.out.println("Launched RoundPe App Successful!");
		
		String username = "rog4499@gmail.com";
		String password = "Welcome1-2";
		
	
		
		logIn(driver,username,password);

				
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
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		        driver.close();
//		        
//		        options.addArguments("user-data-dir="+readFile());
//		        options.addExtensions(new File("C:\\Users\\Roger\\Downloads\\metamaskcrx\\extension_10_24_1_0.crx"));
//		        driver=new ChromeDriver(options);
		        
		    }
	
	
	
	public static String readFile() 
	{
		String userdir=System.getProperty("user.dir");
		 String readValue="";
        // Passing the path to the file as a parameter
		try {
        FileReader fr = new FileReader(
        		userdir+"/src/test/java/helper/ProfilePath.txt");
 
        // Declaring loop variable
        int i;
       	
        // Holds true till there is nothing to read
        
			while ((i = fr.read()) != -1)
			{
			    // Print all the content of a file
			    System.out.print((char)i);
			    readValue=readValue+i;
			}
		
        fr.close();
		}
		catch(Exception e) {
		e.printStackTrace();
		}
    	return readValue;
    }
	
	public static  void logIn(WebDriver driver,String username, String password) {
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

		//verifyText("Your Details", "Your Details");

	}


	}


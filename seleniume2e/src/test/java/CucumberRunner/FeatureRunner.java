package CucumberRunner;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.cucumber.listener.Reporter;

//import helper.ConfigFileReader;
import helper.TestNGRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

//import com.cucumber.listener.Reporter;

//import helper.ConfigFileReader;
import io.cucumber.testng.TestNGCucumberRunner;

//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import io.cucumber.testng.FeatureWrapper;
//import io.cucumber.testng.PickleWrapper;


@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"StepDefinitions"},
 //       stepNotifications = true,
        tags="@navigationLinks",//@signin//@Welcome//@Demorun
 //       dryRun = true,
        monochrome= true,
        plugin = {
                "pretty","html:target/cucumber-reports/cucumber-pretty/cucumber.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/CucumberTestReport.xml",
        		//"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"
        }
        )

public class FeatureRunner extends TestNGRunner{
	
}

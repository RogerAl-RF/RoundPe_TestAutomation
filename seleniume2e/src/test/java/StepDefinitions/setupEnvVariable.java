package StepDefinitions;

	
import io.cucumber.java.en.Given;

public class setupEnvVariable {
	@Given("I setup environment to setupNewProfile as {string} and addMetamask as {string}")
	public void i_setup_environment_to_setup_new_profile_as_and_add_metamask_as(String setupNewProfile, String addMetamask) {
	   
		System.out.println(setupNewProfile+"\n"+addMetamask);
		
	   System.setProperty("setupNewProfile", setupNewProfile);
	   System.setProperty("addMetamask", addMetamask);
	   
	   System.out.println("Environment Variable setupNewProfile is set to: "+System.getProperty("setupNewProfile"));
	   System.out.println("Environment Variable addMetamask is set to: "+System.getProperty("addMetamask"));
	}
}

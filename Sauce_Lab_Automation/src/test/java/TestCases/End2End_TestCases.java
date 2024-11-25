package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Utility.ReadConfig;

public class End2End_TestCases extends BrowserLaunch {
	

		@Test
		public void End2EndWorkflow() throws IOException, InterruptedException {
			
			Login_Testcases loginTest = new Login_Testcases();
	        loginTest.driver = this.driver; // Reuse the shared driver
	        ReadConfig readConfig = new ReadConfig();
	        loginTest.refresh();
	        loginTest.login(readConfig.getUsername(),readConfig.getPassword(),"Valid","Valid");
			
	        
	        
		}
		
}

package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.Patrol_Login;
import generic.BaseLib;
import generic.EmailUtility;


public class Patrol_LoginTest extends BaseLib {
	

		
	

	@Test
	public void VerifyLoginPageOF_Patrol() throws InterruptedException, IOException, MessagingException
	
	{
		
		BaseLib.driver.get(PathFile.Patrolurl);
		Thread.sleep(2000);
		Patrol_Login login = new Patrol_Login(driver);
		login.patrollogin("pratiksha.damodar@legitquest.com", "Patrol@123");
			 if (!generic.Library.errorUrls.isEmpty()) {
		            System.out.println("PatrolLogin page verificaiton");
		            generic.AllureListeners.captureScreenshot(BaseLib.driver, "PatrolLogin Page error");
		            Assert.fail("Test Case Failed: PatrolLogin page contain errror.");
		            String[] recipients = {
		            	    "ghodake6896@gmail.com"
		            	    
		            	};

		            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
		            	    "Patrol Automation - Login Page",
		            	    "Please check issue coming on LR login page , see the attached screenshot for details", 
		            	  generic. Library.errorUrls, 
		            	  generic.  Library. screenshotBytesList);
		            Assert.fail("Test Case Failed: Patrol login page");
		        } else {
		            System.out.println("Patrol login page open successfully");
		        }
		    }
		
	}

	



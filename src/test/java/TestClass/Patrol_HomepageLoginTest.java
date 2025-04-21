package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.Patrol_HomepageLogin;
import generic.BaseLib;
import generic.EmailUtility;



public class Patrol_HomepageLoginTest extends BaseLib{

	
	
	@Test
	public void LoginHomePageOfPatrol() throws InterruptedException, IOException, MessagingException
	
	{
		
		BaseLib.driver.get(PathFile.PatroHomePageurl);
		Patrol_HomepageLogin homelog= new Patrol_HomepageLogin(driver);
		
		homelog.login("pratiksha.damodar@legitquest.com","Patrol@123", BaseLib.driver);
		
		 if (!generic.Library.errorUrls.isEmpty()) {
	            System.out.println("Patrolhome page verificaiton");
	            generic.AllureListeners.captureScreenshot(BaseLib.driver, "PatroHome Page error");
	           // Assert.fail("Test Case Failed: Patrolhome page contain errror.");
	            String[] recipients = {
	            	    "ghodake6896@gmail.com", 
	            	    
	            	};

	            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
	            	    "Patrol Automation - Home Page",
	            	    "Please check issue coming on PatrolHomepage , see the attached screenshot for details", 
	            	  generic. Library.errorUrls, 
	            	  generic.  Library. screenshotBytesList);
	            Assert.fail("Test Case Failed: LR login page");
	        } else {
	            System.out.println("Patrolhome page open successfully");
	        }
	    }


	
	
}

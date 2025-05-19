package TestClass;


import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ConfigurationPath.PathFile;
import PomClass.DashBoardPage1;
import PomClass.Patrol_Login;
import PomClass.DashBoardPage1;
import generic.BaseLib;
import generic.Library;
import generic.EmailUtility;



public class DashBoardTest extends BaseLib{
	
	@Test
	
	public void verifyDashBoard() throws MessagingException, InterruptedException {
		BaseLib.driver.get(PathFile.Patrolurl);
		Patrol_Login login = new Patrol_Login(driver);
		login.patrollogin("pratiksha.damodar@legitquest.com", "Patrol@123");
		DashBoardPage1 dashBoardpage = new DashBoardPage1(driver);
		dashBoardpage.clickOncompany("Legitquest");
		
		
		    if (!Library.errorUrls.isEmpty()) {
		        System.out.println("Issue coming on dashboard page");
		        if (!Library.errorUrls.isEmpty()) {
		            System.out.println("Dashobard page verificaiton");
		            generic.AllureListeners.captureScreenshot(BaseLib.driver, "Patrol dashboard error");
		            String[] recipients = {
		            	    "ghodake6896@gmail.com", 
		            	    
		            	};

		            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
		            	    "Patrol Automation - Dashboard Page",
		            	    "Please check issue coming on Patrol Dashboard page , see the attached screenshot for details", 
		            	   Library.errorUrls, 
		            	   Library. screenshotBytesList);
		            Assert.fail("Test Case Failed: Patrol dashboard page");
		        } else {
		            System.out.println("dashboard page open successfully");
		        }
		    }
	}
}



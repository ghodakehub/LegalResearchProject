package TestClass;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ConfigurationPath.PathFile;
import PomClass.DashBoardPage1;
import PomClass.Patrol_Login;
import PomClass.smokesuite2formanagecases;
import generic.AllureListeners;
import generic.BaseLib;
import generic.EmailUtility;


public class smokesuiteofmanagecase extends BaseLib {
	

	
	@Test
	
	public void VerifysmokeTest() throws InterruptedException, MessagingException {
		
		
		BaseLib.driver.get(PathFile.Patrolurl);
		Patrol_Login login = new Patrol_Login(driver);
		login.patrollogin("pratiksha.damodar@legitquest.com", "Patrol@123");
		DashBoardPage1 dashBoardpage = new DashBoardPage1(driver);
		dashBoardpage.clickOncompany("Legitquest");
		smokesuite2formanagecases suite= new smokesuite2formanagecases(driver);
		suite.verifyManageCasesSubmodules();
		
		 if (!generic.Library.errorUrls.isEmpty()) {
	            System.out.println("Smokesuite of patrol website verificaiton");
	            AllureListeners.captureScreenshot(driver, "Smokesuite error");
	            Assert.fail("Test Case Failed: PatrolLogin page contain errror.");
	            String[] recipients = {
	            	    "ghodake6896@gmail.com", 
	            	    
	            	};

	            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
	            	    "Patrol Automation - SmokeSuite",
	            	    "Please check issue coming on patrol , see the attached screenshot for details", 
	            	  generic. Library.errorUrls, 
	            	  generic.  Library. screenshotBytesList);
	            Assert.fail("Test Case Failed: Patrol smokesuite page");
	        } else {
	            System.out.println("Patrol smokesuite successfully");
	        }
	    }
	

	}

   
	






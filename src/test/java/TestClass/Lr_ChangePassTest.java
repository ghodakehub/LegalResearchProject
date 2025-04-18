package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import ConfigurationPath.PathFile;
import PomClass.LR_ChangePasswordPage;
import PomClass.LR_EditProfile;
import PomClass.Login;
import generic.BaseLib;
import generic.EmailUtility;
import generic.TestResultCollector;

public class Lr_ChangePassTest extends BaseLib{

	
	@Test
	public void LR_Changepassword() throws InterruptedException, IOException, MessagingException
	
	{
		
		
		
		BaseLib.driver.get(PathFile.LRURL);
		Login log = new Login(driver);
		log.login("pratiksha.damodar@legitquest.com","lq@123");
		//TestResultCollector collector = new TestResultCollector ();
		LR_ChangePasswordPage pass= new LR_ChangePasswordPage(driver);
		pass.changePass();
		
		 if (!generic.Library.errorUrls.isEmpty()) {
	            System.out.println("Lr edit page verificaiton");
	            generic.AllureListeners.captureScreenshot(BaseLib.driver, "LR chagepassword page error");
	            if (!generic.Library.errorUrls.isEmpty()) {
	                System.out.println("Lr chagepassword page verification failed.");
	                generic.AllureListeners.captureScreenshot(BaseLib.driver, "LR chagepassowrd page error");

	                String[] recipients = {
	                    "ghodake6896@gmail.com",
	                    "mamta.Kashyap@legitquest.com"
	                };

	                EmailUtility.sendSummaryEmailWithScreenshots(
	                    driver,
	                    recipients,
	                    "LR Automation - ChangePassword",
	                    "Issue detected while change password it show server error. See the attached screenshot and failed URLs below.",
	                    generic.Library.errorUrls,
	                    generic.Library.screenshotBytesList
	                );

	                Assert.fail("Test Case Failed: LR chagepassword page contains errors.");
	            } else {
	                System.out.println("LR Changepaswword Page Verified Successfully!");
	            }
	        }
	       
	    }

	    }
	    
	


package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ConfigurationPath.PathFile;
import PomClass.Login;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;


public class LoginPage extends BaseLib {
	
	
	
	@Test
	public void VerifyLoginPageOFLR() throws InterruptedException, IOException, MessagingException
	
	{
			
			
			BaseLib.driver.get(PathFile.LRURL);
			Login log = new Login(driver);
			log.login("pratiksha.damodar@legitquest.com","lq@123");
			 if (!generic.Library.errorUrls.isEmpty()) {
		            System.out.println("Login page verificaiton");
		            generic.AllureListeners.captureScreenshot(BaseLib.driver, "LR login page error");
		            String[] recipients = {
		            	    "ghodake6896@gmail.com",
		            	    "krishna.jaiswal@legitquest.com","mamta.Kashyap@legitquest.com"
		            	    ,"rajan.batra@legitquest.com"
		            	     
		            	    
		            	};

		            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
		            	    "LR  - Login Page",
		            	    "Please check issue coming on LR login page , see the attached screenshot for details", 
		            	  generic. Library.errorUrls, 
		            	  generic.  Library. screenshotBytesList);
		            Assert.fail("Test Case Failed: LR login page");
		        } else {
		            System.out.println("Lr login page open successfully");
		        }
		    }

		  
		
	}

	
	
	
	
	
	
	
	
	


	
	
	
	
	


package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.LR_EditProfile;
import PomClass.Login;
import generic.BaseLib;
import generic.EmailUtility;
import generic.ErrorChecker;


public class LR_EditProfilePageTest extends BaseLib{

	
	

	
	@Test
	public void verifyLrEditprofile() throws InterruptedException, IOException, MessagingException
	
	{
		
		BaseLib.driver.get(PathFile.LRURL);
		Login log = new Login(driver);
		log.login("pratiksha.damodar@legitquest.com","lq@123");
	
		 LR_EditProfile pass = new LR_EditProfile(driver);
		    boolean isError = pass.EditProfile();  

		    if (isError) {
		        
		        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		        generic.AllureListeners.captureScreenshot(driver, "LR_profile_500");

		        
		        generic.Library.errorUrls.add(driver.getCurrentUrl());
		        generic.Library.screenshotBytesList.add(screenshotBytes);

		       
		        String[] recipients = {"ghodake6896@gmail.com"};
		        EmailUtility.sendSummaryEmailWithScreenshots(
		            driver,
		            recipients,
		            "LR - Edit Profile",
		            "Issue on LR Edit Profile. Server error occurred. Please check attached screenshot.",
		            generic.Library.errorUrls,
		            generic.Library.screenshotBytesList
		        );

		        Assert.fail("Test Failed: Server error on LR edit profile page.");
		    } else {
		        System.out.println("LR Edit Profile page loaded without server errors.");
		    }
		}
	       
	    }

	    
	    
	


package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import generic.ErrorChecker;
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
		

	    if (ErrorChecker.isServerErrorPresent(driver)) {
	        generic.AllureListeners.captureScreenshot(driver, "LR_ChangePassword_500");

	        String[] recipients = { "ghodake6896@gmail.com","mamta.Kashyap@legitquest.com" };
	        generic.Library.errorUrls.add(driver.getCurrentUrl());
	        generic.Library.screenshotBytesList.add(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

	        EmailUtility.sendSummaryEmailWithScreenshots(
	            driver,
	            recipients,
	            "LR - Change Password | Server Error",
	            "Issue on LR Change Password. Server error occurred. Please check attached screenshot.",
	            generic.Library.errorUrls,
	            generic.Library.screenshotBytesList
	        );

	        Assert.fail("Test Failed: Server error on LR change password page.");
	    } else {
	        System.out.println("LR change password page loaded without server errors.");
	    }
	}
	    }
	    
	


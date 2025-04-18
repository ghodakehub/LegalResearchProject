package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ConfigurationPath.PathFile;
import ExtentReportBasic.ExtentReportManager;
import PomClass.LR_ChangePasswordPage;
import PomClass.LR_EditProfile;
import PomClass.Login;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;

public class LR_EditProfilePageTest extends BaseLib{

	
	

	
	@Test
	public void verifyLrEditprofile() throws InterruptedException, IOException, MessagingException
	
	{
		
		BaseLib.driver.get(PathFile.LRURL);
		Login log = new Login(driver);
		log.login("pratiksha.damodar@legitquest.com","lq@123");
	
				LR_EditProfile pass= new LR_EditProfile(driver);
		pass.EditProfile();
		
		 if (!generic.Library.errorUrls.isEmpty()) {
	            System.out.println("Lr edit page verificaiton");
	            generic.AllureListeners.captureScreenshot(BaseLib.driver, "LR EditProfile page error");
	            if (!generic.Library.errorUrls.isEmpty()) {
	                System.out.println("Lr edit page verification failed.");
	                generic.AllureListeners.captureScreenshot(BaseLib.driver, "LR EditProfile page error");

	                String[] recipients = {
	                    "ghodake6896@gmail.com",
	                    "mamta.Kashyap@legitquest.com"
	                };

	                EmailUtility.sendSummaryEmailWithScreenshots(
	                    driver,
	                    recipients,
	                    "LR Automation - EditProfile",
	                    "Issue detected while editing the profile page. See the attached screenshot and failed URLs below.",
	                    generic.Library.errorUrls,
	                    generic.Library.screenshotBytesList
	                );

	                Assert.fail("Test Case Failed: LR EditProfile page contains errors.");
	            } else {
	                System.out.println("LR EditProfile Page Verified Successfully!");
	            }
	        }
	       
	    }

	    
	    }
	


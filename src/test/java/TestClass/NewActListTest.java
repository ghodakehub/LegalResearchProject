package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

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
import PomClass.Login;
import PomClass.NewActList;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;

public class NewActListTest extends BaseLib{
			
			
			@Test
			public void NewActList() throws InterruptedException, IOException, MessagingException
			
			{
					
					
					BaseLib.driver.get(PathFile.LRURL);
					Login log = new Login(driver);
					log.login("pratiksha.damodar@legitquest.com","lq@123");
					NewActList act= new NewActList(driver);
		act.Actlist(driver);
		act.verifyNewActlsit(driver);
		
	}
	
	@AfterMethod()
	public void aftermethod(ITestResult result) throws  IOException, MessagingException
	{
		 if (!generic.Library.errorUrls.isEmpty()) {
	        	String[] recipients = {
	            	    "ghodake6896@gmail.com", 
	            	    
	            	};

	            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
	            	    "LR Automation - ChangePassword",
	            	    "Please check issue coming on LR changepassword page , see the attached screenshot for details", 
	            	  generic. Library.errorUrls, 
	            	  generic.  Library. screenshotBytesList);
	            Assert.fail("Test Case Failed: LR login page");
	        } else {
	            //System.out.println("Lr login page open successfully");
	        }
	    }

}

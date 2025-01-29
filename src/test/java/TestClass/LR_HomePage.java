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
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.ForMultiplemailReceipent;


public class LR_HomePage {
	
	BaseLib base;
	UtilityClass utility1;
	String FrontpageOFweb;
	PathFile urlpath;
	
	
	
public static ExtentReports extent;
	
	public static ExtentSparkReporter spark;
	
	public static ExtentTest test1;
	
	@BeforeClass
	public void launchbrowser()
	{
		ExtentReportManager.initializeExtentReports();

        // Create a new test for this class
        test1 = ExtentReportManager.extent.createTest("verify LR Home page ");

		base= new BaseLib();
		base.initailizbrowser();
		base.implicitwait(10);
		utility1=new UtilityClass();
	}
		
	
		@Test
	    public void verifyHomePageofLR() {
			
			BaseLib.driver.get("https://login.legitquest.com/?redirectUrl=https%3A%2F%2Fwww.legitquest.com%2Fhome&baseurl=https://www.legitquest.com/");
	        try {
	           
	            String pageTitle = BaseLib.driver.getTitle();
	            Assert.assertTrue(pageTitle != null && !pageTitle.isEmpty(), "Website is accessible and page title is retrieved");
	        } catch (TimeoutException e) {
	            System.err.println("The website took too long to load. It might be down.");
	            Assert.fail("Website is not accessible due to a timeout.");
	            test1.fail("Website is not accessible due to a timeout.");
	        } catch (NoSuchSessionException e) {
	            System.err.println("The browser session is not available. It could indicate the website is down.");
	            test1.fail("Browser session was closed unexpectedly.");
	            Assert.fail("Browser session was closed unexpectedly.");
	        } catch (Exception e) {
	            System.err.println("An unexpected error occurred: " + e.getMessage());
	            Assert.fail("Website is not accessible due to an unexpected error.");
	            test1.fail("Website is not accessible due to an unexpected error");
	        }
	    }


		@AfterMethod()
		public void aftermethod(ITestResult result) throws  IOException, MessagingException
		{
			if(result.getStatus()== ITestResult.FAILURE)
			{
				test1.log(Status.FAIL, "test case is failed"+result.getName());
				test1.log(Status.FAIL, "test case is failed"+result.getThrowable());
				String screenshot=  UtilityClass.Capaturescreenshot(BaseLib.driver,result.getName() );
			
				test1.log(Status.FAIL,"test"+ test1.addScreenCaptureFromPath(screenshot));
				String testUrl = BaseLib.driver.getCurrentUrl();  
				
				 ForMultiplemailReceipent.sendEmail(
		            	  BaseLib.driver , new String[]{"ghodake6896@gmail.com"},
		            	    "LR HOME PAGE ",
		            	    "Please check issue coming on LR home page, please find the attached screenshot for details." ,
		            	    screenshot, testUrl
		            	   
		            	);
			
			
			}
			
			else if(result.getStatus()== ITestResult.SKIP){
				
				
				test1.log(Status.SKIP, "test case is skipped"+result.getName());
				

			}ExtentReportManager.flushReports(); // Flush the report
	    
			BaseLib.driver.quit();	
			}

	
}

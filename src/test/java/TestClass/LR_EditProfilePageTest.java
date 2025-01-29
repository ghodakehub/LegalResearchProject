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
import PomClass.LR_EditProfile;
import PomClass.Login;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.ForMultiplemailReceipent;

public class LR_EditProfilePageTest {
	
	
	BaseLib base;
	UtilityClass utility1;
	String FrontpageOFweb;
	PathFile urlpath;
	Login log;
	LR_EditProfile edit;
	
	
public static ExtentReports extent;
	
	public static ExtentSparkReporter spark;
	
	public static ExtentTest test;
	
	
	@BeforeClass
	public void launchbrowser()
	{
		ExtentReportManager.initializeExtentReports();

        // Create a new test for this class
        test = ExtentReportManager.extent.createTest("Verify the SearchResultOF  LR_Website");
		
		base= new BaseLib();
		base.initailizbrowser();
		base.implicitwait(6);
		//base.driver.get(PathFile.LRURL);
		log= new Login(BaseLib.driver, test);
		edit=new LR_EditProfile(BaseLib.driver, test);
		utility1= new UtilityClass();
		
	}
	

	
	@Test
	public void LR_EditProfile() throws InterruptedException, IOException
	
	{
		
		
		
		BaseLib.driver.get(PathFile.LRURL);
		 String pageTitle = BaseLib.driver.getTitle();
		 System.out.println("page title is" +pageTitle);
		log.login("pratiksha.damodar@legitquest.com","lq@123");
		//Thread.sleep(1000);
		edit.EditProfile();
		
		
	}


		@AfterMethod()
		public void aftermethod(ITestResult result) throws  IOException, MessagingException
		{
			if(result.getStatus()== ITestResult.FAILURE)
			{
				test.log(Status.FAIL, "test case is failed"+result.getName());
				test.log(Status.FAIL, "test case is failed"+result.getThrowable());
				String screenshot=  UtilityClass.Capaturescreenshot(BaseLib.driver,result.getName() );
			
				test.log(Status.FAIL,"test"+ test.addScreenCaptureFromPath(screenshot));
				String testUrl = BaseLib.driver.getCurrentUrl();  
				
				 ForMultiplemailReceipent.sendEmail(
		            	  BaseLib.driver , new String[]{"ghodake6896@gmail.com"},
		            	    "LR Edit Profile PAGE ",
		            	    "Please check issue coming on LR EditProfile page showing error, please find the attached screenshot for details." ,
		            	    screenshot, testUrl
		            	   
		            	);
			
			
			}
			
			else if(result.getStatus()== ITestResult.SKIP){
				
				
				test.log(Status.SKIP, "test case is skipped"+result.getName());
				

			}ExtentReportManager.flushReports(); // Flush the report
	    
			BaseLib.driver.quit();	
			}


}

package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;

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
import PomClass.Libil_Login;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.ForMultiplemailReceipent;

public class LIBIL_LoginTest extends BaseLib{
	
	
	
	
	
	@Test
	public void VerifyLoginPageOFLibil() throws InterruptedException, IOException
	
	{
		BaseLib.driver.get(PathFile.LIBILurl);
		Thread.sleep(2000);
		Libil_Login log = new Libil_Login(driver);
		log.login("admin@gmail.com","Admin@345");
		//test1.pass("Login TestCase is passed successfully.");
		
	}

	
	@AfterMethod()
	public void aftermethod(ITestResult result) throws  IOException, MessagingException
	{
		if(result.getStatus()== ITestResult.FAILURE)
		{
			
			String screenshot=  UtilityClass.Capaturescreenshot(BaseLib.driver,result.getName() );
		
			 String testUrl = BaseLib.driver.getCurrentUrl();  
			 ForMultiplemailReceipent.sendEmail(
					 BaseLib.driver, new String[]{"ghodake6896@gmail.com"
							
						
							
							 },
	            	    "LIBIL LOGIN PAGE ",
	            	    "Please check issue coming on LIBIL LOGIN page, please find the attached screenshot for details." ,
	            	    screenshot, testUrl
	            	   
	            	);
		
		}
		
		else if(result.getStatus()== ITestResult.SKIP){
			
		
		}ExtentReportManager.flushReports(); // Flush the report
    
		BaseLib.driver.quit();	
		}

	

}

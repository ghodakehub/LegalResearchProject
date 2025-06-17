package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import ExtentReportBasic.ExtentReportManager;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.ForMultiplemailReceipent;

public class VerifyLibilWebsiteDown extends BaseLib {
	
	
	
	@Test
    public void verifyLibilWeb() {
		
		BaseLib.driver.get(PathFile.LIBILurl);
        try {
           
            String pageTitle = BaseLib.driver.getTitle();
            Assert.assertTrue(pageTitle != null && !pageTitle.isEmpty(), "Website is accessible and page title is retrieved");
        } catch (TimeoutException e) {
            System.err.println("The website took too long to load. It might be down.");
            Assert.fail("Website is not accessible due to a timeout.");
        } catch (NoSuchSessionException e) {
            System.err.println("The browser session is not available. It could indicate the website is down.");
            Assert.fail("Browser session was closed unexpectedly.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            Assert.fail("Website is not accessible due to an unexpected error.");
        }
    }

		
	
	
	
	@AfterMethod()
	public void aftermethod(ITestResult result) throws  IOException, MessagingException
	{
		if(result.getStatus()== ITestResult.FAILURE)
		{
			
			String screenshot=  UtilityClass.Capaturescreenshot(BaseLib.driver,result.getName() );
		
		
			
			String testUrl = BaseLib.driver.getCurrentUrl();  
			 ForMultiplemailReceipent.sendEmail(
	            	   BaseLib.driver, new String[]{"ghodake6896@gmail.com"},
	            	    "LIBIL Website Down Alert ",
	            	    "LIBIL website not working please check may be server error , please find the attached screenshot for details." ,
	            	    screenshot,testUrl
	            	   
	            	);
		
		
		}
		
		else if(result.getStatus()== ITestResult.SKIP){
			
			
			

		}ExtentReportManager.flushReports(); // Flush the report
    
		BaseLib.driver.quit();	
		}
		
}

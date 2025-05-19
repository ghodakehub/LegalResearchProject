package TestClass;

import java.io.IOException;

import javax.mail.MessagingException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.Errordetectionemethod;
import generic.ForMultiplemailReceipent;

public class VerifyLegitquestWebsiteDown  extends BaseLib {
	
	
	
	
	
	@Test
		public void verifyLRwebsite() {
		    BaseLib.driver.get("https://login.legitquest.com/?redirectUrl=https%3A%2F%2Fwww.legitquest.com%2Fhome&baseurl=https://www.legitquest.com/");

		    try {
		        String pageTitle = BaseLib.driver.getTitle();
		        Assert.assertTrue(pageTitle != null && !pageTitle.isEmpty(),
		            "Website is not accessible or title is missing.");

		        
		        boolean isServerError = Errordetectionemethod.checkForServerError(BaseLib.driver, "Legitquest Main Website");
		        Assert.assertFalse(isServerError, "Detected 500/server error after loading website.");

		    } catch (TimeoutException e) {
		        Assert.fail("Website timeout. Possibly down.");
		    } catch (Exception e) {
		        Assert.fail("Unexpected exception: " + e.getMessage());
		    }
		}
	
	    @AfterMethod
	    public void aftermethod(ITestResult result) throws IOException, MessagingException {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            String screenshotPath = UtilityClass.Capaturescreenshot(BaseLib.driver, result.getName());
	            String testUrl = BaseLib.driver.getCurrentUrl();

	            ForMultiplemailReceipent.sendEmail(
	                BaseLib.driver,
	                new String[]{"ghodake6896@gmail.com"},
	                "LR- Website Down Alert",
	                "Please check LR website not working. Possible server error. Please find attached screenshot for details.",
	                screenshotPath,
	                testUrl
	            );
	        }

	       
	        BaseLib.driver.quit();	
	    }
}
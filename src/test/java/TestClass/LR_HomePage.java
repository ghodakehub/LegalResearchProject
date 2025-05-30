package TestClass;


import org.testng.Assert;
import org.testng.annotations.Test;
import generic.BaseLib;
import generic.EmailUtility;
import generic.Library;


public class LR_HomePage extends BaseLib {
		
	
		@Test
	    public void verifyHomePageofLR() {
			
			BaseLib.driver.get("https://www.legitquest.com/");
	        try {
	           
	            String pageTitle = BaseLib.driver.getTitle();
	            Assert.assertTrue(pageTitle != null && !pageTitle.isEmpty(), "Website is accessible and page title is retrieved");
	           Library.verifyText(driver, "500 Server", "issue on homepage");
	            
	            if (!generic.Library.errorUrls.isEmpty()) {
		            System.out.println("Lr home page verification");
		            generic.AllureListeners.captureScreenshot(BaseLib.driver, "LR Home Page error");
		            if (!generic.Library.errorUrls.isEmpty()) {
		                System.out.println("Lr edit page verification failed.");
		                generic.AllureListeners.captureScreenshot(BaseLib.driver, "LR Home page error");

		                String[] recipients = {
		                    "ghodake6896@gmail.com"
		                };

		                EmailUtility.sendSummaryEmailWithScreenshots(
		                    driver,
		                    recipients,
		                    "LR - LR Home Page",
		                    "Please check Issue coming on LR home page. See the attached screenshot and failed URLs below.",
		                    generic.Library.errorUrls,
		                    generic.Library.screenshotBytesList
		                );

		                Assert.fail("Test Case Failed: LR Home page contains errors.");
		            } else {
		                System.out.println(" LR home Page Verified Successfully!");
		            }
		        }
	        }
	        catch(Exception e)
	        {
	        	System.out.println();
	        }
		}
}

		

package TestClass;

import java.time.Duration;

import org.testng.Assert;

import org.testng.annotations.Test;

import UtilityClass.UtilityClass;

import generic.BaseLib;
import generic.ForMultiplemailReceipent;


public class VerifyLegitquestWebsiteDown  extends BaseLib {

	
	@Test
	public void checkLRLoginPageForErrors() {
	    String testUrl = "https://login.legitquest.com/?redirectUrl=https%3A%2F%2Fwww.legitquest.com%2Fhome&baseurl=https://www.legitquest.com/";

	    try {
	        driver.get(testUrl);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	       
	        Thread.sleep(3000);

	        String pageSource = driver.getPageSource().toLowerCase();
	        String pageTitle = driver.getTitle().toLowerCase();

	        boolean isError = false;

	        
	        if (pageSource.contains("500") ||
	            pageSource.contains("internal server error") ||
	            pageSource.contains("this site can’t be reached") ||
	            pageSource.contains("err_connection_timed_out") ||
	            pageSource.contains("mongodb\\driver\\exception") ||
	            pageSource.contains("sqlstate") ||
	            pageSource.contains("stack trace") ||
	            pageTitle.contains("error")) {
	            isError = true;
	        }

	        
	        if (isError) {
	            System.err.println("Server or network error detected on LR login page.");

	            String screenshot = UtilityClass.Capaturescreenshot(driver, "LR_Login_Error");

	            ForMultiplemailReceipent.sendEmail(
	                driver,
	                new String[]{"ghodake6896@gmail.com"},
	                "LR Login Page Error Detected",
	                "The LR login page appears to be returning a 500 error or server-related failure.\nPlease check manually. Screenshot is attached.\n\nURL: " + testUrl,
	                screenshot,
	                testUrl
	            );
	        } else {
	            System.out.println("LR Login page is working fine.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("Exception while checking LR Login page: " + e.getMessage());
	    }
	}
	}

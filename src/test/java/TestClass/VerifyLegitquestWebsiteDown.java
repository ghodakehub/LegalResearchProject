package TestClass;

import java.net.HttpURLConnection;
import java.net.URL;
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
	        
	        if (!isServerHealthy(testUrl)) {
	            System.err.println("Server responded with error status.");

	            String screenshot = UtilityClass.Capaturescreenshot(driver, "LR_Server_Error");

	            ForMultiplemailReceipent.sendEmail(
	                driver,
	                new String[]{"ghodake6896@gmail.com"},
	                "LR Login Page Server Error",
	                "The LR website is not working. Please check.\n\nURL: " + testUrl,
	                screenshot,
	                testUrl
	            );
	            return;
	        }

	      
	        driver.get(testUrl);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        Thread.sleep(3000); 

	        String pageSource = driver.getPageSource().toLowerCase();
	        String pageTitle = driver.getTitle().toLowerCase();

	       
	        boolean isError = false;

	        if (pageSource.contains("internal server error") ||
	            pageSource.contains("this site can’t be reached") ||
	            pageSource.contains("err_connection_timed_out") ||
	            pageSource.contains("mongodb\\driver\\exception") ||
	            pageSource.contains("sqlstate") ||
	            pageSource.contains("stack trace") ||
	            pageTitle.contains("error") ||
	            !pageSource.contains("email") 
	        ) {
	            isError = true;
	        }

	       
	        if (isError) {
	            System.err.println("Frontend error detected on LR login page.");

	            String screenshot = UtilityClass.Capaturescreenshot(driver, "LR_Login_UI_Error");

	            ForMultiplemailReceipent.sendEmail(
	                driver,
	                new String[]{"ghodake6896@gmail.com"},
	                "LR Login Page UI Error Detected",
	                "The LR login page loaded but shows frontend error (like missing form or error message).\n\nURL: " + testUrl,
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

	
	public boolean isServerHealthy(String url) {
	    try {
	        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	        connection.setRequestMethod("GET");
	        connection.setConnectTimeout(5000);
	        connection.setReadTimeout(5000);
	        connection.connect();

	        int responseCode = connection.getResponseCode();
	        return responseCode == 200;
	    } catch (Exception e) {
	        System.err.println("Exception in server check: " + e.getMessage());
	        return false;
	    }
	}
	}

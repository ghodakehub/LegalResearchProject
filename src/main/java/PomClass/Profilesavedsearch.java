package PomClass;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import generic.BaseLib;
import generic.EmailUtility;

public class Profilesavedsearch {
	
	WebDriver driver;
	
	 public Profilesavedsearch (WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }    
		
	 public void testSavedSearchError() {
		 try {
		        
		        WebElement profileDropdown = driver.findElement(By.xpath("//*[@id=\"navbarsExampleDefault\"]/ul/li/a"));
		        profileDropdown.click();

		       
		        driver.findElement(By.xpath("(//a[contains(text(),'Profile')])[1]")).click();
		        Thread.sleep(3000);

		        
		        WebElement savedSearchBtn = driver.findElement(By.xpath("//button[contains(text(),'Saved Search')]"));
		        savedSearchBtn.click();
		        Thread.sleep(4000);

		        
		        String pageSource = driver.getPageSource().toLowerCase();
		        if (pageSource.contains("http 500") || pageSource.contains("this page isn’t working") || pageSource.contains("server error")) {

		        	 byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		 	        generic.AllureListeners.captureScreenshot(driver, "savebtn");

		 	        
		 	        generic.Library.errorUrls.add(driver.getCurrentUrl());
		 	        generic.Library.screenshotBytesList.add(screenshotBytes);

		            generic.AllureListeners.captureScreenshot(BaseLib.driver, "LR_PROFILE_Page Error");

		          
		            String[] recipients = { "ghodake6896@gmail.com"};

		            EmailUtility.sendSummaryEmailWithScreenshots(
		                driver,
		                recipients,
		                "LR - Profile Page",
		                "Issue coming when clicking on Saved Search button — the page throws error. Please find the attached screenshot.",
		                generic.Library.errorUrls,
		                generic.Library.screenshotBytesList
		            );

		           
		            Assert.fail("Test Case Failed: LR profile page encountered a 500/server error.");

		        } else {
		            System.out.println("Profile page opened successfully, no error found after clicking Saved Search.");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        Assert.fail("Exception occurred during test execution: " + e.getMessage());
		    }
		}

}

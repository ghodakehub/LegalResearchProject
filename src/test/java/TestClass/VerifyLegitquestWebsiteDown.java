package TestClass;

import java.io.IOException;
import java.time.Duration;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import UtilityClass.UtilityClass;
import generic.AllureListeners;
import generic.BaseLib;
import generic.ForMultiplemailReceipent;
import io.qameta.allure.Allure;

public class VerifyLegitquestWebsiteDown  extends BaseLib {

	
	@Test
	public void verifyLRwebsite() throws Exception {
	    String testUrl = "https://login.legitquest.com/?redirectUrl=https%3A%2F%2Fwww.legitquest.com%2Fhome&baseurl=https://www.legitquest.com/";
	  
	        BaseLib.driver.get(testUrl);
	        
	        WebDriverWait wait = new WebDriverWait(BaseLib.driver, Duration.ofSeconds(15));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/main/h2"))); // adjust to actual element

	        String pageSource = BaseLib.driver.getPageSource().toLowerCase();
	        String title = BaseLib.driver.getTitle().toLowerCase();

	        boolean isError = false;

	        if (pageSource.contains("http error 500") || 
	            pageSource.contains("server error") ||
	            pageSource.contains("this page isn’t working") ||
	            pageSource.contains("this site can’t be reached") ||
	            title.contains("error") || title.contains("problem") ||
	            !pageSource.contains("login")) {  
	            isError = true;
	        }

	        if (isError) {
	            String screenshotPath = UtilityClass.Capaturescreenshot(BaseLib.driver, "LR_ERROR");
	            AllureListeners.captureScreenshot(BaseLib.driver, "error png");

	            ForMultiplemailReceipent.sendEmail(
	                BaseLib.driver,
	                new String[]{"ghodake6896@gmail.com"},
	                "LR Website Down Alert",
	                "LR website is returning a server error (500 or similar). See attached screenshot for details.",
	                screenshotPath,
	                BaseLib.driver.getCurrentUrl()
	            );
	            Assert.fail("Website shows server error or is not reachable.");
	        } else {
	            System.out.println("Website loaded successfully: " + title);
	        }

	}
}
package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import UtilityClass.Library;
import generic.AllureListeners;
import generic.BaseLib;
import generic.EmailUtility;

public class VerifySearchResult extends BaseLib{
	
	WebDriver driver;
	
	@FindBy(xpath = "//input[@id='search']") // Main Search Box
	private WebElement searchbox;

	@FindBy(xpath = "(//i[@class='fa fa-search'])[1]") // Main Search Icon (Button)
	private WebElement SearchButton;

	@FindBy(xpath = "//*[@id=\"court\"]/label/text()") // All Button result
	private WebElement court;
	
	
	// there ===========================
    public  VerifySearchResult(WebDriver driver) {
        this.driver = driver;
       
        PageFactory.initElements(driver, this);
    }   

	
	
// Actions 	
	public void SearhResultverify(WebDriver driver, String keyword) throws InterruptedException {
	
		 try {
		        // Enter keyword and search
		        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"search\"]"));
		        searchBox.sendKeys(keyword);
		        
		        driver.findElement(By.id("btnSearch")).click();

		       
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		        boolean isResultPresent = false;

		        try {
		            
		            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Found :')]")));
		            isResultPresent = true;
		        } catch (TimeoutException e) {
		 
		            isResultPresent = false;
		        }

		        if (!isResultPresent) {
		            System.out.println("❌ No results found for keyword: " + keyword);
		            AllureListeners.captureScreenshot(driver,"No_Results_Found_" + keyword);
		            String[] recipients = {
		            	    "ghodake6896@gmail.com", 
		            	    
		            	};

		            EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients, 
		            	    "LR Automation - SearchResults",
		            	    "Please check issue coming on LR SearchResults showing no results found error , see the attached screenshot for details", 
		            	  generic. Library.errorUrls, 
		            	  generic.  Library. screenshotBytesList);
		            Assert.fail("Test Case Failed: LR SearchResults page");
		        } 
		    } catch (Exception e) {
		        e.printStackTrace();
		        Assert.fail("Exception during search validation: " + e.getMessage());
		    }
		}
	
}
	




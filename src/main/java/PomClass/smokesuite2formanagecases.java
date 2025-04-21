package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import generic.BaseLib;
import generic.Library;


public class smokesuite2formanagecases extends BaseLib {
	
	WebDriver driver;
	 public smokesuite2formanagecases (WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }    
	
	public void verifyManageCasesSubmodules() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    String[] options = {
	            "Cases", "Matters", "Calendar", "Tasks", "Documents", "Contacts", "Alerts"
	        };

	    for (String subOptionText : options) {
	       
	        	try {
	                JavascriptExecutor js = (JavascriptExecutor) driver;

	                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxStatusDiv")));

	                WebElement manageCases = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//span[text()='Manage Cases']")));
	                js.executeScript("arguments[0].scrollIntoView(true);", manageCases);
	                manageCases.click();
	                Thread.sleep(500);

	                // Dynamic XPath for the sub-option
	                String optionXPath = "//span[text()='Manage Cases']/ancestor::li[contains(@class,'has-submenu')]//span[contains(normalize-space(), '" + subOptionText + "')]";

	                WebElement subOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
	                js.executeScript("arguments[0].scrollIntoView(true);", subOption);
	                subOption.click();

	                System.out.println("Clicked on: " + subOptionText);

	            Library.verifyText(driver, "Stack trace", "error on page");
	         
	        } catch (Exception e) {
	            System.out.println("Exception while verifying " + subOptionText + ": " + e.getMessage());
	           
	        }
	    }
	    }
}
	


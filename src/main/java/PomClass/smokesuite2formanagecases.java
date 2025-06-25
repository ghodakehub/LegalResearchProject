package PomClass;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.AllureListeners;
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
		    JavascriptExecutor js = (JavascriptExecutor) driver;

		   
		    Map<String, String> expectedTexts = Map.of(
		        "Cases", "Cases",
		        "Pre-Litigation", "Matter ",
		        "Calendar", "Calendar",
		        "Tasks", "Tasks",
		        "Documents", "Document",
		        "Contacts", "Contacts",
		        "Alerts", "Appeal Alert"
		    );

		    for (String subOptionText : expectedTexts.keySet()) {
		        try {
		           
		            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxStatusDiv")));
		            WebElement manageCases = wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//span[text()='Manage Cases']")));
		            js.executeScript("arguments[0].scrollIntoView(true);", manageCases);
		            manageCases.click();
		            Thread.sleep(500);
		           
		            String optionXPath = "//span[text()='Manage Cases']/ancestor::li[contains(@class,'has-submenu')]//span[normalize-space(text())='" + subOptionText + "']";
		            WebElement subOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
		            js.executeScript("arguments[0].scrollIntoView(true);", subOption);
		            subOption.click();
		            System.out.println("Clicked on: " + subOptionText);
		            Thread.sleep(4000); 
		            Library.verifyText2(driver, "Strace track" ,"  shows error");
		            String expectedText = expectedTexts.get(subOptionText);
		            boolean isLoaded = driver.getPageSource().contains(expectedText);
		            if (!isLoaded) {
		                AllureListeners.captureScreenshot(driver, subOptionText);
		            }

		        } catch (Exception e) {
		            
		        }
		    }
		}
}
	


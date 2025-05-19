package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.AllureListeners;
import generic.Library;


public class SmokeSuite {
	
	WebDriver driver;
	 public SmokeSuite (WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }    
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	  // List of modules and submodules with their XPaths
    String[][] modules = {
        
        {"Manage Cases", "//a[contains(@class,'nav-link')]//span[text()='Manage Cases']"},
        {"Reports", "//a[contains(@class,'nav-link')]//span[text()='Reports']"},
        {"My Cause List", "//a[contains(@class,'nav-link')]//span[text()='My Cause List']"},
        {"Invoice", "//a[contains(@class,'nav-link')]//span[text()='Invoice']"},
        {"Order Tracker", "//a[contains(@class,'nav-link')]//span[text()='Order Tracker']"},
        {"Notification", "//a[contains(@class,'nav-link')]//span[text()='Notification']"},
        {"Company", "//a[contains(@class,'nav-link')]//i[contains(@class, 'bi-building')]/following-sibling::span[text()='Company']"},
        {"Chat", "//a[contains(@class,'nav-link')]//span[text()='Chat']"},
       // {"Dashboard", "//a[contains(@class,'nav-link')]//span[text()='Dashboard']"}
    };

    // Main method to run the smoke test
    public void runSmokeTest() {
        for (String[] module : modules) {
            verifyModule(module[0], module[1]);
        }
    }

    // Method to verify each module
    public void verifyModule(String moduleName, String moduleXPath) {
        try {
            System.out.println("Verifying: " + moduleName);

            // Wait until the module is clickable
            WebElement moduleElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(moduleXPath)));

            // Scroll and click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", moduleElement);
            Thread.sleep(500);  // Small delay for stability
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", moduleElement);

            // Wait for the page to load
            Thread.sleep(2000);
            Library.verifyText(driver, "Stack trace", "error on page");
           

        } catch (Exception e) {
            System.out.println("Failed to open: " + moduleName);
            AllureListeners.captureScreenshot(driver, moduleName + "_Failure");
            e.printStackTrace();
        }
    }
}



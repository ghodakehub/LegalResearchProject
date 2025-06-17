package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.AllureListeners;
import generic.Library;


public class SmokeSuite {
	
	 WebDriver driver;
	    WebDriverWait wait;

	    public SmokeSuite(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // This line throws error if driver is null
	    }
	
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

   
   public void verifyModule(String moduleName, String moduleXPath) {
       try {
           System.out.println("Verifying: " + moduleName);

           
           WebElement moduleElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(moduleXPath)));

          
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", moduleElement);
           Thread.sleep(500);  
           ((JavascriptExecutor) driver).executeScript("arguments[0].click();", moduleElement);

           
           Thread.sleep(2000);

          
           if (Library.verifyText2(driver, "Stack trace", "error on this module") ||
               Library.verifyText2(driver, "404", "404 error")) {


               System.out.println("Error detected in: " + moduleName);
               AllureListeners.captureScreenshot(driver, moduleName);
               driver.navigate().back();
               Thread.sleep(2000); 

           } else {
               System.out.println("" + moduleName + " opened successfully.");
           }

       } catch (Exception e) {
           System.out.println("Failed to open: " + moduleName);
           AllureListeners.captureScreenshot(driver, moduleName + "_Failure");
           e.printStackTrace();
       }
   }
}



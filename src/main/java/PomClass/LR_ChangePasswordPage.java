package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import generic.Library;



public class LR_ChangePasswordPage {


		WebDriver driver;
		//ExtentTest test;

		@FindBy(xpath = "//*[@id=\"navbarsExampleDefault\"]/ul/li/a")
		private WebElement profilename;                
		
		@FindBy(xpath = "//a[text()='Profile']")        
		private WebElement profile;
		
		@FindBy(xpath = "//a[contains(text(),'Edit Profile')]")
		private WebElement editProfile;

		@FindBy(xpath = "//a[contains(text(),'Change password')]")
		private WebElement changepass;
			
		

	    public LR_ChangePasswordPage (WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }    

		public void changePass() throws InterruptedException {

			Library.click(driver, profilename, "Click on profileIcon");
			Library.threadSleep(3000);
			
			Library.click(driver, profile, "Click on profile");
			Library.threadSleep(3000);
			
			 By changepassLocator = By.xpath("/html/body/main/section/div/div/div/div/aside/section[2]/div/div/div[2]/ul/li[2]/a");

		        // Wait until element is clickable
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		        WebElement changepass = wait.until(ExpectedConditions.elementToBeClickable(changepassLocator));

		        // Try clicking with WebDriver first
		        try {
		            changepass.click();
		        } catch (ElementClickInterceptedException e) {
		            // If intercepted, fallback to JavaScript click
		            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", changepass);
		        }
			Library.threadSleep(3000);
			
			
			Library.verifyText(driver, "HTTP ERROR 500", "error on lr changepassword");
	     }
		
}

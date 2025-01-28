package PomClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import UtilityClass.Library;
import generic.BaseLib;

public class LR_ChangePasswordPage extends BaseLib{


		WebDriver driver;
		ExtentTest test;

		@FindBy(xpath = "//*[@id=\"navbarsExampleDefault\"]/ul/li/a")
		private WebElement profilename;                
		
		@FindBy(xpath = "//a[text()='Profile']")        
		private WebElement profile;
		
		@FindBy(xpath = "//a[contains(text(),'Edit Profile')]")
		private WebElement editProfile;

		@FindBy(xpath = "//a[contains(text(),'Change password')]")
		private WebElement changepass;
			
		

	    public LR_ChangePasswordPage (WebDriver driver, ExtentTest test) {
	        this.driver = driver;
	        this.test = test; // Assign ExtentTest to the POM class
	        PageFactory.initElements(driver, this);
	    }    

		public void changePass() throws InterruptedException {

			Library.click(driver, profilename, "Click on profileIcon");
			Library.threadSleep(3000);
			
			Library.click(driver, profile, "Click on profile");
			Library.threadSleep(3000);
			
			WebElement changepass = driver.findElement(By.xpath("//a[contains(text(),'Change password')]"));
			JavascriptExecutor js7= (JavascriptExecutor) BaseLib.driver;
			js7.executeScript("arguments[0].scrollIntoView(true);",changepass );
			changepass.click();
			Library.threadSleep(3000);
			  WebElement changepasstitle = driver.findElement(By.xpath("//h4[contains(text(),'Change Password')]"));
	          
	          Library.threadSleep(2000);
	         
	          if (changepasstitle.isDisplayed()) {
	              System.out.println("ChangePassword page title is displayed. ChangePassword page open successfully ,Test passed!");
	          } else {
	              System.out.println("ChangePassword title is NOT displayed. Means Server error coming , Test failed!");
	              Assert.fail("ChangePassword page not open it showing error of Server");
	          }
	     
			 
	     
	     }
}

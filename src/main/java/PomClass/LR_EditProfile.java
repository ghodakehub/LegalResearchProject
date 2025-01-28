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

public class LR_EditProfile extends BaseLib{

	
	
	
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
		
	

    public LR_EditProfile (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test; // Assign ExtentTest to the POM class
        PageFactory.initElements(driver, this);
    }    

	public void EditProfile() throws InterruptedException {

		Library.click(driver, profilename, "Click on profileIcon");
		Library.threadSleep(3000);
		
		Library.click(driver, profile, "Click on profile");
		Library.threadSleep(3000);
		
		WebElement editprof = driver.findElement(By.xpath("//a[contains(text(),'Edit Profile')]"));
		JavascriptExecutor js7= (JavascriptExecutor) BaseLib.driver;
		js7.executeScript("arguments[0].scrollIntoView(true);",editprof );
		editprof.click();
		Library.threadSleep(3000);
		  WebElement editprofile = driver.findElement(By.xpath("//h4[contains(text(),'Edit Profile')]"));
          
          Library.threadSleep(2000);
         
          if (editprofile.isDisplayed()) {
              System.out.println("EditProfile page title is displayed. Edit Profile page open successfully ,Test passed!");
          } else {
              System.out.println("EditProfile title is NOT displayed. Means error coming , Test failed!");
              Assert.fail("EditProfile title not display means It throw error while open it");
          }
     
		 
     
     }
}
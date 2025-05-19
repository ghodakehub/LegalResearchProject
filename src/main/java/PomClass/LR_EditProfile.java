package PomClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import generic.BaseLib;
import generic.ErrorChecker;
import generic.Errordetectionemethod;
import generic.Library;

public class LR_EditProfile {

	
	
	
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
		
	

    public LR_EditProfile (WebDriver driver) {
        this.driver = driver;
        
        PageFactory.initElements(driver, this);
    }    

	public boolean EditProfile() throws InterruptedException {

		Library.click(driver, profilename, "Click on profileIcon");
		Library.threadSleep(3000);
		
		Library.click(driver, profile, "Click on profile");
		Library.threadSleep(3000);
		
		WebElement editprof = driver.findElement(By.xpath("//a[contains(text(),'Edit Profile')]"));
		JavascriptExecutor js7= (JavascriptExecutor) BaseLib.driver;
		js7.executeScript("arguments[0].scrollIntoView(true);",editprof );
		editprof.click();
		Library.threadSleep(3000);
		return Errordetectionemethod.checkForServerError(driver, "LR Edit Profile");
     
     }
}
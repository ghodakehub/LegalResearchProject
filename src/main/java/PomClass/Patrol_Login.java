package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import generic.BaseLib;

public class Patrol_Login  {

	
	
	WebDriver driver;
	

	@FindBy(xpath = "//input[@id='email']")

	private WebElement MaildAddress;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement pwd;

	@FindBy(xpath = "//*[@id=\"wrapper\"]/main/div/div[2]/div/main/form/button")
	private WebElement lgbtn;
	
	@FindBy(xpath = "//*[@id=\"userDropdown\"]/span")
	private WebElement validusername;


    public Patrol_Login (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }    
	
    public void patrollogin(String user, String pass) throws InterruptedException {

    	
    	MaildAddress.sendKeys(user);
		               
		Thread.sleep(2000);
		pwd.sendKeys(pass);
		Thread.sleep(1000);
		lgbtn.click();
		
		
		Thread.sleep(3000);
		
		
		generic.Library.verifyText(driver, "Stack trace", "error on Patrol home page");

	
    }
	
	
}

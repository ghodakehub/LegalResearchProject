package PomClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import generic.BaseLib;

public class Patrol_Login  extends BaseLib{

	
	
	WebDriver driver;
	ExtentTest test;

	@FindBy(xpath = "//input[@id='email']")

	private WebElement MaildAddress;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement pwd;

	@FindBy(xpath = "//*[@id=\"wrapper\"]/main/div/div[2]/div/main/form/button")
	private WebElement lgbtn;
	
	@FindBy(xpath = "//*[@id=\"userDropdown\"]/span")
	private WebElement validusername;


    public Patrol_Login (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test; // Assign ExtentTest to the POM class
        PageFactory.initElements(driver, this);
    }    
	
    public void login(String user, String pass) throws InterruptedException {

    	
    	MaildAddress.sendKeys(user);
		test.info("Entered mailaddress: " + user);
		//pwd.clear();
		//test.info("Clear password: " + pass);                
		Thread.sleep(2000);
		pwd.sendKeys(pass);
		test.info("Entered Password: " + pass);
		lgbtn.click();
		
		test.info("Click on login button: ");
		Thread.sleep(2000);
		
     	
     	WebElement dashboardElement = driver.findElement(By.xpath("//*[@id=\"userDropdown\"]/span"));
        if (dashboardElement.isDisplayed()) {
            test.pass("Patrol Login successfully, valid user is displayed on home page");
        } else {
            throw new AssertionError("Valid user not displayed on home page may be Patrol_Login page not working please check.");
        }

	
	
	
    }
	
	
}

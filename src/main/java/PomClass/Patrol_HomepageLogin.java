package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import UtilityClass.Library;
import generic.BaseLib;

public class Patrol_HomepageLogin extends BaseLib {

	WebDriver driver;
	ExtentTest test;

	
	@FindBy(xpath = "/html/body/div[2]/div/nav/div/a[2]")
	private WebElement Loginbtn;

	@FindBy(xpath = "(//*[@id=\"email\"])[3]")

	private WebElement MaildAddress;

	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement pwd;

	@FindBy(xpath = "//*[@id=\"submit\"]")
	private WebElement lgbtn;
	
	@FindBy(xpath = "//*[@id=\"userDropdown\"]/span")
	private WebElement validusername;


    public Patrol_HomepageLogin (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test; // Assign ExtentTest to the POM class
        PageFactory.initElements(driver, this);
    }    
    
    
	
    public void login(String user, String pass,WebDriver driver) throws InterruptedException {
    
    	 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
         WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/nav/div/a[2]"))); // Update the XPath if needed
         loginButton.click();
         Library.threadSleep(2000);
         MaildAddress.click();
    	MaildAddress.sendKeys(user);
		test.info("Entered mailaddress: " + user);
		Library.threadSleep(2000);
		
		
		pwd.sendKeys(pass);
		Library.threadSleep(2000);
		
		test.info("Entered Password: " + pass);
		lgbtn.click();
		Library.threadSleep(2000);

		test.info("Click on login button: ");
		
		
     	
     	WebElement dashboardElement = driver.findElement(By.xpath("//*[@id=\"userDropdown\"]/span"));
     	
        if (dashboardElement.isDisplayed()) {
            test.pass("Patrol Login successfully, valid user is displayed on home page");
        } else {
            throw new AssertionError("Valid user not displayed on home page may be Patrol_Login page not working please check.");
        }
    }
    
	
}

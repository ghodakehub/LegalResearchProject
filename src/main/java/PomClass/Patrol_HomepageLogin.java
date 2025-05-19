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

public class Patrol_HomepageLogin  {

	WebDriver driver;
	

	
	@FindBy(xpath = "/html/body/div[2]/div/nav/div/a[2]")
	private WebElement Loginbtn;

	@FindBy(xpath = "//*[@id=\"email\"]")

	private WebElement MaildAddress;

	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement pwd;

	@FindBy(xpath = "//*[@id=\"submit\"]")
	private WebElement lgbtn;
	
	@FindBy(xpath = "//*[@id=\"userDropdown\"]/span")
	private WebElement validusername;


    public Patrol_HomepageLogin (WebDriver driver) {
        this.driver = driver;
       
        PageFactory.initElements(driver, this);
    }    
    
    
	
    public void login(String user, String pass,WebDriver driver) throws InterruptedException {
    
    	 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
         WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/nav/div/a[2]"))); // Update the XPath if needed
         loginButton.click();
         Library.threadSleep(4000);
         MaildAddress.click();
    	MaildAddress.sendKeys(user);
		Library.threadSleep(2000);
		
		
		pwd.sendKeys(pass);
		Library.threadSleep(2000);
		
		
		lgbtn.click();
		Library.threadSleep(2000);

		
		generic.Library.verifyText(driver, "Stack trace", "error on Patrol home page");

		
     	
    }  
	
}

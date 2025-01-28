package PomClass;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;

import generic.BaseLib;

public class Login extends BaseLib {
	
	WebDriver driver;
	ExtentTest test;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[1]/div/input")

	private WebElement username;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[2]/div/div/input")
	private WebElement pwd;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[3]/button")
	private WebElement lgbtn;
	
	@FindBy(xpath = "(//*[@id=\"firstname\"])[2]")
	private WebElement validusername;


    public Login (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test; // Assign ExtentTest to the POM class
        PageFactory.initElements(driver, this);
    }    

	public void login(String user, String pass) throws InterruptedException {

		username.sendKeys(user);
		Thread.sleep(2000);
		test.info("Entered username: " + user);
		pwd.clear();
		test.info("Clear password: " + pass);
		pwd.sendKeys(pass);
		Thread.sleep(2000);
		test.info("Entered Password: " + pass);
		Thread.sleep(2000);
		
		try {
     	    // Perform login action
     	    WebElement loginButton = driver.findElement(By.xpath("(//button[text()='Take me in!'])[1]"));
     	    loginButton.click();
     	    Thread.sleep(2000);
     	} catch (ElementNotInteractableException e) {
     	    // Log specific error
     	    Reporter.log("Login button not interactable: " + e.getMessage());
     	    Assert.fail("Critical error - Login button issue.");
     	} catch (Exception e) {
     	    // Log generic errors
     	    Reporter.log("Unexpected error occurred: " + e.getMessage());
     	    Assert.fail("Test failed due to unexpected error.");
     	}
     	
     	
     	
		test.info("Click on login button: ");
		 WebElement loginpop = driver.findElement(By.xpath("//button[@class='btn btn-primary bootbox-accept']"));
		Thread.sleep(3000);
		JavascriptExecutor js7= (JavascriptExecutor) BaseLib.driver;
		js7.executeScript("arguments[0].scrollIntoView(true);",loginpop );
		loginpop.click();
     	Thread.sleep(4000);
		
	
     	
     	WebElement dashboardElement = driver.findElement(By.xpath("(//*[@id=\"firstname\"]/strong)[2]"));
     	
        if (dashboardElement.isDisplayed()) {
            test.pass("Login successfully ");
        } else {
            throw new AssertionError("Login page not working properly please check");
        }

     	
     
	}
}


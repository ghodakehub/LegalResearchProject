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
import generic.SwitchWindow;

public class Login extends BaseLib {
	
	WebDriver driver;
	ExtentTest test;

    public Login (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test; // Assign ExtentTest to the POM class
        PageFactory.initElements(driver, this);
    }    

	
    @FindBy(xpath = "/html/body/div[2]/div/nav/div/a[2]")
	private WebElement lgbtn1;

	@FindBy(xpath = "//*[@id=\"login-modal\"]/div/div/div[2]/div/div[1]/div/a/div")
	private WebElement lgbtn2;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[1]/div/input")

	private WebElement username;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[2]/div/div/input")
	private WebElement pwd;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[3]/button")
	private WebElement lgbtn;
		
	@FindBy(xpath = "/html/body/div[3]/div/div/div[3]/button[2]")
	private WebElement loginpop;

	
	public WebElement getlgbtn1() {
		return lgbtn1;
	}

	public WebElement getlgbtn2() {
		return lgbtn2;
	}

	public WebElement getusername() {
		return username;
	}

	public WebElement getpwd() {
		return pwd;
	}

	public WebElement getlgbtn() {
		return lgbtn;
	}

	public WebElement getloginpop() {
		return loginpop;
	}

	public void login(String user, String pass) throws InterruptedException {
		

		lgbtn1.click(); // login button for home page first
		test.info("Click on first login button of home page of .com ");
		Thread.sleep(3000);
		lgbtn2.click(); // login button for home page second
		test.info("Click on login button of LR");
		Thread.sleep(3000);

		// Window switch
		SwitchWindow.switchWindowByIndex(BaseLib.driver, 1);
		Thread.sleep(3000);
		
		username.sendKeys(user);
		
		test.info("Enter username");
		Thread.sleep(1000);
		pwd.sendKeys(pass);
		Thread.sleep(1000);
		lgbtn.click();
		
		Thread.sleep(4000);
		JavascriptExecutor js7= (JavascriptExecutor) BaseLib.driver;
		js7.executeScript("arguments[0].scrollIntoView(true);",loginpop );
		loginpop.click();
	
		

	}
}

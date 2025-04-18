package PomClass;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import generic.BaseLib;
import generic.SwitchWindow;


public class Login  {
	
	WebDriver driver;


    public Login (WebDriver driver) {
        this.driver = driver;
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

	@FindBy(xpath = "(//button[contains(@type,'submit')][normalize-space()='Take me in!'])[1]")
	private WebElement lgbtn;
		
	@FindBy(xpath = "//button[normalize-space()='Login']")
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
		
		Thread.sleep(3000);
		lgbtn2.click(); // login button for home page second
				Thread.sleep(3000);

		// Window switch
	SwitchWindow.switchWindowByIndex(driver, 1);
		Thread.sleep(3000);
		
		username.sendKeys(user);
		
		
		Thread.sleep(1000);
		pwd.sendKeys(pass);
		Thread.sleep(1000);
		lgbtn.click();
		
		Thread.sleep(4000);
		JavascriptExecutor js7= (JavascriptExecutor) BaseLib.driver;
		js7.executeScript("arguments[0].scrollIntoView(true);",loginpop );
		loginpop.click();
	
		generic.Library.verifyText(driver, "500 server", "error on lr Loginpage");

	}
}

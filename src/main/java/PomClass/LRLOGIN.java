package PomClass;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import generic.BaseLib;
import generic.EmailUtility;
import generic.SwitchWindow;

public class LRLOGIN  extends BaseLib{

	
	
	
	WebDriver driver;
	


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
    public LRLOGIN (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }    

    public void login(String user, String pass) throws InterruptedException, MessagingException {
        // Click initial login buttons
        lgbtn1.click();
        Thread.sleep(3000);
        lgbtn2.click();
        Thread.sleep(3000);

        // Switch to the new login window/tab
        SwitchWindow.switchWindowByIndex(driver, 1);
        Thread.sleep(3000);

        // Enter credentials
        username.sendKeys(user);
        Thread.sleep(1000);
        pwd.sendKeys(pass);
        Thread.sleep(1000);

        // Click Login
        lgbtn.click();
        Thread.sleep(4000);

      
     
}
}
	


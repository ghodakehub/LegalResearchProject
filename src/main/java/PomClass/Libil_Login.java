package PomClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

import generic.BaseLib;
import io.qameta.allure.Allure;

public class Libil_Login {

	WebDriver driver;
	

	@FindBy(xpath = "//input[@id='email']")

	private WebElement MaildAddress;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement pwd;

	@FindBy(xpath = "/html/body/main/div/div/div/div/div[2]/form/div[4]/div/button")
	private WebElement lgbtn;
	
	@FindBy(xpath = "/html/body/header/div/div/")
	private WebElement admindashboardtitle;


    public Libil_Login (WebDriver driver) {
        this.driver = driver;
      
        PageFactory.initElements(driver, this);
    }    
	
    public void login(String user, String pass) throws InterruptedException {

    	MaildAddress.sendKeys(user);
    	Thread.sleep(1000);
		pwd.sendKeys(pass);
		
		Thread.sleep(2000);
		
		
		
		WebElement element = driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/div[4]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);
		element.click();
	
		Thread.sleep(2000);
		
     	

     	WebElement dashboardElement = driver.findElement(By.xpath("/html/body/header/div/div/h4"));
        if (dashboardElement.isDisplayed()) {
            Allure.step("Libil login successful");
        } else {
            throw new AssertionError(" Admin user is not displayed on home page may be Libil login page not working please check.");
        }
    }
}

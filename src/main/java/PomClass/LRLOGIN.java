package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;

import generic.BaseLib;

public class LRLOGIN  extends BaseLib{

	
	
	
	WebDriver driver;
	ExtentTest test;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[1]/div/input")

	private WebElement username;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[2]/div/div/input")
	private WebElement pwd;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[3]/button")
	private WebElement lgbtn;
		
	@FindBy(xpath = "//button[@class='btn btn-primary bootbox-accept']")
	private WebElement loginpop;
	
	@FindBy(xpath = "(//*[@id=\"firstname\"])[2]")
	private WebElement validusername;


    public LRLOGIN (WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test; // Assign ExtentTest to the POM class
        PageFactory.initElements(driver, this);
    }    

	public void login(String user, String pass) throws InterruptedException {

	
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

	        try {
	           
	            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Email'])[1]")));
	            emailField.sendKeys("pratiksha.damodar@legitquest.com");

	            // Wait for Password Field and Enter Password
	            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name='password'])[1]")));
	            passwordField.sendKeys("YourSecurePassword"); // Replace with actual password

	            // Click the Login Button
	            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(), 'Take me in!')])[1]")));
	            loginButton.click();

	            // Check if the "Login Alert" popup appears
	            try {
	                WebElement popupLoginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(text(), ' Login')])[1]")));
	                popupLoginButton.click();
	                System.out.println("🔹 Login Alert detected and handled.");
	            } catch (Exception e) {
	                System.out.println("🔹 No Login Alert detected. Continuing...");
	            }

	            // Validate Successful Login
	            boolean isLoginSuccessful = wait.until(ExpectedConditions.urlContains("https://www.legitquest.com/home")); // Adjust expected URL
	            if (isLoginSuccessful) {
	                System.out.println("✅ Login Successful!");
	            } else {
	                System.out.println("❌ Login Failed!");
	            }

	        } catch (Exception e) {
	            System.out.println("❌ Error occurred: " + e.getMessage());
	        } finally {
	            // Close the browser
	           // driver.quit();
	        }
	    
	

}
}
	


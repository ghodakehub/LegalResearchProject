package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import UtilityClass.Library;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.ForMultiplemailReceipent;

public class Patrol_HomepageLogin  {

	WebDriver driver;
	

	
    @FindBy(xpath = "/html/body/div[2]/div/nav/div/a[2]")
    private WebElement loginBtn;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(xpath = "//*[@id='userDropdown']/span")
    private WebElement validUsername;

    
    public Patrol_HomepageLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
    public void login(String username, String password) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

      
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

        
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(username);

        passwordInput.sendKeys(password);
        submitBtn.click();

        
        Thread.sleep(4000); 

        String pageSource = driver.getPageSource().toLowerCase();

        boolean isError = pageSource.contains("stack trace") ||
                          pageSource.contains("connection refused") ||
                          pageSource.contains("mongo") ||
                          pageSource.contains("sqlstate") ||
                          pageSource.contains("server error") ||
                          pageSource.contains("internal server error");

        if (isError) {
            String screenshot = UtilityClass.Capaturescreenshot(driver, "Patrol_Login_Failure");
            ForMultiplemailReceipent.sendEmail(
                driver,
                new String[]{"ghodake6896@gmail.com", "mamta.kashyap@legitquest.com"},
                "⚠️ Patrol Login Error Detected",
                "Login was attempted, but a backend/server error was detected. Screenshot attached.\nURL: " + driver.getCurrentUrl(),
                screenshot,
                driver.getCurrentUrl()
            );
            Assert.fail("Patrol login failed with server/database error.");
        } else {
            System.out.println(" Patrol login successful.");
        }
    }
}
	


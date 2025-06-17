package PomClass;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import generic.BaseLib;
import generic.EmailUtility;

public class Libil_homepage {
	
	WebDriver driver;


@FindBy(xpath = "//input[@id='email']")

private WebElement MaildAddress;

@FindBy(xpath = "//input[@id='password']")
private WebElement pwd;

@FindBy(xpath = "/html/body/main/div/div/div/div/div[2]/form/div[4]/div/button")
private WebElement lgbtn;

@FindBy(xpath = "/html/body/header/div/div/")
private WebElement admindashboardtitle;


public Libil_homepage (WebDriver driver) {
    this.driver = driver;
  
    PageFactory.initElements(driver, this);
}    

public void verifyLibilHomepage(String user, String pass) throws InterruptedException, MessagingException {

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

	Thread.sleep(3000);
	String pageSource = driver.getPageSource().toLowerCase();
    if (pageSource.contains("sqlstate") || pageSource.contains("connection refused") || pageSource.contains("500 internal server error")) {
        System.out.println("Laravel DB error detected after login!");
        
        System.out.println("LIBIL server error detected.");
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        generic.AllureListeners.captureScreenshot(driver, "libillogin");

        
        generic.Library.errorUrls.add(driver.getCurrentUrl());
        generic.Library.screenshotBytesList.add(screenshotBytes);

        generic.AllureListeners.captureScreenshot(BaseLib.driver, "LIBIL HOMEPAGE ERROR");

        String[] recipients = {
            "ghodake6896@gmail.com"
           
        };

        List<String> urls = new ArrayList<>();
        urls.add(driver.getCurrentUrl());

        EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients,
            "LIBIL- HomePage ",
            "Please check error detected on LIBIL home page.\nPlease check the attached screenshot and url.",
            urls,
            generic.Library.screenshotBytesList
        );

        Assert.fail("Test Failed: Real server error detected.");
    } else {
        System.out.println("LIBIL login page loaded fine, no server error.");
    }
	
}

}

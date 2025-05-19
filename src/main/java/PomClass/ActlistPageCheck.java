package PomClass;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import UtilityClass.UtilityClass;
import generic.ForMultiplemailReceipent;

public class ActlistPageCheck {
	WebDriver driver;


    public ActlistPageCheck (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }    


	public void clickActListAndCheckResponsiveness() throws Exception {
	   
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	   
	    WebElement actListButton = driver.findElement(By.xpath("/html/body/div[3]/main/a[2]/button"));  
	    actListButton.click();

	   
	    try {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id='allAct']")));  
	        System.out.println(" Act list loaded successfully.");
	    } catch (TimeoutException e) {
	        System.out.println(" Act list failed to load — page may be unresponsive.");

	        
	        String screenshot = UtilityClass.Capaturescreenshot(driver, "ActList_Unresponsive");

	        
	        String[] recipients = {"ghodake6896@email.com"};
	        ForMultiplemailReceipent.sendEmail(
	                driver, recipients,
	                "LR ActList",
	                "The Act List page did not load properly. Possibly unresponsive. Screenshot attached.",
	                screenshot,
	                driver.getCurrentUrl()
	        );

	       
	        Assert.fail("Page may be unresponsive or hung after clicking Act List.");
	    }
	}
}

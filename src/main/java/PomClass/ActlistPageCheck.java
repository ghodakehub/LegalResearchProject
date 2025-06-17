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
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        
        WebElement actListButton = driver.findElement(By.xpath("/html/body/div[3]/main/a[2]/button"));
        actListButton.click();

        try {
            
            try {
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[contains(@src, 'lq-spin.gif')]")
                ));
            } catch (TimeoutException te) {
                System.out.println("Loader did not appear — may have been skipped.");
            }

            
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//img[contains(@src, 'lq-spin.gif')]")
            ));

           
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[@id='allAct']")
            ));

            System.out.println("Act list loaded successfully.");

        } catch (TimeoutException e) {
            System.out.println("Act list failed to load — likely stuck on loading spinner or unresponsive.");

           
            String screenshot = UtilityClass.Capaturescreenshot(driver, "ActList_Unresponsive");

           
            String[] recipients = {"ghodake6896@gmail.com"};
            ForMultiplemailReceipent.sendEmail(
                driver, recipients,
                "LR Act List Load Failure",
                "The Act List page might be stuck on the loader or failed to load properly. Screenshot is attached.",
                screenshot,
                driver.getCurrentUrl()
            );

           
            Assert.fail("Act List page did not load — possible loading hang.");
        }
    }

}
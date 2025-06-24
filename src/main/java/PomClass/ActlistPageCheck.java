package PomClass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
            // Step 1: Wait for loader (optional)
            try {
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[contains(@src, 'lq-spin.gif')]")
                ));
            } catch (TimeoutException te) {
                System.out.println("Loader did not appear — may have been skipped.");
            }

            // Step 2: Wait for loader to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//img[contains(@src, 'lq-spin.gif')]")
            ));

            // ✅ Step 3: Wait for at least one act link to appear
            WebElement firstActLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='result']/tr[1]/td[1]/a")
            ));

            // Step 4: Optionally log count of act links
            List<WebElement> actLinks = driver.findElements(By.xpath("//*[@id='result']/tr/td[1]/a"));
            System.out.println("Act List page loaded with " + actLinks.size() + " act(s).");

        } catch (Exception e) {
            System.err.println("Act List content did not load properly: " + e.getMessage());

            String screenshot = UtilityClass.Capaturescreenshot(driver, "ActList_Unresponsive");

            String[] recipients = {"ghodake6896@gmail.com"};
            ForMultiplemailReceipent.sendEmail(
                driver,
                recipients,
                "Act List Page Load Issue",
                "The Act List page failed to load act links. Screenshot attached.\nURL: " + driver.getCurrentUrl(),
                screenshot,
                driver.getCurrentUrl()
            );

            Assert.fail("❌ Act List page failed to load with visible content. Exception: " + e.getMessage());
        }
    }
}
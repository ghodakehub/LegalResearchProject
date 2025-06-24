package PomClass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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
import generic.AllureListeners;
import generic.BaseLib;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;

public class VerifySearchResult extends BaseLib{
	
	WebDriver driver;
	
	@FindBy(xpath = "//input[@id='search']") // Main Search Box
	private WebElement searchbox;

	@FindBy(xpath = "(//i[@class='fa fa-search'])[1]") // Main Search Icon (Button)
	private WebElement SearchButton;

	@FindBy(xpath = "//*[@id=\"court\"]/label/text()") // All Button result
	private WebElement court;
	
	
	// there ===========================
    public  VerifySearchResult(WebDriver driver) {
        this.driver = driver;
       
        PageFactory.initElements(driver, this);
    }   

	
	
    public void SearhResultverify(WebDriver driver, String keyword) throws InterruptedException {
        try {
            // Step 1: Enter keyword in the search box
            WebElement searchBox = driver.findElement(By.xpath("//*[@id='search']"));
            searchBox.clear();
            searchBox.sendKeys(keyword);

            // Step 2: Click the Search button
            driver.findElement(By.id("btnSearch")).click();

            // Step 3: Wait for the loading spinner to disappear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//img[contains(@src, 'lq-spin.gif')]")
                ));
                System.out.println(" Spinner disappeared — page loaded.");
            } catch (TimeoutException e) {
                System.out.println(" Spinner did not disappear — possible loading issue.");
            }
            
            Thread.sleep(2000);

            
            List<WebElement> resultLinks = driver.findElements(By.xpath("//u[@class='result_title']"));
            boolean hasResults = resultLinks.size() > 0;

            if (!hasResults) {
                System.out.println(" No search results found for keyword: " + keyword);

               
                String screenshot = UtilityClass.Capaturescreenshot(driver, "NoSearchResults_" + keyword);

                
                ForMultiplemailReceipent.sendEmail(
                    driver,
                    new String[]{"ghodake6896@gmail.com"},
                    "LR Search Returned No Results",
                    "Please check search results not working returned no results on LegitQuest.\nPlease check attached url and screenshot"
                    screenshot,
                    driver.getCurrentUrl()
                );

               
                Assert.fail("Test Case Failed: No results found for keyword '" + keyword + "'");
            } else {
                System.out.println(" Results found for keyword: " + keyword);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(" Exception during search result verification: " + e.getMessage());
        }
    }
}
	




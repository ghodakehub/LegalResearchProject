package PomClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.ForMultiplemailReceipent;

public class NASACTPAGE {
	WebDriver driver;


    public NASACTPAGE (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }    
    public void loginAndCheckError(WebDriver driver) {
        try {
            driver.get("http://148.113.38.3/login");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


            String pageSource = driver.getPageSource().toLowerCase();
            String title = driver.getTitle().toLowerCase();

            
            if (
                pageSource.contains("this site can’t be reached") ||
                pageSource.contains("err_connection_timed_out") ||
                pageSource.contains("sqlstate") ||
                pageSource.contains("too many connections") ||
                pageSource.contains("illuminate\\database\\queryexception") ||
                pageSource.contains("database error") ||
                pageSource.contains("server error") ||
                pageSource.contains("internal server error") ||
                title.contains("error")
            ) {
               
                String screenshot = UtilityClass.Capaturescreenshot(BaseLib.driver, "error_nas_png");
                String testUrl = BaseLib.driver.getCurrentUrl();

                // Send email
                ForMultiplemailReceipent.sendEmail(
                    BaseLib.driver,
                    new String[]{"ghodake6896@gmail.com"},
                    "NAS Error Detected",
                    "NAS is not working. Possible server or SQL error detected. Please find the attached screenshot for details.\n\nURL: " + testUrl,
                    screenshot,
                    testUrl
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }



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
    public void loginAndCheckError() {
        String testUrl = "http://148.113.38.3/login";

        try {
            driver.get(testUrl);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Give the page a moment to load content fully
            Thread.sleep(3000); // Optional small delay if content loads slowly

            String pageSource = driver.getPageSource().toLowerCase();
            String title = driver.getTitle().toLowerCase();

            // ✅ Check for actual failure content
            boolean isError = false;

            if (pageSource.contains("this site can’t be reached") ||
                pageSource.contains("site can’t be reached") ||
                pageSource.contains("err_connection_timed_out") ||
                pageSource.contains("sqlstate") ||
                pageSource.contains("illuminate\\database\\queryexception") ||
                pageSource.contains("too many connections") ||
                pageSource.contains("server error") ||
                pageSource.contains("internal server error") ||
                pageSource.contains("database error") ||
                title.contains("error")) {

                isError = true;
            }

            if (isError) {
                String screenshot = UtilityClass.Capaturescreenshot(driver, "NAS_Error");
                ForMultiplemailReceipent.sendEmail(
                    driver,
                    new String[]{"ghodake6896@gmail.com", "mamta.Kashyap@legitquest.com"},
                    "NAS Login Page Error Detected",
                    "The NAS login page failed to load correctly. A possible server or SQL error has been detected. Please check the attached screenshot.\n\nURL: " + testUrl,
                    screenshot,
                    testUrl
                );
            } else {
                System.out.println("NAS login page loaded successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



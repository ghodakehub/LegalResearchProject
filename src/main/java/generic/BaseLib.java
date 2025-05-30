package generic;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import ConfigurationPath.PathFile;

public class BaseLib {
	
	 public static WebDriver driver;

	    @BeforeClass
	    public void initializeBrowser() {
	        System.setProperty("webdriver.chrome.driver", PathFile.driverpath);

	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*");

	        driver = new ChromeDriver(options);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // default wait
	    }

	   
	    @AfterClass(alwaysRun = true)
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	    public void implicitWait(int seconds) {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	    }
	}


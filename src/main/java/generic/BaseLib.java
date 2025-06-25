package generic;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import ConfigurationPath.PathFile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseLib {
	
	 public static WebDriver driver;

	    @BeforeClass
	    public void initializeBrowser() {
	        //System.setProperty("webdriver.chrome.driver", PathFile.driverpath);
	    	WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*");
	      //  options.addArguments("--headless");           // Run in headless mode
	        options.addArguments("--disable-gpu");        // Optional but good for compatibility
	       

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


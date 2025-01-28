package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import ConfigurationPath.PathFile;

public class BaseLib {
	
		public static WebDriver driver;
		public void initailizbrowser()
		{
			System.setProperty("webdriver.chrome.driver", PathFile.driverpath);
			ChromeOptions opt= new ChromeOptions();
			opt.addArguments("--remote-allow-origins=*");
			
		
			driver = new ChromeDriver(opt);
			driver.manage().window().maximize();
		//	driver.get(PathFile.LRURL);
			
		}
		@SuppressWarnings("deprecation")
		public void implicitwait(int wait)
		{
			driver.manage().timeouts().implicitlyWait(wait,TimeUnit.SECONDS);
			
		}
		
		

}
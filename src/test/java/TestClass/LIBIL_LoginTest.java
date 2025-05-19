package TestClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.Libil_Login;
import generic.BaseLib;
import generic.EmailUtility;
import generic.ErrorChecker;


public class LIBIL_LoginTest extends BaseLib{
	
	
	
	
	
	@Test
	public void VerifyLoginPageOFLibil() throws InterruptedException, IOException, MessagingException
	
	{
		BaseLib.driver.get(PathFile.LIBILurl);
		Thread.sleep(2000);
		Libil_Login log = new Libil_Login(driver);
		log.login("admin@gmail.com","Admin@345");
		
		  
		if (ErrorChecker.isServerErrorPresent(driver)) {
	        System.out.println("LIBIL server error detected.");
	        generic.AllureListeners.captureScreenshot(BaseLib.driver, "LIBIL_login_server_error");

	        String[] recipients = {
	            "ghodake6896@gmail.com"
	        };

	        List<String> urls = new ArrayList<>();
	        urls.add(driver.getCurrentUrl());

	        EmailUtility.sendSummaryEmailWithScreenshots(driver, recipients,
	            "LIBIL ",
	            "Please check error detected on LIBIL login page.\nPlease check the attached screenshot and url.",
	            urls,
	            generic.Library.screenshotBytesList
	        );

	        Assert.fail("Test Failed: Real server error detected.");
	    } else {
	        System.out.println("LIBIL login page loaded fine, no server error.");
	    }
	}
    }


		
	



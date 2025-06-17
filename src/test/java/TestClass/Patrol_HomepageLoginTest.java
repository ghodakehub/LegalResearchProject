package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.Patrol_HomepageLogin;
import UtilityClass.UtilityClass;
import generic.BaseLib;
import generic.EmailUtility;
import generic.ForMultiplemailReceipent;



public class Patrol_HomepageLoginTest extends BaseLib{

	
	
	@Test
	public void LoginHomePageOfPatrol() throws InterruptedException, IOException, MessagingException
	
	{
		
		BaseLib.driver.get(PathFile.PatroHomePageurl);
		Patrol_HomepageLogin homelog= new Patrol_HomepageLogin(driver);
		
		homelog.login("pratiksha.damodar@legitquest.com","Patrol@123", BaseLib.driver);
		
		 if (!generic.Library.errorUrls.isEmpty()) {
	            System.out.println("Patrolhome page verificaiton");
	           
	            String screenshot=  UtilityClass.Capaturescreenshot(BaseLib.driver,"error on homepage" );
				
				String testUrl = BaseLib.driver.getCurrentUrl();  
				 ForMultiplemailReceipent.sendEmail(
		            	   BaseLib.driver, new String[]{"ghodake6896@gmail.com"},
		            	    "PatrolHomepage ",
		            	    "Issue detected on Patrol home page please check , please find the attached screenshot for details." ,
		            	    screenshot,testUrl
		            	   
		            	);
				 Assert.fail("Test Case Failed: patrolHomepage");
	           
	        } else {
	            System.out.println("Patrolhome page open successfully");
	        }
	    }


	
	
}

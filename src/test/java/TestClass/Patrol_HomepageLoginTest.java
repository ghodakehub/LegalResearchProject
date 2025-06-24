package TestClass;

import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.Patrol_HomepageLogin;
import generic.BaseLib;




public class Patrol_HomepageLoginTest extends BaseLib{

	
	
	@Test
	public void LoginHomePageOfPatrol() throws Exception
	
	{
		
		BaseLib.driver.get(PathFile.PatroHomePageurl);
		Patrol_HomepageLogin homelog= new Patrol_HomepageLogin(driver);
		
		homelog.login("pratiksha.damodar@legitquest.com","Patrol@123");
		
		
	            System.out.println("Patrolhome page open successfully");
	        }
	    }


	
	


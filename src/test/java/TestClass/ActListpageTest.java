package TestClass;

import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.ActlistPageCheck;
import PomClass.Login;
import generic.BaseLib;

public class ActListpageTest extends BaseLib {

	
	

	
	@Test
	public void verifyActlistpage() throws Exception
	
	{
		
		BaseLib.driver.get(PathFile.LRURL);
		Login log = new Login(driver);
		log.login("pratiksha.damodar@legitquest.com","lq@123");
		ActlistPageCheck results= new ActlistPageCheck(driver);
		results.clickActListAndCheckResponsiveness();
		
		
	}

}

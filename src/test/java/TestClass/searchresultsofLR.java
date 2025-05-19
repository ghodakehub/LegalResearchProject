package TestClass;

import java.io.IOException;
import org.testng.annotations.Test;

import ConfigurationPath.PathFile;
import PomClass.Login;
import PomClass.VerifySearchResult;
import generic.BaseLib;


public class searchresultsofLR extends BaseLib {

	
	

	
	@Test
	public void LRsearchResult() throws InterruptedException, IOException
	
	{
		
		BaseLib.driver.get(PathFile.LRURL);
		Login log = new Login(driver);
		log.login("pratiksha.damodar@legitquest.com","lq@123");
		VerifySearchResult results= new VerifySearchResult(driver);
		results.SearhResultverify(driver,"The");
		
		
	}
} 


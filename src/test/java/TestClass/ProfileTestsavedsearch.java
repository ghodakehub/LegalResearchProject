package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.Login;
import PomClass.Profilesavedsearch;
import generic.BaseLib;

public class ProfileTestsavedsearch extends BaseLib {
	
	
	
	@Test
	public void verifyprofile() throws InterruptedException, IOException, MessagingException
	
	{
			
			
		BaseLib.driver.get(PathFile.LRURL);
		Login log = new Login(driver);
		log.login("pratiksha.damodar@legitquest.com","lq@123");
			Profilesavedsearch page = new Profilesavedsearch(driver);
			page.testSavedSearchError();

}
}




package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.LRLOGIN;
import generic.BaseLib;

public class NewLrLoginpage  extends BaseLib {
	
	
	
	@Test
	public void VerifyLoginPageOFLR() throws InterruptedException, IOException, MessagingException
	
	{
			
			
			BaseLib.driver.get(PathFile.LRURL);
			LRLOGIN log = new LRLOGIN(driver);
			log.login("pratiksha.damodar@legitquest.com","lq@123");

}
}

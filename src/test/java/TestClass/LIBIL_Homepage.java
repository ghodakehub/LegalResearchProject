package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.annotations.Test;
import ConfigurationPath.PathFile;
import PomClass.Libil_homepage;
import generic.BaseLib;


public class LIBIL_Homepage  extends BaseLib{
	
	
	
	
	
	@Test
	public void VerifyLoginPageOFLibil() throws InterruptedException, IOException, MessagingException
	
	{
		BaseLib.driver.get(PathFile.LIBILurl);
		Thread.sleep(2000);
		Libil_homepage home = new Libil_homepage(driver);
		home.verifyLibilHomepage("admin@gmail.com","Admin@345");
		
}

	}
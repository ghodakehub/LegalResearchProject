package UtilityClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class UtilityClass {
	
//	ExtentReportManager magg;
	
	
	
	
	public  static String Capaturescreenshot(WebDriver driver , String FrontpageOFweb) throws IOException 
	{
		
		 String dateName= new SimpleDateFormat("yyyy/MMddhmmss").format(new Date());
		 
		 TakesScreenshot ts=(TakesScreenshot)driver;
		 File source=ts.getScreenshotAs(OutputType.FILE);
		 String destination= System.getProperty("user.dir")+"/Screenshots/" +FrontpageOFweb +dateName+".png";
		 
		 File finalDestination =new File(destination);
		FileUtils.copyFile(source, finalDestination);
		 
		 return destination;
		
	}
	
	/* public static String readPropertyFileData( String key) throws IOException
	 {
		 Properties prop= new Properties();
		 FileInputStream file= new FileInputStream("C:\\Users\\Super\\eclipse-workspace\\TestProject");
		 prop.load(file);
		 String value= prop.getProperty(key);
		 return value;
	 }
	 
	
		 public void sendEmail() throws EmailException {
				Email email =new SimpleEmail();
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("ghodake68@gmail.com","vgdk nivd xoqm noyr"));
				email.setSSLOnConnect(true);
				email.setFrom("ghodake68@gmail.com");
				email.setSubject("Website down alert Message");
				email.setMsg("Hi Team Search Result Not working please check out");
				email.addTo("ghodake68@gmail.com");
				
				//email.addTo("mamta.Kashyap@legitquest.com.com");
				//email.addTo("rajan.batra@legitquest.com");
				//email.addTo("karan.kalia@legitquest.com");
				email.send();
			}*/
	 
	 
	
	
	
	

}

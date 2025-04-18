package generic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class MailTestListener implements ITestListener {
	
	@Override
	    public void onTestFailure(ITestResult result) {
	        String testName = result.getName();
	        String screenshotPath = captureScreenshot(testName); // Implement your screenshot logic
	        String subject = "Test Failure: " + testName;
	        String body = "Test case \"" + testName;
	     //   EmailUtility.sendSummaryEmailWithScreenshots(driver, subject, message, Library.errorUrls, Library.screenshotBytesList);

	    }

	    private String captureScreenshot(String testName) {
	    	 String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
		        File screenshot = ((TakesScreenshot)BaseLib.driver).getScreenshotAs(OutputType.FILE);
		        try {
		            FileUtils.copyFile(screenshot, new File(screenshotPath));
		            System.out.println("Screenshot captured: " + screenshotPath);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        return screenshotPath;
		    }
	    }
	




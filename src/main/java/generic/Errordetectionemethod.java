package generic;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Errordetectionemethod {
	public static boolean isAnyError = false;

	public static boolean checkForServerError(WebDriver driver, String pageName) {
	    boolean isErrorDetected = false;
	    String pageSource = driver.getPageSource().toLowerCase();

	    List<String> errorKeywords = Arrays.asList(
	        "http error 500",
	        "internal server error",
	        "this page isn’t working",
	        "500 server error"

	    );

	    for (String keyword : errorKeywords) {
	        if (pageSource.contains(keyword)) {
	            isErrorDetected = true;
	            break;
	        }
	    }

	    // Extra fallback: blank page or error title
	    if (!isErrorDetected) {
	        if (pageSource.trim().isEmpty() || driver.getTitle().toLowerCase().contains("error")) {
	            isErrorDetected = true;
	        }
	    }

	    // Optionally check document ready state
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        String readyState = (String) js.executeScript("return document.readyState");
	        if (!"complete".equals(readyState)) {
	            isErrorDetected = true;
	        }
	    } catch (Exception e) {
	        // Ignore JS errors
	    }

	    if (isErrorDetected) {
	        System.out.println("" + pageName + " - Server Error Detected (500 or equivalent)");
	        isAnyError = true;
	    } else {
	        System.out.println("" + pageName + " - No server error found.");
	    }

	    return isErrorDetected;
	}
}

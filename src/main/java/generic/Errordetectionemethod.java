package generic;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class Errordetectionemethod {
	public static boolean isAnyError = false;

	public static boolean checkForServerError(WebDriver driver, String pageName) {
	    boolean isErrorDetected = false;
	    String pageSource = driver.getPageSource().toLowerCase();  // make case insensitive

	    // List of common error signatures
	    List<String> errorKeywords = Arrays.asList(
	        "http error 500",
	        "internal server error",
	        "this page isn’t working",
	        "500 server error",
	        "currently unable to handle this request",
	        "500 server",
	        "server encountered an error",
	        "unexpected error",
	        "something went wrong",
	        "HTTP 500 Internal Server Error"
	    );

	    for (String keyword : errorKeywords) {
	        if (pageSource.contains(keyword)) {
	            isErrorDetected = true;
	            break;
	        }
	    }

	    if (isErrorDetected) {
	        String errorMsg = "❌ " + pageName + " - Server Error Detected (500 or equivalent)";
	        System.out.println(errorMsg);
	        isAnyError = true;  // set the global flag to true
	    } else {
	        String successMsg = "✅ " + pageName + " - No server error found.";
	        System.out.println(successMsg);
	    }

	    return isErrorDetected;
	}
}

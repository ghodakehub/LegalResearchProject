package generic;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class ErrorChecker {
	
	  public static boolean isServerErrorPresent(WebDriver driver) {
	        String pageSource = driver.getPageSource().toLowerCase();

	        List<String> errorKeywords = Arrays.asList(
	            "http 500",
	            "internal server error",
	            "sqlstate",
	            "illuminate\\database\\queryexception"
	        );

	        for (String keyword : errorKeywords) {
	            if (pageSource.contains(keyword)) {
	                return true;
	            }
	        }

	        return false;
	    }
	}






package generic;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class SwitchWindow {
	
	public static void switchWindowByIndex(WebDriver driver, int Tabno) {

		Set<String> windows = driver.getWindowHandles();

		ArrayList<String> tabs = new ArrayList<String>(windows);

		driver.switchTo().window(tabs.get(Tabno));

	}

}
package generic;

import java.util.ArrayList;
import java.util.List;

public class TestResultCollector {
	 private List<String> errorUrls = new ArrayList<>();
	    private List<byte[]> screenshotBytesList = new ArrayList<>();

	    public void add(String url, byte[] screenshot) {
	        errorUrls.add(url);
	        screenshotBytesList.add(screenshot);
	    }

	    public List<String> getErrorUrls() {
	        return errorUrls;
	    }

	    public List<byte[]> getScreenshotBytesList() {
	        return screenshotBytesList;
	    }

	    public boolean hasErrors() {
	        return !errorUrls.isEmpty();
	    }
	}
	 
	



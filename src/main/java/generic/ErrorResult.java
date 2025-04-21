package generic;

public class ErrorResult {
	 public boolean hasError;
	    public String url;
	    public byte[] screenshot;

	    public ErrorResult(boolean hasError, String url, byte[] screenshot) {
	        this.hasError = hasError;
	        this.url = url;
	        this.screenshot = screenshot;
	    }

}

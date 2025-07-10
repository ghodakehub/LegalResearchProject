package TestClass;

import org.testng.annotations.Test;

import PomClass.NASACTPAGE;
import generic.BaseLib;

public class NASTESTPAGE extends BaseLib {

	
	

	
	@Test
	public void verifyActlistpage() throws Exception
	
	{
		
		NASACTPAGE results= new NASACTPAGE(driver);
		results.loginAndCheckError();
		
		
	}

}

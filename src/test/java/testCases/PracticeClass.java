package testCases;

import org.testng.annotations.Test;

public class PracticeClass {
	
	
	@Test (dataProvider = "loginData", dataProviderClass = utilities.DataProviders.class)
	public void myTestPractices (String em , String psw, String exp) {
		
		
		System.out.println(em + "    " + psw);
	}
	@Test(dataProvider = "loginData"  , dataProviderClass = utilities.DataProviders.class)
	void tats(String aa , String bb, String cc) {
		System.out.println( aa );
		System.out.println( bb );
		//System.out.println( cc );
	}
	

}

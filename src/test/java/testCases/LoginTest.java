package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {
	
	@Test (dataProvider = "loginData" ,dataProviderClass = utilities.DataProviders.class)
	public void verifyLoginFunctionWithInvalidCredentials(String email , String password , String Exp) {
		
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		//Assert.assertTrue(lp.successLogin());
		System.out.println(  email + password);
		
		
	}
	//@Test
	public void verifyLoginFunctionWithvalidCredentials() {
		
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		hp.clickMyAccount();
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
	//	Assert.assertTrue(lp.successLogin());
		
		
		
	}
	

}

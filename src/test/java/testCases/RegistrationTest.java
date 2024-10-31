package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class RegistrationTest extends BaseClass {
	// WebDriver driver;
//	HomePage hp = new HomePage(driver);
//	RegistrationPage rp = new RegistrationPage(driver);

	@Test
	public void registerWithValidCredentials() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		RegistrationPage rp = new RegistrationPage(driver);
		logger.info("*****test started for registration test case*******");
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("*****clicked on register*******");
		rp.setFirstName("Samir");
		rp.setLastName("Sami");
		rp.setEmail(randomString() + "@gmail.com");
		rp.setTelephone("1256987541");
		rp.setPassword("Abcdr78");
		rp.setConfirmPassword("Abcdr78");
		rp.setPrivacyPolicy();
		rp.clickContinue();
		String confirmationMessage = rp.getConfirmationMsg();
		Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!");

	}

}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups={"regression", "master"})
	public void test_Account_Registration()
	{
		try
		{
			log.info("*****TC001_AccountRegistrationTest Started*****");
			HomePage homepage=new HomePage(driver);
			log.info("*****Clicked My Account*****");
			homepage.clickMyAccount();
			log.info("*****Clicked Register*****");
			homepage.clickRegister();
			
			log.info("*****Registration Started*****");
			RegistrationPage register=new RegistrationPage(driver);
			register.setFirstName(randomString());
			register.setLastName(randomString());
			register.setEmail(randomString()+"@gmail.com");
			register.setTelephone(randomNumber());
			
			String password=randomStringNumber();
			register.setPassword(password);
			register.setConfirmPassword(password);
			register.clickPolicy();
			register.clickContinue();
			log.info("*****Registration Ends*****");
			String confmsg=register.ConfirmationMessage();
			log.info("*****Validating Confirmation Message*****");
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}

		catch(Exception e)
		{
			System.out.println(e.getMessage());
			log.error("Test Execution Failed");
			log.debug("Debug Logs");
			Assert.fail();
		}
		log.info("*****TC001_AccountRegistrationTest Completed*****");
	}
	
	

}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDTTest extends BaseClass
{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="regression")
	public void verifyLogin_DDT(String email, String pwd, String result)
	{
		log.info("**********Starting TC003_LoginDDTTest************");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage myaccount=new MyAccountPage(driver);
		String txtMyAccount=myaccount.textMyAccountIsDisplay();
		
		/*
		 * If Data is valid
		 * 	1. Login Success --> test should Pass --> Logout
		 * 	2. Login Failed --> test should Fail
		 * 
		 * If Data is Invalid
		 * 	1. Login Success--> test should Fail--> Logout
		 * 	2. Login Failed --> test should Pass
		 */
		
		if(result.equals("Valid"))
		{
			if(txtMyAccount.equals("My Account"))
			{
				 /* If Data is valid
				 * 	1. Login Success --> test should Pass --> Logout */
				myaccount.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				//2. Login Failed --> test should Fail
				Assert.assertTrue(false);
			}
		}
		else
		{
			// * If Data is Invalid
			 //* 	1. Login Success--> test should Fail--> Logout
			if(txtMyAccount.equals("My Account"))
			{
				myaccount.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				//2. Login Failed --> test should Pass
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
		log.info("**********Finished TC003_LoginDDTTest************");
	}

}

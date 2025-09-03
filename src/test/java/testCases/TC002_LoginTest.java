package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.TabletsPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"sanity","master"})
	public void test_LoginTest()
	{
		log.info("****** TC002_LoginTest Started ******");
		try
		{
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmailAddress(property.getProperty("UserEmail"));
			lp.setPassword(property.getProperty("LoginPwd"));
			lp.clickLogin();
			
			
			MyAccountPage myacc=new MyAccountPage(driver);
			String verifymsg=myacc.textMyAccountIsDisplay();
			if(verifymsg.equals("My Account"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertFalse(true);
			}
			
			log.info(".........TC004_AddToCartTest Started......");
			myacc.clickTablets();
			log.info(".........TC004_AddToCartTest Finished......");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		log.info("****** TC002_LoginTest Ended ******");
		
	}
}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.TabletsPage;
import testBase.BaseClass;

public class TC004_AddToCartTest  extends BaseClass
{
	@Test
	public void AddToCart()
	{
		log.info(".........TC004_AddToCartTest Started......");
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
		myacc.clickTablets();
		
		TabletsPage tp=new TabletsPage(driver);		
		tp.clickAddToCart();
		tp.clickCheckout();
		
		CheckoutPage checkout=new CheckoutPage(driver);
		String chkoutmsg=checkout.IsthisCheckoutPage();
		
		if(chkoutmsg.contains("Shopping Cart "))
		{
			System.out.println("you are successfully checked out the product");
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		log.info(".........TC004_AddToCartTest Finished......");
	}
}

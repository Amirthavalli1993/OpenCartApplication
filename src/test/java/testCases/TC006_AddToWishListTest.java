package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.WishListPage;
import testBase.BaseClass;

public class TC006_AddToWishListTest extends BaseClass
{
	@Test
	public void AddToWishListTest()
	{
		try
		{
			log.info("***********TC006_AddToWishListTest started**********");
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmailAddress(property.getProperty("UserEmail"));
			lp.setPassword(property.getProperty("LoginPwd"));
			lp.clickLogin();
			lp.clickWishList();
			
			WishListPage wishlist=new WishListPage(driver);
			List<WebElement> wishlistItems=wishlist.WishListTable();
			System.out.println(wishlistItems.size()+" item in the WishList");
			for(WebElement listItems:wishlistItems)
			{
				System.out.println(listItems.getText());
			}
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		log.info("*************TC006_AddToWishListTest Finished************");
	}

}

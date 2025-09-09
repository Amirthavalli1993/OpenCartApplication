package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage
{
	public WishListPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr")
	List<WebElement> tblWishList;
	
	public List<WebElement> WishListTable()
	{
		return tblWishList;
	}

}

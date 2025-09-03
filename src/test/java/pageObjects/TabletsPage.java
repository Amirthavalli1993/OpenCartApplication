package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TabletsPage extends BasePage
{
	public WebDriver driver;
	
	public TabletsPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(how=How.XPATH, using="//span[normalize-space()='Add to Cart']")
	WebElement btnAddToCart;
	
	@FindBy(how=How.XPATH, using="//span[normalize-space()='Checkout']")
	WebElement btnCheckout;
	
	public void clickAddToCart()
	{
		btnAddToCart.click();
	}
	
	public void clickCheckout()
	{
		btnCheckout.click();
	}
}

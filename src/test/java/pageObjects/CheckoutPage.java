package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage 
{
	public WebDriver driver;
	
	//constructor
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locator
	@FindBy(xpath="//h1[contains(text(),'Shopping Cart')]")
	WebElement txtCheckoutConfirm;
	
	//Action
	public String IsthisCheckoutPage()
	{
		return txtCheckoutConfirm.getText();
	}
}

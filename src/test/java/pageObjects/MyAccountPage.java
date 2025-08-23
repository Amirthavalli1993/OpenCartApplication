package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccountPage extends BasePage 
{
	//constructor
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	@FindBy(how=How.XPATH, using="//h2[normalize-space()='My Account']")
	WebElement msgMyAccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	//Actions
	public String textMyAccountIsDisplay()
	{
		return msgMyAccount.getText();
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
}

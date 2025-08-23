package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage
{
	//constructor
	public LoginPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locators
	@FindBy(how=How.XPATH, using="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(how=How.XPATH, using="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	//Actions
	public void setEmailAddress(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnLogin.click();
	}

}

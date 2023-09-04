package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//identifying elements
	
	@FindBy(xpath="//input[@id='user-name']")
	WebElement txtUsername;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='login-button']")
	WebElement btnLogin;
	
	@FindBy(xpath="//button[@id='react-burger-menu-btn']")
	WebElement sandwichIcon;
	
	@FindBy(xpath="//a[@id='logout_sidebar_link']")
	WebElement btnLogout;
	
	@FindBy(xpath="//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]")
	WebElement invalidMsg;
	
	
	//action methods
	public void setUsername(String user)
	{
		txtUsername.sendKeys(user);	
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnLogin.click();
	}
	
	public void clickSandwichIcon()
	{
		sandwichIcon.click();
	}
	
	public void clickLogout()
	{
		btnLogout.click();
	}
	
	public void dispInvalidMsg()
	{
		System.out.println(invalidMsg.getText());
	}
	
	public boolean isInvalidDisplayed()
	{
		try
		{
		return (invalidMsg.isDisplayed());
		}
		catch(Exception e)
		{
			return(false);
		}
	 }
	
	
}

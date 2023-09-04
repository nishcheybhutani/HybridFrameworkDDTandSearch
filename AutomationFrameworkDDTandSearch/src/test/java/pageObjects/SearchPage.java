package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
WebDriver driver;
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}	
	
	@FindBy(xpath="//input[@name='search']")
	WebElement txtSearch;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement btnSearch;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criteria')]")
	WebElement msg;
	
	@FindBy(xpath="//a[contains(text(),'iPhone')]")
	WebElement prodName;
	
	public void inputSearch(String txt)
	{
		txtSearch.sendKeys(txt);
	}
	
	public void clickSearch() 
	{
		btnSearch.click();
	}
	
	public boolean isMsgExists() 
	{
		try {
			return(msg.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getProductName()
	{
		return prodName.getText();
	}
	
	
	   

}

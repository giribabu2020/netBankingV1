package com.netBanking.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
		
	}
	
@FindBy(name="uid")	
WebElement txtUserName;

@FindBy(name="password")	
WebElement txtPassword;
	
@FindBy(name="btnLogin")	
WebElement btnLogin;

@FindBy(xpath="//a[text()='Log out']")
WebElement logout;

@FindBy(xpath="//a[text()='New Customer']")
WebElement newCustomer;
//Action Methods

public void setUserName(String uname)
{
	txtUserName.sendKeys(uname);
}

public void setPassword(String pwd)
{
	txtPassword.sendKeys(pwd);
}	
	
public void clickSubmit()
{
	btnLogin.click();
}	

public void clicklogOut()
{
	logout.click();
}

public void clickNewCustomer()
{
	newCustomer.click();
}

}
	



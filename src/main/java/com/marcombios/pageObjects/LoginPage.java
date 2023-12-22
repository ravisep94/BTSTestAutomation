package com.marcombios.pageObjects;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(name="loginfmt")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(id="idSIButton9")
	@CacheLookup
	WebElement btnnext;
		
	@FindBy(id="submitButton")
	@CacheLookup
	WebElement btnsignin;
	
	@FindBy(id="idSIButton9")
	@CacheLookup
	WebElement btnyes;
	
	@FindBy(name="Password")
	@CacheLookup
	WebElement txtPassword;
	
//	@FindBy(xpath="//div[contains(text(), 'titleContainer_d71e1ad8') or contains(text(), 'ADPworks Site')]")
//	@CacheLookup
//	WebElement adpWorksSite;

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/button[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]")
	@CacheLookup
	WebElement lnkprofilePicture;
			
	@FindBy(id="mectrl_body_signOut")
	@CacheLookup
	WebElement lnkLogout;
	
	
	
	public void setUserName(String uname) 
	{
		txtUserName.clear();
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd) 
	{
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
		
	}
	
	public void clickNext()
	{
		btnnext.click();
	}	
	
	public void clickYes()
	{
		btnyes.click();
	}	
	
	
	
	public void clickSubmit()
	{
		btnsignin.click();
	}	
	
//	public void clickADPWorksSite()
//	{
//		adpWorksSite.click();
//	}	
	
	public void clickLogout()
	{
		lnkprofilePicture.click();
		lnkLogout.click();
	}
	
	
	
}










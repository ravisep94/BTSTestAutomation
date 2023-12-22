package com.marcombios.testCases;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.marcombios.pageObjects.LoginPage;
import com.marcombios.utilities.Base;


public class LoginTest extends Base
{

	@Test
	public void loginTest(WebDriver driver,String username,String password) throws IOException, InterruptedException 
	{

		LoginPage lp=new LoginPage(driver);	
		logger.info("URL is opened");
		//Reporter.log("URL is opened(testng logger)");
		lp.setUserName(username);
		logger.info("Entered username");
		lp.clickNext();
		logger.info("Clicked on Next button");
		
		lp.setPassword(password);
		logger.info("Entered password");
		Thread.sleep(5000);
		
		lp.clickSubmit();
		
//		logger.info("Clicked on Submit button");
//		Thread.sleep(15000);
//		lp.clickYes();
//		logger.info("Clicked on Yes button");
		String appTitle=driver.getTitle();
		
	
		System.out.println("The application title is: " + appTitle);
		
		if(appTitle.equals("Consultant Biography"))
		{
			Assert.assertTrue(true);
			//logger.info("User Logged in Succsesfully");
			Thread.sleep(3000);
		}
		else
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
		
		 Thread.sleep(2000); 
//		 logoutTest();
//		 logger.info("User Loggedout Sussuesfully");
		 }

	public void logoutTest() {

		LoginPage lp=new LoginPage(driver);
		
		lp.clickLogout();
		
		
	}
	
}

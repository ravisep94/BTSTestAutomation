package com.marcombios.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marcombios.pageObjects.BiographyPage;
import com.marcombios.pageObjects.DeleteUserPage;
import com.marcombios.pageObjects.LoginPage;
import com.marcombios.utilities.Base;
import com.marcombios.utilities.CommonMethods;

public class WebMasterBiographyTest extends Base {
	LoginPage lp;
	BiographyPage b;
	LoginTest lt;
	WebDriverWait wait;
	Actions actions;
	DeleteUserPage del;
@BeforeMethod
	public void setup() throws IOException, InterruptedException {
	lp= new LoginPage(driver);
	b= new BiographyPage(driver);
	del = new DeleteUserPage(driver);
	b.setup(driver);
	lt=new LoginTest();
	lt.loginTest(driver,webmasterusername,userpassword);
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	actions = new Actions(driver);
}
//Web master can mark published to web completed
@Test
public void verifyMasterPublishCompleted_TC35367() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status Tab is selected");
	actions.moveToElement(b.getPublishWebComp()).click().perform();
	logger.info("Publish to web completed checkbox is selected");
	b.clickSubmit();
	if(b.getStatus(publishtospweb).equals(publishtospweb))
	{
		Assert.assertTrue(true);
		logger.info("Status is as expected");
	}
	else
	{
		captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("Status is not as expected");
	}
}
}

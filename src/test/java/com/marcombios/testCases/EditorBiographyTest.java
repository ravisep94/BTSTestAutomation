package com.marcombios.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marcombios.pageObjects.LoginPage;

import com.marcombios.pageObjects.BiographyPage;
import com.marcombios.pageObjects.DeleteUserPage;
import com.marcombios.pageObjects.LoginPage;
import com.marcombios.utilities.Base;
import com.marcombios.utilities.CommonMethods;

public class EditorBiographyTest extends Base {
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
	lt.loginTest(driver,marcomeditorusername,userpassword);
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	actions = new Actions(driver);
}
	
//Marcom editor can update bios ready for editorial review without submitting 
@Test
public void verifyMarcomEditorSave_TC35357() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.updateEmployeeDetails();		
	b.clickNext();
	b.clickNext();
	b.clickSaveDraft();
	if(b.getStatus(ineditorialreview).equals(ineditorialreview))
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
//Marcom editor can send bios ready for editorial review to submitter for further review 
@Test
public void verifyEditorSubmitFurtherReview_TC35358() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status Tab is selected");
	b.getReqFurReview().click();
	logger.info("Requires Further Review Checkbox is selected");
	b.addEditorText();
	b.addEditorCommentsText();
	b.clickSubmit();
	if(b.getStatus(insubmitterreview).equals(insubmitterreview))
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
//Marcom editor can complete editor review
@Test
public void verifyEditorCompleteReview_TC35360() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status Tab is selected");	
	actions.moveToElement(b.getEditReviewComp()).click().perform();
	logger.info("Editor Review Completed checkbox is selected");
	b.clickSubmit();
	if(b.getStatus(editorialreviewcompleted).equals(editorialreviewcompleted))
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

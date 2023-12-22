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

public class UserBiographyTest extends Base {
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
	lt.loginTest(driver,marcomusername,userpassword);
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	actions = new Actions(driver);
}
//Marcom can approve & publish editor review completed bios
@Test
public void verifyUserApprovePublish_TC35362() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status Tab is selected");
	actions.moveToElement(b.getApprovePublish()).click().perform();
	logger.info("Approve and Publish checkbox is selected");
	b.clickSubmit();
	if(b.getStatus(publishtosp).equals(publishtosp))
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
//Marcom can mark as urgent any in process bios 
@Test
public void verifyUserMarkUrgent_TC35364() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status Tab is selected");
	actions.moveToElement(b.getUrgent()).click().perform();
	logger.info("Urgent Checkbox is selected");
	b.clickSubmit();
}
//Marcom can require bio to be published to web 
@Test
public void verifyUserPublishWeb_TC35365() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status Tab is selected");
	actions.moveToElement(b.getPublishWebReq()).click().perform();
	logger.info("Publish to web required checkbox is selected");
	b.clickSubmit();
	if(b.getStatus(pendingwebpost).equals(pendingwebpost))
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
//
//Consultant Biographies - customize multiline text field menu bars
@Test(enabled=false)
public void verifyMenuBar_TC38665() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.getBiographyTab().click();
	logger.info("Biography Details Tab is selected");
}
//Auto format email to lowercase in Preview
@Test
public void verifyAutoformatEmail_TC41200() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	Thread.sleep(3000);
	b.getPreviewTab().click();
	logger.info("Preview Tab is selected");	
	String divText= b.getuserDetails().getText();
	logger.info(divText);
	 String[] lines = divText.split("\n"); 
   String requiredInfo = "";
      for (String line : lines) {
       if (line.trim().endsWith(testdomain)) {
           requiredInfo = line.trim();
           logger.info(requiredInfo);
           break;
       }
   }
	if(checkEmailLowerCase(requiredInfo)) {
		Assert.assertTrue(true);
		logger.info("Email is in lower case");
	}
	else{
		captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("Email is not in lower case");
	}
}
public boolean checkEmailLowerCase(String requiredInfo) {
	boolean allLowerCase = false;
	char[] chars=requiredInfo.toCharArray();
	for(int i=0;i<chars.length;i++)
	{
		char currentChar = chars[i];
		if(Character.isLowerCase(chars[i])==true || currentChar == '.' || currentChar == '@')
		{
			allLowerCase = true;
			break;
		}
	}
	return allLowerCase;
}
//Auto format email to lowercase in PDF file for new approved bios 
@Test
public void verifyEmailInPDF_TC41201() throws IOException, InterruptedException {
	if(b.checkEmployee(empname)==true) {
		Thread.sleep(2000);
		b.getBioEditBtn().click();
		logger.info("Edit button is selected");
		b.getStatusTab().click();
		logger.info("Status tab is selected");
		actions.moveToElement(b.getApprovePublish()).click().perform();
		logger.info("Approve and Publish checkbox is selected");
		b.getPreviewTab().click();
		logger.info("Preview Tab is selected");	
		b.getDwldPDF().click();
		logger.info("Download PDF is selected");
		Thread.sleep(30000);
		String extractedText = b.openDownloadedPDF(userfilepath);	
		String[] lines = extractedText.split("\n"); 
	    String requiredInfo = "";
      for (String line : lines) {
       if (line.trim().endsWith(testdomain)) {
           requiredInfo = line.trim();
           logger.info(requiredInfo);
           break;
       }
      }
	        if(checkEmailLowerCase(requiredInfo)) {
	    		Assert.assertTrue(true);
	    		logger.info("Email in PDF is in lower case");
	    	}
	    	else{
	    		captureScreen(driver,"Consultant Biography");
	    		Assert.assertTrue(false);
	    		logger.info("Email in PDF is not in lower case");
	    	}
	}
	else {
		b.addBiographyDetails();
		b.addAllConsultantFields();
		b.clickSubmit();
		b.clickEmployeeRecord();
		b.clickEdit();
		b.getStatusTab().click();
		actions.moveToElement(b.getApprovePublish()).click().perform();
		logger.info("Approve and Publish checkbox is selected");
		b.getPreviewTab().click();
		logger.info("Preview Tab is selected");	
		b.getDwldPDF().click();
		logger.info("Download PDF is selected");
		Thread.sleep(30000);
		String extractedText = b.openDownloadedPDF(userfilepath);	
		String[] lines = extractedText.split("\n"); 
	    String requiredInfo = "";
      for (String line : lines) {
       if (line.trim().endsWith(testdomain)) {
           requiredInfo = line.trim();
           logger.info(requiredInfo);
           break;
       }
      }
	        if(checkEmailLowerCase(requiredInfo)) {
	    		Assert.assertTrue(true);
	    		logger.info("Email in PDF is in lower case");
	    	}
	    	else{
	    		captureScreen(driver,"Consultant Biography");
	    		Assert.assertTrue(false);
	    		logger.info("Email in PDF is not in lower case");
	    	}	
	        }
}
//Show "milliman.com" web address in bio's footer - Preview 
@Test
public void verifyEmailInPDF_TC41202() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.getPreviewTab().click();
	logger.info("Preview Tab is selected");	
	String PDFdata= b.getOfficePreview().getText();
	if(PDFdata.contains("milliman.com"))
	{
		Assert.assertTrue(true);
		logger.info("Milliman address is displayed");
	}
	else
	{
		captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("Milliman address is not displayed");
	}
}
//Show "milliman.com" web address in bio's footer for new approved bios- PDF file
@Test
public void verifyWebAddressPDF_TC41203() throws IOException, InterruptedException {
	if(b.checkEmployee(empname)==true) {
		b.clickEdit();
		b.getStatusTab().click();
		actions.moveToElement(b.getApprovePublish()).click().perform();
		logger.info("Approve and Publish checkbox is selected");
		b.getPreviewTab().click();
		logger.info("Preview Tab is selected");	
		b.getDwldPDF().click();
		logger.info("Download PDF is selected");
		Thread.sleep(30000);
		String extractedText = b.openDownloadedPDF(userfilepath);	
		String[] lines = extractedText.split("\n"); 
	    String requiredInfo = "";
      for (String line : lines) {
       if (line.trim().endsWith(domain)) {
           requiredInfo = line.trim();
           logger.info(requiredInfo);
           break;
       }
      }
      if(requiredInfo.equals(domain)) {
      	Assert.assertTrue(true);
      	logger.info("milliman.com is displayed");
      }
      else{
		captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("milliman.com is displayed");
      }
	}
	else {
	b.addBiographyDetails();
	b.addAllConsultantFields();
	b.clickSubmit();
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	actions.moveToElement(b.getApprovePublish()).click().perform();
	logger.info("Approve and Publish checkbox is selected");
	b.getPreviewTab().click();
	logger.info("Preview Tab is selected");	
	b.getDwldPDF().click();
	logger.info("Download PDF is selected");
	Thread.sleep(30000);
	String extractedText = b.openDownloadedPDF(userfilepath);	
	String[] lines = extractedText.split("\n"); 
  String requiredInfo = "";
  for (String line : lines) {
   if (line.trim().endsWith(domain)) {
       requiredInfo = line.trim();
       logger.info(requiredInfo);
       break;
   }
  }
  	if(requiredInfo.equals(domain)) {
  		Assert.assertTrue(true);
  		logger.info("milliman.com is displayed");
  	}
  	else{
  		captureScreen(driver,"Consultant Biography");
  		Assert.assertTrue(false);
  		logger.info("milliman.com is displayed");
  	}
  }
}
//Option to hide email address is available for existing/new bios
@Test
public void verifyHideEmail_TC41204() throws IOException, InterruptedException {
	if(b.checkEmployee(empname)==true) {
		b.getConsultantTab().click();
		logger.info("Cosultant Tab is opened");
		if((b.getEmailAddress().isDisplayed())&&(b.getEmailCheckboxDisable().isDisplayed()) )
		{
			Assert.assertTrue(true);
			logger.info("Email address checkbox is displayed");
		}
		else
		{
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Email address checkbox  is not displayed");
		}
	}
	else {
	b.addBiographyDetails();
	if((b.getEmailAddress().isDisplayed())&&(b.getEmailCheckboxDisable().isDisplayed()) )
	{
		Assert.assertTrue(true);
		logger.info("Email address checkbox is displayed");
	}
	else
	{
		captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("Email address checkbox  is not displayed");
	}
	}
}
//Users can choose to not display email address in bios 
@Test
public void verifyDoNotDisplayEmail_TC41205() throws IOException, InterruptedException {
	if(b.checkEmployee(empname)==true) {
		b.getConsultantTab().click();
	    b.selectEmailAddressCheckbox();
	    	if(b.getEmail().isDisplayed()) {
	    	Assert.assertTrue(false);
	    	logger.info("Email is not hidden");
	        }
	    	else	       	{
	     	Assert.assertTrue(true);
	       	logger.info("Email is hidden");
	       	}  
		}
	    else {
	    b.addBiographyDetails();
	    b.selectEmailAddressCheckbox();
	      	if(b.getEmail().isDisplayed()) {
	      		Assert.assertTrue(false);
	      		logger.info("Email is not hidden");
	        	}
	        else	        	{
	        	Assert.assertTrue(true);
	        	logger.info("Email is hidden");
	        	}  
	        }
}
//Hide email address in existing bio's header - Preview 
@Test
public void verifyHideEmailExistingBio_TC41206() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	Thread.sleep(3000);
	b.clickEdit();
	b.getConsultantTab().click();
	logger.info("Consultant Tab is selected");
	String email=b.getEmail().getAttribute("value");
	logger.info(email);
	b.selectEmailAddressCheckbox();
	b.getPreviewTab().click();
	logger.info("Preview Tab is selected");
	Thread.sleep(3000);
	if(!b.getuserDetails().getText().contains(email))
	{
		Assert.assertTrue(true);
		logger.info("Email is hidden in Preview");
	}
	else
	{
		Assert.assertTrue(false);
		logger.info("Email is not hidden in preview");
	}
}
//Hide email address in new bio's header - Preview 
@Test
public void verifyHideEmailNewBio_TC41207() throws IOException, InterruptedException {
	b.addBiographyDetails();
	String email=b.getEmail().getAttribute("value");
	logger.info(email);
	b.selectEmailAddressCheckbox();
	b.getPreviewTab().click();
	logger.info("Preview Tab is selected");
	if(!b.getuserDetails().getText().contains(email))
	{
		Assert.assertTrue(true);
		logger.info("Email is hidden in Preview");
	}
	else
	{
		Assert.assertTrue(false);
		logger.info("Email is not hidden in preview");
	}
}
//Hide email address in bio's header for new approved bios- PDF file
@Test
public void verifyHideEmailPDF_TC41208() throws IOException, InterruptedException {
	if(b.checkEmployee(empname)==true) {
		b.clickEdit();
		b.getConsultantTab().click();
		logger.info("Consultant Tab is selected");
		String email= b.getEmail().getAttribute("value");
		logger.info(email);
		b.selectEmailAddressCheckbox();
		b.getStatusTab().click();
		logger.info("Status tab is selected");
		actions.moveToElement(b.getApprovePublish()).click().perform();
		logger.info("Approve and Publish checkbox is selected");
		b.getPreviewTab().click();
		logger.info("Preview Tab is selected");	
		b.getDwldPDF().click();
		logger.info("Download PDF is selected");
		Thread.sleep(30000);
		String extractedText = b.openDownloadedPDF(userfilepath);			
//	    if(!extractedText.contains(testdomain))
	    if(!extractedText.contains(email))
	    {
	    	Assert.assertTrue(true);
	    	logger.info("Email is not displayed");
	    }
	    else {
	    	captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Email is not displayed");
	    }	    	
	}
	else {
	b.addBiographyDetails();
	b.clickNext();
	b.addAllConsultantFields();
	String email= b.getEmail().getAttribute("value");
	logger.info(email);
	b.selectEmailAddressCheckbox();
	b.clickNext();
	b.clickSubmit();
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	actions.moveToElement(b.getApprovePublish()).click().perform();
	logger.info("Approve and Publish checkbox is selected");
	b.getPreviewTab().click();
	logger.info("Preview Tab is selected");
	b.getDwldPDF().click();
	logger.info("Download PDF is selected");
	Thread.sleep(30000);
	String extractedText = b.openDownloadedPDF(userfilepath);		
//  if(!extractedText.contains(testdomain))
  if(!extractedText.contains(email))
  {
  	Assert.assertTrue(true);
  	logger.info("Email is not displayed");
  }
  else {
  	captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("Email is not displayed");
  }	
	}
}

}

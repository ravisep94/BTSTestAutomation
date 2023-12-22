package com.marcombios.testCases;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.codehaus.plexus.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.marcombios.pageObjects.BiographyPage;
import com.marcombios.pageObjects.DeleteUserPage;
import com.marcombios.pageObjects.LoginPage;
import com.marcombios.utilities.Base;
import com.marcombios.utilities.CommonMethods;

public class BiographyTest extends Base{
	LoginPage lp;
	BiographyPage b;
	LoginTest lt;
	WebDriverWait wait;
	Actions actions;
	DeleteUserPage del;
	CommonMethods cm;
@BeforeMethod
	public void setup() throws IOException, InterruptedException {
	lp= new LoginPage(driver);
	b= new BiographyPage(driver);
	cm=new CommonMethods();
	del = new DeleteUserPage(driver);
	b.setup(driver);
	lt=new LoginTest();
	lt.loginTest(driver,username,password);
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	actions = new Actions(driver);
}
//Consultant Biography form properties
//Milliman user can submit a new bio for himself/other user
@Test
public void verifyConsultantSubmissions_TC35353_TC35351() throws IOException, InterruptedException {
	b.addBiographyDetails();
	b.checkBiographyTab();
	b.checkConsultantTab();
	b.checkPreviewTab();
	b.addConsultantDetails();
	b.clickNext();	
	b.checkPreview();
	b.clickSubmit();
	if(b.getStatus(readyeditorialreview).equals(readyeditorialreview))
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
//Milliman user can start a new bio for himself/other user
@Test
public void verifyConsultantSave_TC35352() throws IOException, InterruptedException {
	b.addBiographyDetails();
	b.addConsultantDetails();
	b.clickNext();	
	b.checkPreview();
	b.clickSaveDraft();	
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
//Milliman user can update and save his current bio
@Test
public void verifyConsultantEditSave_TC35356() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.updateEmployeeDetails();
	b.clickSaveDraft();
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
//Milliman user can update and submit his current bio
@Test
public void verifyConsultantEditSubmit_TC35356() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.updateEmployeeDetails();
	b.clickNext();
	b.checkAllStatusFields();
	b.clickSubmit();
	if(b.getStatus(readyeditorialreview).equals(readyeditorialreview))
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
//Any Milliman employee can update and already published biography
@Test
public void verifySearchBioPage_TC38232() throws IOException, InterruptedException {
	b.getSearchBioNav().click();
	logger.info("Search Biographies on left navigation is selected");
	b.clickEmployeeEditonSearchBioPage();
}
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
//Preview and PDF are matching for new bios
@Test(enabled = false)
public void verifyPreviewandPDFNewBio_TC41209() throws IOException, InterruptedException {
	b.addBiographyDetails();
	b.addConsultantDetails();
	b.clickNext();	
	b.checkPreview();
	b.clickSubmit();
	b.clickEmployeeRecord();
	Thread.sleep(3000);
	b.getPreviewTab().click();
	logger.info("Preview tab is selected");
//	String userInfo = b.getuserDetails().getText();
//	logger.info(userInfo);
//	String consInfo = b.getEditorPreview().getText();
//	logger.info(consInfo);
//	String offInfo = b.getOfficePreview().getText();
//	logger.info(offInfo);			
	b.getDwldPDF().click();
	logger.info("Download PDF is selected");
	Thread.sleep(30000);
	String extractedText = b.openDownloadedPDF(userfilepath);
	boolean allConditions = b.checkPDFPreview(extractedText);
	if(allConditions) {
		Assert.assertTrue(true);
  	logger.info("Bio header Preview and PDF are matched");
	}
else {
	captureScreen(driver,"Consultant Biography");
	Assert.assertTrue(false);
	logger.info("Bio header Preview and PDF are not matched");
}	
			
//	if(extractedText.contains(userInfo))
//	if((extractedText.contains(userInfo)) && (extractedText.contains(consInfo)) && (extractedText.contains(offInfo)))
//  {
//  	Assert.assertTrue(true);
//  	logger.info("Preview and PDF are matched");
//  }
//  else {
//  	captureScreen(driver,"Consultant Biography");
//		Assert.assertTrue(false);
//		logger.info("Preview and PDF are not matched");
//  }		
}
//Preview and PDF are matching for updated bios
@Test(enabled = false)
public void verifyPreviewandPDFExistingBio_TC41210() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.getStatusTab().click();
	logger.info("Status tab is selected");
	actions.moveToElement(b.getApprovePublish()).click().perform();
	logger.info("Approve and Publish checkbox is selected");
	b.getPreviewTab().click();
	b.selectDownloadPDF();
}

@Test
//Submit the Bio form without filling the data in Biography Page
public void verifySubmitBioFormNoFill() throws IOException, InterruptedException {
	b.getAddBioBtnBtn().click();
	logger.info("Add bio button is selected");
	b.clickNext();
	logger.info(b.getConsDetailPageErr().getText());
	b.clickNext();
	logger.info(b.getPreviewPageErr().getText());
	b.getSubmitBtn().click();
	logger.info("Submit button is selected");
	logger.info(b.getEmpNameErr().getText());
	logger.info(b.getcurrentRespErrMsg().getText());
	logger.info(b.getProfWorkExpErrMsg().getText());
	logger.info(b.getEducationErrMsg().getText());
	Thread.sleep(2000);
}
@Test
//Validate the Status page fields and print error messages
public void verifySubmitStatusFormNoFill() throws IOException, InterruptedException {
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status tab is selected");
	b.getSubmitBtn().click();
	logger.info("Submit button on Status page is selected");
	logger.info(b.getReqFurReviewErrMsg().getText());
	logger.info(b.getEditorReviewCompErrMsg().getText());
	actions.moveToElement(b.getReqFurReview()).click().perform();
	logger.info("Requires Further Review checkbox is selected");
	b.getSubmitBtn().click();
	logger.info("Submit button on Status page is selected");
	logger.info(b.getEditorErrMsg().getText());
	logger.info(b.getEditorComntsErrMsg().getText());
}
@Test
//Close form button
public void verifyCloseButton() throws IOException, InterruptedException {
	b.clickAddBio();
	b.getCloseBtn().click();
	logger.info("Close button is selected");
	b.getConfirm().click();
	logger.info("Confirm button on confirmation popup is selected");
	String pageTitle=driver.getTitle();
	if(pageTitle.equals("Consultant Biography")) {
		Assert.assertTrue(true);
		logger.info("Page is successfully closed");
	}
	else {
		captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("Page is not closed");
	}
}
@Test
//Close(X) Icon button
public void verifyCloseIcon() throws IOException, InterruptedException {
	b.clickAddBio();
	actions.moveToElement(b.getCloseXIcon()).click().perform();
	Thread.sleep(2000);	
	logger.info("Close Icon is selected");
	b.getConfirm().click();
	logger.info("Confirm button on confirmation popup is selected");
	String pageTitle=driver.getTitle();
	if(pageTitle.equals("Consultant Biography")) {
		Assert.assertTrue(true);
		logger.info("Page is successfully closed");
	}
	else {
		captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("Page is not closed");
	}
}
@Test
//Close form button
public void verifyAttachments() throws IOException, InterruptedException {
	boolean fileFound = false;
	b.clickEmployeeRecord();
	b.getAttachmentTab().click();
	logger.info("Attachments tab is selected");
	b.getAttachmentDwld().click();
	logger.info("Attachments Download is selected");
	Thread.sleep(3000);
	File fileLocation = new File(userfilepath);
  File[] allFiles = fileLocation.listFiles();
  if (allFiles != null && allFiles.length > 0) {
      for (int i = allFiles.length - 1; i >= 0; i--) {
          File file = allFiles[i];
          String eachFile = file.getName();
          if (eachFile.contains(updatename)) {
              logger.info(eachFile);
              logger.info("Verified: File Has Been Downloaded.");
              fileFound=true;
              break;
          }
      }
  }
  else
  	logger.info("No files found");
  if(fileFound) {
  	Assert.assertTrue(true);
  	logger.info("File is available in downloads");
  }
  else {
  	captureScreen(driver,"Consultant Biography");
		Assert.assertTrue(false);
		logger.info("File is not available in downloads");
  }
}

//MarCom Bios: Auto approve submitted change Edit Bio of the Published Status
@Test
public void verifyAutoApprove_TC48073() throws IOException, InterruptedException {
	SoftAssert sa=new SoftAssert(); 
	if(b.checkEmployee(empname)==true) {
	 b.clickEdit();
	 b.getStatusTab().click();
	 logger.info("Status Tab is selected");
	 actions.moveToElement(b.getBypassRev()).click().perform();
		logger.info("ByPass Review Process checkbox is selected");
		if(b.getSubRevComp().isSelected() && b.getEditReviewComp().isSelected() && 
				b.getApprovePublish().isSelected() && b.getPublishWebReq().isSelected()) {
		Assert.assertTrue(true);		
			logger.info("Submitter Review Completed\r\n"
					+ "Editor Review Completed\r\n"
					+ "Approve & Publish   \r\n"
					+ "Publish to Web Required Checkboxes are selected");
		}
		else {
			Assert.assertTrue(false);
			logger.info("Required checkboxes are not enabled");
		}
		b.clickSubmit();
		if(b.getStatus(pendingwebpost).equals(pendingwebpost)) {
			Assert.assertTrue(true);
				logger.info("Status is as expected");
				}
		else {
			Assert.assertTrue(false);
			logger.info("Status is not as expected");
			}
		//Open bio with marcom webmaster
		b.clickEmployeeRecord();
		b.clickEdit();
		b.getStatusTab().click();
		logger.info("Status tab is selected");
		if(b.getPublishWebComp().isSelected()) {
			Assert.assertTrue(true);
			logger.info("Publish to web completed is enabled");
			}
		else {
			Assert.assertTrue(false);
			logger.info("Publish to web completed is not enabled");
			}
		actions.moveToElement(b.getPublishWebComp()).click().perform();
		logger.info("ByPass Review Process checkbox is selected");
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
		sa.assertAll();
	}
	else {
	b.clickAddBio();
	b.addBiographyDetails();
	b.addAllConsultantFields();
	b.clickSubmit();
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status Tab is selected");
	actions.moveToElement(b.getBypassRev()).click().perform();
	logger.info("ByPass Review Process checkbox is selected");
	if(b.getSubRevComp().isSelected() && b.getEditReviewComp().isSelected() && 
			b.getApprovePublish().isSelected() && b.getPublishWebReq().isSelected()) {
		Assert.assertTrue(true);
		logger.info("Submitter Review Completed\r\n"
				+ "Editor Review Completed\r\n"
				+ "Approve & Publish   \r\n"
				+ "Publish to Web Required Checkboxes are selected");
		}
	else {Assert.assertTrue(false);
		logger.info("Required checkboxes are not enabled");}
	b.clickSubmit();
	if(b.getStatus(pendingwebpost).equals(pendingwebpost)) {Assert.assertTrue(true);
			logger.info("Status is as expected");}
	else {Assert.assertTrue(false);
		logger.info("Status is not as expected");}
	//Open bio with marcom webmaster
	b.clickEmployeeRecord();
	b.clickEdit();
	b.getStatusTab().click();
	logger.info("Status tab is selected");
	if(b.getPubWebComEnable().isDisplayed()) {Assert.assertTrue(true);
		logger.info("Publish to web completed is enabled");}
	else {Assert.assertTrue(false);
		logger.info("Publish to web completed is not enabled");}
	actions.moveToElement(b.getPublishWebComp()).click().perform();
	logger.info("ByPass Review Process checkbox is selected");
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
	sa.assertAll();
	}
}
//MarCom Bios: Auto approve submitted change Edit Bio of the Published Status
@Test
public void verifyEditPublishStatus_TC48074() throws IOException, InterruptedException {
	if((b.checkEmployee(empname)==true) && (b.getStatus(publishtospweb).equals(publishtospweb))) {
			b.clickEdit();
			b.getStatusTab().click();
			logger.info("Status Tab is selected");
			actions.moveToElement(b.getBypassRev()).click().perform();
			logger.info("ByPass Review Process checkbox is selected");
				if(b.getPubWebReqEnable().isDisplayed())
				actions.moveToElement(b.getPublishWebReq()).click().perform();				
			logger.info("Publish to web required checkbox is de-selected");
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
	else {
			b.clickAddBio();
			b.addBiographyDetails();
			b.addAllConsultantFields();
			b.clickSubmit();
			b.clickEmployeeRecord();
			b.clickEdit();
			b.getStatusTab().click();
			logger.info("Status Tab is selected");
			if(b.getPubWebReqEnable().isDisplayed())
				actions.moveToElement(b.getPublishWebReq()).click().perform();	
			logger.info("Publish to web completed checkbox is de-selected");
			b.clickSubmit();
			logger.info(b.getEmpList().getText());
			int count=b.getEmpCount().size();
			for (int j=0;j<count; j++)
			{
			WebElement empName= b.getEmployee(j);
			logger.info(empName.getText());
			WebElement empStatus= b.getEmployeeStatus(j);
			logger.info(empStatus.getText());
	        if((empName.getText().equals(empname))&&(empStatus.getText().equals(publishtospweb)))
	        {
	        	logger.info("Employee Name is " + empname);
	        	b.getEmployee(j).click();
	        	logger.info("Employee name is selected");
	        	break;
	        }
	 	 }
		b.clickEdit();
		actions.moveToElement(b.getBypassRev()).click().perform();
		logger.info("ByPass Review Process checkbox is selected");
		actions.moveToElement(b.getPublishWebReq()).click().perform();
		logger.info("ByPass Review Process checkbox is selected");
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
}
//MarCom Bios: Admin Unlock a bio - Yes
@Test
public void verifyAdminUnlockYes_TC48265() throws IOException, InterruptedException {
	SoftAssert sa=new SoftAssert(); 
	if((b.checkEmployee(empname)==true) && (b.getStatus(insubmitterreview).equals(insubmitterreview))) {
		b.clickUnlock();
		b.getYesBtn().click();
		logger.info("Yes button on confirmation popup is selected");
		driver.navigate().refresh();
		logger.info("Page is refreshed");
		Thread.sleep(3000);
		if(b.getStatus(publishtospweb).equals(publishtospweb)) {
			Assert.assertTrue(true);
			logger.info("Status is as expected");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Status is not as expected");
		}
		if(!(b.getUrgent().isSelected() && b.getReqFurReview().isSelected() &&
			b.getSubRevComp().isSelected() && b.getEditReviewComp().isSelected() &&
			b.getApprovePublish().isSelected() && b.getPublishWebReq().isSelected() && b.getPublishWebComp().isSelected())) {
			Assert.assertTrue(true);
			logger.info("All checkboxes are unchecked");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("All checkboxes are not unchecked");}
		if(b.getEditor().equals(null) && b.getEditorComments().equals(null)) {
			Assert.assertTrue(true);
			logger.info("Editor and Editor comments data is cleared");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Editor and Editor comments data is cleared");
		}
	}
	else {
		//login as non admin
		b.clickAddBio();
		b.addBiographyDetails();
		b.clickSaveDraft();
		b.clickEmployeeRecord();
		b.clickUnlock();
		b.getYesBtn().click();
		logger.info("Yes button on confirmation popup is selected");
		driver.navigate().refresh();
		logger.info("Page is refreshed");
		Thread.sleep(3000);
		if(b.getStatus(publishtospweb).equals(publishtospweb)) {
			Assert.assertTrue(true);
			logger.info("Status is as expected");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Status is not as expected");
		}
		if(!(b.getUrgent().isSelected() && b.getReqFurReview().isSelected() &&
			b.getSubRevComp().isSelected() && b.getEditReviewComp().isSelected() &&
			b.getApprovePublish().isSelected() && b.getPublishWebReq().isSelected() && b.getPublishWebComp().isSelected())) {
			Assert.assertTrue(true);
			logger.info("All checkboxes are unchecked");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("All checkboxes are not unchecked");}
		if(b.getEditor().equals(null) && b.getEditorComments().equals(null)) {
			Assert.assertTrue(true);
			logger.info("Editor and Editor comments data is cleared");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Editor and Editor comments data is cleared");
		}
	}		
}
//MarCom Bios: Admin Unlock a bio - No
@Test
public void verifyAdminUnlockNo_TC48265() throws IOException, InterruptedException {
	String pageTitle=driver.getTitle();
	if((b.checkEmployee(empname)==true) && (b.getStatus(insubmitterreview).equals(insubmitterreview))) {
		b.clickUnlock();
		b.getNoBtn().click();
		logger.info("No button on confirmation popup is selected");
		b.clickClose();
		if(pageTitle.equals("Consultant Biography")) {
			Assert.assertTrue(true);
			logger.info("Bio form closed successfully");
		}
		else{
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Bio form not closed");
		}
	}
	else {
		//login as non admin
		b.clickAddBio();
		b.addBiographyDetails();
		b.clickSaveDraft();
		b.clickEmployeeRecord();
		b.clickUnlock();
		b.getNoBtn().click();
		logger.info("No button on confirmation popup is selected");
		b.clickClose();
		if(pageTitle.equals("Consultant Biography")) {
			Assert.assertTrue(true);
			logger.info("Bio form closed successfully");
		}
		else{
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Bio form not closed");
		}
	}	
}
//MarCom Bios: Unlock a bio Should Not Display for Non Admin Users
@Test
public void verifyUnlockOption_TC48266() throws IOException, InterruptedException {
//	login as marcom editor
	if(b.checkEmployee(empname)==true){
		if(!b.getUnlockBtn().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Unlock button is not displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Unlock button is displayed");
		}
	}	
	else {
//login as non admin
		b.clickAddBio();
		b.addBiographyDetails();
		b.clickSaveDraft();
		b.clickEmployeeRecord();
		if(!b.getUnlockBtn().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Unlock button is not displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Unlock button is displayed");
		}
	}
}

}

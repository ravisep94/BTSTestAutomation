package com.marcombios.pageObjects;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.util.IntList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.codehaus.plexus.logging.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marcombios.pageObjects.DeleteUserPage;
import com.marcombios.utilities.Base;
import com.marcombios.utilities.CommonMethods;
public class BiographyPage extends Base {
WebDriver driver;
DeleteUserPage del;

	
	public BiographyPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	WebDriverWait wait;
	Actions actions;
	CommonMethods cm;
@BeforeMethod
	public void setup(WebDriver driver) throws IOException, InterruptedException {
	this.driver=driver;
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	del = new DeleteUserPage(driver);
	cm=new CommonMethods();
	actions = new Actions(driver);
}	
//-----------------------------Search Biographies-------------------------
	@FindBy(xpath="//span[text()='Search Biographies']")
	@CacheLookup
	WebElement searchBioNav;
	public WebElement getSearchBioNav() {
		return searchBioNav;
	}
	
//Get Employee edit column from list
	public WebElement getEmployeeEdit(int i)
	{
		WebElement name=driver.findElement(By.xpath("//div[@class='ms-List-page']/div[@data-list-index='" +i+ "']/div/div/div[5]"));
		return name;
	}
//-----------------------------Add Bio Button----------------------------	
	@FindBy(xpath="//div[text()='Add Bio']")
	@CacheLookup
	WebElement addBioBtn;
	public WebElement getAddBioBtnBtn() {
		return addBioBtn;
	}
//----------------------------iframe count--------------------------------
	@FindBy(xpath="//iframe[@title='Rich Text Area']")
	@CacheLookup
	List<WebElement> iframec;
	public List getIframeCount() {
		return iframec;
	}
	
	//Get Employee name from list
	public WebElement getIframe(int i)
	{
		WebElement frame=driver.findElement(By.xpath("(//iframe[@title='Rich Text Area'])[" +i+ "]"));
		return frame;
	}
//----------------------------Biography Details Tab------------------------
	@FindBy(xpath="//span[text()='Biography Details']")
	@CacheLookup
	WebElement bioTab;
	public WebElement getBiographyTab() {
		return bioTab;
	}
//----------------------------Consultant Details Tab------------------------
	@FindBy(xpath="//span[text()='Consultant Details']")
	@CacheLookup
	WebElement conTab;
	public WebElement getConsultantTab() {
		return conTab;
	}
//----------------------------Status Tab------------------------
	@FindBy(xpath="//span[text()='Status']")
	@CacheLookup
	WebElement statusTab;
	public WebElement getStatusTab() {
		return statusTab;
	}

//----------------------------Preview Tab------------------------
	@FindBy(xpath="//span[text()='Preview']")
	@CacheLookup
	WebElement preTab;
	public WebElement getPreviewTab() {
		return preTab;
	}
//----------------------------Attachments Tab------------------------
	@FindBy(xpath="//span[text()='Attachments']")
	@CacheLookup
	WebElement attTab;
	public WebElement getAttachmentTab() {
		return attTab;
	}
//-----------------------------Employee Name----------------------------		
	@FindBy(xpath="//input[contains(@aria-label,'People Picker')]")
	@CacheLookup
	WebElement empName;
	public WebElement getEmpName() {
		return empName;
	}
	
	public void selectEmpName(String user) throws InterruptedException
	{
		empName.click();
		empName.sendKeys(user);
		Thread.sleep(4000);
		empName.sendKeys(Keys.ENTER);
	}
//-----------------------------Employee Name Error Message----------------------------		
	@FindBy(xpath="//div[contains(text(),'Please select Consultant')]")
	@CacheLookup
	WebElement empNameErr;
	public WebElement getEmpNameErr() {
		return empNameErr;
	}
	
	@FindBy(xpath="//span[text()='Bio for the selected user already exists']")
	@CacheLookup
	WebElement empNameExists;
	public WebElement getEmpNameExistsError() {
		return empNameExists;
	}
	//-----------------------------Current Responsibility----------------------------		
		@FindBy(xpath="//label[contains(text(),'Current Responsibility')]/following-sibling::div[1]")
		@CacheLookup
		WebElement currentResp;
		public WebElement getcurrentResp() {
			return currentResp;
		}
		
		@FindBy(xpath="//*[@id='tinymce']/p")
		@CacheLookup
		WebElement descTxt;
		public WebElement getDescriptionTxt() {
			return descTxt;
		}
		
		@FindBy(xpath="//*[@id='tinymce']/div/p")
		@CacheLookup
		WebElement desTxt;
		public WebElement getEditDescriptionTxt() {
			return desTxt;
		}

	//-----------------------------Current Responsibility Error Message----------------------------
		@FindBy(xpath="//label[contains(text(),'Current Responsibility')]/following-sibling::div[1]/div[2]")
		@CacheLookup
		WebElement currentRespErrMsg;
		public WebElement getcurrentRespErrMsg() {
			return currentRespErrMsg;
		}
	//-----------------------------Presentations and Publications----------------------------	
		@FindBy(xpath="//label[contains(text(),'Presentations and Publications')]/following-sibling::div[1]")
		@CacheLookup
		WebElement presandpubdesc;
		public WebElement getPresandPub() {
			return presandpubdesc;
		}
	//-----------------------------Professional Work Experience----------------------------		
		@FindBy(xpath="//label[contains(text(),'Professional Work Experience')]/following-sibling::div[1]")
		@CacheLookup
		WebElement profworkexpdesc;
		public WebElement getProfWorkExp() {
			return profworkexpdesc;
		}
	//-----------------------------Professional Work Experience Error Message----------------------------	
		@FindBy(xpath="//label[contains(text(),'Professional Work Experience')]/following-sibling::div[1]/div[2]")
		@CacheLookup
		WebElement profworkexpErrMsg;
		public WebElement getProfWorkExpErrMsg() {
			return profworkexpErrMsg;
		}
	//-----------------------------Professional Designations----------------------------		
		
		@FindBy(xpath="//label[contains(text(),'Professional Designations')]/following-sibling::div[1]")
		@CacheLookup
		WebElement profdesdesc;
		public WebElement getProfDes() {
			return profdesdesc;
		}
	//-----------------------------Education----------------------------	
		@FindBy(xpath="//label[contains(text(),'Education')]/following-sibling::div[1]")
		@CacheLookup
		WebElement educationdesc;
		public WebElement getEducation() {
			return educationdesc;
		}

	//-----------------------------Education Error Message
		@FindBy(xpath="//label[contains(text(),'Education')]/following-sibling::div[1]/div[2]")
		@CacheLookup
		WebElement educationErrMsg;
		public WebElement getEducationErrMsg() {
			return educationErrMsg;
		}
	//-----------------------------Affiliations----------------------------	
		@FindBy(xpath="//label[contains(text(),'Affiliations')]/following-sibling::div[1]")
		@CacheLookup
		WebElement affiliationsdesc;
		public WebElement getAffiliations() {
			return affiliationsdesc;
		}
	//-----------------------------Please contact text and link--------------------------------
		@FindBy(xpath="//div[@class='ms-Grid-row row_4eb3479c']/label")
		@CacheLookup
		WebElement questionsTxt;
		public WebElement getQuestionsTxt() {
			return questionsTxt;
		}
		
		@FindBy(xpath="//div[@class='ms-Grid-row row_4eb3479c']/label/a")
		@CacheLookup
		WebElement infoLink;
		public WebElement getInfoLink() {
			return infoLink;
		}
	//------------------------------Created By------------------------------------
		@FindBy(xpath="//label[text()='Created By']")
		@CacheLookup
		WebElement createdBylabel;
		public WebElement getCreatedBylabel() {
			return createdBylabel;
		}	
	//------------------------------Modified By------------------------------------
		@FindBy(xpath="//label[text()='Modified By']")
		@CacheLookup
		WebElement modifedBylabel;
		public WebElement getModifedBylabel() {
			return modifedBylabel;
		}
	//------------------------------Date Created------------------------------------
		@FindBy(xpath="//label[text()='Date created']")
		@CacheLookup
		WebElement dateCreatedLabel;
		public WebElement getDateCreatedLabel() {
			return dateCreatedLabel;
		}
	//------------------------------Date Modified------------------------------------
		@FindBy(xpath="//label[text()='Date modified']")
		@CacheLookup
		WebElement dateModifiedLabel;
		public WebElement getDateModifiedLabel() {
			return dateModifiedLabel;
		}
	//-----------------------------Save as Draft on Biography Page----------------------------		
		@FindBy(xpath="//div[text()='Save as Draft']")
		@CacheLookup
		WebElement save;
		public WebElement getSaveBtn() {
			return save;
		}
	//-----------------------------Close on Biography Page----------------------------	
		@FindBy(xpath="//div[text()='Close']")
		@CacheLookup
		WebElement close;
		public WebElement getCloseBtn() {
			return close;
		}
	//-----------------------------Next on Biography Page----------------------------	
		@FindBy(xpath="//span[contains(text(),'Next ')]")
		@CacheLookup
		WebElement next;
		public WebElement getNextBtn() {
			return next;
		}

	//-----------------------------Confirm button on close popup----------------------------	
		@FindBy(xpath="//div[text()='Confirm']")
		@CacheLookup
		WebElement confirm;
		public WebElement getConfirm() {
			return confirm;
		}
	//-----------------------------Cancel Button on Close popup----------------------------	
		@FindBy(xpath="//div[text()='Cancel']")
		@CacheLookup
		WebElement cancel;
		public WebElement getCancel() {
			return cancel;
		}
	//-----------------------------Close X Icon on Biography Page----------------------------	
		@FindBy(xpath="//div[contains(@class,'ms-Panel-navigation navigation')]/button")
		@CacheLookup
		WebElement closeXIcon;
		public WebElement getCloseXIcon() {
			return closeXIcon;
		}
	//************************************CONSULTANT PAGE***************************************	
	//--------------------------Name Checkbox-------------------------------	
		@FindBy(xpath="//label[contains(text(),'Name')]//following-sibling::div[1]")
		@CacheLookup
		WebElement name;
		public WebElement getNameCheckbox() {
			return name;
		}
	//-----------------------------Update Name----------------------------	
		@FindBy(name="updateName")
		@CacheLookup
		WebElement updateName;
		public WebElement getUpdateName() {
			return updateName;
		}
	//-----------------------------Familiar Name Checkbox----------------------------	
		@FindBy(xpath="//input[contains(@aria-label,'Check to use familiar name')]//following-sibling::label")
		@CacheLookup
		WebElement famName;
		public WebElement getFamNameCheckbox() {
			return famName;
		}
	//-----------------------------Credential Checkbox----------------------------	
		@FindBy(xpath="//label[contains(text(),'Credentials/Accreditations')]//following-sibling::div[1]")
		@CacheLookup
		WebElement credential;
		public WebElement getCredential() {
			return credential;
		}
	//-----------------------------Update Credentials----------------------------	
		@FindBy(name="modifyCreds")
		@CacheLookup
		WebElement modifyCreds;
		public WebElement getModifyCreds() {
			return modifyCreds;
		}
	//-----------------------------Title Checkbox----------------------------	
		@FindBy(xpath="//label[contains(text(),'Title (external)')]//following-sibling::div[1]")
		@CacheLookup
		WebElement title;
		public WebElement gettitle() {
			return title;
		}
	//-----------------------------Update Title Text field----------------------------	
		@FindBy(name="titleUpdate")
		@CacheLookup
		WebElement titleUpdate;
		public WebElement getTitleUpdate() {
			return titleUpdate;
		}
	//-----------------------------Product Name Checkbox----------------------------	
		@FindBy(xpath="//label[contains(text(),'Product Group Name (if applicable)')]//following-sibling::div[1]")
		@CacheLookup
		WebElement prodGrpName;
		public WebElement getProdGrpName() {
			return prodGrpName;
		}
	//-----------------------------Update Product Name text field----------------------------	
		@FindBy(name="productNameUpdate")
		@CacheLookup
		WebElement productNameUpdate;
		public WebElement getProductNameUpdate() {
			return productNameUpdate;
		}
	//-----------------------------Photo Checkbox----------------------------	
		@FindBy(xpath="//label[contains(text(),'Photo (approved headshot)')]//following-sibling::div[1]/label")
		@CacheLookup
		WebElement photo;
		public WebElement getPhoto() {
			return photo;
		}
	//-----------------------------Office Address Checkbox----------------------------	
		@FindBy(xpath="//label[contains(text(),'Office Address')]//following-sibling::div[1]")
		@CacheLookup
		WebElement officeAdd;
		public WebElement getOfficeAdd() {
			return officeAdd;
		}
	//-----------------------------Update Office Address Text Field----------------------------	
		@FindBy(xpath="//div[contains(@class,'addressFieldDisabled_4eb3479c')]//following-sibling::div[1]/div/div/textarea")
		@CacheLookup
		WebElement updateofficeAdd;
		public WebElement getUpdateOfficeAdd() {
			return updateofficeAdd;
		}
	//-----------------------------Phone Number Checkbox----------------------------	
		@FindBy(xpath="//label[contains(text(),'Phone Number')]//following-sibling::div[1]")
		@CacheLookup
		WebElement phoneNum;
		public WebElement getPhoneNum() {
			return phoneNum;
		}
	//-----------------------------Update Phone Number Text Field----------------------------	
		@FindBy(name="phoneNumberUpdate")
		@CacheLookup
		WebElement phoneNumberUpdate;
		public WebElement getPhoneNumberUpdate() {
			return phoneNumberUpdate;
		}
	//-----------------------------Email Address----------------------------	
		@FindBy(xpath="//label[contains(text(),'Email Address')]//following-sibling::div[1]/label")
		@CacheLookup
		WebElement email;
		public WebElement getEmailAddress() {
			return email;
		}
		
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-enabled checkbox_4eb3479c')])[8]")
		@CacheLookup
		WebElement emailDisable;
		public WebElement getEmailCheckboxDisable() {
			return emailDisable;
		}
		
		@FindBy(xpath="//input[@name='email']")
		@CacheLookup
		WebElement emailVal;
		public WebElement getEmail() {
			return emailVal;
		}
		
	//-----------------------------Prev Button on Consultant Page----------------------------
		@FindBy(xpath="//span[contains(text(),' Prev')]")
		@CacheLookup
		WebElement prev;
		public WebElement getPrevBtn() {
			return prev;
		}
		
	//----------------------------Submit button on Preview Page----------------------------------
		@FindBy(xpath="//div[contains(text(),'Submit')]")
		@CacheLookup
		WebElement submit;
		public WebElement getSubmitBtn() {
			return submit;
		}
	//----------------------------Download PDF button on Preview Page----------------------------------
		@FindBy(xpath="//div[contains(text(),'Download PDF')]")
		@CacheLookup
		WebElement dwldPDF;
		public WebElement getDwldPDF() {
			return dwldPDF;
		}
		
	//----------------------------Reorder Sections button on Preview Page----------------------------------
		@FindBy(xpath="//div[contains(text(),'Reorder Sections')]")
		@CacheLookup
		WebElement reorderSection;
		public WebElement getReorderSection() {
			return reorderSection;
		}
	//----------------------------User Details on Preview----------------------------------
		@FindBy(xpath="//div[@class='marcom-left-head']")
		@CacheLookup
		WebElement userDetails;
		public WebElement getuserDetails() {
			return userDetails;
		}
	//---------------------------List of the all the employees--------------------------------
		@FindBy(xpath="//div[@class='ms-List-page']")
		@CacheLookup
		WebElement empList;
		public WebElement getEmpList() {
			return empList;
		}
		
		@FindBy(xpath="//div[@class='ms-List-page']/div")
		@CacheLookup
		List<WebElement> empCount;
		public List getEmpCount() {
			return empCount;
		}

	//Get Employee name from list
		public WebElement getEmployee(int i)
		{
			WebElement name=driver.findElement(By.xpath("//div[@class='ms-List-page']/div[@data-list-index='" +i+ "']/div/div/div/span"));
			return name;
		}
		
	//Get Employee status from list
		public WebElement getEmployeeStatus(int i)
		{
			WebElement name=driver.findElement(By.xpath("//div[@class='ms-List-page']/div[@data-list-index='" +i+ "']/div/div/div[3]"));
			return name;
		}	

	//--------------------------------Biography Edit button--------------------------------
		@FindBy(xpath="//div[text()='Edit']")
		@CacheLookup
		WebElement bioEditBtn;
		public WebElement getBioEditBtn() {
			return bioEditBtn;
		}
	//--------------------------------Created By in Biograpgy Edit page--------------------------------
		@FindBy(xpath="//label[contains(text(),'Created By')]/following-sibling::div[1]/div/div/input")
		@CacheLookup
		WebElement createdBy;
		public WebElement getCreatedBy() {
			return createdBy;
		}
		
	//--------------------------------Modified By in Biograpgy Edit page--------------------------------
		@FindBy(xpath="//label[contains(text(),'Modified By')]/following-sibling::div[1]/div/div/input")
		@CacheLookup
		WebElement modifiedBy;
		public WebElement getModifiedBy() {
			return modifiedBy;
		}

	//--------------------------------Logged in user details---------------------------------
		@FindBy(xpath="//div[@class='mectrl_profilepic mectrl_profilepic_initials']")
		@CacheLookup
		WebElement loggedInAcnt;
		public WebElement getLoggedInAcnt() {
			return loggedInAcnt;
		}
		
		@FindBy(xpath="//div[@class='mectrl_accountDetails']/div")
		@CacheLookup
		WebElement loggedInUser;
		public WebElement getLoggedInUser() {
			return loggedInUser;
		}
		
		@FindBy(xpath="//div[@class='pivotContent_4eb3479c']")
		@CacheLookup
		WebElement consDetPage;
		public WebElement getConsDetailPageErr() {
			return consDetPage;
		}
	//*********************************Status Page***************************************
	// Urgent Checkbox
		@FindBy(xpath="(//input[@type='checkbox'])[1]")
		@CacheLookup
		WebElement urgent;
		public WebElement getUrgent() {
			return urgent;
		}
	//Document review status
		@FindBy(xpath="//label[text()='Document Review Status']")
		@CacheLookup
		WebElement docRevStatus;
		public WebElement getDocRevStatus() {
			return docRevStatus;
		}
	//Modified Date
		@FindBy(xpath="//label[text()='Modified Date']")
		@CacheLookup
		WebElement modifiedDate;
		public WebElement getModifiedDate() {
			return modifiedDate;
		}
	//Published Previously
		@FindBy(xpath="//label[text()='Published Previously']")
		@CacheLookup
		WebElement publishPrev;
		public WebElement getPublishPrev() {
			return publishPrev;
		}
	//Editor Textbox
		@FindBy(xpath="(//input[@type='text'])[4]")
		@CacheLookup
		WebElement editor;
		public WebElement getEditor() {
			return editor;
		}
	//Requires Further Review Checkbox
		@FindBy(xpath="(//input[@type='checkbox'])[2]")
		@CacheLookup
		WebElement reqFurReview;
		public WebElement getReqFurReview() {
			return reqFurReview;
		}
	//Requires Further Review Error message
		@FindBy(xpath="(//div[@class='fieldErrMessage_4eb3479c'])[1]")
		@CacheLookup
		WebElement reqFurReviewErr;
		public WebElement getReqFurReviewErrMsg() {
			return reqFurReviewErr;
		}
	//Editor Comments	
		@FindBy(xpath="//label[text()='Editor Comments']//following-sibling::div[1]/div/div/div/textarea")
		@CacheLookup
		WebElement editorComments;
		public WebElement getEditorComments() {
			return editorComments;
		}
	//Submitter Review Completed	
		@FindBy(xpath="//label[text()='Submitter Review Completed']")
		@CacheLookup
		WebElement subRevComp;
		public WebElement getSubRevComp() {
			return subRevComp;
		}
	//Editor Review Completed Checkbox
		@FindBy(xpath="(//input[@type='checkbox'])[4]")
		@CacheLookup
		WebElement editReview;
		public WebElement getEditReviewComp() {
			return editReview;
		}
	//Editor Review Completed error message
		@FindBy(xpath="(//div[@class='fieldErrMessage_4eb3479c'])[2]")
		@CacheLookup
		WebElement editReviewErr;
		public WebElement getEditorReviewCompErrMsg() {
			return editReviewErr;
		}	
	//Approved and Publish Checkbox
		@FindBy(xpath="(//input[@type='checkbox'])[5]")
		@CacheLookup
		WebElement appPub;
		public WebElement getApprovePublish() {
			return appPub;
		}
	//Publish to Web required Checkbox
		@FindBy(xpath="(//input[@type='checkbox'])[6]")
		@CacheLookup
		WebElement pubWebReq;
		public WebElement getPublishWebReq() {
			return pubWebReq;
		}
	//Publish to web completed Checkbox	
		@FindBy(xpath="(//input[@type='checkbox'])[7]")
		@CacheLookup
		WebElement pubWebCom;
		public WebElement getPublishWebComp() {
			return pubWebCom;
		}
	//Bypass review process Checkbox	
		@FindBy(xpath="(//input[@type='checkbox'])[8]")
		@CacheLookup
		WebElement bypassRev;
		public WebElement getBypassRev() {
			return bypassRev;
		}
	//Editor Preview
		@FindBy(xpath="(//div[@class='props-wrapper'])[2]")
		@CacheLookup
		WebElement editorPreview;
		public WebElement getEditorPreview() {
			return editorPreview;
		}
	//Footer Preview
		@FindBy(xpath="//div[@class='footer-container']/table/tbody/tr/td")
		@CacheLookup
		WebElement officePreview;
		public WebElement getOfficePreview() {
			return officePreview;
		}
	//Error message on Preview page on initial Bio form submissions	
		@FindBy(xpath="//div[@class='errText_4eb3479c']")
		@CacheLookup
		WebElement prePage;
		public WebElement getPreviewPageErr() {
			return prePage;
		}
	//Editor Error message
		@FindBy(xpath="(//span[@data-automation-id='error-message'])[1]")
		@CacheLookup
		WebElement editorErr;
		public WebElement getEditorErrMsg() {
			return editorErr;
		}
	//Editor Comments error message
		@FindBy(xpath="(//span[@data-automation-id='error-message'])[2]")
		@CacheLookup
		WebElement editorCommErr;
		public WebElement getEditorComntsErrMsg() {
			return editorCommErr;
		}
	//Urgent Disable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-enabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[1]")
		@CacheLookup
		WebElement urgentDisable;
		public WebElement getUrgentDisable() {
			return urgentDisable;
		}
	//Requires Further Review Disable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-enabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[2]")
		@CacheLookup
		WebElement reqFurReviewDisable;
		public WebElement getreqFurReviewDisable() {
			return reqFurReviewDisable;
		}
	//Editor review completed Disable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-enabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[3]")
		@CacheLookup
		WebElement editorReviewComDisable;
		public WebElement geteditorReviewComDisable() {
			return editorReviewComDisable;
		}
	//Publish to web required  Disable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-enabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[4]")
		@CacheLookup
		WebElement pubWebReqDisable;
		public WebElement getPubWebReqDisable() {
			return pubWebReqDisable;
		}
	//Bypass review process  Disable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-enabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[5]")
		@CacheLookup
		WebElement byPassDisable;
		public WebElement getByPassRevDisable() {
			return byPassDisable;
		}
	//Approve and Publish checkbox disable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-disabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[2]")
		@CacheLookup
		WebElement appPubDisable;
		public WebElement getAppPubDisable() {
			return appPubDisable;
		}
	//Publish to web Completed checkbox disable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-disabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[3]")
		@CacheLookup
		WebElement pubWebComDisable;
		public WebElement getPubWebComDisable() {
			return pubWebComDisable;
		}
	//Submitter review completed checkbox enable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-disabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[1]")
		@CacheLookup
		WebElement subReviewCompDisable;
		public WebElement getSubReviewCompDisable() {
			return subReviewCompDisable;
		}
	//Submitter review completed checkbox enable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-checked is-disabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[1]")
		@CacheLookup
		WebElement subReviewCompEnable;
		public WebElement getSubReviewCompEnable() {
			return subReviewCompEnable;
		}
	//Editor review completed checkbox enable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-checked is-enabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[1]")
		@CacheLookup
		WebElement editorReviewCompEnable;
		public WebElement getEditorReviewCompEnable() {
			return editorReviewCompEnable;
		}
	//Publish to web required checkbox enable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-checked is-enabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[2]")
		@CacheLookup
		WebElement pubWebReqEnable;
		public WebElement getPubWebReqEnable() {
			return pubWebReqEnable;
		}
	//Approve and Publish checkbox enable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-checked is-disabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[2]")
		@CacheLookup
		WebElement appPubEnable;
		public WebElement getAppPubEnable() {
			return appPubEnable;
		}
	//Publish to web Completed checkbox enable check
		@FindBy(xpath="(//div[contains(@class,'ms-Checkbox is-checked is-enabled ms-Grid-col ms-sm12 ms-md12 ms-lg12 removePadding_4eb3479c')])[3]")
		@CacheLookup
		WebElement pubWebComEnable;
		public WebElement getPubWebComEnable() {
			return pubWebComEnable;
		}
	//*****************************************Attachments Page*****************************************
		@FindBy(xpath="(//div[contains(@class,'ms-DetailsList-contentWrapper contentWrapper')])[2]")
		@CacheLookup
		WebElement attachFile;
		public WebElement getAttachmentName() {
			return attachFile;
		}
	//Attachment download button
		@FindBy(xpath="//button[@title='Download']")
		@CacheLookup
		WebElement attachDwld;
		public WebElement getAttachmentDwld() {
			return attachDwld;
		}
	//Unlock button
		@FindBy(xpath="//div[text()='Unlock']")
		@CacheLookup
		WebElement unlockBtn;
		public WebElement getUnlockBtn() {
			return unlockBtn;
		}
	//Yes button on Unlock confirmation popup
		@FindBy(xpath="//div[text()='Yes']")
		@CacheLookup
		WebElement yesBtn;
		public WebElement getYesBtn() {
			return yesBtn;
		}
	//No button on Unlock confirmation popup
		@FindBy(xpath="//div[text()='No']")
		@CacheLookup
		WebElement noBtn;
		public WebElement getNoBtn() {
			return noBtn;
		}
	//*****************************************COMMON METHODS**************************************************
		//Add details in Biography Page
		public void addBiographyDetails() throws IOException, InterruptedException {
			clickAddBio();
			addEmpName();
			checkAllBioFields();
			addDataBioFields();
			clickNext();
		}
		//Add details in Consultant Page
		public void addConsultantDetails() throws IOException, InterruptedException {
			checkAllConsultantFields();
			addAllConsultantFields();
		}
		//Add Status details as Marcom Editor
		public void addStatusDetails() throws IOException, InterruptedException {
			actions.moveToElement(getUrgent()).click().perform();
			logger.info("Urgent Checkbox is selected");
			addEditorText();
			addEditorCommentsText();
			actions.moveToElement(getEditReviewComp()).click().perform();
			logger.info("Editor Review Completed checkbox is selected");
			actions.moveToElement(getPublishWebComp()).click().perform();
			logger.info("Publish to web completed checkbox is selected");
			actions.moveToElement(getBypassRev()).click().perform();
			logger.info("Bypass review process checkbox is selected");
			clickNext();	
			}
	/*	public boolean checkBioHeaderPreview(String Bio) throws IOException, InterruptedException{
			String empUpdatedName=updatename.toLowerCase();
			logger.info(empUpdatedName);
			logger.info(Bio);
			String updatedCrendential=updatecredential.toLowerCase();
			logger.info(updatedCrendential);
			String updatedTitle=updatetitle.toLowerCase();
			logger.info(updatedTitle);
			boolean nameMatched = Bio.contains(empUpdatedName);Thread.sleep(5000);
		    boolean credentialMatched = Bio.contains(updatedCrendential);
		    boolean titleMatched = Bio.contains(updatedTitle);
		    boolean phoneMatched = Bio.contains(updatephone);

			if(nameMatched)
				logger.info("Employee name is matched with Preview");
			else
				logger.info("Employee name is not matched with Preview");
			if(credentialMatched)
				logger.info("Credential Value is matched with Preview");
			else 
				logger.info("Credential Value is not matched with Preview");
			if(titleMatched)
				logger.info("Title Value is matched with Preview");
			else
				logger.info("Title Value is not matched with Preview");
			if(phoneMatched)
				logger.info("Phone Value is matched with Preview");
			else
				logger.info("Phone Value is not matched with Preview");
			return nameMatched && credentialMatched && titleMatched && phoneMatched;		
		}
		public boolean checkBioContainerPreview(String Bio) throws IOException, InterruptedException{
			
			return false;
			
		}*/
		//Validating Preview as Marcom consultant and Editor
		public void checkPreview() throws IOException, InterruptedException{
			String PDFpart1=getuserDetails().getText().toLowerCase();
			logger.info(PDFpart1);
			logger.info("Validating Consultant data with Preview");
			String Employeename=updatename.toLowerCase();
			if(PDFpart1.contains(Employeename))	{
				Assert.assertTrue(true);
				logger.info("Employee name is matched with Preview");
			}
			else {
				captureScreen(driver,"Consultant Biography");
				Assert.assertTrue(false);
				logger.info("Employee name is not matched with Preview");
			}
			String credential=updatecredential.toLowerCase();
			if(PDFpart1.contains(credential))	{
				Assert.assertTrue(true);
				logger.info("Credential Value is matched with Preview");
			}
			else {
				captureScreen(driver,"Consultant Biography");
				Assert.assertTrue(false);
				logger.info("Credential Value is not matched with Preview");
			}
			String title=updatetitle.toLowerCase();
			if(PDFpart1.contains(title))	{
				Assert.assertTrue(true);
				logger.info("Title Value is matched with Preview");
			}
			else {
				captureScreen(driver,"Consultant Biography");
				Assert.assertTrue(false);
				logger.info("Title Value is not matched with Preview");
			}
			if(PDFpart1.contains(updatephone))	{
				Assert.assertTrue(true);
				logger.info("Phone Value is matched with Preview");
			}
			else {
				captureScreen(driver,"Consultant Biography");
				Assert.assertTrue(false);
				logger.info("Phone Value is not matched with Preview");
			}
			String PDFpart2=getEditorPreview().getText();
			logger.info(PDFpart2);
			if(PDFpart2.contains(currentresponseheader)&&(PDFpart2.contains(currentresponse)))	{
				Assert.assertTrue(true);
				logger.info("Current Responsibility details are matched with Preview");
			}
			else {
				Assert.assertTrue(false);
				logger.info("Current Responsibility details are not matched with Preview");
			}
			if(PDFpart2.contains(profworkexpheader)&&(PDFpart2.contains(profworkexp)))	{
				Assert.assertTrue(true);
				logger.info("Professional Work experience details are matched with Preview");
			}
			else {
				Assert.assertTrue(false);
				logger.info("Professional Work experience details are not matched with Preview");
			}
			if(PDFpart2.contains(profdesheader)&&(PDFpart2.contains(profdes)))	{
				Assert.assertTrue(true);
				logger.info("Professional Designation details are matched with Preview");
			}
			else {
				Assert.assertTrue(false);
				logger.info("Professional Designation details are not matched with Preview");
			}
			if(PDFpart2.contains(educationheader)&&(PDFpart2.contains(education)))	{
				Assert.assertTrue(true);
				logger.info("Education details are matched with Preview");
			}
			else {
				Assert.assertTrue(false);
				logger.info("Education details are not matched with Preview");
			}
			if(PDFpart2.contains(presandpubheader)&&(PDFpart2.contains(presandpub)))	{
				Assert.assertTrue(true);
				logger.info("Presentations and Publications details are matched with Preview");
			}
			else {
				Assert.assertTrue(false);
				logger.info("Presentations and Publications details are not matched with Preview");
			}
			if((PDFpart2.contains(affiliationsheader))&&(PDFpart2.contains(affiliations)))	{
				Assert.assertTrue(true);
				logger.info("Affiliations details are matched with Preview");
			}
			else {
				Assert.assertTrue(false);
				logger.info("Affiliations details are not matched with Preview");
			}
			String PDFpart3=getOfficePreview().getText();
			logger.info(PDFpart3);
			if(PDFpart3.contains(updateoffice))	{
				Assert.assertTrue(true);
				logger.info("Office details are matched with Preview");
			}
			else {
				Assert.assertTrue(false);
				logger.info("Office details are not matched with Preview");
			}
		}
		public boolean checkPDFPreview(String bio) throws IOException, InterruptedException{
			String Employeename=updatename.toLowerCase();
			if(bio.contains(Employeename))	{
				logger.info("Employee name is matched with Preview");
			}
			else {
				logger.info("Employee name is not matched with Preview");
			}
			String credential=updatecredential.toLowerCase();
			if(bio.contains(credential))	{
				logger.info("Credential Value is matched with Preview");
			}
			else {
				logger.info("Credential Value is not matched with Preview");
			}
			String title=updatetitle.toLowerCase();
			if(bio.contains(title))	{
				logger.info("Title Value is matched with Preview");
			}
			else {
				logger.info("Title Value is not matched with Preview");
			}
			if(bio.contains(updatephone))	{
				logger.info("Phone Value is matched with Preview");
			}
			else {
				logger.info("Phone Value is not matched with Preview");
			}
			if(bio.contains(currentresponseheader)&&(bio.contains(currentresponse))){
				logger.info("Current Responsibility details are matched with Preview");
			}
			else {
				logger.info("Current Responsibility details are not matched with Preview");
			}
			if(bio.contains(profworkexpheader)&&(bio.contains(profworkexp))){
				logger.info("Professional Work experience details are matched with Preview");
			}
			else {
				logger.info("Professional Work experience details are not matched with Preview");
			}
			if(bio.contains(profdesheader)&&(bio.contains(profdes))){
				logger.info("Professional Designation details are matched with Preview");
			}
			else {
				logger.info("Professional Designation details are not matched with Preview");
			}
			if(bio.contains(educationheader)&&(bio.contains(education))){
				logger.info("Education details are matched with Preview");
			}
			else {
				logger.info("Education details are not matched with Preview");
			}
			if(bio.contains(presandpubheader)&&(bio.contains(presandpub)))	{
				logger.info("Presentations and Publications details are matched with Preview");
			}
			else {
				logger.info("Presentations and Publications details are not matched with Preview");
			}
			if((bio.contains(affiliationsheader))&&(bio.contains(affiliations))){
				logger.info("Affiliations details are matched with Preview");
			}
			else {
				logger.info("Affiliations details are not matched with Preview");
			}
			if(bio.contains(updateoffice))	{
				logger.info("Office details are matched with Preview");
			}
			else {
				logger.info("Office details are not matched with Preview");
			}
			return true;
		}
		//Milliman user can edit and update his current bio
		public void updateEmployeeDetails() throws IOException, InterruptedException {
			logger.info(getIframeCount().size());	
			for(int i=1;i<=getIframeCount().size();i++)
			{
				this.driver=driver;
				driver.switchTo().frame(getIframe(i));	
				switch(i) {
				case 1:editDescription(currentresponse);
				logger.info("Edited currentresponse description");
				break;
				case 2:editDescription(profworkexp);
				logger.info("Edited professional Work experience description");
				break;
				case 3:editDescription(profdes);
				logger.info("Edited professional designation description");
				break;
				case 4:editDescription(education);
				logger.info("Edited Education description");
				break;
				case 5:editDescription(presandpub);
				logger.info("Edited Presentation and Publications description");
				break;
				case 6:editDescription(affiliations);
				logger.info("Edited Affiliations description");
				break;
				}		
			}  
		}
		//Selecting Add Bio Button
		public void clickAddBio() throws IOException, InterruptedException {
			  Thread.sleep(2000);
			  try {
			  wait.until(ExpectedConditions.visibilityOf(getAddBioBtnBtn()));
			  getAddBioBtnBtn().click();
			  }
			  catch(StaleElementReferenceException e)
			  {
				  WebElement addbutton= driver.findElement(By.xpath("//div[text()='Add Bio']"));
				  wait.until(ExpectedConditions.visibilityOf(addbutton));
				  addbutton.click();
			  }
			  logger.info("Add Bio Page Displayed");
			  logger.info(driver.getTitle());
			  Thread.sleep(3000); 
		}
	//*******************BIOGRAPHY DETAILS*********************************//
		//Check biography details tab
		public void checkBiographyTab() throws IOException, InterruptedException {
			try {
			  wait.until(ExpectedConditions.visibilityOf(getBiographyTab()));
				} 
				catch (TimeoutException  ex) {
				logger.info("Unable to Find Biography form");
				Assert.assertTrue(false);
				}
			if(getBiographyTab().isDisplayed()) {
				Assert.assertTrue(true);
				logger.info("Biography Details Tab is displayed");
			}
			else {
				captureScreen(driver,"Consultant Biography");
				Assert.assertTrue(false);
				logger.info("Biography Details Tab is not displayed");
			}
		}
		//Check consultant details tab
		public void checkConsultantTab() throws IOException, InterruptedException {
			try {
			  wait.until(ExpectedConditions.visibilityOf(getConsultantTab()));
				} 
				catch (TimeoutException  ex) {
				logger.info("Unable to Find Biography form");
				Assert.assertTrue(false);
				}
			if(getConsultantTab().isDisplayed()) {
				Assert.assertTrue(true);
				logger.info("Consultant Details Tab is displayed");
			}
			else {
				captureScreen(driver,"Consultant Biography");
				Assert.assertTrue(false);
				logger.info("Consultant Details Tab is not displayed");
			}
		}
		//Check preview tab
		public void checkPreviewTab() throws IOException, InterruptedException {
			try {
			  wait.until(ExpectedConditions.visibilityOf(getPreviewTab()));
				} 
				catch (TimeoutException  ex) {
				logger.info("Unable to Find Biography form");
				Assert.assertTrue(false);
				}
			if(getPreviewTab().isDisplayed()) {
				Assert.assertTrue(true);
				logger.info("Preview Tab is displayed");
			}
			else {
				captureScreen(driver,"Consultant Biography");
				Assert.assertTrue(false);
				logger.info("Preview Tab is not displayed");
			}
		}
	//Employee Name in Biography Page
	public void addEmpName2() throws IOException, InterruptedException {	
		int randomEmployeeRow= cm.generateRandomNumber(2, 6);
		String employeeName=cm.readExcel(randomEmployeeRow, 0);
		selectEmpName(employeeName);
			Thread.sleep(3000);
			WebElement emplyeeExistsError=getEmpNameExistsError();
			if(emplyeeExistsError.isDisplayed())
			{
			logger.info("Deleting any pre-existing user having EmployeeName as: "+ empname);
			del.deleteUser(employeeName);
			selectEmpName(employeeName);
			}
		    logger.info("Selected Employee Name");
		    Thread.sleep(5000);
	}
	public void addEmpName() throws IOException, InterruptedException {	
		//selectEmpName(empname);	
		//WebElement emplyeeExistsError=getEmpNameExistsError();
			logger.info("Deleting any pre-existing user having EmployeeName as: "+ empname);
			del.deleteUser(empname);
			selectEmpName(empname);
		    logger.info("Selected Employee Name");
		    Thread.sleep(5000);
	}
	//Adding description in Biography form
	public String addDescription(String text) {
		try {
		wait.until(ExpectedConditions.visibilityOf(getDescriptionTxt()));
		getDescriptionTxt().click();
		getDescriptionTxt().clear();
		getDescriptionTxt().sendKeys(text);
		}
		catch(StaleElementReferenceException e)
		{
			WebElement multipleLinesOfText= driver.findElement(By.xpath("//*[@id='tinymce']/p"));
			wait.until(ExpectedConditions.visibilityOf(multipleLinesOfText));
			multipleLinesOfText.click();
			multipleLinesOfText.clear();
			multipleLinesOfText.sendKeys(text);
		}
		driver.switchTo().defaultContent();
		return text;
	}
	//Editing description in Biography form
	public String editDescription(String text) throws InterruptedException {
		getEditDescriptionTxt().click();
		getEditDescriptionTxt().clear();
		getEditDescriptionTxt().sendKeys(text);
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		return text;
	}
	//Verify all fields on Biography Page
	public void checkAllBioFields() throws IOException, InterruptedException {
		try {
			  wait.until(ExpectedConditions.visibilityOf(getcurrentResp()));
			} 
			catch (TimeoutException  ex) {
			logger.info("Unable to Find Biography form");
			Assert.assertTrue(false);
			}
		if(getcurrentResp().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Current Description field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Current Response field is missing");
		}
		if(getProfWorkExp().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Professional Work Experience Description field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Professional Work Experience field is missing");
		}
		if(getProfDes().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Professional Designation Description field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Professional Designation field is missing");
		}
		if(getEducation().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Education Description field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Education field is missing");
		}
		if(getPresandPub().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Presentation and Publications Description field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Presentation and Publications field is missing");
		}
		if(getAffiliations().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Affiliations Description field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Affiliations field is missing");
		}
		if((getQuestionsTxt().isDisplayed()) && (getInfoLink().isDisplayed())) {
			Assert.assertTrue(true);
			logger.info("If you have questions, please contact more.info@milliman.com. text is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("If you have questions, please contact more.info@milliman.com. text is missing");
		}
		if(getCreatedBylabel().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Created By Label is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Created By Label is missing");
		}
		if(getModifedBylabel().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Modified By Label is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Modified By Label is missing");
		}
		if(getDateCreatedLabel().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Date Created Label is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Date Created Label is missing");
		}
		if(getDateModifiedLabel().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Date Modified Label is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Date Modified Label is missing");
		}
		
	}
	//Add data to the fields on Bio Page
	public void addDataBioFields() {
		logger.info(getIframeCount().size());	
		for(int i=1;i<=getIframeCount().size();i++)
		{
			this.driver=driver;
			driver.switchTo().frame(getIframe(i));	
			switch(i) {
			case 1:addDescription(currentresponse);
			logger.info("Added currentresponse description");
			break;
			case 2:addDescription(profworkexp);
			logger.info("Added professional Work experience description");
			break;
			case 3:addDescription(profdes);
			logger.info("Added professional designation description");
			break;
			case 4:addDescription(education);
			logger.info("Added Education description");
			break;
			case 5:addDescription(presandpub);
			logger.info("Added Presentation and Publications description");
			break;
			case 6:addDescription(affiliations);
			logger.info("Added Affiliations description");
			break;
			}		
		}
	}
	//*******************CONSULTANT DETAILS*********************************//
	//Check all consultant fields
	public void checkAllConsultantFields() throws IOException{
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getNameCheckbox()));
			} 
			catch (TimeoutException  ex) {
			logger.info("Unable to Find Biography form");
			Assert.assertTrue(false);
			}
		if(getNameCheckbox().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Name checkbox is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Name checkbox is missing");
		}
		if(getFamNameCheckbox().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Familiar Name checkbox is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Familiar Name checkbox is missing");
		}
		if(getCredential().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Credential/Accreditations field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Credential/Accreditations field is missing");			
		}
		if(gettitle().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Title field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Title field is missing");
		}
		if(getProdGrpName().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Product name is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Product name is missing");
		}
		if(getPhoto().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Photo checkbox is diaplayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Photo checkbox is missing");
		}
		if(getOfficeAdd().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Office Adrress field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Office Adrress field is missing");
		}
		if(getPhoneNum().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Phone number field is diaplyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Phone number field is missing");
		}
		if(getEmailAddress().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Email address field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Email address field is missing");
		}
	}
	//Add all Consultant add
	//Check all consultant fields
		public void addAllConsultantFields() throws IOException, InterruptedException{
			getNameCheckbox().click();
			logger.info("Name checkbox is selected");
			getUpdateName().click();
			getUpdateName().sendKeys(updatename);
			logger.info("Updated Name value");
			getCredential().click();
			logger.info("Credential checkbox is selected");
			getModifyCreds().click();
			logger.info("Updated Credential value");
			getModifyCreds().sendKeys(updatecredential);
			logger.info("New Credentials is provided");
			gettitle().click();
			logger.info("Title is selected");
			getTitleUpdate().click();
			logger.info("Updated Title value");
			getTitleUpdate().sendKeys(updatetitle);
			logger.info("New Title is provided");
			Thread.sleep(3000);
			getProdGrpName().click();
			logger.info("Prod Grp Name checkbox is selected");
			getProductNameUpdate().click();
			getProductNameUpdate().sendKeys(updateprodgrpname);
			logger.info("Updated Prod Group Name");
			Thread.sleep(3000);
			getPhoto().click();
			logger.info("Photo checkbox is selected");
			Thread.sleep(3000);
			getOfficeAdd().click();
			logger.info("Office Address checkbox is selected");
			getUpdateOfficeAdd().click();
			getUpdateOfficeAdd().sendKeys(updateoffice);
			logger.info("Updated Office Address");
			Thread.sleep(3000);
			getPhoneNum().click();
			logger.info("Phone checkbox is selected");
			getPhoneNumberUpdate().click();
			getPhoneNumberUpdate().sendKeys(updatephone);
			logger.info("Updated Phone number");
			Thread.sleep(3000);		
		}
//*************************************STATUS Page*************************************
	//--------------------------Check all Status page fields----------------------------
	public void checkAllStatusFields() throws IOException{
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getUrgent()));
			} 
			catch (TimeoutException  ex) {
			logger.info("Unable to Find Biography form");
			Assert.assertTrue(false);
			}
		if(getUrgent().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Urgent checkbox is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Urgent checkbox is missing");
		}
		if(getDocRevStatus().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Document Review Status field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Document Review Status field is missing");
		}
		if(getModifiedDate().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Modified Date field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Modified Date field is missing");
		}
		if(getPublishPrev().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Published Previously field is displayed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Published Previously field is missing");
		}
		if(getEditor().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Editor field is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Editor Field is missing");
		}
		if(getReqFurReview().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Required Further Review checkbox is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Required Further Review checkbox is missing");
		}
		if(getEditorComments().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Editor Comments field is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Editor Comments Field is missing");
		}
		if(getSubRevComp().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Submitter Review Completed checkbox is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Submitter Review Completed checkbox is missing");
		}
		if(getEditReviewComp().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Editor Review Completed checkbox is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Editor Review Completed checkbox is missing");
		}
		if(getApprovePublish().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Approve & Publish checkbox is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Approve & Publish checkbox is missing");
		}
		if(getPublishWebReq().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Publish to Web required checkbox is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Publish to Web required checkbox is missing");
		}
		if(getPublishWebComp().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Publish to Web completed checkbox is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Publish to Web completed checkbox is missing");
		}
		if(getBypassRev().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("By pass review process checkbox is dispalyed");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("By pass review process checkbox is missing");
		}
	}
	//Save as Draft button in Biography page
	public void clickSaveDraft() throws IOException, InterruptedException {
		clickAddBio();
		  wait.until(ExpectedConditions.visibilityOf(getSaveBtn()));
		  getSaveBtn().click();
		  logger.info("Save as Draft button is selected");
		  Thread.sleep(3000);
	}
	//Close button in Biography Page
	public void clickClose() throws IOException, InterruptedException {
		clickAddBio();
		  wait.until(ExpectedConditions.visibilityOf(getCloseBtn()));
		  getCloseBtn().click();
		  logger.info("Close button is selected");
		  clickConfirm();
	}
	//Confirm button on Close confirmation popup
	public void clickConfirm() throws IOException, InterruptedException {
		  wait.until(ExpectedConditions.visibilityOf(getConfirm()));
		  getConfirm().click();
		  logger.info("Confirm button is selected");
		  Thread.sleep(3000);
	}
	//Cancel button on Close confirmation popup
	public void clickCancel() throws IOException, InterruptedException {
		  wait.until(ExpectedConditions.visibilityOf(getCancel()));
		  getCancel().click();
		  logger.info("Cancel button is selected");
	}
	//Close X ICon on Biography Page
	public void clickCloseXIcon() throws IOException, InterruptedException {
		clickAddBio();
		  actions.moveToElement(getCloseXIcon()).click().perform();
		  logger.info("Close Icon button is selected");
	}
	//Next button on Biography Page
	public void clickNext() throws IOException, InterruptedException {
		  wait.until(ExpectedConditions.elementToBeClickable(getNextBtn()));
		  Thread.sleep(7000);
		  getNextBtn().click();
		  logger.info("Next button is selected");
		  Thread.sleep(3000);
	}
	//Edit button on Biography Page as Marcom Editor
	public void clickEdit() throws IOException, InterruptedException {
		  wait.until(ExpectedConditions.visibilityOf(getBioEditBtn()));
		  getBioEditBtn().click();
		  logger.info("Edit button is selected");
		  Thread.sleep(5000);
	}
	
	//Yes button on Unlock popup
		public void clickUnlock() throws IOException, InterruptedException {
			  wait.until(ExpectedConditions.visibilityOf(getUnlockBtn()));
			  getUnlockBtn().click();
			  logger.info("Unlock button is selected");
			  Thread.sleep(2000);	  
		}
	
	//Submit button on Preview Page as Marcom Consultant and Editor
	public void clickSubmit() throws IOException, InterruptedException {
		  wait.until(ExpectedConditions.elementToBeClickable(getSubmitBtn()));
		  Thread.sleep(7000);
		  getSubmitBtn().click();
		  logger.info("Submit button is selected");
		  Thread.sleep(3000);
		  driver.navigate().refresh();
		  logger.info("Page is refreshed");
	}
	//*****************************STATUS PAGE************************************
	//Editor Textbox
	public void addEditorText() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getEditor()));
			} 
			catch (TimeoutException  ex) {
			logger.info("Unable to Find Biography form");
			Assert.assertTrue(false);
			}
		if(getEditor().isDisplayed()) {
			Assert.assertTrue(true);
			getEditor().click();
			getEditor().sendKeys(currentresponse);
			logger.info("Editor Name is added");
			Thread.sleep(3000);
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Editor Field is missing");
		}	
	}
	//Editor Comments Textbox
	public void addEditorCommentsText() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getEditorComments()));
			} 
			catch (TimeoutException  ex) {
			logger.info("Unable to Find Biography form");
			Assert.assertTrue(false);
			}
		if(getEditorComments().isDisplayed()) {
			Assert.assertTrue(true);
			getEditorComments().click();
			getEditorComments().sendKeys(currentresponse);
			logger.info("Editor Comments are added");
			Thread.sleep(3000);
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Editor Comments Field is missing");
		}	
	}
	//Email Address checkbox
	public void selectEmailAddressCheckbox() throws IOException, InterruptedException {
			try {
			wait.until(ExpectedConditions.elementToBeClickable(getEmailAddress()));
			} 
			catch (TimeoutException  ex) {
			logger.info("Unable to Find Biography form");
			Assert.assertTrue(false);
			}
		if(getEmailAddress().isDisplayed()) {
			Assert.assertTrue(true);
			getEmailAddress().click();
			logger.info("Email Address checkbox is selected");
			Thread.sleep(3000);
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Email Address checkbox is missing");
		}	
	}
	//**********************************Home Page**********************************
	@Test
	//Select Employee Record As Marcom Editor to Edit  the Biography Page
	public void clickEmployeeRecord() throws IOException, InterruptedException {
		Thread.sleep(3000);
		logger.info(getEmpList().getText());
		int count=getEmpCount().size();
		for (int j=0;j<count; j++)
	 	 {
			WebElement desiredOption= getEmployee(j);
			logger.info(desiredOption.getText());
	        if(desiredOption.getText().equals(empname))
	        {
	        	Assert.assertTrue(true);
	        	logger.info(desiredOption.getText());
	        	Thread.sleep(3000);
	        	desiredOption.click();
	        	logger.info("Employee name is selected");
	        	Thread.sleep(13000);	        	  
	        	break;
	        }
	 	 }
	}
	@Test
	//Get Status of the Record
	public String getStatus(String status) throws InterruptedException
	{
		Thread.sleep(3000);
		logger.info(getEmpList().getText());
		int count=getEmpCount().size();
		logger.info(status);
		for (int j=0;j<count; j++)
	 	 {
			WebElement empName= getEmployee(j);
			logger.info(empName.getText());
			WebElement empStatus= getEmployeeStatus(j);
			logger.info(empStatus.getText());
	        if((empName.getText().equals(empname))&&(empStatus.getText().equals(status)))
	        {
	        	logger.info("Employee Name is " + empname+ "and Status is " + status);
	        	return status;
	        }
	 	 }
		logger.info("Status not found, returning default status");
	    return "not found"; 
	}
		
	//Created By on Biography page
	public void verifyCreatedBy() throws IOException, InterruptedException {
		addStatusDetails();
		getBioEditBtn().click();
		logger.info("Edit button is selected");
		String created=getCreatedBy().getAttribute("value");
		logger.info(created);
	}
	//Modified By on Biography Page
	public void verifyModifiedBy() throws IOException, InterruptedException {
		addStatusDetails();
		getBioEditBtn().click();
		logger.info("Edit button is selected");
		String modified=getModifiedBy().getAttribute("value");
		logger.info(modified);
	}
	//Download PDF 
	public void selectDownloadPDF() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getDwldPDF()));
			} 
			catch (TimeoutException  ex) {
			logger.info("Unable to Find Biography form");
			Assert.assertTrue(false);
			}
		if(getDwldPDF().isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Download PDF is available");
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Download PDF option is missing");
		}	
	}
	//Reorder Sections
	public void selectReorderSections() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getReorderSection()));
			} 
			catch (TimeoutException  ex) {
			logger.info("Unable to Find Biography form");
			Assert.assertTrue(false);
			}
		if(getReorderSection().isDisplayed()) {
			Assert.assertTrue(true);
			getReorderSection().click();
			logger.info("Reorder Section is selected");
			Thread.sleep(3000);
		}
		else {
			captureScreen(driver,"Consultant Biography");
			Assert.assertTrue(false);
			logger.info("Reorder Section option is missing");
		}	
	}
	//Search Biographies
	public void clickEmployeeEditonSearchBioPage() throws IOException, InterruptedException {
		Thread.sleep(3000);
		logger.info(getEmpList().getText());
		int count=getEmpCount().size();
		for (int j=0;j<count; j++)
	 	 {
			WebElement desiredOption= getEmployee(j);
			logger.info(desiredOption.getText());
	        if(desiredOption.getText().equals(empname))
	        {
	        	Assert.assertTrue(true);
	        	getEmployeeEdit(j).click();
	        	logger.info("Edit option of respective employee is selected");  
	        	break;
	        }
	 	 }
	}
	public boolean checkEmployee(String name) throws IOException, InterruptedException {
		String Employeename= name;
		logger.info(getEmpList().getText());
		int count=getEmpCount().size();
		boolean employeeFound = false;
		for (int j=0;j<count; j++)
	 	 {
			WebElement desiredOption= getEmployee(j);
			logger.info(desiredOption.getText());
	        if(desiredOption.getText().equals(Employeename))
	        {
	        	logger.info(desiredOption.getText());
	        	desiredOption.click();
	        	logger.info("Employee name is selected");
	        	Thread.sleep(10000);
	        	 employeeFound = true;
	        	break;
	        }
	      }
		 if (!employeeFound) {
		        logger.info("Employee name not found in the list.");
		    }
		return employeeFound;
	}
	//Open file from PC
	public String openDownloadedPDF(String filePath) throws InterruptedException, IOException {
		File fileLocation = new File(filePath);
	    File[] allFiles = fileLocation.listFiles();
	    StringBuilder extractedText = new StringBuilder();
	    String text="";
	    if (allFiles != null && allFiles.length > 0) {
	        for (int i = allFiles.length - 1; i >= 0; i--) {
	            File file = allFiles[i];
	            String eachFile = file.getName();
	            if (eachFile.contains(updatename)) {
	                logger.info(eachFile);
	                logger.info("Verified: File Has Been Downloaded.");
	                logger.info(filePath + "\\" + eachFile);
	                File PDFfile = new File(filePath + "\\" + eachFile);
	                try {
	                    PDDocument document = PDDocument.load(PDFfile);
	                    PDFTextStripper pdfStripper = new PDFTextStripper();
	                    text = pdfStripper.getText(document).toLowerCase();
//	                    String[] lines = text.split("\\r?\\n");
//	                    for (String line : lines) {
//	                        extractedText.append(line.trim()).append("\n");
//	                    }
	                    logger.info("Please find the PDF text below:\n" + text);
	                    document.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                break; 
	            }
	        }        
	    }
	   return text;
	}
}

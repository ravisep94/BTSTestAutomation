package com.marcombios.pageObjects;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marcombios.pageObjects.*;
import com.marcombios.utilities.Base;

public class DeleteUserPage extends Base
{
	WebDriver driver;
	public DeleteUserPage (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	// Delete
		@FindBy(xpath="//button[@name='Delete']")
		@CacheLookup
		WebElement delete;
		public WebElement getDeleteButton()
		{
			return delete;
		}
	
// More Options icon
	@FindBy(xpath="//i[@data-icon-name='More']")
	@CacheLookup
	WebElement moreIcon;
	public WebElement getMoreIcon() {
		return moreIcon;
	}		
	// Delete Button on popup
		@FindBy(xpath="//button[@data-automationid='confirmbutton']")
		@CacheLookup
		WebElement delete_btn_confirm;
		public WebElement getConfirmDeleteButton()
		{
			return delete_btn_confirm;
		}
	//Empty recycle Bin
	@FindBy(xpath="//button[@name='Empty recycle bin']")
	@CacheLookup
	WebElement emptyRecycleBin;
	public WebElement getEmptyRecycleBinButton()
	{
		return emptyRecycleBin;
	}
	//Yes Button
	@FindBy(xpath="//span[text()='Yes']")
	@CacheLookup
	WebElement yes;
	public WebElement getYesButton()
	{
		return yes;
	}
	
//********************************************************************ConsultantBiography List**************************************************
//Consultant
	@FindBy(xpath="//span[(text()='Consultant')]")
	@CacheLookup
	WebElement consultant;
	public WebElement getConsultant()
	{
		return consultant;
	}
// Filter by
	@FindBy(xpath="//span[(text()='Filter by')]")
	@CacheLookup
	WebElement filterby;
	public WebElement getFilterBy()
	{
		return filterby;
	}
// Filter Search  field
	@FindBy(xpath="//input[@placeholder='Enter a name or email address']")
	@CacheLookup
	WebElement nameAndEmailField;
	public WebElement getNameAndEmailField()
	{
		return nameAndEmailField;
	}
// search result
	@FindBy(xpath="//div[@class='ms-PeoplePicker-personaContent']/div/div[2]/div/div")
	@CacheLookup
	WebElement searchresult;
	public WebElement getSearchResult()
	{
		return searchresult;
	}
// Apply Button
	@FindBy(xpath="//button[@data-automationid='FilterPanel-Apply']")
	@CacheLookup
	WebElement apply;
	public WebElement getApplyButton()
	{
		return apply;
	}
	//Employee Name
	@FindBy(xpath="//div[@data-automation-key='MSCConsultant']")
	@CacheLookup
	List<WebElement> employeeNames;
	public List getEmployees()
	{
		return employeeNames;
	}
	
// Select All Radio button
	@FindBy(xpath="//i[@data-icon-name='StatusCircleCheckmark']")
	@CacheLookup
	WebElement selectAllItemsRadioButton;
	public WebElement getselectAllItemsRadioButton()
	{
		return selectAllItemsRadioButton;
	}
//***********************************************************************Primary/Secondary Recycle Bin*****************************************************
//Recycle Bin items
	@FindBy(xpath="//div[@data-automation-key='name']")
	@CacheLookup
	List<WebElement> employeeNames_rec;
	public List getEmployees_Rec()
	{
		return employeeNames_rec;
	}
// Select Item Radio button
	@FindBy(xpath="//i[@data-icon-name='StatusCircleCheckmark']")
	@CacheLookup
	List<WebElement> radioButtons;
	public List getSelectItemRadioButtons()
	{
		return radioButtons;
	}
//Second Stage Recycle Bin
	@FindBy(xpath="//a[@title='Second-stage recycle bin']")
	@CacheLookup
	WebElement secondStageRecycleBin;
	public WebElement getsecondStageRecycleBin()
	{
		return secondStageRecycleBin;
	}
	//****************************************Delete User**************************
	WebDriverWait wait;
	BiographyPage b;
	DeleteUserPage del;
    public void deleteUser(String employeeName) throws InterruptedException, IOException
    {
    	wait = new WebDriverWait(driver,Duration.ofSeconds(20));
    	b= new BiographyPage(driver);
    	del=new DeleteUserPage(driver);
    	logger.info("Navigating to ConsultantBiography list");
    	driver.get(listURL);
    	Thread.sleep(3000);
    	logger.info("Click on Consultant column");
    	wait.until(ExpectedConditions.elementToBeClickable(del.getConsultant()));
    	del.getConsultant().click();
    	logger.info("Filtering By Consultant Name");
    	wait.until(ExpectedConditions.elementToBeClickable(del.getFilterBy()));
    	del.getFilterBy().click();
    	logger.info("Searching Consultant Name. ");
    	wait.until(ExpectedConditions.elementToBeClickable(del.getNameAndEmailField()));
    	del.getNameAndEmailField().sendKeys(employeeName);
    	logger.info("Selecting the Required Consultant");
    	wait.until(ExpectedConditions.elementToBeClickable(del.getSearchResult()));
    	del.getSearchResult().click();
    	logger.info("Click on Apply");
    	wait.until(ExpectedConditions.elementToBeClickable(del.getApplyButton()));
    	del.getApplyButton().click();
    	List<WebElement> employees = del.getEmployees();
    	if(employees.size()!=0) {
    	logger.info("Selecting All items with Consultant as "+ employeeName);
    	//wait.until(ExpectedConditions.elementToBeSelected(del.getselectAllItemsRadioButton()));
    	Thread.sleep(3000);
    	del.getselectAllItemsRadioButton().click();
    	try{
    	wait.until(ExpectedConditions.elementToBeClickable(del.getDeleteButton()));
    	}
    	catch(NoSuchElementException e) {
    		wait.until(ExpectedConditions.elementToBeClickable(del.getMoreIcon()));
    		del.getMoreIcon().click();
    	}
    	logger.info("Deleteing All Records with Consultant value "+employeeName);
		//Thread.sleep(3000);
    	del.getDeleteButton().click();
		wait.until(ExpectedConditions.elementToBeClickable(del.getConfirmDeleteButton()));
		logger.info("Click on Delete button on confirmation popup");
		//Thread.sleep(3000);
		del.getConfirmDeleteButton().click();
    	}
    	logger.info("Navigating to Primary Stage Recyle Bin ");
    	driver.get(primaryrecyclebinURL);
    	Thread.sleep(5000);
    	List<WebElement> prim_rec_employees = del.getEmployees_Rec();
    	if(prim_rec_employees.size()!=0)
    	{
    	logger.info("Emptying Primary Recycle Bin");
    	wait.until(ExpectedConditions.elementToBeClickable(del.getEmptyRecycleBinButton()));
    	del.getEmptyRecycleBinButton().click();
    	wait.until(ExpectedConditions.elementToBeClickable(del.getYesButton()));
    	del.getYesButton().click();
    	}
    	else
    	{
    		logger.info("Primary stage recycle bin is empty");
    	}
    	logger.info("Navigating to Secondary stage Recyle Bin ");
    	Thread.sleep(5000);
    	wait.until(ExpectedConditions.elementToBeClickable(del.getsecondStageRecycleBin()));
    	del.getsecondStageRecycleBin().click();
    	Thread.sleep(5000);
    	List<WebElement> second_rec_employees = del.getEmployees_Rec();
    	if(second_rec_employees.size()!=0)
    	{
    	//wait.until(ExpectedConditions.elementToBeClickable(getEmptyRecycleBinButton()));
    		Thread.sleep(3000);
    		del.getEmptyRecycleBinButton().click();
    	wait.until(ExpectedConditions.elementToBeClickable(del.getYesButton()));
    	del.getYesButton().click();
    	}
    	else
    	{
    		logger.info("Secondary stage recycle bin is not having any user with name "+empname);
    	}
    	Thread.sleep(3000);
    	driver.get(baseURL);
    	Thread.sleep(3000);
    	b.setup(driver);
    	b.clickAddBio();
    	Thread.sleep(3000);
    	}
}

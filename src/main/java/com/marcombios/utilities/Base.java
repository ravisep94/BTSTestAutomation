package com.marcombios.utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Base {
	
ReadConfig readconfig=new ReadConfig();
ReadTestData readtestdata = new ReadTestData();
	
	public String baseURL=readconfig.getApplicationURL();
	public String listURL=readconfig.getListURL();
	public String primaryrecyclebinURL=readconfig.getPrimaryRecycleBinURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public  String webmasterusername=readconfig.getWebMasterUsername();
	public String userpassword=readconfig.getUserPassword();
	public String marcomusername=readconfig.getMarcomUsername();
	public String marcomeditorusername=readconfig.getMarcomEditorUsername();
	
	//Reading data from Test data config file
	public String insubmitterreview=readtestdata.getSubmitterReview();
	public String readyeditorialreview=readtestdata.getReadyEditorialReview();
	public String ineditorialreview=readtestdata.getInEditorialReview();
	public String editorialreviewcompleted=readtestdata.getEditReviewCompleted();
	public String publishtosp=readtestdata.getPublishSP();
	public String pendingwebpost=readtestdata.getPendingWebPost();
	public String publishtospweb=readtestdata.getPublishSPWeb();
	public String empname=readtestdata.getEmpName();
	public String currentresponseheader=readtestdata.getCurrentResponseHeader();
	public String profworkexpheader=readtestdata.getProfWorkExpHeader();
	public String profdesheader=readtestdata.getProfDesHeader();
	public String educationheader=readtestdata.getEducationHeader();
	public String presandpubheader=readtestdata.getPresandPubHeader();
	public String affiliationsheader=readtestdata.getAffiliationsHeader();
	public String currentresponse=readtestdata.getCurrentResponse();
	public String profworkexp=readtestdata.getProfWorkExp();
	public String profdes=readtestdata.getProfDes();
	public String education=readtestdata.getEducation();
	public String presandpub=readtestdata.getPresandPub();
	public String affiliations=readtestdata.getAffiliations();
	public String updatename=readtestdata.getUpdateName();
	public String updatecredential=readtestdata.getUpdateCredential();
	public String updatetitle=readtestdata.getUpdateTitle();
	public String updateprodgrpname=readtestdata.getUpdateProdGrpName();
	public String updateoffice=readtestdata.getUpdateOffice();
	public String updatephone=readtestdata.getUpdatePhone();
	public String editor=readtestdata.getEditor();
	public String editorcomments=readtestdata.getEditorComments();
	public String userfilepath=readtestdata.getUserFilePath();
	public String testdomain=readtestdata.getTestDomain();
	public String domain=readtestdata.getDomain();
	public WebDriver driver;
	public static Logger logger;
	
	

	@BeforeClass
	public void setup(@Optional("chrome")String br) throws MalformedURLException
	{	
		logger = Logger.getLogger("MarcomBios");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equalsIgnoreCase("chrome"))
		{ 
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(ops);
		}
		else if(br.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(br.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(baseURL);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.manage().deleteAllCookies();
		driver.quit();

	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
}

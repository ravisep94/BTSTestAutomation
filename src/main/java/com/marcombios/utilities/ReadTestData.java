package com.marcombios.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadTestData {

	Properties pro;
	
		public ReadTestData()
	{
		File src = new File("./TestData/testdata.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	/*
	 * public String getApplicationURL() { String url=pro.getProperty("baseURL");
	 * return url; }
	 * 
	 * public String getListURL() { String listURL=pro.getProperty("listURL");
	 * return listURL; }
	 * 
	 * public String getPrimaryRecycleBinURL() { String
	 * primaryRecycleBinURL=pro.getProperty("primaryrecyclebinURL"); return
	 * primaryRecycleBinURL; } public String getUsername() { String
	 * username=pro.getProperty("username"); return username; }
	 * 
	 * public String getPassword() { String password=pro.getProperty("password");
	 * return password; }
	 */
	
	public String getWebMasterUsername()
	{
	String webmasterusername=pro.getProperty("webmasterusername");
	return webmasterusername;
	}
		
	public String getUserPassword()
	{
	String userpassword=pro.getProperty("userpassword");
	return userpassword;
	}
	
	public String getSubmitterReview()
	{
	String insubmitterreview=pro.getProperty("insubmitterreview");
	return insubmitterreview;
	}
	
	public String getReadyEditorialReview()
	{
	String readyeditorialreview=pro.getProperty("readyeditorialreview");
	return readyeditorialreview;
	}
	
	public String getInEditorialReview()
	{
	String ineditorialreview=pro.getProperty("ineditorialreview");
	return ineditorialreview;
	}
	
	public String getEditReviewCompleted()
	{
	String editorialreviewcompleted=pro.getProperty("editorialreviewcompleted");
	return editorialreviewcompleted;
	}
	
	public String getPublishSP()
	{
	String publishtosp=pro.getProperty("publishtosp");
	return publishtosp;
	}
	
	public String getPendingWebPost()
	{
	String pendingwebpost=pro.getProperty("pendingwebpost");
	return pendingwebpost;
	}
	
	public String getPublishSPWeb()
	{
	String publishtospweb=pro.getProperty("publishtospweb");
	return publishtospweb;
	}
	
	public String getEmpName()
	{
	String empname=pro.getProperty("empname");
	return empname;
	}
	
	public String getCurrentResponseHeader()
	{
	String currentresponseheader=pro.getProperty("currentresponseheader");
	return currentresponseheader;
	}
	
	public String getProfWorkExpHeader()
	{
	String profworkexpheader=pro.getProperty("profworkexpheader");
	return profworkexpheader;
	}
	
	public String getProfDesHeader()
	{
	String profdesheader=pro.getProperty("profdesheader");
	return profdesheader;
	}
	
	public String getEducationHeader()
	{
	String educationheader=pro.getProperty("educationheader");
	return educationheader;
	}
	
	public String getPresandPubHeader()
	{
	String presandpubheader=pro.getProperty("presandpubheader");
	return presandpubheader;
	}
	
	public String getAffiliationsHeader()
	{
	String affiliationsheader=pro.getProperty("affiliationsheader");
	return affiliationsheader;
	}
	
	public String getCurrentResponse()
	{
	String currentresponse=pro.getProperty("currentresponse");
	return currentresponse;
	}
	
	public String getProfWorkExp()
	{
	String profworkexp=pro.getProperty("profworkexp");
	return profworkexp;
	}
	
	public String getProfDes()
	{
	String profdes=pro.getProperty("profdes");
	return profdes;
	}
	
	public String getEducation()
	{
	String education=pro.getProperty("education");
	return education;
	}
	
	public String getPresandPub()
	{
	String presandpub=pro.getProperty("presandpub");
	return presandpub;
	}
	
	public String getAffiliations()
	{
	String affiliations=pro.getProperty("affiliations");
	return affiliations;
	}
	
	public String getUpdateName()
	{
	String updatename=pro.getProperty("updatename");
	return updatename;
	}
	
	public String getUpdateCredential()
	{
	String updatecredential=pro.getProperty("updatecredential");
	return updatecredential;
	}
	
	public String getUpdateTitle()
	{
	String updatetitle=pro.getProperty("updatetitle");
	return updatetitle;
	}
	
	public String getUpdateProdGrpName()
	{
	String updateprodgrpname=pro.getProperty("updateprodgrpname");
	return updateprodgrpname;
	}
	
	public String getUpdateOffice()
	{
	String updateoffice=pro.getProperty("updateoffice");
	return updateoffice;
	}
	
	public String getUpdatePhone()
	{
	String updatephone=pro.getProperty("updatephone");
	return updatephone;
	}
	
	public String getEditor()
	{
	String editor=pro.getProperty("editor");
	return editor;
	}
	
	public String getEditorComments()
	{
	String editorcomments=pro.getProperty("editorcomments");
	return editorcomments;
	}
	
	public String getChromePath()
	{
	String chromepath=pro.getProperty("chromepath");
	return chromepath;
	}
	
	public String getIEPath()
	{
	String iepath=pro.getProperty("iepath");
	return iepath;
	}
	
	public String getFirefoxPath()
	{
	String firefoxpath=pro.getProperty("firefoxpath");
	return firefoxpath;
	}
	
	public String getUserFilePath()
	{
	String userFile=pro.getProperty("filepath");
	return userFile;
	}
	public String getTestDomain()
	{
	String testdomain=pro.getProperty("testdomain");
	return testdomain;
	}
	
	public String getDomain()
	{
	String domain=pro.getProperty("domain");
	return domain;
	}
	
	
}





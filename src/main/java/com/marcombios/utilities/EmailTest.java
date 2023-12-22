package com.marcombios.utilities;

import java.time.Duration;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marcombios.pageObjects.Email;
import com.marcombios.pageObjects.ReadOutlook;
import com.marcombios.utilities.*;

import microsoft.exchange.webservices.data.core.ExchangeService;


public class EmailTest extends Base {
	WebDriverWait wait;
	ReadConfig readconfig;
	Actions action;
	ReadOutlook mail;
@BeforeMethod
	public void setup() {
	wait = new  WebDriverWait(driver, Duration.ofSeconds(30));
	action = new Actions(driver);
	readconfig=new ReadConfig();
	 mail = new ReadOutlook();
	}
@Test
	public void TestEmail() throws Exception {
	ExchangeService service= mail.createConnection(webmasterusername,userpassword);
	mail.readEmail(service,"Employee biographies are ready for editorial review");
}
}

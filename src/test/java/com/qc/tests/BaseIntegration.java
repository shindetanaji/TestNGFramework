package com.qc.tests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import com.qc.utils.TestUtils;

public class BaseIntegration {

	TestUtils test = new TestUtils();
	WebDriver driver;
	WebElement email, pass, submit;
	WebElement goToResgiterPage, rName, rMobile, rEmail, rPass, rSubmit;
	Properties prop;
	
	public void doSetUp() throws IOException {
		prop = test.readProp();
		if(prop.getProperty("browser").equals("chrome")) {
			System.setProperty(prop.getProperty("chromeKey"), prop.getProperty("chromeVal"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}else {
			//firefox browser
		}
		driver.get(prop.getProperty("siteUrl"));
	}
	
	@DataProvider
	public Object[][] loginData() throws IOException {
		return test.readData("Sheet1");
	}
	
	@DataProvider
	public Object[][] registerData() throws IOException {
		return test.readData("Sheet2");
	}
}

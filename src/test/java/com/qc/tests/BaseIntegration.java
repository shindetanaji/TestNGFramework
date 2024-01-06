package com.qc.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.qc.utils.TestUtil;

public class BaseIntegration {

	WebDriver driver;
	TestUtil test = new TestUtil();
	Properties prop;
	WebElement email, pass, signin, logout;
	WebElement rLink, rName, rEmail, rMobile, rPass, rSubmit;
	String tName;
	
	public void doSetup() throws IOException {
		prop = test.readProp();
		if(prop.getProperty("browser").equals("chrome")) {
			driver = new ChromeDriver();
		}else if(prop.getProperty("browser").equals("edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("siteUrl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@DataProvider
	public Object[][] loginData() throws IOException{
		return test.readExcelData("Sheet1");
	}
	
	@DataProvider
	public Object[][] registerData() throws IOException{
		return test.readExcelData("Sheet2");
	}
	
}

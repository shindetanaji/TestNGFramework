package com.qc.tests;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RegisterTest extends BaseIntegration {

	@BeforeSuite
	public void setup() throws IOException {
		doSetup();
	}
	
	@BeforeMethod
	public void locateElement() {
		String pageTitle = driver.getTitle();
		if (pageTitle.equals("Queue Codes | Log in")) {
			rLink = driver.findElement(By.linkText("Register a new membership"));
			rLink.click();
		}
		rName = driver.findElement(By.id("name"));
		rName.clear();
		rMobile = driver.findElement(By.id("mobile"));
		rMobile.clear();
		rEmail = driver.findElement(By.id("email"));
		rEmail.clear();
		rPass = driver.findElement(By.id("password"));
		rPass.clear();
		rSubmit = driver.findElement(By.className("btn-flat"));
	}

	@Test(dataProvider = "registerData")
	public void doRegister(String testName, String uName, String uMobile, String uEmail, String uPass) {
		tName = testName;
		rName.sendKeys(uName);
		rMobile.sendKeys(uMobile);
		rEmail.sendKeys(uEmail);
		rPass.sendKeys(uPass);
		rSubmit.click();
	}

	@AfterMethod
	public void doAssert() {
		String actResult, expResult; 
		if (tName.equals("All are valid")) {
			Alert alt = driver.switchTo().alert();
			actResult = alt.getText();
			alt.accept();
			expResult = "User registered successfully.";
		} else {
			actResult = driver.getTitle();
			expResult = "Queue Codes | Registration Page";
		}
		Assert.assertEquals(actResult, expResult);
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
}

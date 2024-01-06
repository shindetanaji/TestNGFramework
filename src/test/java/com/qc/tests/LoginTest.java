package com.qc.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest extends BaseIntegration {

	@BeforeSuite
	public void setup() throws IOException {
		doSetup();
	}
	
	@BeforeMethod
	public void locateElement() {
		email = driver.findElement(By.id("email"));
		email.clear();

		pass = driver.findElement(By.id("password"));
		pass.clear();

		signin = driver.findElement(By.id("submit"));
	}

	@Test(dataProvider = "loginData")
	public void doLogin(String testName, String uName, String uPass) {
		tName = testName;
		email.sendKeys(uName);
		pass.sendKeys(uPass);
		signin.click();
	}

	@AfterMethod
	public void doAssert() {
		String actResult = driver.getTitle();
		String expResult;
		if (tName.equals("Both are valid")) {
			expResult = "Queue Codes | Dashboard";
			doLogout();
		} else {
			expResult = "Queue Codes | Log in";
		}
		Assert.assertEquals(actResult, expResult);
	}

	public void doLogout() {
		logout = driver.findElement(By.id("hlogout"));
		logout.click();
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
}

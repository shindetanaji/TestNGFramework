package com.qc.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest extends BaseIntegration{

	@BeforeSuite
	public void get() throws IOException {
		doSetUp();
	}
	
	@BeforeMethod
	public void getXpath() {
		email = driver.findElement(By.xpath("//input[@type='text']"));
		email.clear();
		
		pass = driver.findElement(By.xpath("//input[@type='password']"));
		pass.clear();
		
		submit = driver.findElement(By.xpath("//button[@type='submit']"));
	}
	
	@Test(dataProvider = "loginData")
	public void doLogin(String testName, String uName, String uPass) {
		email.sendKeys(uName);
		pass.sendKeys(uPass);
		submit.click();
	}
	
	@AfterMethod
	public void doAssert() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
}

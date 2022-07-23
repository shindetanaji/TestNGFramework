package com.qc.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RegisterTest extends BaseIntegration{
	@BeforeSuite
	public void get() throws IOException {
		doSetUp();
	}
	
	public void goToRegisterPage() {
		goToResgiterPage = driver.findElement(By.partialLinkText("Register a new membership"));
		goToResgiterPage.click();
	}

	@BeforeMethod
	public void getLocation() {
		if (driver.getTitle().equals("Queue Codes | Log in")) {
			goToRegisterPage();
		}

		rName = driver.findElement(By.id("name"));
		rName.clear();
		rMobile = driver.findElement(By.id("mobile"));
		rMobile.clear();
		rEmail = driver.findElement(By.id("email"));
		rEmail.clear();
		rPass = driver.findElement(By.id("password"));
		rPass.clear();
		rSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
	}
	
	@Test(dataProvider = "registerData")
	public void doRegister(String uName, String uMobile, String uEmail, String uPass) {
		rName.sendKeys(uName);
		rMobile.sendKeys(uMobile);
		rEmail.sendKeys(uEmail);
		rPass.sendKeys(uPass);
		rSubmit.click();
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

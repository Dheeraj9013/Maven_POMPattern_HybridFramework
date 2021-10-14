package com.qa.hubspot.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.qa.hubspot.base.BaseTest;


public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void homePageSetup() {
		accountpage = loginpage.loginToPage(prop.getProperty("username"), prop.getProperty("password"));
		//accountpage = new HomePage(driver); - Avoid this by using page chaining
	}
	
	
	
	@Test(priority=1)
	public void VerifyEditInformation() {
		Assert.assertTrue(accountpage.verifyEditInformationLink());
	}
	
	@Test(priority=2)
	public void getText() {
		System.out.println("Dheeraj");
		System.out.println(accountpage.EditAccountInformationtext());
	}
}

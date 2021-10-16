package com.qa.hubspot.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.constants;


public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void verifyForgotPasswordLink() {

		Assert.assertTrue(loginpage.isForgetPasswordLinkExist());

	}

	@Test(priority = 2)
	public void loginTitleTest() {
		String title = loginpage.getLooginPageTitle();
		System.out.println("Dheeraj");
		
		Assert.assertEquals(title, constants.Login_Page_Title);
	}

	@Test(priority = 3)
	public void loginTest() {
		loginpage.loginToPage(prop.getProperty("username"), prop.getProperty("password"));

	}

}

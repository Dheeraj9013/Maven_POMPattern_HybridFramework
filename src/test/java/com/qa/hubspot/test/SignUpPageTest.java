package com.qa.hubspot.test;



import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.SignUpPage;

public class SignUpPageTest extends BaseTest {
	
	public SignUpPage signuppage;
	
	@BeforeClass
	
	public void homePageSetUp() throws InterruptedException {
		signuppage = loginpage.navigateRegisterAccountPage();
		Thread.sleep(5000);
		
	}
	
	
	@Test(priority=1)
	
	public void verifyLoginPageLink() {
		Assert.assertTrue(signuppage.verifyLoginPageLink());
	}
	

}

package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Utility;

public class SignUpPage {

	private WebDriver driver;
	private Utility utils;

	private By loginpage = By.xpath("//div[@id='content']/descendant::a[contains(text(),'login page')]");

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utility(driver);
	}

	public boolean verifyLoginPageLink() {

		return utils.doIsDisplayed(loginpage);

	}

	public LoginPage navigateLoginPage() {
		utils.doClick(loginpage);
		return new LoginPage(driver);
	}

}

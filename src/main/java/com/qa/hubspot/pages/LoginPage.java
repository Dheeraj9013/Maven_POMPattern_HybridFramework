package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Utility;
import com.qa.hubspot.utils.constants;

public class LoginPage {

	private WebDriver driver;
	private Utility utils;

	// 1- private BY Repository :OR

	private By emailAddress = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//div[@id='content']/descendant::input[@type='submit']");
	private By forgetPasswordLink = By
			.xpath("//div[@id='content']/descendant::a[contains(text(),'Forgotten Password')]");
	private By newcutomercontinuebutton = By.xpath("//div[@id='content']/descendant::a[contains(text(),'Continue')]");
	private By registerAccountButton = By.xpath("//div[@id=\"content\"]/descendant::a[contains(text(),'Continue')]");

//	Utility utils = new Utility(driver);

	// constructor of the page class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utility(driver);
	}
//	
	// page actions : features of the page

	public String getLooginPageTitle() {
		return utils.waitForTitlePresent(constants.Login_Page_Title, 10, 1);

	}

	public boolean isForgetPasswordLinkExist() {

		return utils.doIsDisplayed(forgetPasswordLink);

	}

	public SignUpPage navigateRegisterAccountPage() {

		utils.doClick(newcutomercontinuebutton);
		return new SignUpPage(driver);
	}

	public AccountPage loginToPage(String url, String pass) {

		utils.doSendKeys(emailAddress, url);
		utils.doSendKeys(password, pass);
		utils.doClick(loginButton);

		return new AccountPage(driver);

	}

	public RegisterPage navigateToRegisterAccountPage() {

		utils.doClick(newcutomercontinuebutton);
		return new RegisterPage(driver);
	}

}

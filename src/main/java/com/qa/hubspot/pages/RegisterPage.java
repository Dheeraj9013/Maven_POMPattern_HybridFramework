package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ExcelUtil;
import com.qa.hubspot.utils.Utility;

public class RegisterPage extends BasePage {

	WebDriver driver;
	Utility utils;
	ExcelUtil excelutil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//div[@id=\"content\"]/descendant::input[@name='newsletter'][position()=1]");
	private By subscribeNo = By.xpath("//div[@id=\"content\"]/descendant::input[@name='newsletter'][position()=2]");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;

		utils = new Utility(driver);

	}

	public void registerAccountPage(String firstname, String lastname, String email, String telephone, String password,
			String subscribe) {
		utils.doSendKeys(this.firstName, firstname);
		utils.doSendKeys(this.lastName, lastname);
		utils.doSendKeys(this.email, email);
		utils.doSendKeys(this.telephone, telephone);
		utils.doSendKeys(this.password, password);
		utils.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("Yes")){
			utils.doActionsClick(subscribeYes);
			
		}
		else {
			utils.doActionsClick(subscribeNo);
		}


	}

}

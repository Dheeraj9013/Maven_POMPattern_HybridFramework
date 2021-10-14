package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Utility;

public class AccountPage  {
	
	
	private WebDriver driver ;
	private Utility utils;
	
	private By editAccountInformation = By.xpath("//div[@id='content']/descendant::a[contains(text(),'Edit your account ')]");
	private By emailAddress = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//div[@id='content']/descendant::input[@type='submit']");
	private By searchTextBox = By.xpath("//div[@id='search']/descendant::input");
	private By searchBoxButton = By.xpath("//div[@id='search']/descendant::button");
	
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utility(driver);
	}
	
	
	
	public boolean verifyEditInformationLink() {
		
		return utils.doIsDisplayed(editAccountInformation);
	}
	
	public String EditAccountInformationtext() {
		String AccountInformationText  = utils.dogetTexts(editAccountInformation);
		return AccountInformationText;
		
	}
	
	public SearchPage searchProduct(String product) {

		utils.doSendKeys(searchTextBox, product);
		utils.doClick(searchBoxButton);
		
		return new SearchPage(driver);

	}
	
	

}

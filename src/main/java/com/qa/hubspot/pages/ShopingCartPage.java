package com.qa.hubspot.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.hubspot.utils.Utility;

public class ShopingCartPage {

	private WebDriver driver;
	private Utility utils;

	private By shoppingCartText = By
			.xpath("//div[@id ='checkout-cart']/descendant::a[contains(text(),'Shopping Cart')]");
	private By productName = By.xpath("//div[@id='content']/descendant::td[@class='text-left']/a");
	private By quantity = By.xpath("//div[@id='content']/descendant::td/descendant::input[@type='text']");
	private By quantityUpdateButton = By.xpath("//div[@id='content']/descendant::td/descendant::button");
	private By successMessages = By.xpath("//div[@id='checkout-cart']/descendant::ul/following-sibling::div[1]");

	public ShopingCartPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utility(driver);
	}

	public String getShoppingCartpageText() {
		return utils.dogetTexts(shoppingCartText).trim();
	}

	public String getProductName() {

		return utils.dogetTexts(productName);
	}

	public void QuantityUpdate(String values, String newvalues) {
		String newquantity = null;
		List<WebElement> qty = driver.findElements(quantity);

		for (WebElement quantvalue : qty) {
			if (quantvalue.getAttribute("value").contains(values)) {

				quantvalue.clear();
				utils.doSendKeys(quantity, newvalues);
				utils.doClick(quantityUpdateButton);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Actions action = new Actions(driver);
				action.click(quantvalue).build().perform();

			}

		}

	}

}

package com.qa.hubspot.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.utils.Utility;

public class SearchPage {

	private WebDriver driver;
	private Utility utils;

	private By productsLink = By.xpath(
			"//div[@id='content']/descendant::div[@class='caption']/descendant::a[contains(@href,'product_id')]");
	private By qtyTextBox = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By totalImages = By.xpath("//div[@id='content']/descendant::li[@class='image-additional']");
	private By headerTitle = By.xpath("//div[@id='content']/descendant::h1");
	private By productInformation = By
			.xpath("//div[@id='content']/descendant::ul[@class='list-unstyled'][1]/child::li");
	private By PriceInformation = By.xpath("//div[@id='content']/descendant::ul[@class='list-unstyled'][2]/child::li");
	private By successMessage = By
			.xpath("//div[@id='product-product']/descendant::div[contains(@class,'alert-success')][1]");

	private By shoppingcart = By
			.xpath("//div[@id = 'product-product']/descendant::a[contains(text(),'shopping cart')]");

	private By shoppingCartText = By
			.xpath("//div[@id ='checkout-cart']/descendant::a[contains(text(),'Shopping Cart')]");

	public SearchPage(WebDriver driver) {
		this.driver = driver;

		utils = new Utility(driver);

	}

	public void selectProductFromResults(String productName) {

		List<WebElement> products = utils.getElements(productsLink);

		for (WebElement prod : products) {

			if (prod.getText().equalsIgnoreCase(productName)) {

				prod.click();
				break;

			}

		}
		;

	}

	public void selectQuantity(String qty) {
		driver.findElement(qtyTextBox).clear();
		utils.doSendKeys(qtyTextBox, qty);
	}

	public void selectAddToCart() {
		utils.doClick(addToCart);

	}

	public int totalNumberOfImages() {
		return utils.getElements(totalImages).size();
	}

	public Map<String, String> getProductInformation() {

		Map<String, String> productInfoMap = new HashMap<String, String>();

		List<WebElement> productList = utils.getElements(productInformation);

		for (WebElement lists : productList) {
			productInfoMap.put(lists.getText().split(":")[0].trim(), lists.getText().split(":")[1].trim());
		}

		List<WebElement> priceList = utils.getElements(PriceInformation);

		productInfoMap.put("price", priceList.get(0).getText().trim());
		productInfoMap.put("exPrice", priceList.get(1).getText().split(":")[1].trim());

		return productInfoMap;

	}

	public String getSuccessMessage() {
		utils.waitForElementVisible(successMessage, 20, 1000);

		return utils.dogetTexts(successMessage).replaceAll("×", "").trim();
	}

	public ShopingCartPage navigateShoppingCart() {
		utils.waitForElementVisible(successMessage, 20, 1000);
		utils.doClick(shoppingcart);

		return new ShopingCartPage(driver);

	}

}

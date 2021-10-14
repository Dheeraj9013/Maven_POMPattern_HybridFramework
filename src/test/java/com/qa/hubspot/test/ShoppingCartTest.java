package com.qa.hubspot.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;

public class ShoppingCartTest extends BaseTest {

	@BeforeClass

	public void HomePageSetUp() {
		accountpage = loginpage.loginToPage(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyShoppingcartTextpageTest() {
		productpage = accountpage.searchProduct("Mac");
		productpage.selectProductFromResults("MacBook");
		productpage.selectQuantity("1");
		productpage.selectAddToCart();
		shoppingpage = productpage.navigateShoppingCart();

		String shoppingcarttext = shoppingpage.getShoppingCartpageText();

		Assert.assertEquals(shoppingcarttext, "Shopping Cart");
	}

	@Test(priority = 2)
	public void verifyProductName() {
		System.out.println(shoppingpage.getProductName());

	}

	@Test(priority = 3)

	public void verifyNewUpdatedValue() {
		String newvalue = "57";
		String oldvalue = "56";
		shoppingpage.QuantityUpdate(oldvalue, newvalue);
		//System.out.println(shoppingpage.QuantityUpdate(oldvalue, newvalue));
//		Assert.assertEquals(newvalue, shoppingpage.QuantityUpdate(oldvalue, newvalue));
	}

}

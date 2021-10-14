package com.qa.hubspot.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.SearchPage;

public class SearchPageTest extends BaseTest {

	@BeforeClass
	public void homePageSetUp() {
		accountpage = loginpage.loginToPage(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void searchProductTest() {
		productpage = accountpage.searchProduct("Mac");
	}
	
	

	@Test(priority = 2)
	public void verifyproductInfoTest() {
		productpage.selectProductFromResults("MacBook");

		int total = productpage.totalNumberOfImages();

		Assert.assertTrue(total == 4);

		Map<String, String> productinfo = productpage.getProductInformation();

		System.out.println(productinfo);

	}

	@Test(priority = 3)

	public void totalQuantityTest() {
		productpage.selectQuantity("1");
	}

	@Test(priority = 4)
	public void addToCartTest() {
		productpage.selectAddToCart();
	}

	@Test(priority = 5,dependsOnMethods="addToCartTest")
	public void verifySucessMessageTest() {
		String expected  = "Success: You have added MacBook to your shopping cart!";
		
		String successmessage = productpage.getSuccessMessage();
		System.out.println(successmessage);
		Assert.assertEquals(successmessage, expected);
	}
	
	
	@Test(priority=6)
	public void navigateToShoppingCartTest() {
		productpage.navigateShoppingCart();
		
	}

}

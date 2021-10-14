package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.AccountPage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pages.RegisterPage;
import com.qa.hubspot.pages.SearchPage;
import com.qa.hubspot.pages.ShopingCartPage;
import com.qa.hubspot.pages.SignUpPage;
import com.qa.hubspot.utils.OptionsManager;

public class BaseTest {

	public BasePage basepage;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginpage;
	public AccountPage accountpage;
	public SearchPage productpage;
	public ShopingCartPage shoppingpage;
	public RegisterPage register;

	@Parameters({"browser", "version"})

	@BeforeTest
	public void setUp(String browsername, String versions) {

		basepage = new BasePage();
		prop = basepage.init_properties();
		String browser = prop.getProperty("browser");

		if (!browsername.equals(null)) {
			browser = browsername;
		}
		

		driver = basepage.init_driver(browser,versions);
		loginpage = new LoginPage(driver);

		String url = prop.getProperty("url");

		BasePage.getDriver().get(url);

	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

}

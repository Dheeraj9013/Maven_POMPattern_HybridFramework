package com.qa.hubspot.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.ExcelUtil;
import com.qa.hubspot.utils.constants;

public class CreateRegisterTest extends BaseTest {

	@BeforeClass

	public void homePageSetUp() {
		register = loginpage.navigateToRegisterAccountPage();
	}

	@DataProvider //- DataProvider Return two Dimension Array
	public  Object[][] getRegistrationData() {
		Object data[][] = ExcelUtil.getTestData(constants.Sheet_Name, constants.excel_path);
		return data;
	}

	@Test(dataProvider = "getRegistrationData")
	public void createRegistrationTest(String firstname, String lastname, String email, String telephone,String  password, String subscribe) {
		
		register.registerAccountPage(firstname, lastname, email, telephone, password, subscribe);

	}

}

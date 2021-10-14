package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.hubspot.utils.OptionsManager;
import com.qa.hubspot.utils.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Dheeraj
 *
 */
public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionmanager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the Driver.
	 * 
	 * @param browser
	 * @return This Return WebDriver driver
	 */
	public WebDriver init_driver(String browser,String versionname) {
		
		optionmanager = new OptionsManager(prop);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_RemoteDriver(browser,versionname);
			} else {

				tlDriver.set(new ChromeDriver());
			}
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_RemoteDriver(browser,versionname);
			} else {
				tlDriver.set(new FirefoxDriver());
			}
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
		}

		else {
			System.out.println("Please pass the correct browser value :" + browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		return getDriver();
	}

	public void init_RemoteDriver(String browser,String versionname) {
		if (browser.equalsIgnoreCase("chrome")) {

			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(ChromeOptions.CAPABILITY,optionmanager.getChromeOptions());
			capability.setCapability("browserName", "chrome");
			capability.setCapability("browserVersion", versionname);
			capability.setCapability("enableVNC", true);
			
			
			
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), capability));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (browser.equalsIgnoreCase("firefox")) {

			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionmanager.getfireFoxOptions());
			capability.setCapability("browserName", "firefox");
			capability.setCapability("browserVersion", versionname);
			capability.setCapability("enableVNC", true);
			

			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), capability));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * It is used to Read Properties file.
	 *
	 * @return properties prop reference
	 */
	public Properties init_properties() {

		prop = new Properties();
		try {
			// FileInputStream will make the Connection with the Config properties but
			// properties are not loaded
			FileInputStream fis = new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			try {
				// now config file will loaded and able to read properties file.
				prop.load(fis);
			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return path;

	}

}

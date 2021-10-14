package com.qa.hubspot.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitleByJS() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title").toString();

		return title;
	}

	public void getAlertByJs(String message) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + message + "')");
	}

	public void refreshBrowserByJs() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	public String browserInformationByJs() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String info = js.executeScript("return navigator.userAgent;").toString();

		return info;
	}

	public void sendKeysUsingIdByJs(String Id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("document.getElementById('" + Id + "').value ='" + value + "' ");

	}

	public void clickByJS(WebElement element) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("arguments[0].click;", element);
	}

	public void drawaBorderByJs(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border = '3px solid red'", element);
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.background='" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public void flashByJs(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(100,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	public String getInnerTextByJs() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		String innerText = js.executeScript("return document.documentElement.innerText").toString();
		return innerText;

	}

	public void scrollDownByJs() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollIntoViewByJs(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		js.executeScript("arguments[0].scrollInToView(true)",element);
	}
	
	

}

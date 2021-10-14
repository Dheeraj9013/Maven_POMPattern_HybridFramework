package com.qa.hubspot.utils;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.google.common.collect.Iterators;

public class Utility {

	private WebDriver driver;

	public Utility(WebDriver driver) {
		this.driver = driver;

	}

	/**
	 * This is used to get element of the web-page
	 * 
	 * @param locator
	 */

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			JavaScriptUtil util = new JavaScriptUtil(driver);
			util.flashByJs(element);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return element;
	}

	/**
	 * This is used to get elements of Web-page
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElements(By locator) {

		List<WebElement> elements = null;
		try {

			elements = driver.findElements(locator);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return elements;
	}

	/**
	 * This methos is for waiting to be Element to be present in DOM
	 * 
	 * @param locator
	 * @param timeOut
	 * @param interval
	 * @return
	 */
	public void waitForElementPresent(By locator, int timeOut, int interval) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		
		
	
		

	}
	
	public void waitForElementPresent(WebElement web, int timeOut, int interval) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchElementException.class);

		//wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(web)));
		
	
		

	}
	
	
	
	

	/**
	 * This method is to wait for Title present
	 * 
	 * @param title
	 * @param timeOut
	 * @param interval
	 * @return
	 */

	public String waitForTitlePresent(String title, int timeOut, int interval) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(interval)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	/**
	 * This method is used to wait for Element to be PResent in DOM and visible to
	 * the WebPage
	 * 
	 * @param locator
	 * @param timeOut
	 * @param interval
	 * @return
	 */
	public void waitForElementClickable(By locator, int timeOut, int interval) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.elementToBeClickable(locator));

		

	}

	/**
	 * This method is to wait for Elements which will be Visible in webpage like
	 * text,image etc
	 * 
	 * @param locator
	 * @param timeOut
	 * @param interval
	 * @return
	 */
	public void waitForElementVisible(By locator, int timeOut, int interval) {
		
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(WebDriverException.class);

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		
		
		

		

	}

	public WebElement CustomXpath(By locator, int timeout) {
		WebElement element = null;
		for (int i = 0; i < timeout; i++) {
			try {

				element = getElement(locator);
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}

		}
		return element;

	}

	/**
	 * This method is to wait for URL to be present on web page
	 * 
	 * @param locator
	 * @param url
	 * @param timeOut
	 * @param interval
	 * @return
	 */
	public String waitForUrlElements(By locator, String url, int timeOut, int interval) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.urlContains(url));

		return dogetTexts(locator);
	}

	/**
	 * This method is used to wait for java-script alert
	 * 
	 * @param timeOut
	 * @param interval
	 */
	public void waitForJavaScrptAlert(int timeOut, int interval) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.alertIsPresent());

	}

	/**
	 * This method is used to wait for frames to be present in web-page
	 * 
	 * @param locator
	 * @param timeOut
	 * @param interval
	 */
	public void waitForFrames(By locator, int timeOut, int interval) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(interval)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}

	/**
	 * This method is used to click a webElement in webpage
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		if (getElements(locator) != null) {
			try {
				getElement(locator).click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				for (int i = 0; i < getElements(locator).size(); i++) {

					getElements(locator).get(i).click();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	
	public boolean doIsDisplayed(By locator) {
		boolean displays = false;
		
		try {
			displays = getElement(locator).isDisplayed();
		
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return displays;
		
	}

	/**
	 * This method is used to enter text in text field
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		try {

			getElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method is for JavaScript normal alert and JavaScript Confirm alert-pop
	 * up.
	 * 
	 * @param value
	 * @return
	 */
	public String javaScriptAlertPopup(String value) {
		String getTexts = null;

		try {
			switch (value) {
			case "accept":
				driver.switchTo().alert().accept();
				break;
			case "getText":
				getTexts = driver.switchTo().alert().getText();
				break;
			case "dismiss":
				driver.switchTo().alert().dismiss();
				break;

			default:
				System.out.println("Please provide the proper value" + " " + value);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return getTexts;

	}

	/**
	 * This method is for JavaScript prompt pop-up.
	 * 
	 * @param inputText
	 */

	public void javaScriptAlertPromptPopUp(String inputText) {

		try {

			driver.switchTo().alert().sendKeys(inputText);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This method is used to get text of web-page
	 * 
	 * @param locator
	 * @return
	 * @return
	 * @return
	 */
	public String dogetTexts(By locator) {
		StringBuffer totalTexts = new StringBuffer();
		try {

			if (getElements(locator) != null) {

				getElements(locator).forEach(texts -> {
					if (!texts.getText().isEmpty()) {
						totalTexts.append(texts.getText());
						totalTexts.append(System.getProperty("line.separator"));
					}
				});

			} else {

				totalTexts.append(getElement(locator).getText());
				totalTexts.append(System.getProperty("line.separator"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return totalTexts.toString();

	}

	/**
	 * This method is used upload file in web-page
	 * 
	 * @param locator
	 * @param texts
	 */
	public void fileUploadPopUp(By locator, String texts) {

		try {

			getElement(locator).sendKeys(texts);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This method is for window pop up operations
	 */
	private String mainHandle;

	public void windowPopUp() {

		try {

			Set<String> handles = driver.getWindowHandles();

			Iterator<String> itr = handles.iterator();

			mainHandle = itr.next();

			String childHandle = itr.next();

			driver.switchTo().window(childHandle);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method is used to switch back the window to parent window.
	 */
	public void windowSwitchBackToParent() {
		try {
			driver.close();
			driver.switchTo().window(mainHandle);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This method is used to close current browser
	 */
	public void closeTheBrowser() {
		try {
			driver.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This method is used to close all the browser
	 */
	public void quitTheBrowsers() {
		try {
			driver.quit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method is to get total links text
	 * 
	 * @param locator
	 * @return
	 */
	public String totalPageLinks(By locator) {
		String sb = null;
		try {
			sb = dogetTexts(locator);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sb;
	}

	/**
	 * This method is used to get all the image links.
	 * 
	 * @param locator
	 * @return
	 */
	public String getTotalImage(By locator, String src) {
		String imageAttribute = null;
		try {
			imageAttribute = getAttributes(locator, src);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return imageAttribute;

	}

	/**
	 * This method is used to get the Attributes of a webelement.
	 * 
	 * @param locator
	 * @param src
	 * @return
	 */
	public String getAttributes(By locator, String src) {
		StringBuffer sb = new StringBuffer();
		try {

			if (getElements(locator) != null) {

				for (WebElement i : getElements(locator)) {

					if (!i.getAttribute(src).equals(null)) {
						sb.append(i.getAttribute(src));
						sb.append(System.getProperty("line.separator"));
					}
				}

			} else {
				sb.append(getElement(locator).getAttribute(src));
				sb.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return sb.toString();

	}

	public void selectDropDownWithSelect(By locator, String type, String value) {

		try {
			Select select = new Select(getElement(locator));

			switch (type) {

			case "value":

				select.selectByValue(value);

				break;
			case "visibletext":

				select.selectByVisibleText(value);

				break;
			case "index":

				select.selectByIndex(Integer.parseInt(value));

				break;

			default:
				System.out.println(type + "is not correct");

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectDropDownWithoutSelect(By locator, String value) {
		try {

			for (WebElement dropdownvalue : getElements(locator)) {

				if (dropdownvalue.equals(value)) {
					dropdownvalue.click();
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public StringBuffer getDropDownValues(By locator) {

		StringBuffer dropdownvalues = new StringBuffer();

		try {

			Select select = new Select(getElement(locator));

			List<WebElement> options = select.getOptions();

			for (WebElement elements : options) {

				dropdownvalues.append(elements.getText() + "\n");

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return dropdownvalues;
	}

	public void switchToFrames(By locator, String type) {

		try {

			switch (type) {

			case "webelement":
				driver.switchTo().frame(getElement(locator));

				break;

			case "name_id":

				driver.switchTo().frame(getElement(locator));

				break;

			case "defaultcontent":
				driver.switchTo().defaultContent();
				break;

			case "parentframe":
				driver.switchTo().parentFrame();

			default:
				System.out.println("please provide valid frame types");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void moveToElement(By locator) {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(getElement(locator)).build().perform();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<String> domoveToElementValues(By locator) {
		ArrayList<String> li = new ArrayList<String>();
		try {

			for (WebElement x : getElements(locator)) {
				li.add(x.getText());

			}
			System.out.println(li);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return li;
	}

	public void doDragAndDrop(By source, By target, String type) {

		try {

			Actions action = new Actions(driver);
			switch (type) {

			case "normal":

				action.clickAndHold(getElement(source)).moveToElement(getElement(target)).release().build().perform();
				break;

			case "draganddrop":

				action.dragAndDrop(getElement(source), getElement(target)).build().perform();

				break;

			default:
				System.out.println("Type is not correct please try correct ones");

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void doRightClick(By locator) {
		try {
			Actions action = new Actions(driver);

			action.contextClick(getElement(locator)).build().perform();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void doActionsSendKeys(By locator, String value) {
		try {
			Actions action = new Actions(driver);

			action.sendKeys(getElement(locator), value).build().perform();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void doActionsClick(By locator) {

		Actions action = new Actions(driver);
		if (getElements(locator) == null) {
			System.out.println("dheeraj");

			try {
				WebElement element = getElement(locator);
				action.click(element).perform();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {

				List<WebElement> click = getElements(locator);
				for (WebElement actionclick : click) {
					action.click(actionclick).build().perform();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void getPageScreenShot(String screenShotName) {

		try {

			TakesScreenshot screen = (TakesScreenshot) driver;

			File source = screen.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(source, new File("./target/Screenshot/" + screenShotName + ".png"));

		} catch (Exception e) {
			e.getMessage();

		}
	}

	public void getElementScreenShot(By locator, String screenShotName) {

		try {

			TakesScreenshot screen = (TakesScreenshot) getElement(locator);

			File source = screen.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(source, new File("./target/Screenshot/" + screenShotName + ".png"));

		} catch (Exception e) {
			e.getMessage();

		}
	}

	/**
	 * Used to Select J-query DropDown values
	 * 
	 * @param locator
	 * @param value1
	 */
	public void jqueryDropDown(By locator, String... value) {

		List<WebElement> multipleList = getElements(locator);

		for (int i = 0; i < value.length; i++) {

			for (int j = 0; j < multipleList.size(); j++) {
				if (value[i].equalsIgnoreCase("All")) {
					multipleList.get(j).click();

				} else if (value[i].equals(multipleList.get(j).getText())) {

					multipleList.get(j).click();
					break;

				}

			}

		}
	}

	public void doPaginationClick(By locator, By locator1, By locator3) throws InterruptedException {

		List<WebElement> pagination = getElements(locator);

		for (int i = 0; i < pagination.size(); i++) {

			if (driver.findElements(locator1).size() >= 1) {

				doActionsClick(locator3);

				break;

			} else {

				try {
					pagination.get(i).click();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				pagination = getElements(locator);
			}
		}
	}

}

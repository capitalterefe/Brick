package com.brick.reusablecomponents;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class WebDriverHelper extends PageObject{
	private static Logger log = Logger.getLogger(WebDriverHelper.class);
	private static final long DEFAULT_WAIT_IN_SEC = 30;
	private static final long POOL_IN_MIL_SEC = 500;
	protected static WebDriver driver;
	protected static Properties properties;

	protected void clickOn(By by) {
		WebElement element = waitForClickable(by);
		try {
			element.click();
		} catch (Exception e) {
			javaScriptExecute("arguments[0].click();", by);
		}
	}

	private void javaScriptExecute(String script, By by) {
		WebElement element = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript(script, element);
	}

	protected WebElement waitForClickable(By by) {
		return waitFor(by, DEFAULT_WAIT_IN_SEC, POOL_IN_MIL_SEC);
	}

	protected WebElement waitFor(By by, long waitTime, long poolingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime, poolingTime);
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public boolean isElementPresent(By elementBy, int waitForSeconds) throws Exception {

		boolean elementPresent = true;
		int count = 0;

		while (elementPresent) {
			try {
				if (driver.findElement(elementBy).isDisplayed()) {
					break;
				} else {
					if (count == waitForSeconds) {
						elementPresent = false;
						break;
					}

					Thread.sleep(1000);
					count++;
				}
			} catch (Exception ex) {
				if (count == waitForSeconds) {
					elementPresent = false;
					break;
				}

				Thread.sleep(1000);
				count++;
			}
		}

		return elementPresent;
	}

	public void clicksOn(By by) {
		WebElement element = waitForClickable(by);
		try {
			element.click();
		} catch (Exception e) {
			log.info(e.getMessage());
			log.info("FAILED TO CLICK WITH SELENIUM CLICK!!...TRYING JavaScript");
			javaScriptExecute("arguments[0].click();", by);
		}
	}

	public void clicksOn(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			log.info(e.getMessage());
			javaScriptExecute("arguments[0].click();", element);
		}
	}

	public void typeInto(By by, CharSequence... keysToSend) {
		WebElement targetElement = waitForClickable(by);
		javaScriptExecute("arguments[0].value ='';", targetElement);
		targetElement.sendKeys(keysToSend);
	}

	public void uploadDocument(By by, String docName) {
		String baseDir = SystemUtils.USER_DIR;
		String pathToFile = "";
		WebElement targetElement = waitForClickable(by);
		if (SystemUtils.IS_OS_WINDOWS) {
			pathToFile = baseDir + "\\TestUploads\\" + docName + ".pdf";
			log.info("Path To upload File: " + pathToFile);
			targetElement.sendKeys(pathToFile);
		} else {
			log.info("Path To upload File: " + pathToFile);
			pathToFile = baseDir + "/TestUploads/" + docName + ".pdf";
			upload(pathToFile).to(targetElement);
			Serenity.takeScreenshot();
		}
	}

	public WebElement waitFor(WebElement element, long waitTime, long poolingEveryMilSec) {
		WebDriverWait wait = getWaitDriver(waitTime, poolingEveryMilSec);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	private WebDriverWait getWaitDriver(long timeOutInSeconds, long poolEveryMilSec) {
		return new WebDriverWait(driver, timeOutInSeconds, poolEveryMilSec);
	}

	public void sleep(long miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitUntilElementDisplayBy(By locator, long seconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, seconds);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public boolean isDisplayed(By by) {
		boolean isDisplayed = false;
		try {
			if (getWebElement(by).isDisplayed())
				isDisplayed = true;
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	public WebElement getWebElement(By by) {
		return driver.findElement(by);
	}

	public List<WebElement> getWebElements(By by) {
		return driver.findElements(by);
	}

	/**
	 * Will Use the Action class to move to a given element
	 * 
	 * @param element
	 */
	public void moveToElementCustom(By by) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(by)).build().perform();
	}

	public void moveToElementCustom(WebElement webElement) {
		Actions action = new Actions(driver);
		action.moveToElement(webElement).build().perform();
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public void acceptAlert() {

		if (isAlertPresent())
			driver.switchTo().alert().accept();
	}

	public void dismissAlert() {
		if (isAlertPresent())
			driver.switchTo().alert().dismiss();
	}

	public String getAlertText() {
		if (isAlertPresent())
			return driver.switchTo().alert().getText();
		return "No Alert For You!";
	}

	public void deleteCookieByName(String name) {
		if (driver != null) {
			driver.manage().deleteCookieNamed(name);
		}
	}

	public void selectOneByIndex(String idPrefix, int indexToSelect) {
		By getSelectionPanelPop = By.id(idPrefix + "_label");
		By selectElementFromPanel = By.xpath("//div[@id='" + idPrefix + "_panel']/div/ul/li[" + indexToSelect + "]");

		clicksOn(getSelectionPanelPop);
		sleep(1000);
		clicksOn(selectElementFromPanel);

	}
	public void selectOneByText(By byelement, String visibleText) {
		WebElement element = driver.findElement(byelement);
		Select select=new Select(element);
		select.selectByVisibleText(visibleText);
		
	}

	public boolean selectCheckBoxByIndex(int position) {

		By checkBox = By.xpath("(//input[@type='checkbox'])[" + position + "]");
		if (isDisplayed(checkBox)) {
			clicksOn(checkBox);
			return true;
		} else {
			return false;
		}

	}

	public String getElementAttribute(By locator, String attributeName) {

		List<WebElement> element = driver.findElements(locator);
		if (element.size() >= 1) {

			if (driver.findElement(locator).getAttribute(attributeName) != null
					&& driver.findElement(locator).getAttribute(attributeName) != "") {
				return driver.findElement(locator).getAttribute(attributeName);
			}
		}
		return null;
	}

	public void selectDateFromDatePicker(String id, String date) {
		javaScriptExecute("document.getElementById('" + id + "').value='" + date + "'");

	}

	public void javaScriptExecute(String script) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}

	public void javaScriptExecute(String script, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, element);
	}

	public String getInnerHtml(By locator) {
		return driver.findElement(locator).getText();

	}

	public String getInnerHtml(WebElement locator) {
		return locator.getText();

	}

	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().perform();
	}

	public void moveAndMoveAndClick(By firstLocator, By secondLocator, By thirdLocator) {
		Actions action = new Actions(driver);
		WebElement firstElement = driver.findElement(firstLocator);
		WebElement secondElement = driver.findElement(secondLocator);
		WebElement thirdElement = driver.findElement(thirdLocator);
		action.moveToElement(firstElement).moveToElement(secondElement).click(thirdElement).build().perform();

	}

	public void moveAndMoveAndMoveAndClick(By firstLocator, By secondLocator, By thirdLocator, By forthLocator) {
		Actions action = new Actions(driver);
		WebElement firstElement = driver.findElement(firstLocator);
		WebElement secondElement = driver.findElement(secondLocator);
		WebElement thirdElement = driver.findElement(thirdLocator);
		WebElement forthElement = driver.findElement(forthLocator);
		action.moveToElement(firstElement).moveToElement(secondElement).moveToElement(thirdElement).click(forthElement)
				.build().perform();

	}

	public void moveAndClick(By firstLocator, By secondLocator) {
		Actions action = new Actions(driver);
		WebElement firstElement = driver.findElement(firstLocator);
		WebElement secondElement = driver.findElement(secondLocator);
		action.moveToElement(firstElement).moveToElement(secondElement).click().build().perform();
	}

	public List<WebElement> findElments(By locator) {
		return driver.findElements(locator);
	}

	public WebElement findElment(By locator) {
		return driver.findElement(locator);
	}

	public boolean isSelected(By locator) {
		return driver.findElement(locator).isSelected();

	}

	public void clearTextBox(By locator) {
		driver.findElement(locator).clear();

	}

	public String getElementValue(By lastnamelocator) {
		return driver.findElement(lastnamelocator).getAttribute("value");
	}

	public void enterRetunKey(By locator) {
		driver.findElement(locator).sendKeys(Keys.ENTER);
	}

	/**
	 * Check ELement Is Visible or not after opening page on a separate window
	 * 
	 * @param locator
	 * @param primaryWinHandle
	 * @return
	 */
	public boolean isElementDisplayedOnSecondWindow(By locator, String primaryWinHandle) {
		boolean elementDisplayed = false;
		for (String winHandles : driver.getWindowHandles()) {
			if (!winHandles.equalsIgnoreCase(primaryWinHandle)) {
				driver.switchTo().window(winHandles);
				sleep(3000);
				elementDisplayed = isDisplayed(locator);
				driver.close();
				driver.switchTo().window(primaryWinHandle);
			}
		}
		return elementDisplayed;
	}

	public void switchToNewWindowAndClosePrimaryWindow(String primaryWinHandle) {
		for (String winHandles : driver.getWindowHandles()) {
			if (!winHandles.equalsIgnoreCase(primaryWinHandle)) {
				driver.switchTo().window(winHandles);
				sleep(3000);
				driver.switchTo().window(primaryWinHandle);
				driver.close();
				driver.switchTo().window(winHandles);
			}
		}
	}

	public boolean switchToNewWindow(By locator, String primaryWinHandle) {
		boolean elementDisplayed = false;
		for (String winHandles : driver.getWindowHandles()) {
			if (!winHandles.equalsIgnoreCase(primaryWinHandle)) {
				driver.switchTo().window(winHandles);
				sleep(3000);
				elementDisplayed = isDisplayed(locator);
			}
		}
		return elementDisplayed;
	}

	public void switchToNewWindow(String primaryWinHandle) {
		for (String winHandles : driver.getWindowHandles()) {
			if (!winHandles.equalsIgnoreCase(primaryWinHandle)) {
				driver.switchTo().window(winHandles);
				sleep(3000);
			}
		}
	}

	public boolean switchBackToPrimaryWindow(By locator, String primaryWinHandle) {
		driver.switchTo().window(primaryWinHandle);
		sleep(3000);
		return isDisplayed(locator);

	}

	/**
	 * Check if certain properties exist for an element
	 */

	public boolean isAttributeValueExist(By locator, String attributeName, String valueInSearch) {
		String elementAtr = getElementAttribute(locator, attributeName);
		if (elementAtr != null && elementAtr.contains(valueInSearch))
			return true;
		return false;
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void openWindowInNewTab() {
		driver.findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "t");

	}

	public void switchToNewTabAndClose(String primaryWindowHandle) {
		for (String winHandles : driver.getWindowHandles()) {
			if (!winHandles.equalsIgnoreCase(primaryWindowHandle)) {
				driver.switchTo().window(winHandles);
				sleep(3000);
				driver.close();
				driver.switchTo().window(primaryWindowHandle);
			}
		}
	}

	public boolean loopAndVerifyIfElementExist(By locator, int waitTime) {
		int i = 0;
		while (!isDisplayed(locator)) {

			if (i > waitTime)
				return false;
			i++;
		}
		return true;

	}

	public int getElementCount(By locators) {
		return driver.findElements(locators).size();
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}

	public void navigateToCurrentUrl() {
		driver.navigate().to(driver.getCurrentUrl());
		driver.manage().window().maximize();
	}

	public void clearAllCookie() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
		}
	}

}

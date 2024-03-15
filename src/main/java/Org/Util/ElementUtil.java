package Org.Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.github.javafaker.Faker;

import Org.factory.DriverFactory;
import io.cucumber.java.Scenario;

public class ElementUtil {
	public static WebDriver driver=DriverFactory.getDriver();
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	public static JavascriptExecutor jse = (JavascriptExecutor) driver;
	public static WebElement element;
	public static String identifier;
	public static String locator;
	public static String locatorDescription;
	public static Faker faker = new Faker();
	public static int waitTime = 30;
	public static String dateTime;

	// ********** Constant Page Action Message
	// *********************************************************//
	public static final String elemPresentSuccessMsg = "PASS: Expected Element is Present - ";
	public static final String elemPresentFailureMsg = "FAIL: Expected Element is not Presnet - ";
	public static final String waitElemPresentSuccessMsg = "PASS: Wait For Element Present. "
			+ "Expected Element is Present - ";
	public static final String waitElemPresentFailureMsg = "FAIL: Wait For Element Present. "
			+ "Expected Element is not Presnet - ";
	public static final String elemDisplayedSuccessMsg = "PASS: Expected Element is displayed - ";
	public static final String elemDisplayedFailureMsg = "FAIL: Expected Element is not displayed - ";
	public static final String elemClickSuccessMsg = "PASS: Successfully Clicked on element ";
	public static final String elemClickFailureMsg = "FAIL: Unable to Click on element ";
	public static final String typeTextSuccessMsg = "PASS: Successfully typed text ";
	public static final String TypeTextFailureMsg = "FAIL: Unable to type text ";

	public static void zoomIn(int x) {
		for (int i = 0; i < x; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}

	public static void zoomOut(int x) {
		for (int i = 0; i < x; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}

	public static void zoomDefault() {
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}

	public static String addPassTextToExtentReport(String Text) {
		// Get the ExtentTest object
		ExtentTest extentTest = ExtentCucumberAdapter.getCurrentStep();
		// Get the text you want to highlight
		String expItem = Text; // Replace with your actual text
		// Log the result with HTML tags for highlighting
		extentTest.log(Status.PASS, "PASS: <mark>" + expItem + "</mark>");
		return expItem;
	}

	public static String addFailTextToExtentReport(String Text) {
		// Get the ExtentTest object
		ExtentTest extentTest = ExtentCucumberAdapter.getCurrentStep();
		// Get the text you want to highlight
		String expItem = Text; // Replace with your actual text
		// Log the result with HTML tags for highlighting
		extentTest.log(Status.FAIL, "FAIL: <mark>" + expItem + "</mark>");
		extentTest.fail("Fail : <font color=red>" + expItem + "</font>");
		return expItem;
	}

	public static String getURL(boolean printToReport) {
		String value = "";
		try {
			value = driver.getCurrentUrl();

			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Got url:- [" + value + " ]");
			}
			return value;
		} catch (Exception e) {
			Assert.assertFalse("FAILED:- to get url: " + e.toString(), true);
			return e.toString();
		}

	}

	public static String getPageTitle(boolean printToReport) {
		String value = "";
		try {
			value = driver.getTitle();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Page Titile is:- [ " + value + " ]");
			}
			return value;
		} catch (Exception e) {
			Assert.assertFalse("FAILED:- to get Page Title: " + e.toString(), true);
			return e.toString();
		}
	}

	public static String getPageURL(boolean printToReport) {
		String value = "";
		try {
			value = driver.getCurrentUrl();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Page URL is:- [ " + value + " ]");
			}
			return value;
		} catch (Exception e) {
			Assert.assertFalse("FAILED:- to get Current Page url: " + e.toString(), true);
			return e.toString();
		}
	}

	public static String getPageSource(boolean printToReport) {
		String value = "";
		try {
			value = driver.getPageSource();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Page Source is:- [ " + value + " ]");
			}
			return value;
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to get PageSource: " + e.toString(), true);
			return e.toString();
		}
	}

	public static void close(boolean printToReport) {
		try {
			driver.close();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Closed Driver:- ");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to close driver: " + e.toString(), true);
		}
	}

	public static void quit(boolean printToReport) {
		try {
			driver.quit();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Quit Driver:- ");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to quit driver: " + e.toString(), true);
		}
	}

	// navigation-commands
	public static void navigateToURL(String url, boolean printToReport) {
		try {
			driver.navigate().to(url);
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Navigate to url:- [ " + url + " ]");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to navigate to url: " + e.toString(), true);
		}
	}

	public static void navigateForward(boolean printToReport) {
		try {
			driver.navigate().forward();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Navigate browser forward:- ");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to navigage forward: " + e.toString(), true);
		}
	}

	public static void navigateBackward(boolean printToReport) {
		try {
			driver.navigate().back();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Navigate browser backward:- ");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to navigate back: " + e.toString(), true);
		}
	}

	public static void pageRefresh(boolean printToReport) {
		try {
			driver.navigate().refresh();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Browser Refresh:- ");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to refresh page: " + e.toString(), true);
		}
	}

	// ********** WebElement-commands *****************//

	public static void click(By by, String elemName) {
		try {
			waitForElementPresent(by, elemName, false);
			driver.findElement(by).click();
			ExtentCucumberAdapter.addTestStepLog(elemClickSuccessMsg + elemName);

		} catch (Exception ElementClickInterceptedException) {
			ExtentCucumberAdapter.addTestStepLog(
					elemClickFailureMsg + elemName + " : " + ElementClickInterceptedException.toString());
		}
	}

	public static void clear(By by, String elemName) {
		try {
			waitForElementPresent(by, elemName, false);
			driver.findElement(by).clear();
			ExtentCucumberAdapter.addTestStepLog("Successfully Cleared element:- " + elemName);

		} catch (Exception e) {
			ExtentCucumberAdapter.addTestStepLog("Failed to clear element:- " + elemName);
		}
	}

	public static void clearAlternative(By by, boolean printToReport) {
		try {
			driver.findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			String inputText = driver.findElement(by).getAttribute("value");
			if (inputText != null) {
				for (int i = 0; i < inputText.length(); i++) {
					driver.findElement(by).sendKeys(Keys.BACK_SPACE);
				}
			}
			ExtentCucumberAdapter.addTestStepLog(" clear text");
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Cleared element:- " + by);
			}
		} catch (Exception e) {
			ExtentCucumberAdapter.addTestStepLog("Failed to clear element:- " + by);
		}
	}

	public static void Jclick(By by, String elemName, boolean printToReport) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(by));
	}

	public static void type(By by, String textToType, String elemName) {
		try {
			waitForElementPresent(by, elemName, false);
			// scrollToElement(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(textToType);
			ExtentCucumberAdapter.addTestStepLog("PASS: Typed text:- [ " + textToType + " ] to element: " + elemName);
		} catch (Exception ElementNotInteractableException) {

			Assert.assertFalse("FAILED to type text:- [ " + textToType + "] to element: " + elemName + ": "
					+ ElementNotInteractableException.toString(), true);
		}
	}

	public static String getText(By by, String elemName, boolean printToReport) {
		String value = "";
		try {
			waitForElementPresent(by, elemName, false);
			value = driver.findElement(by).getText().trim();
			System.out.println("VALUE:- " + value);
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Get text:- [ " + value + " from element: " + elemName);
			}
			return value;
		} catch (Exception e) {
			try {
				value = driver.findElement(by).getAttribute("value");
			} catch (Exception e1) {
				try {
					value = driver.findElement(by).getAttribute("textContent");
					System.out.println("VALUE:- " + value);
					if (printToReport) {
						ExtentCucumberAdapter
								.addTestStepLog("PASS: Get text:- [ " + value + " from element: " + elemName);
					}
				} catch (Exception e2) {
					Assert.assertFalse(
							"FAILED:- to Get text:- [ " + value + " from element: ]" + elemName + ": " + e.toString(),
							true);
					value = e.toString();
				}
			}
		}
		return value;
	}

	public static String getTextValue(By by, String elemName, boolean printToReport) {
		String value = "";
		try {
			value = driver.findElement(by).getAttribute("value");
			System.out.println("VALUE:- " + value);
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Get text:- [ " + value + " from element: " + elemName);
			}
			return value;
		} catch (Exception e) {
			Assert.assertFalse(
					"FAILED:- to Get text:- [ " + value + " from element: ]" + elemName + ": " + e.toString(), true);
			return e.toString();
		}
	}

	public static String getTagName(By by, String elemName, boolean printToReport) {

		String value = "";
		try {
			waitForElementPresent(by, elemName, false);
			value = driver.findElement(by).getTagName();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Get TagName:- [ " + value + " ] from element: " + elemName);
			}
			return value;
		} catch (Exception e) {
			Assert.assertFalse(
					"FAIL:- to get TagName:- [ " + value + " ] from element: " + elemName + ": " + e.toString(), true);
			return e.toString();
		}

	}

	public static String getCssValue(By by, String propertyName, boolean printToReport) {
		String value = "";
		try {

			value = driver.findElement(by).getCssValue(propertyName);
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Get CssValue:- [ " + value + " ] from element: " + by);
			}
			return value;
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to get CSS Value:- [ " + value + " ] from element: " + by + ": " + e.toString(),
					true);
			return e.toString();
		}

	}

	public static String getAttribute(By by, String elemName, String propertyName, boolean printToReport) {
		String value = "";
		try {
			value = driver.findElement(by).getAttribute(propertyName);
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Get Attribute:- [ " + value + " from element: ] " + elemName);
			}
			return value;
		} catch (Exception e) {
			Assert.assertFalse(
					"FAIL:- to get Attribute Value:- [ " + value + " ] from element: " + elemName + ": " + e.toString(),
					true);
			return e.toString();
		}

	}

	public static Dimension getSize(By by, boolean printToReport) {
		Dimension dimensions;
		try {
			dimensions = driver.findElement(by).getSize();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("Get Size:- [ " + dimensions + " ] from element: " + by);
			}
			return dimensions;
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to get Size from element: " + by + ": " + e.toString(), true);
			return null;
		}

	}

	public static Point getLocation(By by, String elemName, boolean printToReport) {
		Point point;
		try {
			waitForElementPresent(by, elemName, false);
			point = driver.findElement(by).getLocation();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog(
						"Get Location:- [ X cordinate : " + point.x + "Y cordinate: " + point.y + " " + elemName);
			}
			return point;
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to get Location:-  from element: " + elemName + ": " + e.toString(), true);
			return null;
		}

	}

	public static void selectValueFromDropdown(By dropdown, String elemName, String dropdownValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdown));
			WebElement selectElem = driver.findElement(dropdown);
			Select select = new Select(selectElem);
			select.selectByValue(dropdownValue);
			ExtentCucumberAdapter
					.addTestStepLog("PASS: Selected Value: " + dropdownValue + " from dropdown - " + elemName);

		} catch (Exception e) {
			Assert.assertFalse("FAIL: Failed to select value: " + dropdownValue + " from dropdown - " + elemName + " : "
					+ e.toString(), true);
		}
	}

	public static List<String> getDropdownText(By by, boolean printToReport) {
		List<WebElement> we = driver.findElements(by);
		List<String> ls = new ArrayList<String>();
		for (WebElement a : we) {
			ls.add(a.getText());
		}
		if (printToReport) {
			ExtentCucumberAdapter.addTestStepLog("Dropdown values:- " + ls);
		}
		return ls;
	}

	public static void scrollToBottomOfPage(boolean printToReport) {
		try {
			new Actions(driver).sendKeys(Keys.PAGE_DOWN).build().perform();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("scroll To Bottom Of Page");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to scroll To Bottom Of Page " + ": " + e.toString(), true);
		}
	}

	public static void scrollHorizontalPage(boolean printToReport) {
		try {

			new Actions(driver).sendKeys(Keys.ARROW_RIGHT).build().perform();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("scroll To horizontally");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to scroll horizontally " + ": " + e.toString(), true);
		}
	}

	public static void pressEnter() {
		try {
			new Actions(driver).sendKeys(Keys.ENTER).build().perform();
			ExtentCucumberAdapter.addTestStepLog("press enter");
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to scroll horizontally ", true);
		}
	}

	public static void pressTab() {
		try {
			ElementUtil.waitTime(2);
			new Actions(driver).sendKeys(Keys.TAB).build().perform();
			ExtentCucumberAdapter.addTestStepLog("press Tab");
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to scroll horizontally ", true);
		}
	}

	public static void moveMoveClick(By by, boolean printToReport) {
		try {
			new Actions(driver).moveToElement(driver.findElement(by))
					.click(driver.findElement(by));
			new Actions(driver).perform();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("clicked element using mouse operation");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to click " + ": " + e.toString(), true);
		}
	}

	public static void mouseMove(By by, boolean printToReport) {
		try {
			new Actions(driver).moveToElement(driver.findElement(by)).perform();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("mouse to element using actions");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to click " + ": " + e.toString(), true);
		}
	}

	public static void highlightElement(By by, boolean printToReport) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.background='yellow'",
				driver.findElement(by));
	}

	public static void highlightElementFail(By by, boolean printToReport) {
		WebElement elem = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", elem);
	}

	public static void highlightElementPass(By by, boolean printToReport) {
		WebElement elem = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", elem);
	}

	public static void jsClick(By by, String elemName) {
		try {
//			waitForElementPresent(by, elemName, false);
			element = driver.findElement(by);
			jse.executeScript("arguments[0].click();", element);
			ExtentCucumberAdapter.addTestStepLog(elemClickSuccessMsg + elemName);

		} catch (Exception e) {
			ExtentCucumberAdapter.addTestStepLog(elemClickFailureMsg + elemName + ": " + e.toString());
			Assert.assertFalse(elemClickFailureMsg + elemName + ": " + e.toString(), true);
		}
	}

	public static void jsType(By by, String valueToType, boolean printToReport) {
		try {

			WebElement elemToType = driver.findElement(by);
			String js = "arguments[0].setAttribute('value','" + valueToType + "')";
			jse.executeScript(js, elemToType);
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to type in element " + by + ": " + e.toString(), true);
		}
	}

	public static void scrollToTopOfPage(boolean printToReport) {
		try {
			new Actions(driver).sendKeys(Keys.PAGE_UP).build().perform();
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("scroll To top Of Page");
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to scroll To Top Of Page" + ": " + e.toString(), true);
		}
	}

	public static void waitForElementPresent(By by, String elemName, boolean printToReport) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(75));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog(waitElemPresentSuccessMsg + elemName);
			}
		} catch (Exception e) {
			Assert.assertFalse(waitElemPresentFailureMsg + elemName + ": " + e.toString(), true);
		}
	}

	public static boolean isElementDisplayed(By by, String elemName) {
		boolean isDisplayed = false;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		try {
			isDisplayed = driver.findElement(by).isDisplayed();
			ExtentCucumberAdapter.addTestStepLog(elemDisplayedSuccessMsg + elemName);

		} catch (Exception e) {
			ExtentCucumberAdapter.addTestStepLog(elemDisplayedFailureMsg + elemName + ": " + e.toString());
			isDisplayed = false;
		}
		return isDisplayed;
	}

	public static boolean isElementPresent(By by, String elemName) {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		try {
			flag = driver.findElement(by) != null;
			ExtentCucumberAdapter.addTestStepLog(elemPresentSuccessMsg + elemName);
		} catch (Exception e) {
			ExtentCucumberAdapter.addTestStepLog(elemPresentFailureMsg + elemName + ": " + e.toString());
			flag = false;
		}
		return flag;
	}

	public static boolean isElementPresent(By by, String elemName, int waitTime) {
		boolean elemPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by);
			ExtentCucumberAdapter.addTestStepLog(elemPresentSuccessMsg + elemName);
			elemPresent = true;
		} catch (Exception e) {
			elemPresent = false;
			ExtentCucumberAdapter.addTestStepLog(elemPresentFailureMsg + elemName);
			ExtentCucumberAdapter.addTestStepLog(e.toString());
		}
		return elemPresent;
	}

	public static void waitForPageLoad() {
		for (int i = 0; i < 60; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("Page has not loaded yet ");
			}
			// again check page state
			if (((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete")) {
				break;
			}
		}
	}

	public static void waitForElementVisible(By by, String elemName, boolean printToReport) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("PASS: Element is Visible: " + elemName);
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL: Element is not Visible: " + elemName + ": " + e.toString(), true);
		}
	}

	public static void waitForElementVisible(WebElement element, String elemName, boolean printToReport) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("PASS: Element is Visible: " + elemName);
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL: Element is not Visible: " + elemName + ": " + e.toString(), true);
		}
	}

	public static void waitForElementClickable(By by, String elemName, boolean printToReport) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("PASS: Element is Clickable: " + elemName);
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL: Element is not Clickable: " + elemName + ": " + e.toString(), true);
		}
	}

	public static boolean waitForElementVisibleReturn(By by) {
		boolean flag = false;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			;
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	public static void waitForElementInvisible(By by, String elemName, boolean printToReport) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(by)));
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("PASS: Element is not Visible: " + elemName);
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL: Element is Visible: " + elemName + ": " + e.toString(), true);
		}
	}

	public static void scrollToElement(By by, String elemName, boolean printToReport) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
					driver.findElement(by));
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("PASS: Successfully scrolled to element " + elemName);
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL: Failed to scroll to element " + elemName + ": " + e.toString(), true);
		}
	}

	public static void waitForFrame(By by, String frame, boolean printToReport) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			if (printToReport) {
				ExtentCucumberAdapter.addTestStepLog("wait for frame " + by);
			}
		} catch (Exception e) {
			Assert.assertFalse("FAIL:- to wait for frame" + ": " + e.toString(), true);

		}
	}

	public static String generateRandomStringWithLowercase(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";
		String website = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		System.out.println(temp);
		website = temp;
		return website;
	}

	public static String generateRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public static String generateRandomAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public String generateStringWithSplChars(int length, String allowdSplChrs) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				allowdSplChrs;
		return RandomStringUtils.random(length, allowedChars);
	}

	public static String generateEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + "_"; // special
									// characters
		String email = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		System.out.println(temp);
		email = temp + "@gmail.com";
		return email;
	}

	public static String generateIDPEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + "_"; // special
									// characters
		String email = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		email = temp + "@idp.com";
		// System.out.println("EMAIL:- " + email);

		return email;
	}

	public String generateUrl(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				"_-."; // special characters
		String url = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		url = temp.substring(0, 3) + "." + temp.substring(4, temp.length() - 4) + "."
				+ temp.substring(temp.length() - 3);
		return url;
	}

	public static String generateAlphaNumbericString(int minimumLength, int maximumLength, boolean includeUppercase,
			boolean includeSpecial) {
		if (includeSpecial) {
			char[] password = faker.lorem().characters(minimumLength, maximumLength, includeUppercase).toCharArray();
			char[] special = new char[] { '!', '@', '#', '$', '%', '^', '&', '*' };
			for (int i = 0; i < faker.random().nextInt(minimumLength); i++) {
				password[faker.random().nextInt(password.length)] = special[faker.random().nextInt(special.length)];
			}
			return new String(password);
		} else {
			return faker.lorem().characters(minimumLength, maximumLength, includeUppercase);
		}
	}

	public static String generateRandomDigits(int length) {
		return faker.number().digits(length);
	}

	public static boolean isUUID(String string) {
		try {
			UUID.fromString(string);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	public static String generateUUID() {
		try {
			UUID uuid = UUID.randomUUID();
			return uuid.toString();
		} catch (Exception ex) {
			return ex.toString();
		}

	}

	public static void waitTime(int seconds) {
		try {
			long waitTime = 1000 * seconds;
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void credentialType(By by, String elemName, String encodedCredential) {
		try {
			waitForElementPresent(by, elemName, false);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(decodeString(encodedCredential));
			ExtentCucumberAdapter.addTestStepLog(
					"Encoded credential:- [ " + encodedCredential + " ]  is decrypted & typed to element: " + elemName);
		} catch (Exception e) {
			Assert.assertFalse(
					e.getMessage()+"--"+e.fillInStackTrace().toString()+"--"+"FAILED to decrypt & type credential:- [ " + encodedCredential + " to element " + elemName, true);
		}

	}

	public static String decodeString(String encodedPassword) {
		try {
			Base64.Decoder decoder = Base64.getMimeDecoder();
			String str = new String(decoder.decode(encodedPassword));
			return str;
		} catch (Exception e) {
			Assert.assertFalse("Failed to decode String:- " + e.toString(), true);
			return null;
		}
	}

	public static String encodeString(String string) {
		try {
			byte[] encodedBytes = Base64.getEncoder().encode(string.getBytes());
			String EncodedString = new String(encodedBytes);
			return EncodedString;
		} catch (Exception e) {
			Assert.assertFalse("Failed to Encode String:- " + e.toString(), true);
			return null;
		}
	}

	public static Boolean checkButtonEnabled(By by) {
		WebElement element = driver.findElement(by);
		System.out.println("IsEnabled:- " + element.isEnabled());
		return element.isEnabled();
	}

	public static List<String> getStringList(By by) {

		List<WebElement> lst = driver.findElements(by);
		List<String> strings = new ArrayList<String>();
		for (WebElement e : lst) {
			strings.add(e.getText());
		}
		return strings;
	}

	public static void findElements(By by) {
		List<WebElement> elements = driver.findElements(by);
		for (WebElement element : elements) {
			Assert.assertTrue(!(element.isSelected()));
		}
	}

	public static void waitForElementNotPresent(By by) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		List<WebElement> dynamicElement = null;
		for (int i = 0; i < 60; i++) {
			dynamicElement = driver.findElements(by);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				// System.out.println("Page has not loaded yet ");
			}
			// again check page state
			if (dynamicElement.size() == 0) {
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public static void waitForElementText(By by, String Text) {
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by, Text));
			ExtentCucumberAdapter.addTestStepLog("Expected Text is present in the Element " + by);
		} catch (Exception e) {
			Assert.assertFalse("Expected Text is not present in the Element " + by + ": " + e.toString(), true);

		}
	}

	public static void jsScrollToElement(By by) {
		try {
			WebElement element = driver.findElement(by);
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			ExtentCucumberAdapter.addTestStepLog("Move to element using js command " + by);
		} catch (Exception e) {
			System.out.println("EXCEPTION:- " + e.toString());
			Assert.assertFalse("Failed to move to element using js command " + by + ": " + e.toString(), true);
		}
	}

	public static String getCurrentDateTime(String format) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
			LocalDateTime now = LocalDateTime.now();
			dateTime = dtf.format(now);
			return dateTime;
		} catch (Exception e) {
			Assert.assertFalse("FAILED:- to get current date time " + ": " + e.toString(), true);
			return null;
		}
	}

	public static void getScreenshot(Scenario scenario, String FileName) {
		try {
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", FileName);
			ExtentCucumberAdapter.addTestStepLog("PASS: Take screenshot as requested");
		} catch (Exception e) {
			Assert.assertFalse("FAILED:- to get screenshot " + e.toString(), true);
		}
	}

	public static void GoOutOfEmbededFrame() {
		try {
			driver.switchTo().defaultContent();
			ExtentCucumberAdapter.addTestStepLog("Move out of embedded frame is successfull");
		} catch (Exception e) {
			Assert.assertFalse("Failed to move out of embedded frame " + e.toString(), true);
		}
	}

	public static void GoToEmbededFrame(String elemName) {
		try {
			waitForElementPresent(By.xpath("//iframe"), elemName, false);
			driver.switchTo().frame("embeddedTool");
			ExtentCucumberAdapter.addTestStepLog("Move to embedded frame is successfull");
		} catch (Exception e) {
			Assert.assertFalse("Failed to move to embedded frame " + e.toString(), true);
		}
	}

	public void jsFocusClick(By by, String elemName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			jse.executeScript("arguments[0].focus();", element);
			jse.executeScript("arguments[0].click();", element);
			ExtentCucumberAdapter.addTestStepLog(elemClickSuccessMsg + elemName);
		} catch (Exception e) {
			ExtentCucumberAdapter.addTestStepLog(elemClickFailureMsg + elemName + " : " + e.toString());
		}
	}

	public void jsEnterText(By by, String value, String elemName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			ElementUtil.scrollToElement(by, "Expected Element", true);
			jse.executeScript("arguments[0].value=\"" + value + "\";", element);
			ExtentCucumberAdapter.addTestStepLog("PASS: Typed text:- [ " + value + " ] to element: " + elemName);
		} catch (Exception e) {
			Assert.assertFalse("Failed to Enter Text in Element " + elemName + " : " + e.toString(), true);
		}
	}

	public static List<WebElement> getElementsList(By by, String elemName) {
		List<WebElement> expElementsList = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			final List<WebElement> elemList = driver.findElements(by);
			expElementsList = elemList;
		} catch (Exception e) {
			Assert.assertFalse("Failed to get list of elements; " + elemName + e.toString(), true);
		}
		return expElementsList;
	}

}

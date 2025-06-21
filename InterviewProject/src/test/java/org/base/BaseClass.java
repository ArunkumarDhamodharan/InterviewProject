package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	public static void getDriver(String browserType) {
		switch (browserType.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser type: " + browserType);
		}
	}

	// WindowMaximize
	public static void windMax() {
		driver.manage().window().maximize();
	}

	// SendURL
	public static void getUrl(String url) {
		driver.get(url);
	}

	// file upload
	public static void uploadFile(WebElement uploadButton, String filePath) {
		uploadButton.sendKeys(filePath);
	}

	// delete cookies
	public static void deleteAllCookies() {
		driver.manage().deleteAllCookies();

	}

	// screenshot
	public static String takeScreenshot(String screenshotName) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		String dest = "./screenshots/" + screenshotName + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(src, destination);
		return dest;
	}

	// *************************************** wait methods
	// *****************************************

	public static WebElement waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// ************************************ wait methods end
	// ******************************************

	// *******************************************Using JavaScript
	// Executer************************************

	// Using JavaScript Send keys
	public static void sendKeysBasedOnJs(String txtInput, WebElement obj) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + txtInput + "')", obj);
	}

	// Method to click an element using JavaScript
	public static void clickElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// Method to scroll to an element using JavaScript
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Method to get the inner text of an element using JavaScript
	public static String getElementText(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].innerText;", element);
	}

	// Method to set the value of an input field using JavaScript
	public static void setElementValue(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + value + "';", element);
	}

	// Method to get the title of the page using JavaScript
	public static String getPageTitle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return document.title;");
	}

	// Method to refresh the page using JavaScript
	public static void refreshPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0);");
	}

	// highlight the element
	public static void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	// **************************************JavaScript Executer
	// End********************************************

	// *************************Navigation Commands to Forward Backward and
	// Refresh***************************

	// to
	public static void toUrl(String URL) {
		driver.navigate().to(URL);
	}

	// Forward
	public static void forwardButton() {
		driver.navigate().forward();
	}

	// Backward
	public static void backwardButton() {
		driver.navigate().back();
	}

	// Refresh
	public static void refresh() {
		driver.navigate().refresh();
	}

	// ************************************Navigation Commands
	// End******************************************

	// ***********************************Actions Commands
	// Start*******************************************

	// normal Click
	public static void click() {
		Actions act = new Actions(driver);
		act.click().perform();
	}

	// Double Click
	public static void doubleClick() {
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}

	// RightClick
	public static void rightClick() {
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}

	// Move offset
	public static void moveOffset(int xOffset, int yOffset) {
		Actions act = new Actions(driver);
		act.moveByOffset(xOffset, yOffset).perform();
	}

	// DragandDrop
	public static void dragandDrop(WebElement source, WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, target).perform();
	}

	// DragandDrop by offset
	public static void dragandDropbyOffset(WebElement source, int xOffset, int yOffset) {
		Actions act = new Actions(driver);
		act.dragAndDropBy(source, xOffset, yOffset).perform();
	}

	// ClickMoveRelease
	public static void clickandMoverelease(WebElement source, WebElement target) {
		Actions act = new Actions(driver);
		act.clickAndHold(source).moveToElement(target).release().perform();
	}

	// Hover Over Element (Move to Element)
	public static void hoverOverElement(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	// *******************************Actions
	// End**********************************************************

	// *******************************Alerts
	// Start**********************************************************

	// Accept alert
	public static void acceptAlert() {
		Alert acceptAlert = driver.switchTo().alert();
		acceptAlert.accept();
	}

	// Dismiss alert
	public static void dismissAlert() {
		Alert dismissAlert = driver.switchTo().alert();
		dismissAlert.dismiss();
	}

	// Prompt Alert
	public static void promptAlert(String prompt) {
		Alert promptAlert = driver.switchTo().alert();
		promptAlert.sendKeys(prompt);
	}

	// getText Alert
	public static void getTextAlert() {
		Alert getTextAlert = driver.switchTo().alert();
		String text = getTextAlert.getText();
		System.out.println(text);
	}

	// *******************************Alerts
	// End**********************************************************

	// *******************************Select
	// Start**********************************************************

	// selectByVisibleText
	public static void tselectByVisibleTex(WebElement obj, String text) {
		Select s = new Select(obj);
		s.selectByVisibleText(text);
	}

	// selectByValue
	public static void selectByValue(WebElement obj, String text) {
		Select s = new Select(obj);
		s.selectByValue(text);
	}

	// selectByIndex
	public static void selectByIndex(WebElement obj, int index) {
		Select s = new Select(obj);
		s.selectByIndex(index);
	}

	// drop down select
	public static void selectDropDownOption(WebElement dropdown, String optionText) {
		new Select(dropdown).selectByVisibleText(optionText);
	}

	// *******************************Select
	// End**********************************************************

	// *******************************Excel
	// Read**********************************************************

	public static String excelRead(int rownum, int cellnum) throws IOException {

		File file = new File("C:\\Users\\ELCOT\\Documents\\xl datas\\DemoBlaze.xlsx");

		FileInputStream stream = new FileInputStream(file);

		Workbook book = new XSSFWorkbook(stream);

		Sheet sheet = book.getSheet("Sheet1");

		Row row = sheet.getRow(rownum);

		Cell cell = row.getCell(cellnum);

		CellType cellType = cell.getCellType();
		String value = null;
		switch (cellType) {
		case STRING:
			value = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
				value = simple.format(dateCellValue);

			} else {
				double numericCellValue = cell.getNumericCellValue();
				long l = (long) numericCellValue;
				BigDecimal valueOf = BigDecimal.valueOf(l);
				value = valueOf.toString();
			}
			break;

		default:

			break;
		}
		return value;
	}

	// *******************************Excel Read
	// End**********************************************************

	// *******************************Windows Handling
	// Start**********************************************************

	// Method to switch to a new window by its title
	public static void switchToWindowByTitle(String windowTitle) {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> iterator = allWindows.iterator();
		while (iterator.hasNext()) {
			String currentWindow = iterator.next();
			driver.switchTo().window(currentWindow);
			if (driver.getTitle().equals(windowTitle)) {
				return;
			}
		}
		driver.switchTo().window(mainWindow);
	}

	// Method to switch to a new window by its URL
	public static void switchToWindowByUrl(String windowUrl) {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> iterator = allWindows.iterator();
		while (iterator.hasNext()) {
			String currentWindow = iterator.next();
			driver.switchTo().window(currentWindow);
			if (driver.getCurrentUrl().equals(windowUrl)) {
				return;
			}
		}
		driver.switchTo().window(mainWindow);
	}

	// Method to close all windows except the main window
	public static void closeAllOtherWindows() {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);
	}

	// Method to perform actions in a new window
	public static void performActionInNewWindow(String windowTitle, Runnable action) {
		switchToWindowByTitle(windowTitle);
		action.run();
		String mainWindow = driver.getWindowHandle();
		driver.switchTo().window(mainWindow);
	}

	public static String mainWindowHandle;

	public static void setMainWindowHandle() {
		mainWindowHandle = driver.getWindowHandle();
	}

	public static void switchBackToMainWindow() {
		if (mainWindowHandle != null) {
			driver.switchTo().window(mainWindowHandle);
		} else {
			System.out.println("Main window handle is not set. Cannot switch back.");
		}
	}

	// *******************************Windows Handling
	// End*************************************************

	// *******************************Frames
	// Start*********************************************************

	public static void switchToFrameByNameOrId(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	public static void switchToFrameByWebElement(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	public static void switchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	public static void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}

	public static void switchToMainWindow() {
		driver.switchTo().defaultContent();
	}

	// *******************************Frames
	// End*********************************************************

	// *******************************Robot
	// Class**********************************************************

	public static void escPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
	}

	public static void escRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	public static void windowsPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_WINDOWS);
	}

	public static void windowsRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_WINDOWS);
	}

	public static void altPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
	}

	public static void altRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_ALT);
	}

	public static void tabPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
	}

	public static void tabRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	public static void shiftPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_SHIFT);
	}

	public static void shiftRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_SHIFT);
	}

	public static void capsPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CAPS_LOCK);
	}

	public static void capsRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
	}

	public static void ctrlPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
	}

	public static void ctrlRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public static void spacePress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_SPACE);
	}

	public static void spaceRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_SPACE);
	}

	public static void enterPress() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void backspaceKey() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
	}

	public static void deleteKey() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DELETE);
		robot.keyRelease(KeyEvent.VK_DELETE);
	}

	public static void aLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
	}

	public static void bLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_B);
		robot.keyRelease(KeyEvent.VK_B);
	}

	public static void cLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
	}

	public static void dLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_D);
	}

	public static void eLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
	}

	public static void fLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F);
		robot.keyRelease(KeyEvent.VK_F);
	}

	public static void gLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_G);
	}

	public static void hLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_H);
		robot.keyRelease(KeyEvent.VK_H);
	}

	public static void iLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_I);
		robot.keyRelease(KeyEvent.VK_I);
	}

	public static void jLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_J);
		robot.keyRelease(KeyEvent.VK_J);
	}

	public static void kLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_K);
		robot.keyRelease(KeyEvent.VK_K);
	}

	public static void lLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_L);
		robot.keyRelease(KeyEvent.VK_L);
	}

	public static void mLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_M);
		robot.keyRelease(KeyEvent.VK_M);
	}

	public static void nLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
	}

	public static void oLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
	}

	public static void pLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_P);
		robot.keyRelease(KeyEvent.VK_P);
	}

	public static void qLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_Q);
		robot.keyRelease(KeyEvent.VK_Q);
	}

	public static void rLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_R);
	}

	public static void sLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
	}

	public static void tLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
	}

	public static void uLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_U);
		robot.keyRelease(KeyEvent.VK_U);
	}

	public static void vLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
	}

	public static void wLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
	}

	public static void xLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_X);
		robot.keyRelease(KeyEvent.VK_X);
	}

	public static void yLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_Y);
		robot.keyRelease(KeyEvent.VK_Y);
	}

	public static void zLetter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_Z);
		robot.keyRelease(KeyEvent.VK_Z);
	}

	public static void number0() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
	}

	public static void number1() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_1);
	}

	public static void number2() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);
	}

	public static void number3() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_3);
		robot.keyRelease(KeyEvent.VK_3);
	}

	public static void number4() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_4);
		robot.keyRelease(KeyEvent.VK_4);
	}

	public static void number5() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_5);
		robot.keyRelease(KeyEvent.VK_5);
	}

	public static void number6() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_6);
		robot.keyRelease(KeyEvent.VK_6);
	}

	public static void number7() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_7);
		robot.keyRelease(KeyEvent.VK_7);
	}

	public static void number8() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_8);
		robot.keyRelease(KeyEvent.VK_8);
	}

	public static void number9() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_9);
		robot.keyRelease(KeyEvent.VK_9);
	}

	public static void pressF1() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F1);
		robot.keyRelease(KeyEvent.VK_F1);
	}

	public static void pressF2() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F2);
		robot.keyRelease(KeyEvent.VK_F2);
	}

	public static void pressF3() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F3);
		robot.keyRelease(KeyEvent.VK_F3);
	}

	public static void pressF4() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F4);
		robot.keyRelease(KeyEvent.VK_F4);
	}

	public static void pressF5() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);
	}

	public static void pressF6() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F6);
		robot.keyRelease(KeyEvent.VK_F6);
	}

	public static void pressF7() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F7);
		robot.keyRelease(KeyEvent.VK_F7);
	}

	public static void pressF8() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F8);
		robot.keyRelease(KeyEvent.VK_F8);
	}

	public static void pressF9() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F9);
		robot.keyRelease(KeyEvent.VK_F9);
	}

	public static void pressF10() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_F10);
	}

	public static void pressF11() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F11);
		robot.keyRelease(KeyEvent.VK_F11);
	}

	public static void pressF12() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F12);
		robot.keyRelease(KeyEvent.VK_F12);
	}

	public static void pressF13() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F13);
		robot.keyRelease(KeyEvent.VK_F13);
	}

	public static void pressF14() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F14);
		robot.keyRelease(KeyEvent.VK_F14);
	}

	public static void pressF15() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F15);
		robot.keyRelease(KeyEvent.VK_F15);
	}

	public static void pressF16() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F16);
		robot.keyRelease(KeyEvent.VK_F16);
	}

	public static void pressF17() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F17);
		robot.keyRelease(KeyEvent.VK_F17);
	}

	public static void pressF18() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F18);
		robot.keyRelease(KeyEvent.VK_F18);
	}

	public static void pressF19() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F19);
		robot.keyRelease(KeyEvent.VK_F19);
	}

	public static void pressF20() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F20);
		robot.keyRelease(KeyEvent.VK_F20);
	}

	public static void pressF21() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F21);
		robot.keyRelease(KeyEvent.VK_F21);
	}

	public static void pressF22() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F22);
		robot.keyRelease(KeyEvent.VK_F22);
	}

	public static void pressF23() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F23);
		robot.keyRelease(KeyEvent.VK_F23);
	}

	public static void pressF24() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F24);
		robot.keyRelease(KeyEvent.VK_F24);
	}

	public boolean verifyPageNavigation(WebElement link, WebElement pageTitleElement) {
		String expectedText = link.getText();
		link.click();
		WebElement pageTitle = waitForElementToBeVisible(pageTitleElement, 10);
		return pageTitle.getText().equalsIgnoreCase(expectedText);
	}

}

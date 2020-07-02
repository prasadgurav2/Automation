
package com.Automation.Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.support.ui.Select;

public class MasterHelper {

	private static WebDriver driver;
	private static Logger Log = LoggerHelper.getLogger(MasterHelper.class);

	WebDriverWait wait;
	ScenarioContext sc = new ScenarioContext();
	@FindBy(xpath = "//div[@class='viewcubeWrapper']")
	WebElement ViewCube;
	
	@FindBy(xpath = "//div[@id='guiviewer3d-toolbar']")
	WebElement ViewToolbar;
	
	@FindBy(xpath = "//fieldset[legend[contains(text(),'Base Version')]]/mat-toolbar/h6")
	WebElement baseVersion;
	
	// ################################################## AlertHelper
	// #####################################################################################

	public MasterHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("MasterHelper : " + this.driver.hashCode());
	}

	public Alert getAlert() {
		Log.debug("");
		return driver.switchTo().alert();
	}

	public void AcceptAlert() {
		Log.info("");
		getAlert().accept();
	}

	public void DismissAlert() {
		Log.info("");
		getAlert().dismiss();
	}

	public String getAlertText() {
		String text = getAlert().getText();
		Log.info(text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			Log.info("true");
			return true;
		} catch (NoAlertPresentException e) {
			// Ignore
			Log.info("false");
			return false;
		}
	}

	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		AcceptAlert();
		Log.info("");
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
		Log.info("");
	}

	public void AcceptPrompt(String text) {

		if (!isAlertPresent())
			return;

		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		Log.info(text);
	}

	// ################################################## BrowserHelper
	// #####################################################################################

	/*
	 * //public BrowserHelper(WebDriver driver) { this.driver = driver;
	 * Log.debug("BrowserHelper : " + this.driver.hashCode()); }
	 */

	public void goBack() {
		driver.navigate().back();
		Log.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		Log.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		Log.info("");
	}

	public Set<String> getWindowHandlens() {
		Log.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		if (index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsId.get(index));
		Log.info(index);
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		Log.info("");
	}

	public void switchToParentWithChildClose() {
		switchToParentWindow();

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			Log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}

	public void switchToFrame(By locator) {
		driver.switchTo().frame(new MasterHelper(driver).getElement(locator));
		Log.info(locator);
	}

	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		Log.info(nameOrId);
	}

	public void clickOn(WebElement webElement, WebDriver driver, int timeout) {
		final WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(webElement)));
		driver.findElement((By) webElement).click();
	}

	public static boolean retryingFindClick(By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 5) {
			try {
				driver.findElement(by).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	// ################################################## ButtonHelper
	// #####################################################################################

	public void selectCheckBox(By locator) {
		Log.info(locator);
		selectCheckBox(driver.findElement(locator));
	}

	public void unSelectCheckBox(By locator) {
		Log.info(locator);
		unSelectCheckBox(driver.findElement(locator));
	}

	public boolean isIselected(By locator) {
		Log.info(locator);
		return isIselected(driver.findElement(locator));
	}

	public boolean isIselected(WebElement element) {
		boolean flag = element.isSelected();
		Log.info(flag);
		return flag;
	}

	public void selectCheckBox(WebElement element) {
		if (!isIselected(element))
			element.click();
		Log.info(element);
	}

	public void unSelectCheckBox(WebElement element) {
		if (isIselected(element))
			element.click();
		Log.info(element);
	}

	// ################################################## DateTimeHelper
	// #####################################################################################

	public static String getCurrentDateTime() {

		DateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String time = "" + dateFormat.format(cal.getTime());
		return time;
	}

	public static String getCurrentDate() {
		return getCurrentDateTime().substring(0, 11);
	}
	
	

	public void Selectyear(String beforexpath , String afterxpath ,String afterxpath2,String Row_Xpath, String column_Xpath, String CompareValue ) {
		
	
			
			Compare_table_column(beforexpath, afterxpath, afterxpath2, Row_Xpath, column_Xpath, CompareValue);
		
		
	}
	
	
	public void SelectMonth() {
		
	
		
		
	}
	
	
	public void SelectDate() {
		
	
		
		
	}
	
	

	// ################################################## DropDownHelper
	// #####################################################################################

	public void SelectUsingVisibleValue(WebElement element, String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		Log.info("Locator : " + element + " Value : " + visibleValue);
	}

	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		Log.info("WebELement : " + element + " Value : " + value);
		return value;
	}

	public void SelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		Log.info("Locator : " + element + " Value : " + index);
	}

	public void compareStringValue(WebElement element , String CompareWithElement) {
		
		
		if(element.getText().contentEquals(CompareWithElement))
		{
			System.out.println("Element Text :: "+element.getText()+" matched Perfectly" );
		
		}else {
			System.out.println("Result Faild Text not Match");
			
		}
		
		
	}
	
public void CompareStringValue(String CompareValue1 , String CompareValue2) {
		
		
		if(CompareValue1.contentEquals(CompareValue2))
		{
			System.out.println("Element Text :: "+CompareValue1+" matched Perfectly\n" );
		
		}else {
			System.out.println("Result Faild Text not Match");
			
		}
		
		
	}
	
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();

		for (WebElement element : elementList) {
			Log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}

	public void DropdownSelectandClick(WebElement ddm, String ListXpath, String compare) throws InterruptedException {

		new WebDriverWait(driver, 10);
		Thread.sleep(1000);
		Actions action = new Actions(driver);

		action.click(ddm).build().perform();
		WebElement wait = driver.findElement(By.xpath(ListXpath));
		//waitForElementVisible(wait, 30, 5);
		highlight(ddm, driver);
		//retryingFindClick((By) ddm);

		//ddm.click();
		System.out.println("User Clicked on Dropdown button");

		List<WebElement> list = driver.findElements(By.xpath(ListXpath));

		//System.out.println("Dropdown size= " + list.size());
		a: for (int i = 0; i <= list.size(); i++) {

			// System.out.println(list.get(i).getText());

			// wait.until(ExpectedConditions.elementToBeClickable(list.get(i)));

			String data = list.get(i).getText().trim();
			WebElement element = list.get(i);
			//System.out.println("element :: " + element);
			System.out.println("Comparing String ::" + data);
			System.out.println("Compare with ::" + compare);

			if (data.contentEquals(compare)) {
				System.out.println("Dropdown selected :: " + data);
					
				// MasterHelper.retryingFindClick(By.xpath(ListXpath));
				highlight(list.get(i), driver);
				action.click(list.get(i)).perform();
				break a;

			}

		}

	}

	public void DropdownSelectandClick(String ListXpath, String compare) throws InterruptedException {

		List<WebElement> list = driver.findElements(By.xpath(ListXpath));

		System.out.println("size= " + list.size());
		c: for (int i = 0; i < list.size(); i++) {
			if (i == list.size()) {
				break c;
			}
		//	System.out.println(list.get(i).getText());

			String data = list.get(i).getText();
			if (data.equals(compare)) {
				System.out.println("Dropdown selected :: " + data);	
				Thread.sleep(1000);
				clickElementByJS(list.get(i), driver); 
				//list.get(i).click();
				break;

			}

		}

	}

	public void DisplayList(String ListXpath) {

		List<WebElement> list = driver.findElements(By.xpath(ListXpath));

		System.out.println("size= " + list.size());
		c: for (int i = 0; i <= list.size(); i++) {
			if (i == list.size()) {
				break c;
			}
			System.out.println(list.get(i).getText());
		}

	}

	public void DropdownSelectandMove(String ListXpath, String compare) {
		Actions actions = new Actions(driver);
		List<WebElement> list = driver.findElements(By.xpath(ListXpath));
		System.out.println("size= " + list.size());
		c: for (int i = 0; i <= list.size(); i++) {
			if (i == list.size()) {
				break c;
			}
			System.out.println(list.get(i).getText());
			String data = list.get(i).getText();
			if (data.equals(compare)) {
				System.out.println("Dropdown selected :: " + data);
				actions.moveToElement(list.get(i)).perform();
				// list.get(i).click();
				break;
			}
		}

	}

	public List<String> SortString_to_List(String sort) {

		// Remove whitespace and split by comma
		List<String> StringList = Arrays.asList(sort.split("\\s*,\\s*"));

		return StringList;
	}

	public void Dropdown_Select_MultipleOptions(WebElement ddm, String ListXpath, String compare) {

		Actions action = new Actions(driver);
		action.click(ddm).perform();

		System.out.println("User Clicked on Dropdown button");

		List<String> Selectoption = SortString_to_List(compare);

		List<WebElement> list = driver.findElements(By.xpath(ListXpath));

		System.out.println("Dropdown weblist size= " + list.size());

		System.out.println("Property list = " + Selectoption.size());

		for (int j = 0; j <Selectoption.size(); j++) {

			if (j >= Selectoption.size()) {
				break;
			}

			// System.out.println("j = " + j);

			for (int i = 0; i <list.size(); i++) {
				// System.out.println("i= " + i);

				// System.out.println(list.get(i).getText());

				String data = list.get(i).getText();

				// System.out.println("Index j = " + Selectoption.get(j));

				String object = Selectoption.get(j);

				if (data.equals(object)) {
					System.out.println("Dropdown selected :: " + data);
					list.get(i).click();
					break;
				}

			}

		}

	}

	public void Get_Tabledata(String beforexpath, String afterxpath, String afterxpath2, String Row_Xpath,
		String Begain_ColXpath, String End_ColXpath) {
		System.out.println("################### All TABLE DATA #####################");
		List<WebElement> row = (List<WebElement>) driver.findElements(By.xpath(Row_Xpath));
		System.out.println("Total Row:: " + row.size());

		br: for (int i = 1; i <= row.size(); i++) {
			String column_Xpath = Begain_ColXpath + i + End_ColXpath;
			List<WebElement> column = (List<WebElement>) driver.findElements(By.xpath(column_Xpath));
			System.out.println("Total column:: " + column.size());

			cr: for (int j = 1; j <= column.size(); j++) {

				String ActualXpath = beforexpath + i + afterxpath + j + afterxpath2;
				WebElement ele = driver.findElement(By.xpath(ActualXpath));
				System.out.print(" \t" + ele.getText());

			}
			i = i + 1;
			System.out.println("");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");

			if (i == row.size()) {
				break br;
			}
		}

	}

	/// Below Code is Working for table iteration and click

	@SuppressWarnings("unused")
	public void Compare_table_column(String beforexpath, String afterxpath, String afterxpath2, String Row_Xpath,String column_Xpath, String CompareValue) {

		System.out.println("Started");
		boolean flag = false;

		List<WebElement> row = (List<WebElement>) driver.findElements(By.xpath(Row_Xpath));
		System.out.println("Total Row:: " + row.size());
		List<WebElement> column = (List<WebElement>) driver.findElements(By.xpath(column_Xpath));
		System.out.println("Total column:: " + column.size());

		// List<WebElement> column = (List<WebElement>)
		// driver.findElement(By.xpath("column_header_Xpath")); //
		// mat-header-row/mat-header-cell[1]
		br: for (int i = 1; i <= row.size(); i++) {
			
			cr: for (int j = 1; j <= column.size(); j++) {

				String ActualXpath = beforexpath + i + afterxpath + j + afterxpath2;
				WebElement ele = driver.findElement(By.xpath(ActualXpath));

				// String ActualheaderXpath = column_header_Xpath1 + j + column_header_Xpath2;
				// WebElement header = driver.findElement(By.xpath(ActualheaderXpath));
				// System.out.println(ele.getText());
				if (ele.getText().contentEquals(CompareValue)) {
					// System.out.println(i+")TableRow where " + header.getText()+" Column contain
					// value:: " + ele.getText());
					System.out.println("Element Value Match:: " + ele.getText());
					ele.click();
					System.out.println("Clicked on Elelment");
					flag = true;
					break br;

				}
				if (flag == true) {
					break;
				}

			}
		}

	}

	//(String beforexpath, String afterxpath, String afterxpath2, String Row_Xpath,
//	String column_Xpath, String CompareValue)
	
	
	//String Month = month.toUpperCase();

	@SuppressWarnings("unused")
	public void Calender(String beforexpath, String afterxpath,String afterxpath1,String afterxpath2, String Row_Xpath,	String CompareValue) throws InterruptedException {

		System.out.println("Started");
		boolean flag = false;

		List<WebElement> row = (List<WebElement>) driver.findElements(By.xpath(Row_Xpath));
		//System.out.println("Total Row:: " + row.size());
			
		br: for (int i = 1; i <= row.size(); i++) {
			String ColumnXpath = beforexpath + i+ afterxpath;
			List<WebElement> column = (List<WebElement>) driver.findElements(By.xpath(ColumnXpath));
			//System.out.println("Total column:: " + column.size());
			cr: for (int j = 1; j <= column.size(); j++) {
				String ActualXpath = beforexpath + i + afterxpath+ afterxpath1+ j + afterxpath2;
				//System.out.println("Actual Xpath::: "+ ActualXpath);
				WebElement ele = driver.findElement(By.xpath(ActualXpath));
				if (ele.getText().contentEquals(CompareValue)) {
					//System.out.println("Element Value Match:: " + ele.getText());
					System.out.println("Selected ::  " + ele.getText());
					ele.click();
					Thread.sleep(1000);
					flag = true;
					break br;
				}
				if (flag == true) {
					break;
				}

			}
		}
	}
	
	
	
	public void Direct_GetTable() {

		String Row_Xpath = "//mat-row[@role='row']";
		String Begain_ColXpath = "//mat-row[@role='row'][";
		String End_ColXpath = "]/mat-cell";	
		String beforexpath = "//mat-row[@role='row'][";
		String afterxpath = "]/mat-cell[";
		String afterxpath2 = "]";

		System.out.println("################### All TABLE DATA #####################");
		List<WebElement> row = (List<WebElement>) driver.findElements(By.xpath(Row_Xpath));
		System.out.println("Total Row:: " + row.size());

		br: for (int i = 1; i <= row.size(); i++) {
			String column_Xpath = Begain_ColXpath + i + End_ColXpath;
			List<WebElement> column = (List<WebElement>) driver.findElements(By.xpath(column_Xpath));
			System.out.println("Total column:: " + column.size());

			cr: for (int j = 1; j <= column.size(); j++) {

				String ActualXpath = beforexpath + i + afterxpath + j + afterxpath2;
				WebElement ele = driver.findElement(By.xpath(ActualXpath));
				System.out.print(" \t" + ele.getText());

			}
			System.out.println("");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");

			if (i == row.size()) {
				break br;
			}
		}
	}

	public void DropDownValueSelector(String beforexpath, String afterxpath, String TableXpath, String CompareValue) {

		System.out.println("Started");
		boolean flag = false;

		List<WebElement> row = (List<WebElement>) driver.findElements(By.xpath(TableXpath));
		System.out.println("Total Row:: " + row.size());

		br: for (int i = 1; i <= row.size(); i++) {

			String ActualXpath = beforexpath + i + afterxpath;

			WebElement ele = driver.findElement(By.xpath(ActualXpath));

			if (ele.getText().contentEquals(CompareValue)) {
				// value:: " + ele.getText());
				System.out.println("Element Value Match:: " + ele.getText());
				ele.click();
				System.out.println("Clicked on Elelment");
				flag = true;
				break br;

			}
			if (flag == true) {
				break;
			}

		}
	}

	public void Listvalidation(String ListXpath, String compare1) {

		List<String> Mainlist = SortString_to_List(compare1);

		List<WebElement> Weblist = driver.findElements(By.xpath(ListXpath));

		System.out.println("compare  list = " + Mainlist.size());
		c: for (int j = 0; j <= Mainlist.size(); j++) {
			if (j == Mainlist.size()) {
				break c;
			}
			b: for (int i = 0; i <= Weblist.size(); i++) {
				if (i == Weblist.size()) {
					break b;
				}

				String data = Weblist.get(i).getText();

				String object = Mainlist.get(j);
				if (data.equals(object)) {
					System.out.println("Tab matched :: " + data);
					break;
				}
//				else {
//					System.out.println("Not Matched");
//				}
			}
		}
	}

	
	public void DashProjectList(String PListXpath) {
		// List<String> Plist = SortString_to_List(compare1);

		List<WebElement> list = driver.findElements(By.xpath(PListXpath));
		System.out.println("Dashboard Project List = " + list.size());
		System.out.println("***********Project List***********\n");
		a: for (int i = 0; i <= list.size(); i++) {
			if (i == list.size()) {
				break a;
			}
			String data = list.get(i).getText();
			System.out.println(data);
		}
	}

	public void DropdownSelectandVerify(String ListXpath, String compare) {
		List<WebElement> list = driver.findElements(By.xpath(ListXpath));
		c: for (int i = 0; i <= list.size(); i++) {
			if (i == list.size()) {
				break c;
			}
			String data = list.get(i).getText();
			if (data.equals(compare)) {
				Assert.assertEquals(data, compare);
				System.out.println("Mached Data");

			} else {
				Assert.assertNotSame(data, compare);
				System.out.println("Unmatched data");

			}

		}
	}

	public void DashProjectVerify(String PListXpath, String Compare) {
		List<WebElement> list = driver.findElements(By.xpath(PListXpath));
		a: for (int i = 0; i <= list.size(); i++) {
			if (i == list.size()) {
				break a;
			}
			String data = list.get(i).getText();
			if (data.equals(Compare)) {
				Assert.assertEquals(data, Compare);
				System.out.println("same project name");
			} else {
				Assert.assertNotSame(data, Compare);
				System.out.println("Project name was not same");

			}
		}
	}

	public void ProjectAdminTabsvalidation(String TabsXpath, String compare1) {

		List<String> Tabslist = SortString_to_List(compare1);
		List<WebElement> list = driver.findElements(By.xpath(TabsXpath));

		System.out.println("compare Admin tab list = " + Tabslist.size());
		c: for (int j = 0; j <= Tabslist.size(); j++) {
			if (j == Tabslist.size()) {
				break c;
			}
			b: for (int i = 0; i <= list.size(); i++) {
				if (i == list.size()) {
					break b;
				}
				String data = list.get(i).getText();

				String object = Tabslist.get(j);
				if (data.equals(object)) {
					System.out.println("Tab matched :: " + data);
					break;
				}
			}
		}
	}

	// ################################################## GenricHelper
	// #####################################################################################

	public String readValueFromElement(WebElement element) {

		if (null == element) {
			Log.info("weblement is null");
			return null;
		}

		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			Log.error(e);
			return null;
		}

		if (!displayed)
			return null;
		String text = element.getText();
		Log.info("weblement valus is.." + text);
		return text;
	}

	public String readValueFromInput(WebElement element) {
		if (null == element)
			return null;
		if (!isDisplayed(element))
			return null;
		String value = element.getAttribute("value");
		Log.info("weblement valus is.." + value);
		return value;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			Log.info("element is displayed.." + element);
			return true;
		} catch (Exception e) {
			Log.info(e);
			return false;
		}
	}

	protected boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			Log.info("element is displayed.." + element);
			return false;
		} catch (Exception e) {
			Log.error(e);
			return true;
		}
	}

	protected String getDisplayText(WebElement element) {
		if (null == element)
			return null;
		if (!isDisplayed(element))
			return null;
		return element.getText();
	}

	public static synchronized String getElementText(WebElement element) {
		if (null == element) {
			Log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
		} catch (Exception ex) {
			Log.info("Element not found " + ex);
		}
		return elementText;
	}

	public static void scrollToElementAndClick(WebDriver driver, WebElement webelement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(webelement).click().perform();
	}

	public static void scrollToOneElementtoAnotherandClick(WebDriver driver, WebElement webelement1,
			WebElement webelement2) {
		Actions actions = new Actions(driver);
		actions.moveToElement(webelement1).click().moveToElement(webelement2).click().build().perform();
	}
	
	public static void scrollToOneElementHoverandClickonAnother(WebDriver driver, WebElement webelement1,
			WebElement webelement2) {
		Actions actions = new Actions(driver);
		actions.moveToElement(webelement1).moveToElement(webelement2).click().build().perform();
	}
	
	

	public static void HoverText(WebDriver driver, WebElement webelement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(webelement).perform();
	}

	public static void SendKey(WebDriver driver, WebElement webelement, String Text) {
		Actions actions = new Actions(driver);
		actions.moveToElement(webelement).click().sendKeys("Text").build().perform();
	}

	public boolean AssertEquals(WebElement Alert_Message_PopupElement, String expect) {
		try {

			String Actual_Message = Alert_Message_PopupElement.getText();
			Assert.assertEquals(Actual_Message, expect);
			System.out.println("Assertion Passed for Message:: " + Actual_Message);
			return true;
		} catch (Exception e) {
			// Ignore
			System.out.println("" + e);
			return false;
		}

	}

	public boolean AssertAlert_Message(String expected_AlertMessage) {
		try {

			WebElement Alert_Message_PopupElement = driver
					.findElement(By.xpath("//html/body/div/div/div/snack-bar-container/simple-snack-bar/span"));
			waitForElement(driver, Alert_Message_PopupElement, 10);
			String Actual_Message = Alert_Message_PopupElement.getText();
			System.out.println("WebElement Text:: " + Actual_Message);
			System.out.println("Expected:: " + expected_AlertMessage);
			Assert.assertEquals(Actual_Message, expected_AlertMessage);
			System.out.println("Assertion Passed for Message:: " + Actual_Message);
			Thread.sleep(10000);
			return true;
		} catch (Exception e) {
			// Ignore
			System.out.println("" + e);
			return false;
		}

	}

	// ################################################## JavaScriptHelper
	// #####################################################################################

	public Object executeScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Log.info(script);
		return js.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Log.info(script);
		return js.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		Log.info(element);
	}

	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
		Log.info(element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		Log.info(element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		Log.info(element);
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	public void scrollUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}

	public void ZoomInBypercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}
	
	 public void highlight(WebElement element, WebDriver driver) {
		  flash(element, driver);
		 drawBorder(element, driver);
	    }
	public static void flash(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgcolor  = element.getCssValue("backgroundColor");
        for (int i = 0; i <  10; i++) {
            changeColor("rgb(0,200,0)", element,driver);//1
            changeColor(bgcolor, element,driver);//2
        }
    }
    public static void changeColor(String color, WebElement element, WebDriver driver) {
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '"+color+"'",  element);

        try {
            Thread.sleep(20);
        }  catch (InterruptedException e) {
        }
     }
	
    public void drawBorder(WebElement element, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
    
    public static void generateAlert(WebDriver driver, String message) throws InterruptedException{
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("alert('"+message+"')");
    	

    }
    
    public static void clickElementByJS(WebElement element, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].click();", element);
    
    	
    }
	
    public static void refreshBrowserByJS(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("history.go(0)");
    }
    
    public static String getTitleByJS(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	String title = js.executeScript("return document.title;").toString();
    	return title;
    }
    
    public static String getPageInnerText(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	String pageText = js.executeScript("return document.documentElement.innerText;").toString();
    	return pageText;
    }
    
    public static void scrollPageDown(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    
    public static void scrollIntoView(WebElement element, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
	
    public String  jsGetText (String Attribute, String Value)
    {
    	JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

		String result = (String) javascriptExecutor
				.executeScript("return document.querySelectorAll('[" + Attribute + "=" + Value + "]')[0].value;");
	
		System.out.println("ElementText:: "+ result);
		return result;
    }
			
  
   
		
	public boolean getTextandCompare(String Attribute, String Value , String Compare) {

		boolean flag = false;
		if (driver instanceof JavascriptExecutor) {
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

			String result = (String) javascriptExecutor
					.executeScript("return document.querySelectorAll('[" + Attribute + "=" + Value + "]')[0].value;");
		
			System.out.println("ElementText:: "+ result);
			
			if (result.contentEquals(Compare))
			{
				
				flag = true;
				
				System.out.println("Element Matched");
				System.out.println("----------------------------------------------------\n");
			}
			else {
				
				System.out.println("Element not Matched");
				System.out.println("----------------------------------------------------\n");
			}
			
			
			return flag;

		}
		return  flag;

	}

	// ################################################## NavigationHelper// #####################################################################################

	public void navigateTo(String url) {
		Log.info(url);
		driver.get(url);
	}

	public void naviagteTo(URL url) {
		Log.info(url.getPath());
		driver.get(url.getPath());
	}

	public String getTitle() {
		String title = driver.getTitle();
		Log.info(title);
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		Log.info(url);
		return driver.getCurrentUrl();
	}

	// ################################################## ResourceHelper// #####################################################################################

	public static String getResourcePath(String resource) {
		String path = getBaseResourcePath() + resource;
		return path;
	}

	public static String getBaseResourcePath() {
		String path = System.getProperty("user.dir");
		System.out.println(path);
		return path;
	}

	public static InputStream getResourcePathInputStream(String path) throws FileNotFoundException {
		return new FileInputStream(MasterHelper.getResourcePath(path));
	}

	public static void main(String[] args) throws FileNotFoundException {
		// System.out.println(ResourceHelper.getResourcePath("configfile/"+
		// "config.properties"));

		getBaseResourcePath();
	}

	// ################################################## VerificationHelper// #####################################################################################

	public static synchronized boolean verifyElementPresent(WebElement element) {
		boolean isDispalyed = false;
		try {
			isDispalyed = element.isDisplayed();
			Log.info(element.getText() + " is dispalyed");
		} catch (Exception ex) {
			Log.error("Element not found " + ex);
		}

		return isDispalyed;
	}

	public static synchronized boolean verifyElementNotPresent(WebElement element) {
		boolean isDispalyed = false;
		try {
			isDispalyed=element.isDisplayed();
			Log.info(element.getText() + " is dispalyed");
		} catch (Exception ex) {
			Log.error("Element not found " + ex);
			isDispalyed = true;
		}

		return isDispalyed;
	}

	public static synchronized boolean verifyTextEquals(WebElement element, String expectedText) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if (actualText.equals(expectedText)) {
				Log.info("actualText is :" + actualText + " expected text is: " + expectedText);
				return flag = true;
			} else {
				Log.error("actualText is :" + actualText + " expected text is wrong: " + expectedText);
				return flag;
			}
		} catch (Exception ex) {
			Log.error("actualText is :" + element.getText() + " expected text is: " + expectedText);
			Log.info("text not matching" + ex);
			return flag;
		}
	}
	
	

	public WebElement getElement(By locator) {
		Log.info(locator);
		if (IsElementPresentQuick(locator))
			return driver.findElement(locator);

		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			Log.error(re);
			throw re;
		}
	}

	/**
	 * Check for element is present based on locator If the element is present
	 * return the web element otherwise null
	 * 
	 * @param locator
	 * @return WebElement or null
	 */

	public WebElement getElementWithNull(By locator) {
		Log.info(locator);
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	public boolean IsElementPresentQuick(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		Log.info(flag);
		return flag;
	}

	// ################################################## WaitHelperHelper #####################################################################################

	public void setImplicitWait(long timeout, TimeUnit unit) {
		Log.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	@SuppressWarnings("deprecation")
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Log.debug("");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		Log.info(locator);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public void waitForElement(WebDriver driver, WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		Log.info("element found..." + element.getText());
	}

	public WebElement waitForElement(WebDriver driver, long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void RoboFileupload(String upload_Path) throws Exception {
		Robot robo = new Robot();
	//	Opn_window.click();
		Thread.sleep(2000);
		System.out.println("UloadPATH:: " + upload_Path);
		robo.setAutoDelay(2000);
		StringSelection SS = new StringSelection(upload_Path);
		System.out.println("PATH:: " + SS);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(SS, null);// This will select string
		robo.setAutoDelay(2000);

		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);

		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);

		robo.setAutoDelay(1000);

		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);

	}

	
	// ################################################## PageHelper #####################################################################################
	
	public void drawScopebox(WebElement element,String x,String y ) throws Exception {
		
		  Actions act = new Actions(driver);   
		  x.trim();
		  y.trim();
		  int a  = Integer.parseInt(x);
		  int b  = Integer.parseInt(y);
		  
		    act.moveToElement(element,a,b).click().build().perform();
		      Thread.sleep(2000);
		  
			}
	
	
	
	
	  
	public void user_Select_NevTab(String TabName) throws Throwable {
		String TabList = "//app-left-menu-navigation/mat-nav-list/a";
		DropdownSelectandClick(TabList, TabName);
	
	
	}
	 
    public boolean RadioBtn(WebElement element) {
    	
    	boolean flag = true;
    	String value = "radio-button mat-radio-button mat-accent mat-radio-checked";
    	String name = element.getAttribute("Class");
    	   
    		if (value.contentEquals(name))
    		{
    			flag = false;
    		}
    		
		return flag;
      }
	
    
    
    public boolean Checkbox(WebElement element) {
    	
    	boolean flag = true;
    	String value = "mat-checkbox mat-accent mat-checkbox-checked";
    	String name = element.getAttribute("Class");
    	   
    		if (value.contentEquals(name))
    		{
    			flag = false;
    		}
    		
		return flag;
      }
	
    
    
    
}

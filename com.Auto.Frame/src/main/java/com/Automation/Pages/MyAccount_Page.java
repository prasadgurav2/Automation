package com.Automation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Automation.Helper.MasterHelper;
import com.Automation.Util.TestBase;

public class MyAccount_Page extends TestBase {

	MasterHelper mh = new MasterHelper(driver);

	@FindBy(xpath = "//h1[contains(text(),'My account')]")
	WebElement MyAccountHeader;

	@FindBy(xpath = "(//span)[1]")
	WebElement MyAccountName;

	@FindBy(xpath = "//span[contains(text(),'Order history and details')]")
	WebElement MyOrderHistory;

	public MyAccount_Page() {

		PageFactory.initElements(driver, this);
	}

	public void user_can_able_to_succesfully_signedin() {
		if (MyAccountHeader.isDisplayed()) {
			mh.highlight(MyAccountHeader, driver);
			System.out.println("User Successfully Logged In");
		} else {
			System.out.println("XXXXX User Is not signed in XXXXX");
		}

	}
	public void user_click_on_Profile() {
		mh.scrollToElemetAndClick(MyAccountName);
		mh.highlight(MyAccountName, driver);
	}

	public void user_click_on_Order_History_and_Detail() {
		mh.highlight(MyOrderHistory, driver);
		mh.scrollToElemetAndClick(MyOrderHistory);
		
	}

}

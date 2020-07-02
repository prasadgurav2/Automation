package com.Automation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Automation.Helper.MasterHelper;
import com.Automation.Util.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStore_LoginPage extends TestBase {

	MasterHelper mh = new MasterHelper(driver);

	@FindBy(xpath = "//input[@name='email_create']")
	WebElement Emailid;

	@FindBy(xpath = "//input[@name='email']")
	WebElement Signin_Emailid;

	@FindBy(xpath = "//input[@name='passwd']")
	WebElement Signin_Password;

	@FindBy(xpath = "//button[@name='SubmitLogin']")
	WebElement Signin_btn;

	@FindBy(xpath = "//div/a[contains(text(),'Sign in')]")
	WebElement SignIn_Tab;

	@FindBy(xpath = "//h1[contains (text(),'Authentication')]")
	WebElement Authentication;

	public MyStore_LoginPage() {

		PageFactory.initElements(driver, this);

	}

	public void user_is_on_loginPage() {
		mh.isDisplayed(Authentication);
		mh.highlight(Authentication, driver);
		System.out.println("User is on Login Page");
	}

	@SuppressWarnings("static-access")
	public void Then_User_Click_on_Signin() {
		mh.scrollToElementAndClick(driver, SignIn_Tab);
		mh.highlight(SignIn_Tab, driver);
	}

	public void user_enter_email_address() {

		Emailid.sendKeys(prop.getProperty("Create_Email_Id"));
		mh.highlight(Emailid, driver);

	}

	public void enter_valid_Emailid() {

		mh.highlight(Signin_Emailid, driver);
		Signin_Emailid.sendKeys(prop.getProperty("Email_Id"));

	}

	public void enter_valid_Password() {
		mh.highlight(Signin_Password, driver);
		Signin_Password.sendKeys(prop.getProperty("Password"));

	}

	public void user_Click_on_Sign_in_button() {
		mh.highlight(Signin_btn, driver);
		Signin_btn.click();
	}

	
}

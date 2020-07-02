package com.Automation.Pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Automation.Helper.LoggerHelper;
import com.Automation.Helper.MasterHelper;
import com.Automation.Util.TestBase;

public class CreateUserPage extends TestBase {

	MasterHelper mh = new MasterHelper(driver);
	Logger Log = LoggerHelper.getLogger(CreateUserPage.class);

	@FindBy(xpath = "//h1[contains(text(),'Create an account')]")
	WebElement CreateanAccountName;

	@FindBy(xpath = "//label[@class='top']")
	List<WebElement> lable;

	@FindBy(xpath = "//input[@id='customer_firstname']")
	WebElement firstname;

	@FindBy(xpath = "//input[@id='customer_lastname']")
	WebElement lastname;

	@FindBy(xpath = "//input[@id='passwd']")
	WebElement passward;

	@FindBy(xpath = "//select[@id='days']")
	WebElement DayBtn;

	@FindBy(xpath = "//select[@id='months']")
	WebElement monthsBtn;

	@FindBy(xpath = "//select[@id='years']")
	WebElement yearsBtn;

	@FindBy(xpath = "//input[@name='company']")
	WebElement companyName;

	@FindBy(xpath = "//input[@name='address1']")
	WebElement address1;

	@FindBy(xpath = "//input[@name='address2']")
	WebElement address2;

	@FindBy(xpath = "//input[@name='city']")
	WebElement city;

	@FindBy(xpath = "//select[@name='id_state']")
	WebElement State;

	@FindBy(xpath = "//select[@name='id_country']")
	WebElement Country;

	@FindBy(xpath = "//input[@name='postcode']")
	WebElement postCode;

	@FindBy(xpath = "//input[@name='phone_mobile']")
	WebElement mobileNumer;

	@FindBy(xpath = "//input[@name='alias']")
	WebElement alias;

	@FindBy(xpath = "//textarea")
	WebElement additional_Information;

	@FindBy(xpath = "//button/span[contains(text(),'Register')]")
	WebElement Register_btn;

	@FindBy(xpath = "//h3[contains (text(),'Create an account')]")
	WebElement Create_an_account;

	@FindBy(xpath = "(//button/span)[2]")
	WebElement Button_Create_an_account;

	

	public CreateUserPage() {

		PageFactory.initElements(driver, this);

	}

	public void user_can_able_to_view_Create_An_Account() {

		mh.isDisplayed(Create_an_account);
		mh.highlight(Create_an_account, driver);
	}

	public void Click_on_Create_an_Account() {
		mh.highlight(Button_Create_an_account, driver);
		Button_Create_an_account.click();
	}

	

	public void user_should_navigated_to_account_creation_Page() {
		mh.isDisplayed(CreateanAccountName);
		Log.info(mh.isDisplayed(CreateanAccountName));
		Log.info("User Is On Create login Page");
		System.out.println("User Is On Create login Page");

	}

	public void Click_title_Radio_Button() {
		boolean is_selected = lable.get(0).isSelected();
		if (is_selected == true) {
			lable.get(1).click();

			System.out.println("Mrs. Radio button Selected");
		} else {
			lable.get(0).click();
			System.out.println("Mr. Radio button Selected");
		}
	}

	public void Enter_First_name() {

		mh.isDisplayed(firstname);
		firstname.sendKeys(prop.getProperty("FirstName"));
	}

	public void Enter_Last_name() {

		mh.isDisplayed(lastname);
		lastname.sendKeys(prop.getProperty("LastName"));

	}

	public void Enter_Password() {

		mh.isDisplayed(lastname);
		passward.sendKeys(prop.getProperty("Password"));
	}

	public void Enter_DateOfBirth() throws InterruptedException {

		String[] dob = prop.getProperty("DateOfBirth").split(" ");
		String day = dob[0].toString().trim();
		String month = dob[1].toString().trim();
		String year = dob[2].toString().trim();

		DayBtn.sendKeys(day);
		monthsBtn.sendKeys(month);
		yearsBtn.sendKeys(year);
	}

	public void Enter_ComapnyName() {
		if (true == companyName.isDisplayed()) {
			companyName.sendKeys(prop.getProperty("CompanyName"));
		}
	}

	public void Enter_Address() {

		mh.isDisplayed(address1);
		address1.sendKeys(prop.getProperty("Address1"));
		mh.isDisplayed(address2);
		address2.sendKeys(prop.getProperty("Address2"));

	}

	public void Enter_City() {

		city.sendKeys(prop.getProperty("City"));

	}

	public void Enter_State() {

		State.sendKeys(prop.getProperty("State"));

	}

	public void Enter_PostalCode() {

		postCode.sendKeys(prop.getProperty("ZipCode"));

	}

	public void additional_Information() {

		additional_Information.sendKeys(prop.getProperty("Additional_Information"));
	}

	public void Enter_mobile() {

		mobileNumer.sendKeys(prop.getProperty("Mobile_phone"));
	}

	public boolean checkMandatory() {
		boolean flag = true;

		if (true == firstname.getText().toString().isEmpty()) {
			flag = false;
		} else if (true == lastname.getText().toString().isEmpty()) {
			flag = false;
		} else if (true == passward.getText().toString().isEmpty()) {
			flag = false;
		}else if (true == address1.getText().toString().isEmpty()) {
			flag = false;
		}else if (true == city.getText().toString().isEmpty()) {
			flag = false;
		}else if (true == State.getText().toString().isEmpty()) {
			flag = false;
		}else if (true == postCode.getText().toString().isEmpty()) {
			flag = false;
		}else if (true == Country.getText().toString().isEmpty()) {
			flag = false;
		}else if (true == mobileNumer.getText().toString().isEmpty()) {
			flag = false;
		}
		return flag;

	}

	public void User_fill_all_Personal_information_details() throws InterruptedException {
		Click_title_Radio_Button();
		Enter_First_name();
		Enter_Last_name();
		Enter_Password();
		Enter_DateOfBirth();
		Enter_ComapnyName();
		Enter_Address();
		Enter_City();
		Enter_State();
		Enter_PostalCode();
		additional_Information();
		Enter_mobile();

	}

	public void click_on_Register_button() {

		if (checkMandatory() == true) {
			Register_btn.click();

		}

	}

}

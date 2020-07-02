package com.Automation.StepDef;

import com.Automation.Pages.MyAccount_Page;
import com.Automation.Pages.MyStore_LoginPage;
import com.Automation.Util.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStore_Login_StepDef extends TestBase {

	MyStore_LoginPage lp = new MyStore_LoginPage();
	MyAccount_Page myaccount = new MyAccount_Page();

	@Then("^User is on loginPage$")
	public void user_is_on_loginPage() {
		lp.user_is_on_loginPage();
	}

	@Given("^User Click on Signin$")
	public void Then_User_Click_on_Signin() {
		lp.Then_User_Click_on_Signin();
	}

	@Then("^User enter email address$")
	public void user_enter_email_address() {

		lp.user_enter_email_address();
	}
	
	@Then("^Enter valid Emailid$")
	public void enter_valid_Emailid() {
  
		lp.enter_valid_Emailid();
	}

	@Then("^Enter valid Password$")
	public void enter_valid_Password() {
	   
		lp.enter_valid_Password();
	}

	@When("^User Click on Sign in button$")
	public void user_Click_on_Sign_in_button() {
		lp.user_Click_on_Sign_in_button();
	}

	@Then("^User can able to succesfully signedin$")
	public void user_can_able_to_succesfully_signedin()  {
		myaccount.user_can_able_to_succesfully_signedin();
	   
	}
	
	
	

}

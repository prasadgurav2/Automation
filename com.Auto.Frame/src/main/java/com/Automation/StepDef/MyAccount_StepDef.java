package com.Automation.StepDef;

import com.Automation.Pages.MyAccount_Page;

import cucumber.api.java.en.Given;

public class MyAccount_StepDef {

	MyAccount_Page ac = new MyAccount_Page();

	@Given("^User click on Profile$")
	public void user_click_on_Profile() {

		ac.user_click_on_Profile();
	}

	@Given("^User click on Order History and Detail$")
	public void user_click_on_Order_History_and_Detail() {
		ac.user_click_on_Order_History_and_Detail();

	}

}

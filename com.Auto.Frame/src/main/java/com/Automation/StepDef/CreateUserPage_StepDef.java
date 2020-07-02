package com.Automation.StepDef;

import com.Automation.Pages.CreateUserPage;
import cucumber.api.java.en.Then;

public class CreateUserPage_StepDef {

	CreateUserPage cup = new CreateUserPage();

	@Then("^User can able to view 'Create An Account'$")
	public void user_can_able_to_view_Create_An_Account() throws Throwable {

		cup.user_can_able_to_view_Create_An_Account();
	}

	@Then("^Click on Create an Account$")
	public void Click_on_Create_an_Account() {

		cup.Click_on_Create_an_Account();
	}

	@Then("^User should navigated to account-creation Page$")
	public void User_should_navigated_to_account_creation_Page() {
		cup.user_should_navigated_to_account_creation_Page();
	}

	@Then("^User fill all Personal information details$")
	public void User_fill_all_Personal_information_details() throws InterruptedException {
		cup.User_fill_all_Personal_information_details();
	}

	@Then("^click on Register button$")
	public void click_on_Register_button() {
		cup.click_on_Register_button();
	}

}

package com.Automation.StepDef;

import com.Automation.Pages.OrderHistory_Page;
import com.Automation.Util.TestBase;

import cucumber.api.java.en.Then;

public class OrderHistory_StepDef extends TestBase {

	
	OrderHistory_Page oh = new OrderHistory_Page();
			
	
	@Then("^Order history get Open$")
	public void order_history_get_Open() {
		oh.order_history_get_Open();
	}
	
	@Then("^User can able to verify the amount under Order Summary$")
	public void User_can_able_to_verify_the_amount_under_Order_Summary() {
	 		oh.user_can_able_to_verify_the_amount_under_Order_Summary();
	}
	
	
}

package com.Automation.StepDef;

import com.Automation.Helper.MasterHelper;
import com.Automation.Pages.WomentSection_Page;
import com.Automation.Util.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class WomenSection_StepDef extends TestBase {

	MasterHelper mh = new MasterHelper(driver);
	WomentSection_Page wp = new WomentSection_Page();
	
	
	@Given("^User navigate to Women Section and select Tshirt$")
	public void user_navigate_to_Women_Section_and_select_Tshirt() throws Throwable {
		wp.user_navigate_to_Women_Section_and_select_Tshirt();
		
	}
	
	@Then("^User can able to see All Tshirt$")
	public void user_can_able_to_see_All_Tshirt() throws Throwable {
	    
	   wp.user_can_able_to_see_All_Tshirt();
	   
	}

	@Then("^User move cursor to Quick view for one of the Faded Short Sleeve T-shirts$")
	public void user_move_cursor_to_Quick_view_for_one_of_the_Faded_Short_Sleeve_T_shirts() throws Throwable {
	    
		wp.user_move_cursor_to_Quick_view_for_one_of_the_Faded_Short_Sleeve_T_shirts();
	}

	@Then("^Popup window with Tshirt detail got open$")
	public void popup_window_with_Tshirt_detail_got_open() throws Throwable {
		wp.popup_window_with_Tshirt_detail_got_open();
	   
	}

	@Then("^User Choose two Quantity for same Product$")
	public void user_Choose_two_Quantity_for_same_Product() throws Throwable {
		wp.user_Choose_two_Quantity_for_same_Product();
		   
	}

	@Then("^User Click on Add to Cart$")
	public void user_Click_on_Add_to_Cart() throws Throwable {
		wp.user_Click_on_Add_to_Cart();
	   
	}

	@Then("^User verify amount to be paid by checking Unit price ,Qty,Total,Total Shipping$")
	public void user_verify_amount_to_be_paid_by_checking_Unit_price_Qty_Total_Total_Shipping() throws Throwable {
	    
	   wp.user_verify_amount_to_be_paid_by_checking_Unit_price_Qty_Total_Total_Shipping();
	}
	
	
	@Then("^click on Proceed to Checkout$")
	public void click_on_Proceed_to_Checkout() throws Throwable {
	    
		wp.click_on_Proceed_to_Checkout();
	}

	@Then("^User click on Proceed to Checkout$")
	public void User_click_on_Proceed_to_Checkout() throws Throwable {
	    
		wp.click_on_Proceed_to_Checkout1();
	}
	
	@Then("^Check Address detail$")
	public void Check_Address_detail() throws Throwable {
	   	wp.Check_Address_detail();
	}

	
	@Then("^check shipping detail with amount$")
	public void check_shipping_detail_with_amount() throws Throwable {
	    
		wp.check_shipping_detail_with_amount();
	}

	@Then("^Click on Tearms of service$")
	public void click_on_Tearms_of_service() throws Throwable {
	    
		wp.click_on_Tearms_of_service();
	}

	@Then("^User Choose Payment Meathod$")
	public void user_Choose_Payment_Meathod() throws Throwable {
	    
	   wp.user_Choose_Payment_Meathod();
	}

	@Then("^Click on Pay by bank wire$")
	public void click_on_Pay_by_bank_wire() throws Throwable {
	    
	   
	}

	@Then("^User Confirm Order by clicking confirm my order$")
	public void user_Confirm_Order_by_clicking_confirm_my_order() throws Throwable {
	    
	   
	}

	@Then("^User can able to see Order Summary with order amount, Order Ref get genrated$")
	public void user_can_able_to_see_Order_Summary_with_order_amount_Order_Ref_get_genrated() throws Throwable {
	    
	   
	}

}

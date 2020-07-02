package com.Automation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Automation.Helper.MasterHelper;
import com.Automation.Util.TestBase;

public class OrderHistory_Page extends TestBase {
	
	MasterHelper mh = new MasterHelper(driver);
	
	public static String ProductCost;
	
	@FindBy(xpath = "(//*[contains(text(),'Order history')])[3]")
	WebElement OrderHistoryHeader;
	
	@FindBy(xpath = "(//span[@class='price'])[2]")
	WebElement OrderPrice;
	
	

	
	public OrderHistory_Page() {

		PageFactory.initElements(driver, this);
		

	}
	
	

	public void order_history_get_Open() {
		
		if(true==OrderHistoryHeader.isDisplayed())
		{
			System.out.println("User can see Order History");
		}
		
		else {
			System.out.println("Somthing went Wrong");
		}
		
	}

	@SuppressWarnings("static-access")
	public void user_can_able_to_verify_the_amount_under_Order_Summary() {
		String OrderAmount = WomentSection_Page.ProductCost;
		System.out.println("OrderAmount"+ WomentSection_Page.ProductCost);
		System.out.println("WebEle:: "+ OrderPrice.getText());
		
		if(OrderPrice.getText().equals(OrderAmount))
		{
			System.out.println("TestCase Passed Order Amount Matched");
		}
		else {
			System.out.println(test.fail("Order Amount Not Matched"));
		}
		
	}

}

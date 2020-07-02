package com.Automation.Pages;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Automation.Helper.MasterHelper;
import com.Automation.Helper.ScenarioContext;
import com.Automation.Util.TestBase;

public class WomentSection_Page extends TestBase {

	MasterHelper mh = new MasterHelper(driver);
	ScenarioContext sc = new ScenarioContext();
	public static String ProductCost;

	public String lastWindowHandle = "";

	@FindBy(xpath = "//a[@title='Women']")
	WebElement WomenSection;

	@FindBy(xpath = "(//a[contains(text(),'T-shirts')])[1]")
	WebElement T_Shirt;

	@FindBy(xpath = "//li/div[@class='product-container']")
	WebElement TshirtDisplay;

	@FindBy(xpath = "//img[@title='Faded Short Sleeve T-shirts']")
	WebElement TshirtImage;

	@FindBy(xpath = "//span[contains(text(),'Quick view')]")
	WebElement QuickView;

	@FindBy(xpath = "//i[@class='icon-plus']")
	WebElement PlusIcon;

	@FindBy(xpath = "//*[@class='fancybox-iframe']")
	WebElement IframeQuickView;

	@FindBy(xpath = "//button[@type='submit']/span[contains(text(),'Add to cart')]")
	WebElement AddToCart_Btn;

	@FindBy(xpath = "//span[@id='our_price_display']")
	WebElement ActualProductCost;

	@FindBy(xpath = "//span[@id='layer_cart_product_quantity']")
	WebElement ActualProductQty;

	@FindBy(xpath = "//span[@class='ajax_cart_shipping_cost']")
	WebElement ShippingCharges;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement ProceedToCheckOut;

	@FindBy(xpath = "(//span[contains(text(),'Proceed to checkout')])[2]")
	WebElement ProceedToCheckOut2;

	@FindBy(xpath = "//span[starts-with(@id,'product_price')]")
	WebElement ProductPrice;

	@FindBy(xpath = "(//input[starts-with(@name,'quantity')])[2]")
	WebElement ProductQuantity;

	@FindBy(xpath = "//span[starts-with(@id,'total_product_price')]")
	WebElement TotalCost;

	@FindBy(xpath = "//span[starts-with(@id,'total_product_price')]")
	WebElement ShippingCost;

	@FindBy(xpath = "(//td[starts-with(@id,'total_price')])[2]")
	WebElement FinalPrice;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement TearmsAndCondition;

	@FindBy(xpath = "//a[@title='Pay by bank wire']")
	WebElement PayByBankWire;

	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	WebElement confirmOrder;

	@FindBy(xpath = "//span[@class='price']/strong")
	WebElement OrderAmount;

	public WomentSection_Page() {

		PageFactory.initElements(driver, this);

	}
	
	

	@SuppressWarnings("static-access")
	public void user_navigate_to_Women_Section_and_select_Tshirt() {
		mh.scrollToOneElementHoverandClickonAnother(driver, WomenSection, T_Shirt);
		mh.highlight(WomenSection, driver);
		mh.highlight(T_Shirt, driver);
	}

	public void user_can_able_to_see_All_Tshirt() {

		if (mh.isDisplayed(TshirtDisplay)) {
			System.out.println("TSHIRT Product Displayed");
		}
	}

	@SuppressWarnings("static-access")
	public void user_move_cursor_to_Quick_view_for_one_of_the_Faded_Short_Sleeve_T_shirts() {
		mh.scrollToOneElementHoverandClickonAnother(driver, TshirtImage, QuickView);
		mh.highlight(TshirtImage, driver);
	}

	public void popup_window_with_Tshirt_detail_got_open() {

		driver.switchTo().frame(IframeQuickView);
		System.out.println(ActualProductCost.isDisplayed());
		sc.setContext("ProductCost", ActualProductCost.getText());

		System.out.println("ProductCost:" + sc.getContext("ProductCost"));

	}

	public void user_Choose_two_Quantity_for_same_Product() {
		mh.scrollToElemetAndClick(PlusIcon);
		mh.highlight(PlusIcon, driver);

	}

	public void user_Click_on_Add_to_Cart() {
		mh.isDisplayed(AddToCart_Btn);
		mh.highlight(AddToCart_Btn, driver);
		AddToCart_Btn.click();
	}

	public Set<String> handleWindow() {
		Set<String> handlewindow = driver.getWindowHandles();

		for (String handle : handlewindow) {
			System.out.println("Switching to window - > " + handle);
			driver.switchTo().window(handle);
			lastWindowHandle = handle;
		}
		return handlewindow;
	}

	public String JSgetText(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = ele;
		return (String) jse.executeScript("return arguments[0].text", element);
	}

	public String JSgetHtml(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = ele;
		return (String) jse.executeScript("return arguments[0].innerHTML", element);
	}

	public void user_verify_amount_to_be_paid_by_checking_Unit_price_Qty_Total_Total_Shipping() {
		String parentWindowHandle = driver.getWindowHandle();
		handleWindow();

		if (mh.isDisplayed(ShippingCharges) == true) {
			mh.highlight(ShippingCharges, driver);
			mh.waitForElement(driver, ShippingCharges, 10);
			String ShippingCharges1 = ShippingCharges.getAttribute("innerHTML");
			System.out.println("ShippingCharges1: " + ShippingCharges1);
			sc.setContext("ShippingCharges", ShippingCharges1);
			System.out.println("ShippingCharges: " + sc.getContext("ShippingCharges"));
			mh.scrollToElemetAndClick(ActualProductQty);
			mh.highlight(ActualProductQty, driver);
			String ActualProductQty1 = ActualProductQty.getAttribute("innerHTML");
			System.out.println("ActualProductQty1: " + ActualProductQty1);
			sc.setContext("ProductQty", ActualProductQty1);
			System.out.println("ProductQty:" + sc.getContext("ProductQty"));
			driver.switchTo().window(parentWindowHandle);
		} else {
			test.fail("Varification Failed");
		}

	}

	public void click_on_Proceed_to_Checkout() {
		System.out.println("Visibility :" + mh.isDisplayed(ProceedToCheckOut));
		mh.scrollToElemetAndClick(ProceedToCheckOut);

		/*
		 * if (mh.isDisplayed(ProceedToCheckOut) == true) {
		 * mh.highlight(ProceedToCheckOut, driver); mh.waitForElement(driver,
		 * ProceedToCheckOut, 10); mh.scrollToElemetAndClick(ProceedToCheckOut); } else
		 * { test.fail("Proceed to checkout button Not clicked"); }
		 */ }

	public void click_on_Proceed_to_Checkout1() {

		System.out.println("Visibility :" + mh.isDisplayed(ProceedToCheckOut2));

		mh.scrollToElemetAndClick(ProceedToCheckOut2);

		/*
		 * if (mh.isDisplayed(ProceedToCheckOut2) == true) { mh.waitForElement(driver,
		 * ProceedToCheckOut, 10); mh.highlight(ProceedToCheckOut2, driver);
		 * mh.scrollToElemetAndClick(ProceedToCheckOut2);
		 * 
		 * } else { test.fail("Proceed to checkout button Not clicked"); }
		 */
	}

	public float ConvertStringtoInt(String val) {

		return Float.parseFloat(val);
	}

	public String ReplaceDollar(String object) {

		return object.replace("$", "");
	}

	public void check_shipping_detail_with_amount() {

		String unitPrice = (String) sc.getContext("ProductCost");
		// System.out.println("unitPrice: "+unitPrice);
		String up = ReplaceDollar((String) sc.getContext("ProductCost")).trim();
		// System.out.println("up: "+up);
		float up1 = ConvertStringtoInt(up);
		// System.out.println("up1: "+up1);
		String quantity = (String) sc.getContext("ProductQty");
		System.out.println("quantity: " + quantity);
		String qty = ReplaceDollar((String) sc.getContext("ProductQty")).trim();
		System.out.println("qty: " + qty);
		float qty1 = ConvertStringtoInt(qty);
		System.out.println("qty1: " + qty1);
		String shipping = (String) sc.getContext("ShippingCharges");
		System.out.println("shipping: " + shipping);
		String ship = ReplaceDollar((String) sc.getContext("ShippingCharges")).trim();
		System.out.println("ship: " + ship);
		float ship1 = ConvertStringtoInt(qty);
		System.out.println("ship1: " + ship1);
		float TotalPriceWithoutShipping = up1 * qty1;
		String TPWS = "$" + TotalPriceWithoutShipping;
		System.out.println("TPWS:: " + TPWS);

		float TotalPriceWithShipping = TotalPriceWithoutShipping + ship1;
		String ActualCost = "$" + TotalPriceWithShipping;
		sc.setContext("ActualCost", ActualCost);
		System.out.println("ActualCost With Shipping :: " + ActualCost);
		boolean flag = false;
		if (unitPrice.contentEquals(ProductPrice.getText())) {
			System.out.println("UnitPrice is Mached : " + ProductPrice.getText());
		} else {
			System.out.println("Not Matched");
			flag = true;
		}

		if (quantity.contentEquals(ProductQuantity.getText())) {
			System.out.println("ProductQuantity is Mached : " + ProductQuantity.getText());
		} else {
			System.out.println("ProductQuantity Not Matched : " + ProductQuantity.getText());
			flag = true;
		}

		if (TPWS.contentEquals(TotalCost.getText())) {
			System.out.println("TotalCost is Mached : " + TotalCost.getText());
		} else {
			System.out.println("TotalCost Not Matched : " + TotalCost.getText());
			flag = true;
		}

		if (shipping.contentEquals(ShippingCost.getText())) {
			System.out.println("ShippingCost is Mached : " + ShippingCost.getText());
		} else {
			System.out.println("ShippingCost Not Matched : " + ShippingCost.getText());
			flag = true;
		}

		if (ActualCost.contentEquals(FinalPrice.getText())) {
			System.out.println("FinalPrice is Mached : " + FinalPrice.getText());
		} else {
			System.out.println("FinalPrice Not Matched : " + FinalPrice.getText());
			flag = true;
		}

		mh.highlight(ProceedToCheckOut2, driver);

		ProceedToCheckOut2.click();
		WomentSection_Page.ProductCost = sc.getContext("ActualCost").toString();
		
	}

	public void Check_Address_detail() {
		mh.highlight(ProceedToCheckOut2, driver);
		ProceedToCheckOut2.click();

	}

	public void click_on_Tearms_of_service() {
		mh.highlight(TearmsAndCondition, driver);
		TearmsAndCondition.click();
	}

	public void user_Choose_Payment_Meathod() {
		mh.highlight(PayByBankWire, driver);

	}

	public void click_on_Pay_by_bank_wire() {
		PayByBankWire.click();
	}

	public void user_Confirm_Order_by_clicking_confirm_my_order() {
		mh.highlight(confirmOrder, driver);
	}

	public void user_can_able_to_see_Order_Summary_with_order_amount_Order_Ref_get_genrated() {
		mh.highlight(OrderAmount, driver);
		System.out.println("OrderAmount: " + OrderAmount.getText());
		sc.setContext("OrderAmount", OrderAmount.getText());
	}

}

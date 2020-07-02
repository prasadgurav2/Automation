@addProduct
Feature: Add same product with 2 qunatity in a cart

  Background: 
    Given User Click on Signin
    Then User is on loginPage
    And Enter valid Emailid
    And Enter valid Password
    When User Click on Sign in button
    Then User can able to succesfully signedin
	@Reg
  Scenario: Choose product and do the payment
    Given User navigate to Women Section and select Tshirt
    And User can able to see All Tshirt
    Then User move cursor to Quick view for one of the Faded Short Sleeve T-shirts
    And Popup window with Tshirt detail got open
    Then User Choose two Quantity for same Product
    And User Click on Add to Cart
    Then User verify amount to be paid by checking Unit price ,Qty,Total,Total Shipping
    And click on Proceed to Checkout
    Then check shipping detail with amount
    Then Check Address detail
    And Click on Tearms of service
    And click on Proceed to Checkout
    Then User Choose Payment Meathod
    And Click on Pay by bank wire
    Then User Confirm Order by clicking confirm my order
    
    And User can able to see Order Summary with order amount, Order Ref get genrated

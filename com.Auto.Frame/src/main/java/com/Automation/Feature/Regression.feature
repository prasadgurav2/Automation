@E2E
Feature: End to end TestCase

  Background: 
    Given User Click on Signin
    Then User is on loginPage

  #@CreateUser
  #Scenario: Create New User
    #Given User can able to view 'Create An Account'
    #Then User enter email address
    #And Click on Create an Account
    #Then User should navigated to account-creation Page
    #Then User fill all Personal information details
    #And click on Register button

  @addProductdetail
  Scenario: Add Product till Payment
    And Enter valid Emailid
    And Enter valid Password
    When User Click on Sign in button
    Then User can able to succesfully signedin
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
    And User click on Proceed to Checkout
    Then User Choose Payment Meathod
    And Click on Pay by bank wire
    Then User Confirm Order by clicking confirm my order
    And User can able to see Order Summary with order amount, Order Ref get genrated
    And User click on Profile
    And User click on Order History and Detail
    Given Order history get Open
    Then User can able to verify the amount under Order Summary
    
